package crw.clock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user_login")
public class UserLoginPO extends CommonEntityPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*用户ID*/
    @TableId(type = IdType.AUTO)
    private Long userId;
    /*用户密码*/
    @TableField("password")
    private String password;
    /*用户状态 0关闭 1活跃*/
    @TableField("status")
    private String status;
}
