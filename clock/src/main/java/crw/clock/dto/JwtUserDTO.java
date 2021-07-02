package crw.clock.dto;

import crw.clock.entity.UserLoginPO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description Description
 * @Author crw
 * @Date Created in 2020/1/31 0031
 * @Time 14:44
 */
public class JwtUserDTO extends UserLoginPO implements UserDetails {

  public JwtUserDTO(UserLoginPO userLoginPO) {
    if (userLoginPO != null) {
      this.setUserId(userLoginPO.getUserId());
      this.setPassword(userLoginPO.getPassword());
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    Long userId = this.getUserId();
    if (userId != null) {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
      authorities.add(authority);
    }
    return authorities;
  }

  /**
   * 返回用户账号ID
   * @return
   */
  @Override
  public String getUsername() {
    return String.valueOf(this.getUserId());
  }

  /**
   * 账户是否未过期，过期无法验证
   *
   * @return
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * 指定用户是否解锁，锁定的用户无法进行身份验证
   *
   * @return
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * 指示是否已过期的用户的凭据（密码），过期的凭据防止认证
   *
   * @return
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * 是否可用，禁用的用户不能身份验证
   *
   * @return
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}
