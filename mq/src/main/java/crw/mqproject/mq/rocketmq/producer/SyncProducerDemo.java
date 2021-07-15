package crw.mqproject.mq.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class SyncProducerDemo {

  public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
    DefaultMQProducer producer = new DefaultMQProducer("test");
    producer.setNamesrvAddr("localhost:9876");
    producer.start();
    for (int i = 0; i < 5; i++) {
      Message message = new Message("TopicTest", "TagB", ("hello world" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
      SendResult sendResult = producer.send(message);
      System.out.println("result: " + sendResult);
    }

    producer.shutdown();
  }

}
