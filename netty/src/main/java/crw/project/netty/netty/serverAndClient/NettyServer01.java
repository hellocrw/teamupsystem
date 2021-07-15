package crw.project.netty.netty.serverAndClient;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * Netty Server类
 */
public class NettyServer01 {

  static final boolean SSL = System.getProperty("ssl") != null;

  static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

  public static void main(String[] args) throws CertificateException, SSLException {
    System.out.println("Netty server start");
    // configure SSL
    final SslContext sslContext;

    if (SSL) {
      SelfSignedCertificate ssc = new SelfSignedCertificate();
      sslContext = null;
    } else {
      sslContext = null;
    }

    /**
     * 步骤：
     *  1. 创建一个ServerBootStrap b 实例用来配置启动服务器
     *  2. b.group指定NioEventLoopGroup来接收处理新连接
     *  3. b.channel指定通道类型
     *  4. b.option设置一些参数
     *  5. b.handler设置日志记录
     *  6. b.childHandler指定连接请求，后续调用的channelHandler
     *  7. b.bind设置绑定端口
     *  8. b.sync阻塞直至启动服务
     */

    /**
     * 服务端应用程序使用两个NioEventLoopGroup创建两个EventLoop的组，EventLoop这个相当于一个处理线程，是Netty接收请求和处理IO请求的线程。
     * 主线程组，用于接收客户端的连接，但是不做任何处理
     */
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);

    // 从线程组，当bossGroup接收连接并注册被接收的连接到worker时，处理被接收连接的流量
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
        // .option(ChannelOption.SO_BACKLOG, 100)
      .childHandler(new ChannelInitializer<SocketChannel>() {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
          ChannelPipeline pipeline = socketChannel.pipeline();
          if (sslContext != null) {
            pipeline.addLast(sslContext.newHandler(socketChannel.alloc()));
          }
        }
      });
    } catch (Exception e) {

    }
  }

}
