package crw.project.netty.netty.serverAndClient;

import crw.project.netty.netty.handler.SimpleNettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netty Client
 */
public class NettyClient02 {

  public void connect(String host, int port) {
    EventLoopGroup worker = new NioEventLoopGroup();
    try {
      // 客户端启动类程序
      Bootstrap bootstrap = new Bootstrap();
      // EventLoop的组
      bootstrap.group(worker);
      // 用于构造socketChannel工厂
      bootstrap.channel(NioSocketChannel.class);
      /**
       * 设置选项：
       *  参数： Socket的标准参数(key, value)
       */
      bootstrap.option(ChannelOption.SO_KEEPALIVE, true);

      // 自定义客户端Handler
      bootstrap.handler(new ChannelInitializer<SocketChannel>() {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
          socketChannel.pipeline().addLast(new SimpleNettyClientHandler());
        }
      });

      // 开启客户端监听，连接到远程节点，阻塞等待知道连接完成
      ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

      // 阻塞等待数据，直到channel关闭（客户端关闭）
      channelFuture.channel().closeFuture().sync();

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      // 优雅的关闭
      worker.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    NettyClient02 nettyClient02 = new NettyClient02();
    nettyClient02.connect("localhost", 8888);
  }

}
