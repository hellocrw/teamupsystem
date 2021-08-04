package demo.crw.rocketmqversion3.client;

import demo.crw.rocketmqversion3.handler.ConsumerDemo02ClientHandler;
import demo.crw.rocketmqversion3.handler.ConsumerDemo02ServerHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class NettyClientDemo02 {
  public static void main(String[] args) {
    ClientBootstrap clientBootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
        Executors.newCachedThreadPool(),
        Executors.newSingleThreadExecutor())
    );
    clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
      @Override
      public ChannelPipeline getPipeline() throws Exception {
        return Channels.pipeline(new ConsumerDemo02ClientHandler());
      }
    });

    ChannelFuture future = clientBootstrap.connect(new InetSocketAddress("localhost", 9999));

    future.getChannel().getCloseFuture().awaitUninterruptibly();

    // 关闭
    clientBootstrap.releaseExternalResources();

  }
}
