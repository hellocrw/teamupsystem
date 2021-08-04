package crw.project.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandlerDemo extends ChannelInboundHandlerAdapter {

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    System.out.println("");
    super.userEventTriggered(ctx, evt);
  }

  /**
   * 消息处理
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("===> NettyServerHandlerDemo");
    // 读取客户端的数据
    ByteBuf byteBuf = (ByteBuf) msg;
    byte[] bytes = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bytes);
    System.out.println("client say: " + new String(bytes));

    ctx.channel().writeAndFlush(ctx.channel().alloc().buffer().writeBytes(" HELLO".getBytes()));
    // 通知执行下一个Handler
    ctx.fireChannelRead(msg);    // ChannelHandler之间传递
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.flush();
    System.out.println("channelReadComplete执行完毕");
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelActive执行完毕");
  }
}
