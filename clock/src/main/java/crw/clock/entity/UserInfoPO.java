package crw.clock.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("user_info")
public class UserInfoPO extends CommonEntityPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*ID*/
    @TableId(type = IdType.AUTO)
    private Long Id;
    /*用户ID*/
    @TableField("user_id")
    private Long UserId;
    /*用户姓名*/
    @TableField("user_name")
    private String userName;
    /*用户邮箱*/
    @TableField("email")
    private String email;
}
