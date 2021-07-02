package crw.clock.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class JwtConfig {

  private static final Logger logger = LoggerFactory.getLogger(JwtConfig.class);

  /**
   * 过期时间，单位为秒
   */
  @Value("${config.jwt.expire}")
  private long expire;

  /**
   * 存放token请求头对应的key的名字
   */
  @Value("${config.jwt.headerKey}")
  private String headerKey;

  /**
   * 加密的secret
   */
  @Value("${config.jwt.secret}")
  private String secret;

  /**
   * 根据身份ID标识，生成Token
   * @param identityId
   * @return
   */
  public String getToken(String identityId){
    Date nowDate = new Date();
    // 设置过期时间
    Date expireDate = new Date(nowDate.getTime() + expire * 1000);
    return Jwts.builder()
      .setHeaderParam("typ","JWT")  // 设置头部信息
      .setSubject(identityId)
      .setIssuedAt(nowDate)
      .setExpiration(expireDate)
      .signWith(SignatureAlgorithm.HS512, secret)
      .compact();
  }

  /**
   * 获取Token中注册信息
   * @param token
   * @return
   */
  public Claims getTokenClaim(String token){
    try{
      return Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();
    }catch (Exception e){
      e.printStackTrace();
      logger.error("获取Token失败: {}", token);
      return null;
    }
  }

  /**
   * Token是否过期验证
   * @param expirationTime
   * @return
   */
  public boolean isTokenExpired (Date expirationTime){
    return expirationTime.before(new Date());
  }

  /**
   * 获取用户名
   * @param token
   * @return
   */
  public String getUsername(String token){
    Claims claims = getTokenClaim(token);
    return claims.get("username").toString();
  }

  /**
   * 获取用户角色
   * @param token
   * @return
   */
  public String getUserRole(String token){
    Claims claims = getTokenClaim(token);
    return claims.get("auth").toString();
  }

}
