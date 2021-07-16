package crw.mqproject.mq.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

public class BatchProducerDemo01 {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("group_producer_batch_demo01");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        String topic = "BatchTopic";
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(topic, "TagA", "orderid-001", "hello crw 0".getBytes()));
        messageList.add(new Message(topic, "TagB", "orderid-002", "hello crw 1".getBytes()));
        messageList.add(new Message(topic, "TagC", "orderid-003", "hello crw 3".getBytes()));
        try {
            producer.send(messageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
