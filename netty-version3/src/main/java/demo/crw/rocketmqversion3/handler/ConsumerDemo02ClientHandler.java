package demo.crw.rocketmqversion3.handler;

import org.jboss.netty.channel.*;

import java.nio.ByteBuffer;

public class ConsumerDemo02ClientHandler extends SimpleChannelHandler {

  @Override
  public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    System.out.println("ConsumerDemo02ClientHandler.channelConnected");
  }

  @Override
  public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
    System.out.println("ConsumerDemo02ClientHandler.handleUpstream");
  }

  @Override
  public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
    System.out.println("ConsumerDemo02ClientHandler.messageReceived");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
    System.out.println("ConsumerDemo02ClientHandler.exceptionCaught");
  }
}
