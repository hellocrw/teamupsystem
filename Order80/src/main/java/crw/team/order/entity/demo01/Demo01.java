package crw.team.order.entity.demo01;

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
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Demo01对象", description="")
public class Demo01 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String demoName;

    private String demoContent;

    private String demoLog;


}
