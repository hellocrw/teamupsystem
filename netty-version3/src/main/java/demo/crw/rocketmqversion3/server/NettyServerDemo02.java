package demo.crw.rocketmqversion3.server;

import demo.crw.rocketmqversion3.handler.ConsumerDemo02ServerHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NettyServerDemo02 {
  public static void main(String[] args) {
    ExecutorService boos = Executors.newCachedThreadPool();
    ExecutorService worker = Executors.newCachedThreadPool();

    ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(boos, worker));

    bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
      @Override
      public ChannelPipeline getPipeline() throws Exception {
        return Channels.pipeline(new ConsumerDemo02ServerHandler());
      }
    });

    bootstrap.bind(new InetSocketAddress(9999));

    System.out.println("netty server start");

  }
}
