package crw.project.netty.netty.consumerMessage;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

public class MessageClient {
  public static void main(String[] args) {
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(group)
          .channel(NioSocketChannel.class)
          .handler(new MessageClientInitializer());
      Channel ch = bootstrap.connect("localhost", 8888).sync().channel();

      int version = 1;
      String sessionId = UUID.randomUUID().toString();
      String content = "hello crw !";

      MessageHeader messageHeader = new MessageHeader(version, content.length(), sessionId);
      MessageBody messageBody = new MessageBody(messageHeader, content);

      ch.writeAndFlush(messageBody);

      ch.close();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      group.shutdownGracefully();
    }
  }
}
