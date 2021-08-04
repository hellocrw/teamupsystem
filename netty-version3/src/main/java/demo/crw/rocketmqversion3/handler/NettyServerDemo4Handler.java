package demo.crw.rocketmqversion3.handler;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class NettyServerDemo4Handler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("e.getMessage() = " + e.getMessage());
        Channel channel = ctx.getChannel();
        channel.write("我收到了");
        super.messageReceived(ctx, e);
    }

}
