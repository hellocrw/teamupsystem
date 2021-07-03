package crw.study.demos.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * jedis操作zset的应用
 */
public class JedisDemo08 {

  private static final Logger logger = LoggerFactory.getLogger(JedisDemo08.class);

  private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

  private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

  private static void setData() {
    Jedis jedis = jedisPool.getResource();
    Map<String, Double> map = new HashMap<>();
    map.put("demo01", 1.0);
    map.put("demo02", 4.0);
    map.put("demo03", 3.0);
    jedis.zadd("zset_key", map);
  }

  private static void getData() {
    Jedis jedis = jedisPool.getResource();
    Long zset_key = jedis.zcard("zset_key");
    logger.info("zset_key: {}", zset_key);
    Long zrank = jedis.zrank("zset_key", "demo03");
    logger.info("zrank: {}", zrank);

    Set<String> zsetKey = jedis.zrange("zset_key", 0, -1);
    logger.info("zsetKey: {}", zsetKey);

    Set<Tuple> zset_key_values = jedis.zrangeByScoreWithScores("zset_key", 1.0, 4.0);
    logger.info("zset_key_values: {}", zset_key_values);

    Double zscore = jedis.zscore("zset_key", "demo03");
    logger.info("zscore: {}", zscore);

    Set<String> zrevrange = jedis.zrevrange("zset_key", 0, -1);
    logger.info("zrevrang: {}", zrevrange);

    Set<Tuple> zrangeWithScores = jedis.zrangeWithScores("zset_key", 0, 1);
    logger.info("zrangeWithScores: {}", zrangeWithScores);

  }

  public static void main(String[] args) {
    // setData();
    getData();
  }

}
