package crw.xw;

public class TestDemo {
  public static void main(String[] args) {
    System.out.println(demo());
  }

  public static String demo(){
    try{
      return "OK";
    }finally {
      System.out.println("TestDemo.demo");
    }
  }
}
