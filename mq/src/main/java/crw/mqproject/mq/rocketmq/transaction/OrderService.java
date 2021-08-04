package crw.mqproject.mq.rocketmq.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OrderService {
  public static void main(String[] args) throws MQClientException, UnsupportedEncodingException {
    TransactionMQProducer producer = new TransactionMQProducer();
    producer.setNamesrvAddr("localhost:9876");
    producer.setProducerGroup("group_producer_order");

    // 自定义线程池，执行事务操作
    ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20), (Runnable r) -> new Thread("Order Transaction Message Thread !"));
    producer.setExecutorService(executor);

    // 设置监听器
    producer.setTransactionListener(new OrderTransactionListener());

    producer.start();

    System.out.println("OrderService start ...");

    for (int i = 0; i < 10; i++) {
      String orderId = UUID.randomUUID().toString();
      String payload = "下单，orderId: " + orderId;
      String tags = "tag";
      Message message = new Message("topic_order", tags, orderId, payload.getBytes(RemotingHelper.DEFAULT_CHARSET));

      // 发送事务消息
      TransactionSendResult result = producer.sendMessageInTransaction(message, orderId);

      System.out.println("发送事务消息，发送结果： " + result);
    }

  }
}
