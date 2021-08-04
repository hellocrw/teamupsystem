package crw.project.netty.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("msg = " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 发送数据
        for (int i = 0; i < 10; i++) {
            ctx.channel().write("  hello world ,send message to server , id = " + i);
        }

        ctx.channel().flush();
    }
}
