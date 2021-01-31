package crw.online.team.service.order_info;

import crw.online.team.entity.order_info.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author caorongwu
 * @since 2021-01-09
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    /**
     * 查询订单表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<OrderInfo>
     */
    IPage<OrderInfo> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加订单表
     *
     * @param orderInfo 订单表
     * @return int
     */
    int add(OrderInfo orderInfo);

    /**
     * 删除订单表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改订单表
     *
     * @param orderInfo 订单表
     * @return int
     */
    int updateData(OrderInfo orderInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return OrderInfo
     */
    OrderInfo findById(Long id);
}
