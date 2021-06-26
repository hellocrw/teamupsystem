package crw.study.demos.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * redis过期时间设置
 */
public class JedisDemo03 {

    private static final Logger logger = LoggerFactory.getLogger(JedisDemo03.class);

    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        try {
            for (int i = 0; i < 100; i++) {
                Thread thread = new Thread(() -> {
                    Jedis jedis = jedisPool.getResource();
                    jedis.incr("collection");
                    jedis.close();
                });
                executorService.execute(thread);
                Jedis jedis = jedisPool.getResource();
                jedis.expire("collection",10);
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
            logger.info("execute time : " + (System.currentTimeMillis() - beginTime) + "ms");
        }
    }
}
