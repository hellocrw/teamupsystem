package crw.teamup.cloud_redis.controller.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisTestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisTestController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    private Integer num = 0;

    @GetMapping("/demo")
    public String demo() throws InterruptedException {
        System.out.println("线程ID：" + Thread.currentThread().getId());
        long startTime = System.currentTimeMillis();
        Thread.sleep(2000);
        num ++;
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        LOGGER.info("执行时间: {}", endTime - startTime + ", num: " + num);
        return String.valueOf("执行时间: " + (endTime - startTime) + ",  num: " + num);
    }
}
