package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Java信号量机制,通过信号量机制控制线程顺序执行
 */
public class SemaphoreDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(SemaphoreDemo.class);

    // 通过信号量控制线程的执行顺序
    private static final Semaphore semaphore = new Semaphore(0);
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            executorService.execute(new R1());
            executorService.execute(new R2());
            executorService.execute(new R3());
        }
        executorService.shutdown();
    }

    public static class R1 implements Runnable {

        @Override
        public void run() {
            try {
                semaphore.release();
                semaphore.acquire();
                LOGGER.info(Thread.currentThread().getName() + "-------->" + this.getClass().getName());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class R2 implements Runnable {

        @Override
        public void run() {
            try {
                semaphore.acquire();
                LOGGER.info(Thread.currentThread().getName()  + "-------->" + this.getClass().getName());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class R3 implements Runnable {

        @Override
        public void run() {
            try {
                semaphore.acquire();
                LOGGER.info("R3" + Thread.currentThread().getName() + "-------->" + this.getClass().getName());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
