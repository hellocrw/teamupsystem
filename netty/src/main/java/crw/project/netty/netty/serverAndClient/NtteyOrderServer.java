package crw.project.netty.netty.serverAndClient;

import crw.project.netty.netty.handler.OrderOneHandler;
import crw.project.netty.netty.handler.OrderTwoHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NtteyOrderServer {
    public static void main(String[] args) {
        EventLoopGroup bossLoop = new NioEventLoopGroup();
        EventLoopGroup workerLoop = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossLoop, workerLoop).channel(NioServerSocketChannel.class)
                    // TODO: 2021/8/4 handler 和 clildHandler的区别
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("order1", new OrderOneHandler());
                            pipeline.addLast("order2", new OrderTwoHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(9999)).sync();
            System.out.println("netty server start");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossLoop.shutdownGracefully();
            workerLoop.shutdownGracefully();
        }
    }
}
