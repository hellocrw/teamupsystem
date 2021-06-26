package crw.study.demos.designPattern.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息工厂
 */
public class MessageFactoryDemo {

    private static final Logger logger = LoggerFactory.getLogger(MessageFactoryDemo.class);

    private static List<String> sendModels = new ArrayList<>();

    public static void main(String[] args) {

        logger.info(Thread.currentThread().getName() + " 统一生产消息接口");

        sendModels.add("system");
        sendModels.add("custom");

        for (String messageModel : sendModels) {
            MessageFactory messageFactory = getFactory(messageModel);
            if (messageFactory != null) {
                messageFactory.newMessage().sendMessage();
            }
        }
    }

    private static MessageFactory getFactory(String sendModel) {

        logger.info(" 正在获取对应的消息工厂 ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ("system".equals(sendModel)) {
            return new SystemMessageFactory();
        }
        if ("custom".equals(sendModel)) {
            return new CustomMessageFactory();
        }
        return null;
    }
}



interface Message {
    void sendMessage();
}

class SystemMessage implements Message {

    private static final Logger logger = LoggerFactory.getLogger(SystemMessage.class);

    @Override
    public void sendMessage() {
        try {
            logger.info(Thread.currentThread().getName() + " 正在发送系统消息");
            Thread.sleep(3000);
            logger.info(Thread.currentThread().getName() + " 发送系统消息完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CustomMessage implements Message {

    private static final Logger logger = LoggerFactory.getLogger(CustomMessage.class);

    @Override
    public void sendMessage() {
        try {
            logger.info(Thread.currentThread().getName() + " 正在发送站外自定义消息");
            Thread.sleep(3000);
            logger.info(Thread.currentThread().getName() + " 发送站外自定义消息完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

interface MessageFactory {
    Message newMessage();
}

class SystemMessageFactory implements MessageFactory {

    private static final Logger logger = LoggerFactory.getLogger(SystemMessageFactory.class);

    @Override
    public Message newMessage() {
        logger.info("get system_message");
        return new SystemMessage();
    }
}

class CustomMessageFactory implements MessageFactory {

    private static final Logger logger = LoggerFactory.getLogger(SystemMessageFactory.class);

    @Override
    public Message newMessage() {
        logger.info("get custom_message");
        return new CustomMessage();
    }
}