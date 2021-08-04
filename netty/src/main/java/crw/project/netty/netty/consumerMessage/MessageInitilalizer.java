package crw.project.netty.netty.consumerMessage;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MessageInitilalizer extends ChannelInitializer<SocketChannel> {
  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();

    pipeline.addLast(new MessageDecoder());
    pipeline.addLast(new MessageEncoder());
    pipeline.addLast(new MessageHandler());

  }
}
