package crw.project.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettySimpleHandler extends SimpleChannelInboundHandler {
  @Override
  protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf byteBuf = (ByteBuf) msg;
    byte[] bytes = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bytes);
    System.out.println(" message : " + new String(bytes));

    // 将数据写回channel中
    ByteBuf buffer = ctx.alloc().buffer(4 * "OK".length());
    ctx.channel().writeAndFlush(buffer.writeBytes("OK".getBytes()));
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.flush();
  }
}
