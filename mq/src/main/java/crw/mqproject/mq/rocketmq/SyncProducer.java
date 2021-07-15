package crw.mqproject.mq.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 同步消息生产者SyncProducer
 */
public class SyncProducer {

  public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
    DefaultMQProducer producer = new DefaultMQProducer("test1");
    producer.setNamesrvAddr("localhost:9876");
    producer.start();
    for (int i = 0; i < 10; i++) {
      Message message = new Message("TopicTest", "TagA", ("hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
      SendResult send = producer.send(message);
      System.out.println("send message: {}"+ send);
    }

    producer.shutdown();
  }

}
