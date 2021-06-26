package crw.study.demos.designPattern.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例模式：饿汉模式创建实例对象
 */
public class SingletonDemo01 {

    private static final Logger logger = LoggerFactory.getLogger(SingletonDemo01.class);

    private static volatile SingletonDemo01 singleton = null;

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private SingletonDemo01() { }

    public static synchronized SingletonDemo01 getSingletonDemo01() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (singleton == null) {
            singleton = new SingletonDemo01();
        }
        return singleton;
    }

    public void demoMethod() {
        logger.info(Thread.currentThread().getName() + "单例创建实例对象" + this.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        SingletonDemo01 singletonDemo01 = SingletonDemo01.getSingletonDemo01();
        singletonDemo01.demoMethod();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                SingletonDemo01 singleton = SingletonDemo01.getSingletonDemo01();
                logger.info("singleton : " + singleton);
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}
