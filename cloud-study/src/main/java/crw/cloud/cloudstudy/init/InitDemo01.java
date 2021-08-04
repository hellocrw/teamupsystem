package crw.cloud.cloudstudy.init;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitDemo01 {

  @PostConstruct
  public void init() {
    System.out.println("PostConstruct...");
  }
}
