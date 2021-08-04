package crw.project.netty.netty.consumerMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageHandler extends SimpleChannelInboundHandler<MessageBody> {
  @Override
  protected void messageReceived(ChannelHandlerContext ctx, MessageBody msg) throws Exception {
    System.out.println("message content: " + msg.toString());
  }
}
