package crw.study.demos.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo02 {

    private static final Logger logger = LoggerFactory.getLogger(JedisDemo02.class);

    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

    public static void main(String[] args) {

        Jedis jedis = jedisPool.getResource();

        jedis.lpush("friends", "小明", "小红");

        String friend = jedis.lpop("friends");

        logger.info(friend);

        jedisPool.destroy();

    }
}
