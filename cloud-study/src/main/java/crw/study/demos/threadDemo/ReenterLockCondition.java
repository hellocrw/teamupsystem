package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(ReenterLockCondition.class);

    public static ReentrantLock lock = new ReentrantLock();

    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            LOGGER.info("start " + Thread.currentThread().getName());
            condition.await();
            LOGGER.info(Thread.currentThread().getName() + " Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition reenterLockCondition = new ReenterLockCondition();
        new Thread(reenterLockCondition, "Thread01").start();

        Thread.sleep(2000);

        lock.lock();

        LOGGER.info("main Thread " + Thread.currentThread().getName());

        condition.signal();

        lock.unlock();
    }
}
