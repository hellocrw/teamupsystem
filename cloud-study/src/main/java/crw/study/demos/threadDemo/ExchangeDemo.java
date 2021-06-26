package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger的应用:
 *      线程之间的数据交换
 */
public class ExchangeDemo {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeDemo.class);

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static final Exchanger<String> exchanger = new Exchanger();

    public static void main(String[] args) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String data = "thread01 data";
                logger.info(Thread.currentThread().getName() + "正在把数据" + data + "换出去");

                try {
                    String exchangeData = exchanger.exchange(data);

                    logger.info(Thread.currentThread().getName() + " 换回来的数据 " + exchangeData);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String data = "Thread02 data";
                logger.info(Thread.currentThread().getName() + "正在把数据 " + data + "换出去");

                try {

                    Thread.sleep(5000);

                    String exchangeData = exchanger.exchange(data);

                    logger.info(Thread.currentThread().getName() + " 换回来的数据 " + exchangeData);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        logger.info("main");

        executorService.shutdown();

    }
}
