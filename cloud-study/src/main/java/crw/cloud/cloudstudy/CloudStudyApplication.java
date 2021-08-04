package crw.cloud.cloudstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "crw.cloud.cloudstudy")
public class CloudStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStudyApplication.class, args);
    }

}
