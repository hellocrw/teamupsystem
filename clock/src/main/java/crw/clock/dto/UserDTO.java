package crw.clock.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    /*用户名*/
    private String userId;
    /*密码*/
    private String password;
    /*用户名*/
    private String userName;
    /*邮箱*/
    private String email;
}
