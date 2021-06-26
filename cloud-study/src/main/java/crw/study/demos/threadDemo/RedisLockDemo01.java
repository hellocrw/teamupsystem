package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisLockDemo01 {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockDemo01.class);

    private static final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    private static final JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        // 给redis中的key加一操作
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                Jedis jedis = jedisPool.getResource();
                jedis.incr("key");
                logger.info(Thread.currentThread().getName() + jedis.get("key"));
                jedis.close();
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}
