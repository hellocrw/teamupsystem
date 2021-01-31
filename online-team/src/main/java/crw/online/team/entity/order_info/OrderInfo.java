package crw.online.team.entity.order_info;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author caorongwu
 * @since 2021-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderInfo对象", description="订单表")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;

    private String userId;

    private String shoppingId;

    private String payment;

    private String paymentType;

    private String orderPostage;

    private String status;

    private String paymentTime;

    private String orderSendtime;

    private String orderEndtime;

    private String orderClosetime;

    private String paymentCreatetime;

    private String paymentUpdatetime;


}
