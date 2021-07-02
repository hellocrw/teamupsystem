package crw.clock.filter;

import crw.clock.config.JwtConfig;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

  private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

  @Autowired
  private JwtConfig jwtConfig;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String requestURL = request.getRequestURL().toString();
    if (requestURL.contains("login") || requestURL.contains("register") || requestURL.contains("abi")) {
      return true;
    }

    String token = request.getHeader(jwtConfig.getHeaderKey());
    if (StringUtils.isEmpty(token)) {
      logger.error("token不能为空");
      throw new Exception("token不能为空");
    }
    Claims tokenClaim = jwtConfig.getTokenClaim(token);
    if (tokenClaim == null || jwtConfig.isTokenExpired(tokenClaim.getExpiration())) {
      logger.error("token失效，请重新登录");
      throw new Exception("token失效，请重新登录");
    }
    request.setAttribute("identifyId", tokenClaim.getSubject());
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    logger.info("postHandle execute...");
  }
}
