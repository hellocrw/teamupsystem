package crw.project.netty.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SecondHandler extends SimpleChannelInboundHandler<String> {
  @Override
  protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
    System.out.println("second handler get message : ");
  }
}
