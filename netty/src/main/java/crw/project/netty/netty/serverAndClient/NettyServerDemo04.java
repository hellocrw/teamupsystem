package crw.project.netty.netty.serverAndClient;

import crw.project.netty.netty.initializer.MyChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NettyServerDemo04 {
    public static void main(String[] args) {
        // 1. create bossLoop and workerLoop
        EventLoopGroup bossLoop = new NioEventLoopGroup();
        EventLoopGroup workerLoop = new NioEventLoopGroup();
        try {
            // 2. create server bootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 3. assign channel -> NioServerSocketChannel
            bootstrap.group(bossLoop, workerLoop).channel(NioServerSocketChannel.class)
                    // 4. childHandler
                    .childHandler(new MyChannelInitializer());
            // 5. bind port
            ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(8888)).sync();

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
