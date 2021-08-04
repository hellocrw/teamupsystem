package crw.study.demos.demo;

import java.util.ArrayList;

public class Demo01 {
  public static void main(String[] args) {

    String rocketmq_home = System.getProperty("rocketmq.home.dir", System.getenv("ROCKETMQ_HOME"));
    System.out.println("rocketmq_home = " + rocketmq_home);

    String rocket_home = System.getProperty("ROCKET_HOME");
    System.out.println("rocket_home = " + rocket_home);

    ArrayList<Object> list = new ArrayList<>();
    int count = 0;
    try {
      while (true) {//在这无线循环去new对象
        list.add(new Demo01());
        count++;
      }
    } catch (OutOfMemoryError e) {
      System.out.println("count" + count);
      e.printStackTrace();
    }

  }
}