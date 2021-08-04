package crw.mqproject.mq.rocketmq.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class OrderTransactionListener implements TransactionListener {

  private static final Map<String, Boolean> results = new ConcurrentHashMap<>();

  @Override
  public LocalTransactionState executeLocalTransaction(Message message, Object o) {
    String orderId = (String) o;
    // 记录本地事务执行结果
    boolean success = persistTransactionResult(orderId);
    System.out.println("订单服务执行本地事务下单，orderId:" + orderId + ", result: " + success);
    return success ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.ROLLBACK_MESSAGE;
  }

  private boolean persistTransactionResult(String orderId) {
    boolean success = Math.abs(Objects.hash(orderId)) % 2 == 0;
    results.put(orderId, success);
    return success;
  }

  @Override
  public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
    String orderId = messageExt.getKeys();
    System.out.println("执行本地事务消息回查，orderId: " + orderId);
    return Boolean.TRUE.equals(results.get(orderId)) ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.ROLLBACK_MESSAGE;
  }
}
