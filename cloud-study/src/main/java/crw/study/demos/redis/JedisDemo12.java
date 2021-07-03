package crw.study.demos.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

/**
 * redis事务
 */
public class JedisDemo12 {

  private static final Logger logger = LoggerFactory.getLogger(JedisDemo12.class);

  private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

  private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

  public static void main(String[] args) {

    Jedis jedis = jedisPool.getResource();

    Transaction transaction = jedis.multi();

    transaction.set("userInfo:01", "name:zhangsan");

    transaction.get("userInfo:01");

    transaction.exec();

    jedis.close();
  }
}
