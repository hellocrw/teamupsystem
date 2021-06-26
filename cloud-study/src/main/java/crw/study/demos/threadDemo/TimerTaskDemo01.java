package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * TimerTask的应用:
 *      指定时间执行任务
 */
public class TimerTaskDemo01 {

    private static final Logger logger = LoggerFactory.getLogger(TimerTaskDemo01.class);

    private static final SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse("2021-06-22 05:26:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.info(Thread.currentThread().getName() + " execute");

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                logger.info(Thread.currentThread().getName() + " end");

                countDownLatch.countDown();
            }
        }, calendar.getTime());

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(Thread.currentThread().getName() + " error");
        }

        timer.cancel();

        int purge = timer.purge();
        logger.info(" 取消的任务数量 " + purge);


    }
}
