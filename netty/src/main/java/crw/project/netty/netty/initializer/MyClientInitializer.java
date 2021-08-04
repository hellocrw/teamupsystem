package crw.project.netty.netty.initializer;

import crw.project.netty.netty.handler.MyClientHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // TODO 加入粘包拆包编码解码器
        ChannelPipeline pipeline = ch.pipeline();
        // pipeline.addLast("Frame", new FixedLengthFrameDecoder(4));
        pipeline.addLast("Decoder", new StringDecoder());
        pipeline.addLast("Encoder", new StringEncoder());
        pipeline.addLast("myHandler", new MyClientHandler());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("MyClientInitializer.exceptionCaught 异常处理");
        System.out.println("与服务器server断开连接");
    }
}
