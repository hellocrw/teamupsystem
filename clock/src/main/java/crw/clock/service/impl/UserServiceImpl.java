package crw.clock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import crw.clock.config.JwtConfig;
import crw.clock.dto.UserDTO;
import crw.clock.entity.UserInfoPO;
import crw.clock.entity.UserLoginPO;
import crw.clock.mapper.UserLoginMapper;
import crw.clock.mapper.UserMapper;
import crw.clock.service.UserService;
import crw.clock.utils.CommonEntityUtil;
import crw.clock.utils.LoginInfoUtil;
import crw.clock.vo.LoginInfoVo;
import crw.clock.vo.UserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfoPO> implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserLoginMapper userLoginMapper;

  @Autowired
  private JwtConfig jwtConfig;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public UserInfoVo queryUserInfo(String userId) {
    UserInfoPO userInfoPO = userMapper.selectOne(new QueryWrapper<UserInfoPO>().eq("user_id", userId));
    UserInfoVo userInfoVo = new UserInfoVo();
    BeanUtils.copyProperties(userInfoPO, userInfoVo);
    return userInfoVo;
  }

  @Override
  public LoginInfoVo login(UserDTO userDto, HttpServletRequest request) {
    LoginInfoVo loginInfoVo = new LoginInfoVo();
    UsernamePasswordAuthenticationToken principal = new UsernamePasswordAuthenticationToken(userDto.getUserId(), userDto.getPassword());
    principal.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    Authentication authentication = authenticationManager.authenticate(principal);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtConfig.getToken(userDto.getUserId() + userDto.getPassword());
    if (token != null) {
      loginInfoVo.setToken(token);
    }
    return loginInfoVo;
  }

  @Override
  @Transactional
  public Long register(UserDTO userDto) {
    // 插入用户登录表
    userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
    UserLoginPO userLoginPO = new UserLoginPO();
    BeanUtils.copyProperties(userDto, userLoginPO);
    BeanUtils.copyProperties(CommonEntityUtil.createCommonEntity(LoginInfoUtil.getLoginUserId()), userLoginPO);
    userLoginPO.setStatus("1");
    userMapper.register(userLoginPO);
    // 插入用户信息表
    UserInfoPO userInfoPO = new UserInfoPO();
    BeanUtils.copyProperties(userDto, userInfoPO);
    BeanUtils.copyProperties(userLoginPO, userInfoPO);
    userMapper.insert(userInfoPO);
    return userLoginPO.getUserId();
  }

  @Override
  public Boolean alterPassword(String oldPassword, String password, HttpServletRequest request) {
    UserLoginPO userLoginPO = userLoginMapper.selectById(Long.parseLong(LoginInfoUtil.getLoginUserId()));
    boolean matches = new BCryptPasswordEncoder().matches(oldPassword, userLoginPO.getPassword());
    if (matches) {
      userLoginMapper.alterPassword(Long.parseLong(LoginInfoUtil.getLoginUserId()), BCrypt.hashpw(password, BCrypt.gensalt()));
      return true;
    }
    return false;
  }

  @Override
  public Integer alterEmail(String email) {
    String loginUserId = LoginInfoUtil.getLoginUserId();
    UserInfoPO userInfoPO = baseMapper.selectById(Long.parseLong(loginUserId));
    userInfoPO.setEmail(email);
    int update = baseMapper.updateById(userInfoPO);
    return update;
  }
}
