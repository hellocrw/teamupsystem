package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

/**
 * TimerTask的应用:
 *      每隔两秒执行一次,执行5次后结束循环调度
 */
public class TimerTaskDemo {

    private static final Logger logger = LoggerFactory.getLogger(TimerTaskDemo.class);

    private static volatile int count = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.info("info " + Thread.currentThread().getName());
                countDownLatch.countDown();
            }
        }, new Date(),2000);

        try {
            countDownLatch.await();
            timer.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("执行main线程的任务");
    }
}
