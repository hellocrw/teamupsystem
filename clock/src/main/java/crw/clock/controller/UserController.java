package crw.clock.controller;

import crw.clock.dto.UserDTO;
import crw.clock.service.UserService;
import crw.clock.vo.LoginInfoVo;
import crw.clock.vo.ResultVo;
import crw.clock.vo.UserInfoVo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("abi/user/user")
@Api(tags = {"用户接口"})
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  /**
   * 用户登录功能
   *
   * @return 用户基本信息
   */
  @PostMapping("login")
  public ResponseEntity<ResultVo> login(@RequestBody UserDTO userDto, HttpServletRequest httpRequest, HttpServletResponse response) {
    LoginInfoVo loginInfoVo = userService.login(userDto, httpRequest);
    response.setHeader("token", loginInfoVo.getToken());
    return new ResponseEntity<>(new ResultVo(200, "登录成功", loginInfoVo), HttpStatus.OK);
  }

  /**
   * 用户注册功能
   *
   * @return 用户账号ID
   */
  @PostMapping("register")
  public ResponseEntity<ResultVo> register(@RequestBody UserDTO userDto) {
    Long userId = userService.register(userDto);
    return new ResponseEntity<>(new ResultVo(200, "注册成功", userId), HttpStatus.OK);
  }

  /**
   * 修改密码
   *
   * @return
   */
  @PostMapping("alterPassword")
  public ResponseEntity<ResultVo> alterPassword(@RequestParam("oldPassword") String oldPassword ,@RequestParam("password") String password, HttpServletRequest request) {
    Boolean result = userService.alterPassword(oldPassword, password, request);
    if (result) {
      return new ResponseEntity<>(new ResultVo(200, "修改密码", true), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(new ResultVo(400, "密码不成功"), HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * 修改邮箱
   *
   * @return
   */
  @PostMapping("alterEmail")
  public ResponseEntity<ResultVo> alterEmail(@RequestParam("email") String email) {
    Integer integer = userService.alterEmail(email);
    if (integer == 1) {
      return new ResponseEntity<>(new ResultVo(200, "修改邮箱", integer), HttpStatus.OK);
    }else {
      return new ResponseEntity<>(new ResultVo(400, "修改邮箱", integer), HttpStatus.OK);
    }
  }

  /**
   * 修改是否发送邮箱通知
   *
   * @return
   */
  @PostMapping("alterEmailNotify")
  public ResponseEntity<ResultVo> alterEmailNotify() {
    return null;
  }

  @GetMapping("queryUserInfo/{userId}")
  public ResponseEntity<ResultVo> queryUserInfo(@PathVariable("userId") String userId) {
    UserInfoVo userInfoVo = userService.queryUserInfo(userId);
    return new ResponseEntity<>(new ResultVo(200, "查询成功", userInfoVo), HttpStatus.OK);
  }
}
