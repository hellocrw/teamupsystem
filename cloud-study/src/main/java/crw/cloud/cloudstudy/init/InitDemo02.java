package crw.cloud.cloudstudy.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDemo02 implements CommandLineRunner {
  @Override
  public void run(String... args) throws Exception {
    System.out.println("CommandLineRunner...");
  }
}
