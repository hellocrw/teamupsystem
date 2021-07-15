package crw.project.netty.netty.serverAndClient;

import crw.project.netty.netty.handler.FirstHandler;
import crw.project.netty.netty.handler.SecondHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class NettyServerDemo02 {

  private final int port;

  public NettyServerDemo02(int port) {
    this.port = port;
  }

  public void start() {
    // bossLoopGroup && workLoopGroup
    EventLoopGroup bossLoopGroup = new NioEventLoopGroup();
    EventLoopGroup workLoopGroup = new NioEventLoopGroup();
    // ServerBootstrap
    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      System.out.println("server start...");
      serverBootstrap.group(bossLoopGroup, workLoopGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            ChannelPipeline pipeline = socketChannel.pipeline();
            pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
            pipeline.addLast("decoder", new StringDecoder());
            pipeline.addLast("encoder", new StringEncoder());
            pipeline.addLast(new FirstHandler());
            pipeline.addLast(new SecondHandler());
          }
        });
      ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(port)).sync();
      channelFuture.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      bossLoopGroup.shutdownGracefully();
      workLoopGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    new NettyServerDemo02(8080).start();
  }

}
