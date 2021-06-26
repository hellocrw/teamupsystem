package crw.study.demos.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 对redis的String自增操作
 * 通过JedisPool创建Jedis线程池
 */
public class JedisDemo01 {

    private static final Logger logger = LoggerFactory.getLogger(JedisDemo01.class);

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

    public static void main(String[] args) {

        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(() -> {
                Jedis jedis = jedisPool.getResource();
                jedis.incr("collection");
                jedis.close();
            });
            executorService.execute(thread);
        }

        executorService.shutdown();

        logger.info(Thread.currentThread().getName() + "执行完毕");

    }
}
