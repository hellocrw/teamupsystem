package crw.xw;

import java.io.IOException;
import java.util.Properties;

public class StudyDemo {
  public static void main(String[] args) throws IOException {
    Properties properties = new Properties();
    properties.load(StudyDemo.class.getResourceAsStream("/system.properties"));
    System.out.println(properties.getProperty("info.version"));

  }
}
