package crw.study.demos.redis;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Set;

/**
 * 集合set的使用
 */
public class JedisDemo06 {

  private static final Logger logger = LoggerFactory.getLogger(JedisDemo06.class);

  private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

  private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

  public static void main(String[] args) {
    Jedis jedis = jedisPool.getResource();

    logger.info("set_key: {}", jedis.smembers("set_key"));
    logger.info("set_key1: {}", jedis.smembers("set_key1"));
    logger.info("set_result: {}" , jedis.sunion("set_key", "set_key1"));
  }

}
