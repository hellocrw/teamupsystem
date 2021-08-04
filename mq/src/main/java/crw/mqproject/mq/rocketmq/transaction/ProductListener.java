package crw.mqproject.mq.rocketmq.transaction;

import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductListener implements MessageListenerConcurrently {
  @Override
  public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
    Optional.ofNullable(list).orElse(Collections.emptyList()).forEach(m -> {
      String orderId = m.getKeys();
      System.out.println("监听到下单消息，orderId: " + orderId + ", 商品服务减库存");
    });
    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
  }
}
