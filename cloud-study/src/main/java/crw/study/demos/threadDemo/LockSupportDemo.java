package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;


public class LockSupportDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(LockSupportDemo.class);

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            LockSupport.park();
            for (int i = 0; i < 10; i++) {
                LOGGER.info(Thread.currentThread().getName());
            }
        }, "ThreadA");
        threadA.start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                LOGGER.info(Thread.currentThread().getName());
            }
            LockSupport.unpark(threadA);
        }, "ThreadB").start();
    }
}
