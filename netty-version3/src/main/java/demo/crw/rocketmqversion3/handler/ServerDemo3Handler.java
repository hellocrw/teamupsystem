package demo.crw.rocketmqversion3.handler;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ServerDemo3Handler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("ServerDemo3Handler.messageReceived");
        System.out.println(e.getMessage().toString());
    }

    @Override
    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        System.out.println("ServerDemo3Handler.handleUpstream");
        super.handleUpstream(ctx, e);
    }
}
