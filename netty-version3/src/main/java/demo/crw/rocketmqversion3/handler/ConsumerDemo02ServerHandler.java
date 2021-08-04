package demo.crw.rocketmqversion3.handler;

import org.jboss.netty.channel.*;

public class ConsumerDemo02ServerHandler extends SimpleChannelUpstreamHandler {

  @Override
  public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
    System.out.println("ConsumerDemo02ServerHandler.messageReceived");
  }

  @Override
  public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
    System.out.println("ConsumerDemo02ServerHandler.handleUpstream");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
    System.out.println("ConsumerDemo02ServerHandler.exceptionCaught");
  }
}
