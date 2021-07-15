package crw.project.netty.netty.serverAndClient;

import crw.project.netty.netty.initializer.SimpleChatServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class SimpleChatServerDemo {

  private int port;

  public SimpleChatServerDemo(int port) {
    this.port = port;
  }

  public void run() {
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(new SimpleChatServerInitializer())
        .option(ChannelOption.SO_BACKLOG, 128)
        .childOption(ChannelOption.SO_KEEPALIVE, true);

      System.out.println("simple chat server start...");

      ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(port)).sync();

      channelFuture.channel().closeFuture().sync();

    } catch (InterruptedException e) {
      e.printStackTrace();
    }finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();

      System.out.println(" simple chat server shutdown...");
    }
  }

  public static void main(String[] args) {
    int port;
    if (args.length > 0) {
      port = Integer.parseInt(args[0]);
    } else {
      port = 8080;
    }
    new SimpleChatServerDemo(port).run();
  }


}
