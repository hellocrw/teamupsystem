package crw.project.netty.netty.serverAndClient;

import crw.project.netty.netty.handler.SimpleNettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 1. 初始化用于Acceptor的主“线程池”以及用于IO工作的从“线程池”
 * 2. 初始化ServerBootstrap实例，此实例时netty服务端应用开发的入口
 * 3. 通过ServerBootstrap的group方法，设置(1)中初始化的主从“线程池”
 * 4. 指定通道channel的类型，由于是服务端，故而是NioServerSocketChannel
 * 5. 设置ServerSocketChannel的处理器
 * 6. 设置子通道也就是SocketChannel的处理器，其内部是实际业务开发的“主战场”
 * 7. 配置子通道也就是SocketChannel的选项
 * 8. 绑定并侦听某个端口
 */
public class NettyServer02 {

  public void bind(int port) throws Exception{

    // 服务端应用程序使用两个NioEventLoopGroup创建两个EventLoop的组，EventLoop这个相当于一个处理线程，是Netty接收请求和处理IO请求的线程
    // 主线程组，用于接收客户端的连接
    EventLoopGroup bossGroup = new NioEventLoopGroup();

    // 从线程组，当bossGroup接收连接并注册被接收的连接到worker时，处理被接收连接的流量
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      // netty服务器启动类的创建，辅助工具类，用于服务器通道的一系列配置
      ServerBootstrap serverBootstrap = new ServerBootstrap();

      /**
       * 使用了多少线程以及如何将他们映射到创建的通道取决于EventLoopGroup实现，甚至可以通过构造函数进行配置
       * 设置循环线程组，前者用于处理客户端的连接事件，后者用于处理网络IO
       * public ServerBootstrap group(EventLoopGroup group)
       * public ServerBootstrap group(EventLoopGroup parentGroup, EventLoopGroup childGroup)
       */
      serverBootstrap.group(bossGroup, workerGroup) // 绑定两个线程组
        // 用于构造socketChannel工厂
        .channel(NioServerSocketChannel.class)  //指定NIO模式, 异步的服务器端TCP Socket连接
        // 子处理器通过下面的内部方法实现
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new SimpleNettyServerHandler());
          }
        });

      // 启动server，绑定端口，开始接收进来的连接，设置8888为启动的端口号，同时启动方式为同步
      ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

      System.out.println("netty server start...");

      // 监听关闭的Channel，等待服务器socket关闭，设置位同步方式
      channelFuture.channel().closeFuture().sync();

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      // 优雅的退出线程组
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) throws Exception {
    int port = 8888;
    new NettyServer02().bind(port);
  }

}
