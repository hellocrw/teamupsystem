package crw.clock.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class LoginInfoUtil {

  /**
   * 获取登录的用户ID
   *
   * @return
   */
  public static String getLoginUserId() {
    HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    return request.getHeader("loginUserId");
  }

}
