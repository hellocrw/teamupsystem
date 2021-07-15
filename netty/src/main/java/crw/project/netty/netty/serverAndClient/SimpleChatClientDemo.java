package crw.project.netty.netty.serverAndClient;

import crw.project.netty.netty.initializer.SimpleChatClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleChatClientDemo {

  private final String host;

  private final int port;

  public SimpleChatClientDemo(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void run() {
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap()
        .group(group)
        .channel(NioSocketChannel.class)
        .handler(new SimpleChatClientInitializer());
      Channel channel = bootstrap.connect(host, port).sync().channel();

      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      while (true) {
        System.out.println("请输入发送内容:");
        channel.writeAndFlush(in.readLine() + "\r\n");
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      group.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    new SimpleChatClientDemo("localhost", 9999).run();
  }
}
