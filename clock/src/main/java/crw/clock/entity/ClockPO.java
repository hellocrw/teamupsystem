package crw.clock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("clock")
public class ClockPO extends CommonEntityPO {
    /*ID*/
    @TableId(type = IdType.AUTO)
    private Long id;
    /*用户ID*/
    @TableField("user_id")
    private Long userId;
    /*开始打卡时间*/
    @TableField("start_clock_time")
    private Date startClockTime;
    /*结束打卡时间*/
    @TableField("end_clock_time")
    private Date endClockTime;
    /*打卡内容*/
    @TableField("clock_content")
    private String clockContent;
    /*完成情况 1 完成 0 未完成*/
    @TableField("status")
    private Boolean status;
}
