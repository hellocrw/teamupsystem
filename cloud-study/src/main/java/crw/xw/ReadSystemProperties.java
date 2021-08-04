package crw.xw;

import java.util.Properties;

import static org.apache.rocketmq.remoting.netty.NettySystemConfig.COM_ROCKETMQ_REMOTING_CLIENT_ASYNC_SEMAPHORE_VALUE;

public class ReadSystemProperties {
  public static void main(String[] args) {
    Properties properties = new Properties();
    System.setProperty(COM_ROCKETMQ_REMOTING_CLIENT_ASYNC_SEMAPHORE_VALUE, "30000");
  }
}
