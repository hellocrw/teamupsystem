package crw.study.demos.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo09 {

  private static final Logger logger = LoggerFactory.getLogger(JedisDemo09.class);

  private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

  private static JedisPool jedisPool = new JedisPool(jedisPoolConfig, "8.129.110.202", 6379);

  private static void publishMessage() {
    Jedis jedis = jedisPool.getResource();
    jedis.publish("dev_publish", "publish message to subscribe!!!");
  }

  private static void subscribeMessage() {
    Jedis jedis = jedisPool.getResource();
  }

  public static void main(String[] args) {
    publishMessage();
    // subscribeMessage();
  }
}
