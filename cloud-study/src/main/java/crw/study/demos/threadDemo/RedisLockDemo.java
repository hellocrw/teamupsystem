package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class RedisLockDemo {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockDemo.class);

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static CountDownLatch countLatch = new CountDownLatch(100);

    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    private static JedisPool jedisPool = new JedisPool(jedisPoolConfig,"8.129.110.202", 6379);

    private static ExecutorService executorService = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {

        long beginTime = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                Jedis jedis = jedisPool.getResource();
                try {
                    reentrantLock.lock();
                    jedis.incr("number");
                    logger.info("number : {} ", jedis.get("number"));
                    countLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    jedis.close();
                    reentrantLock.unlock();
                }
            });
            executorService.execute(thread);
        }
        executorService.shutdown();

        try {
            countLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.info("countLatch error");
        }
        Jedis jedis = jedisPool.getResource();
        logger.info(Thread.currentThread().getName() + " get number value: {}" , jedis.get("number"));
        jedis.close();

        logger.info("execute time : {} " + (System.currentTimeMillis() - beginTime) + "ms");
    }
}
