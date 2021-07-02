package crw.clock.filter;

import crw.clock.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  @Autowired
  private JwtConfig jwtConfig;

  @Autowired
  public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    logger.info(new Date(System.currentTimeMillis()) + ": doFilterInternal...");
    String tokenHeader = request.getHeader(jwtConfig.getHeaderKey());
    if (tokenHeader == null || !tokenHeader.startsWith(jwtConfig.getHeaderKey())) {
      chain.doFilter(request, response);
      return;
    }
    SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
  }

  private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
    String token = tokenHeader.replace(jwtConfig.getHeaderKey(), "");
    String userId = jwtConfig.getUsername(token);
    String userRole = jwtConfig.getUserRole(token);
    if (userId != null) {
      return new UsernamePasswordAuthenticationToken(userId, null, Collections.singleton(new SimpleGrantedAuthority(userRole)));
    }
    return null;
  }
}
