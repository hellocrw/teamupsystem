package crw.study.demos.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * jedis sort应用
 */
public class JedisDemo10 {

  private static final Logger logger = LoggerFactory.getLogger(JedisDemo10.class);

  private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

  private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

  private static void setData() {
    Jedis jedis = jedisPool.getResource();
    jedis.rpush("list_key", "2", "3", "16", "12", "13", "21", "12", "19");
  }

  private static void getData() {
    Jedis jedis = jedisPool.getResource();
    List<String> listKey = jedis.sort("list_key");
    logger.info("listKey: {}", listKey);

  }

  public static void main(String[] args) {
    // setData();
    getData();
  }
}
