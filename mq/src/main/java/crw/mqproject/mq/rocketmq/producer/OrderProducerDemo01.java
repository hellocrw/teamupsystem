package crw.mqproject.mq.rocketmq.producer;

import crw.mqproject.mq.rocketmq.po.OrderPO;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderProducerDemo01 {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("group_produer_order_demo01");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD"};
        List<OrderPO> orderPOList = new ArrayList<>();
        orderPOList.add(OrderPO.builder().orderId(1).desc("1").build());
        orderPOList.add(OrderPO.builder().orderId(2).desc("2").build());
        orderPOList.add(OrderPO.builder().orderId(3).desc("3").build());
        orderPOList.add(OrderPO.builder().orderId(4).desc("4").build());
        orderPOList.add(OrderPO.builder().orderId(5).desc("5").build());
        orderPOList.add(OrderPO.builder().orderId(6).desc("6").build());
        orderPOList.add(OrderPO.builder().orderId(7).desc("7").build());
        orderPOList.add(OrderPO.builder().orderId(8).desc("8").build());
        orderPOList.add(OrderPO.builder().orderId(9).desc("9").build());
        orderPOList.add(OrderPO.builder().orderId(10).desc("10").build());
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        for (int i = 0; i < 10; i++) {
            String body = dateStr + " hello RocketMQ " + orderPOList.get(i);
            Message message = new Message("topic_order_demo01", tags[i % tags.length], "key" + i, body.getBytes());
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object args) {
                    System.out.println(list.size() + "list=> " + list);
                    Integer id = (Integer) args;
                    Integer index = id % list.size();
                    System.out.println("index:=> " + index);
                    return list.get(index);
                }
            }, orderPOList.get(i).getOrderId());
            System.out.println(sendResult.getSendStatus() + "-->" + sendResult.getMessageQueue().getQueueId() +" : "+ body);
        }
        producer.shutdown();
    }
}
