package crw.project.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientSimpleHandler extends SimpleChannelInboundHandler {

  /**
   * 读取数据
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("client channelRead");
    ByteBuf byteBuf = (ByteBuf) msg;
    byte[] bytes = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bytes);
    System.out.println("server said: " + new String(bytes));
  }

  @Override
  protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("client messageReceived");
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelHandlerContext的channel被激活!!!");
  }

}
