package crw.online.team.entity.team;

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
 * @since 2020-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Team对象", description="")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "team_id", type = IdType.AUTO)
    private Long teamId;

    @ApiModelProperty(value = "团队名称")
    private String teamName;

    @ApiModelProperty(value = "管理员ID")
    private Long adminId;

    @ApiModelProperty(value = "队长ID")
    private Long leaderId;

    @ApiModelProperty(value = "队长名称")
    private String leaderName;

    @ApiModelProperty(value = "团队描述")
    private String teamDescribe;

    @ApiModelProperty(value = "团队类型")
    private String teamType;

    @ApiModelProperty(value = "团队范围（校内、校外）")
    private String teamScope;

    @ApiModelProperty(value = "团队当前人数")
    private Integer teamNumber;

    @ApiModelProperty(value = "团队计划人数")
    private Integer sumNumber;

    @ApiModelProperty(value = "团队创建日期")
    private LocalDate teamDate;

    @ApiModelProperty(value = "团队状态(0表示发起组队，1表示管理员审核通过、2表示完成组队)")
    private Integer status;

    @ApiModelProperty(value = "人员类型")
    private String staff;

    @ApiModelProperty(value = "团队性质")
    private String teamNature;

    @ApiModelProperty(value = "团队标签")
    private String teamLabel;

    @ApiModelProperty(value = "查看人数")
    private Integer seeNum;


}
