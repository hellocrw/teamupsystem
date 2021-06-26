package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class FutrueDemo2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(FutrueDemo2.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        try {
            FutureTask future = new FutureTask( () -> {
                LOGGER.info("开始执行异步操作");
                Thread.sleep(5000);
                return"异步执行完毕";
            });

            LOGGER.info("main线程执行自己的操作");
            executorService.submit(future);

            LOGGER.info("异步执行的返回结果：" + future.get());

            LOGGER.info("main执行完毕");

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
