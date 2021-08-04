package crw.project.netty.netty;

import crw.project.netty.netty.handler.NettyClientSimpleHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class NettyClient {
  public static void main(String[] args) {
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(workerGroup)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .handler(new ChannelInitializer<Channel>() {
          @Override
          protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast(new IdleStateHandler(5, 10, 15, TimeUnit.SECONDS));
            ch.pipeline().addLast(new NettyClientSimpleHandler());
          }
        });
      ChannelFuture channelFuture = bootstrap.connect("localhost", 9090).sync();
      Channel channel = channelFuture.channel();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      while (true) {
        System.out.println("请输入内容:");
        String msg = bufferedReader.readLine();
        if ("bye".equals(msg)) {
          break;
        }
        ByteBuf buffer = channel.alloc().buffer(msg.length());
        buffer.writeBytes(msg.getBytes());
        channel.writeAndFlush(buffer);
      }
      channelFuture.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      workerGroup.shutdownGracefully();
    }
  }
}
