package crw.team.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @LoadBalanced // 负载均衡
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
