package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * fork/join框架的应用:
 *      分成多个子任务执行计算，然后结果集求和
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private static final Logger logger = LoggerFactory.getLogger(ForkJoinDemo.class);

    private static final int THREAD_NUM = 100;

    private static final int THRESHOLD = 1000;

    private long start;

    private long end;

    private ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        long sum = 0;

        if (end - start <= THRESHOLD) {
            for (long i = start; i <= end; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sum = sum + i;
            }
        } else {
            ArrayList<ForkJoinDemo> forkJoinDemoList = new ArrayList<>();
            long step = (end - start) / THREAD_NUM;
            long position = start;

            for (int i = 0; i < THREAD_NUM; i++) {
                long lastPosition = position + step;
                if (lastPosition > end) {
                    lastPosition = end;
                }

                ForkJoinDemo forkJoinDemo = new ForkJoinDemo(position, lastPosition);
                forkJoinDemoList.add(forkJoinDemo);
                forkJoinDemo.fork();

                position = lastPosition + 1;
            }

            for (ForkJoinDemo forkJoinDemo : forkJoinDemoList) {
                sum = sum + forkJoinDemo.join();
            }

        }

        return sum;
    }

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();

        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(1, 2000L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinDemo);
        try {
            logger.info(Thread.currentThread().getName() + " result : " + submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        logger.info("耗时 " + (System.currentTimeMillis() - beginTime) + "ms");
        logger.info("main end ");

        long sum = 0;
        for (long i = 0; i <= 2000L; i++) {
            sum = sum + i;
        }
        logger.info("check data : " + sum);

    }

}
