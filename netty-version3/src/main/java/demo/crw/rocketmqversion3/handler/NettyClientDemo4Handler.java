package demo.crw.rocketmqversion3.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class NettyClientDemo4Handler extends SimpleChannelHandler {
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("NettyClientDemo4Handler.messageReceived");
        System.out.println(ctx.getChannel().getRemoteAddress() + " e.getMessage() = " + e.getMessage());
    }
}
