package crw.study.demos.designPattern.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布订阅模式（观察者模式）
 */
public class MessageObserverDemo {

    private static final Logger logger = LoggerFactory.getLogger(MessageObserverDemo.class);

    public static void main(String[] args) {
        logger.info(Thread.currentThread().getName() + "统一发送消息接口");
        MessagePublisher publisher = new MessagePublisher();
        MessageReceiver studentMessageReceiver = new StudentMessageReceiver();
        MessageReceiver adminMessageReceiver = new AdminMessageReceiver();
        // 消息订阅
        studentMessageReceiver.subscribeMessage(publisher);
        adminMessageReceiver.subscribeMessage(publisher);
        // 消息发送
        publisher.sendMessageToReceiver("通知所有人明天8:00开会");
    }
}

class MessagePublisher {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    List<MessageReceiver> messageReceiverList = new ArrayList<>();

    public void addMessageReceiver(MessageReceiver receiver) {
        logger.info("添加消息接收人 ");
        messageReceiverList.add(receiver);
    }

    public void removeMessageReceiver(MessageReceiver receiver) {
        logger.info("删除消息接收人");
        messageReceiverList.remove(receiver);
    }

    public void sendMessageToReceiver(String messageInfo) {
        for (MessageReceiver messageReceiver : messageReceiverList) {
            messageReceiver.receiveMessage(messageInfo);
        }
    }
}

interface MessageReceiver {
    void receiveMessage(String messageInfo);
    void subscribeMessage(MessagePublisher publisher);
    void unsubscribeMessage(MessagePublisher publisher);
}

class AdminMessageReceiver implements MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(AdminMessageReceiver.class);

    @Override
    public void receiveMessage(String messageInfo) {
        logger.info("正在给Admin发送消息");
        try {
            Thread.sleep(3000);
            logger.info("已经给Admin发送消息完毕,消息内容：" + messageInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribeMessage(MessagePublisher publisher) {
        publisher.addMessageReceiver(this);
    }

    @Override
    public void unsubscribeMessage(MessagePublisher publisher) {
        publisher.removeMessageReceiver(this);
    }
}

class StudentMessageReceiver implements MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(StudentMessageReceiver.class);

    @Override
    public void receiveMessage(String messageInfo) {
        logger.info("正在给student发送消息");
        try {
            Thread.sleep(3000);
            logger.info("已经给student发送消息完毕, 消息内容：" + messageInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribeMessage(MessagePublisher publisher) {
        publisher.addMessageReceiver(this);
    }

    @Override
    public void unsubscribeMessage(MessagePublisher publisher) {
        publisher.removeMessageReceiver(this);
    }
}
