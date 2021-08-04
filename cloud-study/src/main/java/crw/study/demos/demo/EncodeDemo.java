package crw.study.demos.demo;

import java.nio.charset.Charset;

public class EncodeDemo {
  public static void main(String[] args) {
    System.out.println(Charset.defaultCharset());
    System.out.println(System.getProperty("file-encoding"));
  }
}
