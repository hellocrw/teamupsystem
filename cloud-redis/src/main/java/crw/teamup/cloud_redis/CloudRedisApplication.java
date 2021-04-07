package crw.teamup.cloud_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudRedisApplication.class, args);
    }

}
