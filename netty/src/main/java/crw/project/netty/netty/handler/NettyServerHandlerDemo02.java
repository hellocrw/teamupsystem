package crw.project.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandlerDemo02 extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("=====> NettyServerHandlerDemo02");
    ByteBuf buf = (ByteBuf) msg;
    byte[] bytes = new byte[buf.readableBytes()];
    buf.readBytes(bytes);
    System.out.println("客户端的数据: " + new String(bytes));

    ByteBuf byteBuf = Unpooled.copiedBuffer("收到1".getBytes());

    ctx.write(byteBuf);  // ctx.write()将传递到ChannelOutboundHandler
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.flush();
    System.out.println("channelReadComplete2执行完毕");
  }

}
