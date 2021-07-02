package crw.clock.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommonEntityPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*客观锁*/
    @TableField("revision")
    private Integer revision;
    /*创建人*/
    @TableField("created_by")
    private String createdBy;
    /*创建时间*/
    @TableField("created_time")
    private Date createdTime;
    /*更新人*/
    @TableField("updated_by")
    private String updatedBy;
    /*更新时间*/
    @TableField("updated_time")
    private Date updatedTime;
}
