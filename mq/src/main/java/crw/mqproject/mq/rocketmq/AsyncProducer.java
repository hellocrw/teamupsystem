package crw.mqproject.mq.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * 异步消息生产者
 */
public class AsyncProducer {

  private static final Logger logger = LoggerFactory.getLogger(AsyncProducer.class);

  public static void main(String[] args) throws Exception {

    DefaultMQProducer producer = new DefaultMQProducer("group_name_demo01");

    producer.setNamesrvAddr("localhost:9876");

    producer.start();

    producer.setRetryTimesWhenSendAsyncFailed(0);

    int messageCount = 10;

    final CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);

    for (int i = 0; i < messageCount; i++) {

      final int index = i;

      Message message = new Message("TopicTest"
        , "TagA", "OrderID", "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));

      producer.send(message,new SendCallback(){

        @Override
        public void onSuccess(SendResult sendResult) {
          logger.info("ok : {} , and messageID: {}", index, sendResult.getMsgId());
        }

        @Override
        public void onException(Throwable throwable) {
          logger.error("Exception message: {}, {}", index, throwable);
          throwable.printStackTrace();
        }
      });
    }

    countDownLatch.await();
    // server.shutdown();
  }
}
