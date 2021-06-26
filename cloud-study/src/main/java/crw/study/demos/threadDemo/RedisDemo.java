package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过redis实现线程的顺序执行
 */
public class RedisDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDemo.class);

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static Jedis jedis = new Jedis("8.129.110.202", 6379);

    public static void main(String[] args) {
        jedis.set("stateA", "1");
        jedis.set("stateB", "0");
        jedis.set("stateC", "0");
        for (int i = 0; i < 10; i++) {
            executorService.execute(new R1());
            executorService.execute(new R2());
            executorService.execute(new R3());
        }
        executorService.shutdown();
    }

    public static class R1 implements Runnable {

        @Override
        public void run() {
            if ("1".equals(jedis.get("stateA"))){
                LOGGER.info(Thread.currentThread().getName() + this.getClass().getSimpleName());
                jedis.decr("stateA");
                jedis.incr("stateB");
            }

        }
    }

    public static class R2 implements Runnable {

        @Override
        public void run() {
            if ("1".equals(jedis.get("stateB"))){
                LOGGER.info(Thread.currentThread().getName() + this.getClass().getSimpleName());
                jedis.decr("stateB");
                jedis.incr("stateC");

            }
        }
    }

    public static class R3 implements Runnable {

        @Override
        public void run() {
            if ("1".equals(jedis.get("stateC"))){
                LOGGER.info(Thread.currentThread().getName() + this.getClass().getSimpleName());
                jedis.decr("stateC");
                jedis.incr("stateA");
            }
        }
    }
}
