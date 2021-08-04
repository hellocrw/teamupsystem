package demo.crw.rocketmqversion3.demo;

import java.util.ArrayList;
import java.util.List;

public class TestDemo {
  public static void main(String[] args) {
    List<List> list = new ArrayList<>();
    while (true) {
      list.add(list);
    }
  }
}
