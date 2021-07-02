package crw.clock.vo;

import crw.clock.entity.UserInfoPO;
import lombok.Data;

@Data
public class LoginInfoVo extends UserInfoPO {
    /*token*/
    private String token;
}
