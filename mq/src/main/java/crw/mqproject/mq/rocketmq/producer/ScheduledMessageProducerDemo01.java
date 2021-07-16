package crw.mqproject.mq.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class ScheduledMessageProducerDemo01 {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group_producer_scheduled_demo01");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        int totalMessagesToSend = 10;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("topic_scheduled_demo01", ("hello crw" + i).getBytes());
            // 设置延迟等级3, 10s发送
            message.setDelayTimeLevel(4);
            producer.send(message);
        }
        producer.shutdown();
    }
}
