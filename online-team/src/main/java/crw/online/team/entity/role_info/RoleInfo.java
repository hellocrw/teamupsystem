package crw.online.team.entity.role_info;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author caorongwu
 * @since 2021-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RoleInfo对象", description="角色表")
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleId;

    private String username;

    private String password;

    private String role;


}
