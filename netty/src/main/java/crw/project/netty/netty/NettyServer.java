package crw.project.netty.netty;

import crw.project.netty.netty.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
  public static void main(String[] args) {

    EventLoopGroup bossLoopGroup = new NioEventLoopGroup();
    EventLoopGroup workLoopGroup = new NioEventLoopGroup();

    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();

      serverBootstrap.group(bossLoopGroup, workLoopGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<Channel>() {
          @Override
          protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast(new HookHandler());
            ch.pipeline().addLast(new NettyServerHandlerDemo());
            ch.pipeline().addLast(new NettyServerHandlerDemo02());
//            ch.pipeline().addLast(new NettyServerOutHandler02());
//            ch.pipeline().addLast(new NettyServerOutHandler());
          }
        });

      ChannelFuture channelFuture = serverBootstrap.bind(9090).sync();
      System.out.println("开始监听端口：" + channelFuture.channel().localAddress());
      channelFuture.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
        bossLoopGroup.shutdownGracefully();
        workLoopGroup.shutdownGracefully();
    }
  }
}
