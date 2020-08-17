package crw.online.team;

import crw.online.team.myrule.MySelfRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@RibbonClient(name ="ONLINE-TEAM", configuration = MySelfRule.class)
@MapperScan("crw.online.team.mapper")
@EnableEurekaClient
public class OnlineTeam8081 {
    public static void main(String[] args) {
        SpringApplication.run(OnlineTeam8081.class, args);
    }
}
