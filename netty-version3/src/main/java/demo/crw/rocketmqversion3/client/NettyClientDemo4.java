package demo.crw.rocketmqversion3.client;

import demo.crw.rocketmqversion3.handler.NettyClientDemo4Handler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class NettyClientDemo4 {
    public static void main(String[] args) throws IOException {

        System.out.println("netty client start");

        // 1. 设置bootstrap -> boss and worker
        ClientBootstrap clientBootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newSingleThreadExecutor()
        ));

        // 2. 设置pipelineFactory -> handler
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                // pipeline.addLast("frame", new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
                pipeline.addLast("encode", new StringEncoder());
                pipeline.addLast("decode", new StringDecoder());
                pipeline.addLast("handler", new NettyClientDemo4Handler());
                return pipeline;
            }
        });

        // 3. connect netty server
        ChannelFuture future = clientBootstrap.connect(new InetSocketAddress("localhost", 9999));

        // 4. get channel
        Channel channel = future.awaitUninterruptibly().getChannel();

        // 5. write message to channel
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String readLine = bufferedReader.readLine();
            channel.write(readLine);
        }


    }
}
