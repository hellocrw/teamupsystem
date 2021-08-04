package demo.crw.rocketmqversion3.server;

import demo.crw.rocketmqversion3.handler.NettyServerDemo4Handler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NettyServerDemo4 {
    public static void main(String[] args) {
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        ServerBootstrap serverBootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(boss, worker));

        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decode", new StringDecoder());
                pipeline.addLast("encode", new StringEncoder());
                pipeline.addLast("handler", new NettyServerDemo4Handler());
                return pipeline;
            }
        });

        ChannelFuture future = serverBootstrap.bindAsync(new InetSocketAddress(9999));

        future.awaitUninterruptibly();

        System.out.println("netty server start");
    }
}
