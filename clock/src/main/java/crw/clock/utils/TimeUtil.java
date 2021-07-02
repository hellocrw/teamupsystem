package crw.clock.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

  private static final Logger logger = LoggerFactory.getLogger(TimeUtil.class);

  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  private static final SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * 将时间格式转为字符串格式
   * @param date
   * @return
   */
  public static String parseDateTime(Date date) {
    String dateTime = simpleDateFormat.format(date);
    return dateTime;
  }

  /**
   * 获取上周此刻的时间点
   * @return
   */
  public static Date getLastWeekDateTime() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_WEEK, -7);
    return calendar.getTime();
  }

  /**
   * 获取当前时间点
   * @return
   */
  public static Date getCurrentDateTime() {
    Calendar calendar = Calendar.getInstance();
    return calendar.getTime();
  }

  /**
   * 上午是否缺勤
   * @return
   */
  public static boolean startAttendanceDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR, 8);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    return new Date().before(calendar.getTime());
  }

  /**
   * 晚上是否缺勤
   * @return
   */
  public static boolean endAttendanceDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR, 23);
    calendar.set(Calendar.MINUTE, 30);
    calendar.set(Calendar.SECOND, 0);
    return new Date().before(calendar.getTime());
  }

  /**
   * 早上打卡时间
   * @return
   */
  public static boolean morningClock() {
    Calendar startTime = Calendar.getInstance();
    startTime.set(Calendar.HOUR, 6);
    startTime.set(Calendar.MINUTE, 0);
    startTime.set(Calendar.SECOND, 0);

    Calendar endTime = Calendar.getInstance();
    endTime.set(Calendar.HOUR, 9);
    endTime.set(Calendar.MINUTE, 30);
    endTime.set(Calendar.SECOND, 0);

    return new Date().before(endTime.getTime()) && new Date().after(startTime.getTime());
  }

  public static boolean nightClock() {
    Calendar startTime = Calendar.getInstance();
    startTime.set(Calendar.HOUR, 20);
    startTime.set(Calendar.MINUTE, 0);
    startTime.set(Calendar.SECOND, 0);

    Calendar endTime = Calendar.getInstance();
    endTime.set(Calendar.HOUR, 23);
    endTime.set(Calendar.MINUTE, 30);
    endTime.set(Calendar.SECOND, 0);

    return new Date().before(endTime.getTime()) && new Date().after(startTime.getTime());
  }

  public static String parseCurrentDate() {
    return simpleDate.format(Calendar.getInstance().getTime());
  }

  public static void main(String[] args) {
    logger.info("result : {}", endAttendanceDate());
  }
}
