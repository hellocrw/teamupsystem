package crw.project.netty.netty.consumerMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 解码器
 */
@ChannelHandler.Sharable
public class MessageEncoder extends MessageToByteEncoder<MessageBody> {
  @Override
  protected void encode(ChannelHandlerContext ctx, MessageBody msg, ByteBuf out) throws Exception {

    MessageHeader messageHeader = msg.getMessageHeader();

    out.writeInt(messageHeader.getVersion());
    out.writeInt(msg.getContent().length());
    out.writeBytes(messageHeader.getSessionId().getBytes());

    out.writeBytes(msg.getContent().getBytes());
  }
}
