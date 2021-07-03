package crw.study.demos.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo11 {

  private static final Logger logger = LoggerFactory.getLogger(JedisDemo11.class);

  private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

  private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

  private static void setData() {
    Jedis jedis = jedisPool.getResource();
    jedis.set("string_key", "demo01");
    jedis.expire("string_key", 30);
  }

  private static void getData() {
    Jedis jedis = jedisPool.getResource();
    Long key = jedis.ttl("string_key");
    logger.info("key_time : {}", key);
  }

  public static void main(String[] args) {
    // setData();
    getData();
  }
}
