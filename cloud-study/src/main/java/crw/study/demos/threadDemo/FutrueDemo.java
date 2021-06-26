package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Future的单独使用
 */
public class FutrueDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(FutrueDemo.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask futureTask = new FutureTask<String>(()-> {
           LOGGER.info("futureTask 异步执行");
           Thread.sleep(5000);
           LOGGER.info("过了很久");
           return "异步执行完毕";
        });

        LOGGER.info("启动异步");
        new Thread(futureTask).start();

        LOGGER.info("主线程执行其他任务");

        Thread.sleep(1000);
        LOGGER.info("获取异步执行的结果： " + futureTask.get());

        LOGGER.info("执行完毕");
    }
}
