package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReentrantLockDemo.class);

    private ReentrantLock lock = new ReentrantLock();

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    private static int state = 0;

    @Override
    public void run() {
        lock.lock();

        LOGGER.info(Thread.currentThread().getName() + " execute");

        for (int i = 0; i < 100000; i++) {
            state++;
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countDownLatch.countDown();
        lock.unlock();

    }

    public static void main(String[] args) throws InterruptedException {

        long beginTime = System.currentTimeMillis();

        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();

        new Thread(reentrantLockDemo, "Thread01").start();
        new Thread(reentrantLockDemo, "Thread02").start();

        // Thread.sleep(5000);

        countDownLatch.await();

        LOGGER.info(Thread.currentThread().getName() + " : state = " + state);

        LOGGER.info("执行耗时" + (System.currentTimeMillis() - beginTime) + "ms");
    }
}
