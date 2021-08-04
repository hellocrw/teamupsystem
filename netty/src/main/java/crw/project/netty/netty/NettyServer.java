package crw.project.netty.netty;

import crw.project.netty.netty.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

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
            // TODO 加入心跳机制IdleStateHandler,如何确保心跳机制连接断开，以及断线重连?
            ch.pipeline().addLast(new IdleStateHandler(5, 5, 5, TimeUnit.SECONDS));
            ch.pipeline().addLast("simpleHandler", new SimpleHandler());
            ch.pipeline().addLast(new HookHandler());
            ch.pipeline().addLast(new NettyServerHandlerDemo());
            ch.pipeline().addLast(new NettyServerHandlerDemo02());
            ch.pipeline().addLast(new NettyServerOutHandler02());
            ch.pipeline().addLast(new NettyServerOutHandler());
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
