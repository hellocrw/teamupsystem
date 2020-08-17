package crw.commons.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author caorongwu
 * @since 2020-08-17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {
    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 项目ID
     */
    private Long proId;

    /**
     * 任务创建时间
     */
    private LocalDate taskCreateTime;

    /**
     * 任务开始时间
     */
    private LocalDate taskStartTime;

    /**
     * 任务结束时间
     */
    private LocalDate taskEndTime;

    /**
     * 任务内容
     */
    private String taskContent;

    /**
     * 任务负责人ID
     */
    private Long userId;

    /**
     * 执行者
     */
    private String userName;

    /**
     * 任务状态
     */
    private Integer taskStatus;

    /**
     * 备注
     */
    private String taskMark;

}
