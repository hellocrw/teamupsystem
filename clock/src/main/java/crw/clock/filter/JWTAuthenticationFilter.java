package crw.clock.filter;

import crw.clock.config.JwtConfig;
import crw.clock.dto.JwtUserDTO;
import crw.clock.entity.UserLoginPO;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

  private AuthenticationManager authenticationManager;

  private JwtConfig jwtConfig;

  @Autowired
  public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
    this.authenticationManager = authenticationManager;
    this.jwtConfig = jwtConfig;
    super.setFilterProcessesUrl("/abi/user/user/login");
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
    // 从输入流中获取登录信息
    try {
      UserLoginPO userLoginPO = new ObjectMapper().readValue(request.getInputStream(), UserLoginPO.class);
      return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginPO.getUserId(), userLoginPO.getPassword()));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    JwtUserDTO jwtUserDTO = (JwtUserDTO) authResult.getPrincipal();
    String token = jwtConfig.getToken(jwtUserDTO.getUsername() + jwtUserDTO.getPassword());
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=utf-8");
    response.setHeader("token", token);
    logger.info("token: {}", token);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    response.getWriter().write("authentication failed, reason: " + failed.getMessage());
  }
}
