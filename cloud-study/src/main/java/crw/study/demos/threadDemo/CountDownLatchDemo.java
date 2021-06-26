package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch的应用
 */
public class CountDownLatchDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountDownLatchDemo.class);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized(this) {
                        LOGGER.info(Thread.currentThread().getName() + "运行");

                        try {
                            Thread.sleep(1000);
                            latch.countDown();
                            LOGGER.info(Thread.currentThread().getName() + "子线程继续执行后面的内容");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }).start();
        }

        LOGGER.info("等待子线程运行结束");
        // 设置线程等待以及响应的超时时间
        latch.await(20, TimeUnit.SECONDS);
        LOGGER.info("子线程运行结束");
    }

}
