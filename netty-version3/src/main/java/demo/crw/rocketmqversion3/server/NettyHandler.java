package demo.crw.rocketmqversion3.server;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class NettyHandler extends SimpleChannelHandler {

  @Override
  public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
    String message = (String) e.getMessage();
    System.out.println("message:" + message);
    Channel channel = ctx.getChannel();
    channel.write("OK");
  }
}
