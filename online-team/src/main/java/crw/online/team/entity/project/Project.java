package crw.online.team.entity.project;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author caorongwu
 * @since 2020-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Project对象", description="")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    @TableId(value = "pro_id", type = IdType.AUTO)
    private Long proId;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "队长名称")
    private String leaderName;

    @ApiModelProperty(value = "项目描述")
    private String proDescribe;

    @ApiModelProperty(value = "项目创建时间")
    private LocalDate proDate;

    @ApiModelProperty(value = "项目开始时间")
    private LocalDate proStartTime;

    @ApiModelProperty(value = "项目结束时间")
    private LocalDate proEndTime;

    @ApiModelProperty(value = "项目当前状态")
    private String proStatus;

    @ApiModelProperty(value = "所属团队id号")
    private Long teamId;

    @ApiModelProperty(value = "项目类型")
    private String proType;

    @ApiModelProperty(value = "项目当前人数")
    private Integer proCurrentNum;

    @ApiModelProperty(value = "项目限制人数")
    private Integer proLimiedNum;

    @ApiModelProperty(value = "查看人数")
    private Integer seeNum;

    @ApiModelProperty(value = "技术类型")
    private String staffList;

    @ApiModelProperty(value = "人员类型")
    private String staff;


}
