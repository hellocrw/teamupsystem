package crw.clock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("statistics")
public class StatisticsPO extends CommonEntityPO implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(type = IdType.AUTO)
  private Long id;

  @TableField("user_id")
  private Long userId;

  @TableField("all_earn")
  private Integer allEarn;

  @TableField("all_expenses")
  private Integer allExpenses;

  @TableField("lastweek_earn")
  private Integer lastweekEarn;

  @TableField("lastweek_expenses")
  private Integer lastweekExpenses;

  @TableField("all_missclock_times")
  private Integer allMissclockTimes;

  @TableField("lastweek_missclock_times")
  private Integer lastweekMissclockTimes;
}
