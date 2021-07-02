package crw.clock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("comment_money")
public class CommentMoneyPO extends CommonEntityPO implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(type = IdType.AUTO)
  private Long id;
  @TableField("current_money")
  private Long currentMoney;
  @TableField("sunsume_money")
  private Long sunsumeMoney;
  @TableField("remain_money")
  private Long remainMoney;
  @TableField("operation_user")
  private Long operationUser;
  @TableField("remark")
  private String remark;
}
