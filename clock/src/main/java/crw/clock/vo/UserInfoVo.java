package crw.clock.vo;

import lombok.Data;

@Data
public class UserInfoVo {
    /*用户ID*/
    private Long UserId;
    /*用户姓名*/
    private String userName;
    /*用户邮箱*/
    private String email;
}
