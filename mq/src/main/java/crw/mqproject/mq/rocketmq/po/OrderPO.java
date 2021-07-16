package crw.mqproject.mq.rocketmq.po;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderPO {
    private int orderId;
    private String desc;
}
