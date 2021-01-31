package crw.online.team.entity.task;

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
 * @since 2021-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Task对象", description="")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务ID")
    @TableId(value = "task_id", type = IdType.AUTO)
    private Long taskId;

    @ApiModelProperty(value = "项目ID")
    private Long proId;

    @ApiModelProperty(value = "任务创建时间")
    private LocalDate taskCreateTime;

    @ApiModelProperty(value = "任务开始时间")
    private LocalDate taskStartTime;

    @ApiModelProperty(value = "任务结束时间")
    private LocalDate taskEndTime;

    @ApiModelProperty(value = "任务内容")
    private String taskContent;

    @ApiModelProperty(value = "任务负责人ID")
    private Long userId;

    @ApiModelProperty(value = "执行者")
    private String userName;

    @ApiModelProperty(value = "任务状态")
    private Integer taskStatus;

    @ApiModelProperty(value = "备注")
    private String taskMark;


}
