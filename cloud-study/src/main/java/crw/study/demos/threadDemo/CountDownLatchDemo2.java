package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch的应用
 */
public class CountDownLatchDemo2 implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountDownLatchDemo2.class);

    private CountDownLatch countDownLatch;

    private CountDownLatch await;

    public CountDownLatchDemo2(CountDownLatch countDownLatch, CountDownLatch await){
        this.countDownLatch = countDownLatch;
        this.await = await;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            LOGGER.info("子线程" + Thread.currentThread().getName() + "处理自己的事情");
            Thread.sleep(1000);
            await.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch await = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(new CountDownLatchDemo2(countDownLatch, await)).start();
        }

        LOGGER.info("主线程处理自己的事情");

        Thread.sleep(3000);
        countDownLatch.countDown();

        LOGGER.info("主线程处理完毕");

        await.await();
        LOGGER.info("子线程处理完毕");

    }
}
