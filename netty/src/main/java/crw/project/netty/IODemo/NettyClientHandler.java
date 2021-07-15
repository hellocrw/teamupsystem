package crw.project.netty.IODemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NettyClientHandler extends ChannelHandlerAdapter {

  private ByteBuf firstMessage;

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      System.out.println("请输入内容：");
      String readLine = bufferedReader.readLine();
      // byte[] data = "服务器，给我一个APPLE".getBytes();
      firstMessage = Unpooled.buffer();
      firstMessage.writeBytes(readLine.getBytes());
      ctx.writeAndFlush(firstMessage);
    }

  }

  /**
   * 获取服务器的数据
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("========");
    ByteBuf buf = (ByteBuf) msg;
    String message = getMessage(buf);
    System.out.println("客户端收到服务器的数据：" + message);

  }

  private String getMessage(ByteBuf buf) {
    byte[] bytes = new byte[buf.readableBytes()];
    buf.readBytes(bytes);
    try {
      return new String(bytes);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
