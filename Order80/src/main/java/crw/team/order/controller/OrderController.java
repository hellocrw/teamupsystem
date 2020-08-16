package crw.team.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    public static final String ONLINE_TEAM_URL = "http://localhost:8081";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("order/consumer")
    public String consumer(){
        return restTemplate.getForObject(ONLINE_TEAM_URL + "/team/payment", String.class);
    }
}
