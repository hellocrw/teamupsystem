package crw.study.demos.threadDemo;

import com.google.common.util.concurrent.MoreExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MoreExecutorDemo {

    private static final Logger logger = LoggerFactory.getLogger(MoreExecutorDemo.class);

    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        ExecutorService exitingExecutorService = MoreExecutors.getExitingExecutorService(threadPool);

        exitingExecutorService.execute( () -> {
            logger.info(Thread.currentThread().getName() + " execute");
        });

        executorService.execute( () -> {
            logger.info(Thread.currentThread().getName() + " execute");
        });
        executorService.shutdown();
    }
}
