package crw.mqproject.mq.rocketmq.transaction;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

public class ProductService {
  public static void main(String[] args) throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
    consumer.setNamesrvAddr("localhost:9876");
    consumer.setConsumerGroup("group_consumer_product");
    consumer.subscribe("topic_order", "*");
    consumer.registerMessageListener(new ProductListener());
    consumer.start();
    System.out.println("product service start...");
  }
}
