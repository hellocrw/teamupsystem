package crw.project.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HookHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("ChannelhandlerContext的Channel被注册到EventLoop中!!!");
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("ChannelHandlerContext的channel从eventLoop中注销!!!");
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelHandlerContext的channel被激活!!!");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelHandlerContext的channel结束生命周期!!!");
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println(" 从当前的Channel中读取消息");
    ByteBuf byteBuf = (ByteBuf) msg;
    byte[] bytes = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bytes);
    System.out.println(new String(bytes));

    String result = "OK";
    ByteBuf buffer = ctx.channel().alloc().buffer();
    buffer.writeBytes(result.getBytes());
    ctx.channel().write(buffer);

    // 传递给下一个Handler执行
    ctx.fireChannelRead(msg);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.channel().flush();
    System.out.println(" 消息读取完毕有执行!!!");
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    System.out.println(" 一个用户事件触发!!!");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    System.out.println("重写父类ChannelHandler的方法，处理异常!!!");
  }

}
