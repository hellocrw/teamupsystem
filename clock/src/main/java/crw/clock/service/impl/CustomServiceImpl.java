package crw.clock.service.impl;

import crw.clock.entity.Role;
import crw.clock.entity.UserLoginPO;
import crw.clock.mapper.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomServiceImpl implements UserDetailsService {

  private UserLoginMapper userLoginMapper;

  @Autowired
  public CustomServiceImpl(UserLoginMapper userLoginMapper) {
    this.userLoginMapper = userLoginMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    Long userId = Long.parseLong(id);
    UserLoginPO userLoginPO = userLoginMapper.selectById(userId);
    return userLoginPO == null ? null : User.withUsername(userLoginPO.getUserId().toString()).password(userLoginPO.getPassword()).authorities(Role.USER.name()).build();
  }
}
