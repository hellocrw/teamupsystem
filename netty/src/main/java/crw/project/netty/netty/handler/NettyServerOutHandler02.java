package crw.project.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class NettyServerOutHandler02 extends ChannelOutboundHandlerAdapter {
  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    System.out.println("===> NettyServerOutHandler02");
    ByteBuf byteBuf = Unpooled.copiedBuffer("收到2".getBytes());
    ctx.writeAndFlush(byteBuf);
  }
}
