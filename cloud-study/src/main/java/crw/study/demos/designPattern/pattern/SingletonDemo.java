package crw.study.demos.designPattern.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单例模式：懒汉模式创建实例对象
 */
public class SingletonDemo {

    private static final Logger logger = LoggerFactory.getLogger(SingletonDemo.class);

    private static final SingletonDemo singleton = new SingletonDemo();

    private SingletonDemo() { }

    public static SingletonDemo getSingletonDemo() {
        if (singleton != null) {
            return singleton;
        }
        return new SingletonDemo();
    }

    public void demoMethod() {
        logger.info(Thread.currentThread().getName() + " 通过单例创建并调用" + this.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        SingletonDemo singletonDemo = SingletonDemo.getSingletonDemo();
        singletonDemo.demoMethod();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo singleton = SingletonDemo.getSingletonDemo();
                logger.info("singleton: " + singleton);
            }).start();
        }
    }

}
