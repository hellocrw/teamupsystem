package crw.project.netty.netty.serverAndClient;

import crw.project.netty.netty.initializer.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NettyClientDemo4 {
    public static void main(String[] args) {
        // 1. create workerLoop
        EventLoopGroup workerLoop = new NioEventLoopGroup();
        try {
            // 2. create clientBootStrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerLoop).channel(NioSocketChannel.class)
                    // 3. add handler
                    .handler(new MyClientInitializer());
            // 4. bind address and port
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 8888)).sync();
            // close channel 阻塞等待数据，直到channel关闭（客户端关闭）
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerLoop.shutdownGracefully();
        }
    }
}
