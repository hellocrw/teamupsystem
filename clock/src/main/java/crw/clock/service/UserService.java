package crw.clock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import crw.clock.dto.UserDTO;
import crw.clock.entity.UserInfoPO;
import crw.clock.vo.LoginInfoVo;
import crw.clock.vo.UserInfoVo;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<UserInfoPO> {

    UserInfoVo queryUserInfo(String userId);

    LoginInfoVo login(UserDTO userDto, HttpServletRequest request);

    Long register(UserDTO userDto);

    Boolean alterPassword(String oldPassword, String password, HttpServletRequest request);

    Integer alterEmail(String email);
}
