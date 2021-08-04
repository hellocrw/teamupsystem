package crw.cloud.cloudstudy.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitDemo03 implements InitializingBean {
  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("InitializingBean...");
  }
}
