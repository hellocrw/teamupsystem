package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock读写锁的应用:
 *      读读不互斥，读写互斥，写写互斥
 */
public class ReadWriteLockDemo01 {

    private static final Logger logger = LoggerFactory.getLogger(ReadWriteLockDemo01.class);

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static final ExecutorService executorService = Executors.newFixedThreadPool(20);

    private static int age = 0;

    public static void main(String[] args) {
        ReadWriteLockDemo01 readWriteLockDemo01 = new ReadWriteLockDemo01();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.submit( () -> {
              readWriteLockDemo01.put(finalI);
              logger.info(Thread.currentThread().getName() + "写入的数据为：" + age);
            });

            executorService.submit( () -> {
                int result = readWriteLockDemo01.get();
                logger.info(Thread.currentThread().getName() + "读取到的数据为：" + result);
            });
        }

        executorService.shutdown();
    }

    public void put(int age) {
        readWriteLock.writeLock().lock();
        logger.info(Thread.currentThread().getName() + " 准备写数据");
        try {
            Thread.sleep(5000);
            this.age = age;

            logger.info(Thread.currentThread().getName() + " 写入数据的值： " + age);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int get() {
        readWriteLock.readLock().lock();
        logger.info(Thread.currentThread().getName() + " 准备读取数据");
        try {
            Thread.sleep(1000);
            logger.info(Thread.currentThread().getName() + "读取到的数据为 ： " + age);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return age;
    }

}
