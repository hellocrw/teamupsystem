package crw.project.netty.netty.initializer;

import crw.project.netty.netty.handler.NettyServerDemo4Handler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // TODO 这里需要加入粘包拆包编码解码器
        pipeline.addLast("frame", new FixedLengthFrameDecoder(23));
        pipeline.addLast("Decoder", new StringDecoder());
        pipeline.addLast("Encoder", new StringEncoder());
        pipeline.addLast("myHandler", new NettyServerDemo4Handler());
    }
}
