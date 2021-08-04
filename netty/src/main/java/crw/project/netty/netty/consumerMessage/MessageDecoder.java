package crw.project.netty.netty.consumerMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 编码器
 */
public class MessageDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    int version = in.readInt();

    int contentLength = in.readInt();

    byte[] sessionByte = new byte[36];

    in.readBytes(sessionByte);

    String sessionId = new String(sessionByte);

    MessageHeader messageHeader = new MessageHeader(version, contentLength, sessionId);

    byte[] content = in.readBytes(in.readableBytes()).array();

    MessageBody messageBody = new MessageBody(messageHeader, new String(content));

    out.add(messageBody);

  }
}
