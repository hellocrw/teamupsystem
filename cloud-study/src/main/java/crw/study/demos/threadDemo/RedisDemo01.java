package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Jedis的使用
 */
public class RedisDemo01 {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDemo01.class);

    private static final Jedis jedis = new Jedis("8.129.110.202", 6379);

    public static void main(String[] args) {
        oparetionJedisMethod();
    }

    public static void oparetionJedisMethod() {

        Set<String> list_demo = jedis.keys("list_demo");
        LOGGER.info("list_demo: " + list_demo);

        LOGGER.info(jedis.lpop("list_demo"));
        LOGGER.info("execute successful");
    }
}
