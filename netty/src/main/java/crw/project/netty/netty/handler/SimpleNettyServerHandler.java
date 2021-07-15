package crw.project.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class SimpleNettyServerHandler extends ChannelInboundHandlerAdapter {

  /**
   * 该方法用于读取客户端发送的信息
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println(" simpleNettyServerHandler channelRead...");

    Thread.sleep(5000);

    ByteBuf result = (ByteBuf) msg;
    byte[] bytesMsg = new byte[result.readableBytes()];
    // msg中存储的是byteBuf类型的数据，把数据读取到byte[]中
    result.readBytes(bytesMsg);
    String resultStr = new String(bytesMsg);
    // 接收并打印客户端的信息
    System.out.println("Client said : " + resultStr);
    // 释放资源
    result.release();

    // 向客户端发送消息
    String response = "我已经接收到";
    // 在当前场景下，发送的数据必须转换位ByteBuf数组
    ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
    encoded.writeBytes(response.getBytes());
    ctx.write(encoded);
    ctx.flush();
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
   * 信息获取完毕操作
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.flush();
  }
}
