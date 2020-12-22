package com.spring.cloud.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SentinelController {

    @GetMapping("/sentinel/port")
    public String testSentinel(){
        return "7010";
    }

    @GetMapping("/testE")
    public String testE(){
      log.info("testE测试异常数");
      int age = 10/0;
      return "-----testE测试异常数";
    }
}
