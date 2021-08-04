package crw.study.demos.demo;

import java.util.Properties;

public class ReaderSystemProperties {
  public static void main(String[] args) {

    String java_version = System.getProperty("java.version");
    System.out.println("java_version = " + java_version);

    String java_home = System.getProperty("java.home");
    System.out.println("java_home = " + java_home);

    /*Properties properties = System.getProperties();

    properties.stringPropertyNames().forEach(key -> System.out.println(key + " = " + properties.getProperty(key)));*/

    System.setProperty("version.info", "1.0v");
    String version_info = System.getProperty("version.info");
    System.out.println("version_info = " + version_info);

  }
}
