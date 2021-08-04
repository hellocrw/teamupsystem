package crw.clock.controller;

import crw.clock.utils.LoginInfoUtil;
import crw.clock.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("abi/user/test")
@Api(tags = {"测试接口"})
public class TestController {

  @Autowired
  private JedisPool jedisPool;

  @GetMapping("demo")
  public ResponseEntity<ResultVo> demo() {
    return new ResponseEntity<>(new ResultVo(200, "success", "demo"), HttpStatus.OK);
  }

  @GetMapping("jedisDemo")
  public ResponseEntity<ResultVo> jedisDemo() {
    Jedis jedis = jedisPool.getResource();
    String loginUserId = LoginInfoUtil.getLoginUserId();
    Map<String, String> valueMap = new HashMap<>();
    valueMap.put("name", "张三");
    jedis.hset(loginUserId, valueMap);
    if (jedis.hget(loginUserId, "name") != null) {
      Map<String, String> jedisResultMap = jedis.hgetAll(loginUserId);
      return new ResponseEntity<>(new ResultVo(200, "jedisDemo", jedisResultMap), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ResultVo(200, "jedisDemo"), HttpStatus.OK);
  }

}
