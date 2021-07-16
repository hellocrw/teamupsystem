package crw.mqproject.mq.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

public class AsyncProducerDemo02 {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group_producer_demo02");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 10;
        final CountDownLatch2 countDownLatch2 = new CountDownLatch2(messageCount);

        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            Message message = new Message("Topic_Demo02", "Tag_Demo02", "Demo02", ("hello world" + index).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("sendResult: " + sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        }

        countDownLatch2.await(10, TimeUnit.SECONDS);
        producer.shutdown();
    }
}
