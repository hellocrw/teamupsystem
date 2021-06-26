package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ExecutorServiceDemo {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorServiceDemo.class);

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future<String> future = executorService.submit(() -> {
            return Thread.currentThread().getName();
        });

        executorService.shutdown();

        logger.info(future.get());

    }

}
