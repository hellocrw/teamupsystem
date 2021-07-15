package crw.study.demos.timeUtil;

import java.util.Calendar;

/**
 * 时间工具类TimeUtils
 */
public class TimeUtils {

  public static void testDemo() {
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.MINUTE, -1);
      System.out.println(calendar.getTime());
  }

  public static void main(String[] args) {
    testDemo();
  }

}
