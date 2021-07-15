package crw.project.netty.IODemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class NettyServerHandler extends ChannelHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf byteBuf = (ByteBuf) msg;
    String message = getMessage(byteBuf);
    System.out.println("服务器收到的消息： "+ message);

    try {
      ctx.writeAndFlush(getSendByteBuf("APPLE"));
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  private ByteBuf getSendByteBuf(String message) {
    byte[] req = message.getBytes();
    ByteBuf pingMessage = Unpooled.buffer();
    pingMessage.writeBytes(req);

    return pingMessage;
  }

  private String getMessage(ByteBuf byteBuf) {
    byte[] bytes = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bytes);
    try {
      return new String(bytes);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
