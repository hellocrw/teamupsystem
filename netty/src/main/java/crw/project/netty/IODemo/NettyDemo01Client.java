package crw.project.netty.IODemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyDemo01Client {

  private int port;

  private String host;

  public NettyDemo01Client(int port, String host) {
    this.port = port;
    this.host = host;
    start();
  }

  private void start() {
    EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)
        .group(eventLoopGroup)
        .remoteAddress(host, port)
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new NettyClientHandler());
          }
        });

      ChannelFuture future = bootstrap.connect(host, port).sync();
      if(future.isSuccess()){
        SocketChannel socketChannel = (SocketChannel) future.channel();
        System.out.println("----connect server success----");
      }
      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      eventLoopGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    new NettyDemo01Client(9999, "localhost");
  }
}
