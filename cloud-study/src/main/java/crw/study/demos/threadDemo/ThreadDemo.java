package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.yield;

/**
 * 通过yield方法控制线程的有序执行
 */
public class ThreadDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadDemo.class);

    private volatile static Integer state = 1;

    public static void main(String[] args) {

        Thread T1 = new Thread(() -> {
            while (state != 1){
                yield();
            }
            LOGGER.info(Thread.currentThread().getName() + "start");
            LOGGER.info(Thread.currentThread().getName());
            LOGGER.info(Thread.currentThread().getName() + "end");
            state = 2;
        }, "Thread01");
        T1.start();

        Thread T2 = new Thread(() -> {
            while (state != 2){
                yield();
            }
            LOGGER.info(Thread.currentThread().getName() + "start");
            LOGGER.info(Thread.currentThread().getName());
            LOGGER.info(Thread.currentThread().getName() + "end");
            state = 3;
        }, "Thread02");
        T2.start();

        Thread T3 = new Thread(() -> {
            while (state != 3){
                yield();
            }
            LOGGER.info(Thread.currentThread().getName() + "start");
            LOGGER.info(Thread.currentThread().getName());
            LOGGER.info(Thread.currentThread().getName() + "end");
        }, "Thread03");
        T3.start();
    }
}
