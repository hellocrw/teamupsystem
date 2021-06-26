package crw.study.demos.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用redis实现线程同步
 */
public class JedisDemo04 {

    private static final Logger logger = LoggerFactory.getLogger(JedisDemo04.class);

    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

    private static final ExecutorService executorService = Executors.newFixedThreadPool(100);

    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        logger.info(Thread.currentThread().getName() + " start");
        Jedis resource = jedisPool.getResource();
        logger.info("setnx : " + resource.setnx("key", Thread.currentThread().getName()));
        resource.del("key");
        AtomicInteger number = new AtomicInteger(Integer.parseInt(resource.get("number")));
        resource.close();
        for (int i = 0; i < 100; i++) {
            Thread T1 = new Thread(() -> {
                Jedis jedis = jedisPool.getResource();
                try {
                    while (jedis.setnx("key", Thread.currentThread().getName()) == 0) {

                    }
                    logger.info(" execute " + Thread.currentThread().getName());
                    jedis.setnx("key", Thread.currentThread().getName());
                    jedis.expire("key", 10);
                    number.getAndIncrement();
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    jedis.del("key");
                    jedis.close();
                }

            });
            executorService.execute(T1);
        }

        try {
            countDownLatch.await();
            Jedis jedis = jedisPool.getResource();
            jedis.set("number", String.valueOf(number.get()));
            jedis.close();
            logger.info("number: {}", String.valueOf(number.get()));
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("execute time: " + String.valueOf(System.currentTimeMillis() - beginTime));
    }
}
