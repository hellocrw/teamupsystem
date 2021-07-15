package crw.project.netty.netty.serverAndClient;

import crw.project.netty.netty.handler.SimpleNettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.net.InetSocketAddress;

public class NettyServer03 {

  public void startNetty(int port) {
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workGroup = new NioEventLoopGroup();

    EventExecutorGroup eventExecutorGroup = new DefaultEventExecutorGroup(10);

    ServerBootstrap serverBootstrap = new ServerBootstrap();

    serverBootstrap.group(bossGroup, workGroup);
    serverBootstrap.channel(NioServerSocketChannel.class);
    serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(eventExecutorGroup, new SimpleNettyServerHandler());
      }
    });
    serverBootstrap.childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60);
    serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
    try {
      ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(port)).sync();
      channelFuture.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      eventExecutorGroup.shutdownGracefully();
      workGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    NettyServer03 nettyServer03 = new NettyServer03();
    nettyServer03.startNetty(8000);
    System.out.println("netty server start ...");
  }

}
