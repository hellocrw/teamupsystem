package crw.team.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 子模块: Order服务模块的测试类
 */
@RestController
@Slf4j
public class OrderController {
    public static final String ONLINE_TEAM_URL = "http://team-01/api/test";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/all")
    public String consumer(){
        return restTemplate.getForObject(ONLINE_TEAM_URL + "/", String.class);
    }

    @GetMapping("/demo01")
    public String demo(){
        return "Order 80";
    }
}
