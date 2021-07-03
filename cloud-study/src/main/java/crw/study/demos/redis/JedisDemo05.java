package crw.study.demos.redis;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 列表list的使用
 */
public class JedisDemo05 {

  private static final Logger logger = LoggerFactory.getLogger(JedisDemo05.class);

  private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

  private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

  public static void main(String[] args) {
    Jedis jedis = jedisPool.getResource();

    jedis.rpoplpush("list_key", "list_key");

    logger.info(JSON.toJSONString(jedis.lrange("list_key", 0 , -1)));
    jedis.close();
  }
}
