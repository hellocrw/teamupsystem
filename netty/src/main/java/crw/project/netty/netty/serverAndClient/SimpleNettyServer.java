package crw.project.netty.netty.serverAndClient;

import crw.project.netty.netty.handler.SimpleNettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SimpleNettyServer {

  public void bind(int port) {
    EventLoopGroup bossLoopGroup = new NioEventLoopGroup();
    EventLoopGroup workLoopGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap.group(bossLoopGroup,workLoopGroup);
      bootstrap.channel(NioServerSocketChannel.class);
      bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
          socketChannel.pipeline().addLast(new SimpleNettyServerHandler());
        }
      });
      ChannelFuture channelFuture = bootstrap.bind(port).sync();
      channelFuture.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      bossLoopGroup.shutdownGracefully();
      workLoopGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    new SimpleNettyServer().bind(9999);
  }

}
