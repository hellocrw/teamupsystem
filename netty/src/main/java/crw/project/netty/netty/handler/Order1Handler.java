package crw.project.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class Order1Handler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Order1Handler.messageReceived");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Order1Handler.channelActive");
        ByteBuf byteBuf = Unpooled.copiedBuffer("msg".getBytes());
        // 写数据
        ctx.channel().writeAndFlush(byteBuf);
    }
}
