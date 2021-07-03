package crw.study.demos.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis的hash使用
 */
public class JedisDemo07 {

  private static final Logger logger = LoggerFactory.getLogger(JedisDemo07.class);

  private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

  private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

  private static void setData() {
    Jedis jedis = jedisPool.getResource();
    jedis.hset("hash_key", "name", "李四");
    Map<String, String> map = new HashMap<>();
    map.put("age", "14");
    map.put("height","175");
    jedis.hmset("hash_key", map);
  }

  private static void getData() {
    Jedis jedis = jedisPool.getResource();
    Long hlen = jedis.hlen("hash_key");
    logger.info("hlen: {}", hlen);
    Map<String, String> hash_key = jedis.hgetAll("hash_key");
    logger.info("hash_key_value: {}", hash_key);

    Boolean hexists = jedis.hexists("hash_key", "name");
    logger.info("hexists: {}", hexists);

    Set<String> hash_keys = jedis.hkeys("hash_key");
    logger.info("hash_keys: {}", hash_keys);

    List<String> hash_values = jedis.hvals("hash_key");
    logger.info("hash_values: {}", hash_values);

    jedis.hincrBy("hash_key", "age", 1);
    logger.info("hash_key: {}", jedis.hgetAll("hash_key"));

  }

  public static void main(String[] args) {
    // setData();
    getData();
  }

}
