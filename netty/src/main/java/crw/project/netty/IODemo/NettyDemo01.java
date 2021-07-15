package crw.project.netty.IODemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyDemo01 {

  private static Logger logger = LoggerFactory.getLogger(NettyDemo01.class);

  private int port;

  public NettyDemo01(int port) {
    this.port = port;
    bind();
  }

  private void bind() {
    EventLoopGroup bossLoopGroup = new NioEventLoopGroup();
    EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap.group(bossLoopGroup, workerLoopGroup).channel(NioServerSocketChannel.class)
        .option(ChannelOption.SO_BACKLOG, 1024)
        .option(ChannelOption.TCP_NODELAY, true)
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            ChannelPipeline pipeline = socketChannel.pipeline();
            pipeline.addLast(new NettyServerHandler());
          }
        });
      ChannelFuture future = bootstrap.bind(port).sync();
      if (future.isSuccess()) {
        logger.debug("启动Netty服务成功，端口号: {}", this.port);
      }

      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      bossLoopGroup.shutdownGracefully();
      workerLoopGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    NettyDemo01 nettyDemo01 = new NettyDemo01(9999);
  }
}
