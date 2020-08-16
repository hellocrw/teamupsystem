package crw.commons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author caorongwu
 * @since 2020-08-17
 */
public class Task extends Model<Task> {

    private static final long serialVersionUID=1L;

    /**
     * 任务ID
     */
    @TableId(value = "task_id", type = IdType.AUTO)
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


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public LocalDate getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(LocalDate taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }

    public LocalDate getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(LocalDate taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public LocalDate getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(LocalDate taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskMark() {
        return taskMark;
    }

    public void setTaskMark(String taskMark) {
        this.taskMark = taskMark;
    }

    @Override
    protected Serializable pkVal() {
        return this.taskId;
    }

    @Override
    public String toString() {
        return "Task{" +
        "taskId=" + taskId +
        ", proId=" + proId +
        ", taskCreateTime=" + taskCreateTime +
        ", taskStartTime=" + taskStartTime +
        ", taskEndTime=" + taskEndTime +
        ", taskContent=" + taskContent +
        ", userId=" + userId +
        ", userName=" + userName +
        ", taskStatus=" + taskStatus +
        ", taskMark=" + taskMark +
        "}";
    }
}
