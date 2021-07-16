package crw.mqproject.mq.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class OnewayProducerDemo01 {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group_oneway_producr_demo01");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            Message message = new Message("Topic_Oneway_demo01", "Tag_Oneway_demo01", "one way producer message".getBytes());
            producer.sendOneway(message);
        }
        producer.shutdown();
    }
}
