package crw.project.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

public class SimpleNettyClientHandler extends ChannelInboundHandlerAdapter {

  /**
   * 该方法用于接收服务端发送过来的数据
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println(" simpleNettyClientHandler channelRead...");

    ByteBuf result = (ByteBuf) msg;
    byte[] resultMessage = new byte[result.readableBytes()];
    result.readBytes(resultMessage);
    System.out.println("netty server said: " + new String(resultMessage));
    result.release();
  }

  /**
   * 该方法用于处理异常
   * @param ctx
   * @param cause
   * @throws Exception
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }

  /**
   * 该方法用于向服务器发送消息
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    String msg = "hello Server";
    ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
    encoded.writeBytes(msg.getBytes());
    ctx.write(encoded);
    ctx.flush();
  }
}
