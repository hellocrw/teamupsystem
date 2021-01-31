package crw.online.team.service.order;

import crw.online.team.entity.order.Order;
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
public interface IOrderService extends IService<Order> {

    /**
     * 查询订单表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Order>
     */
    IPage<Order> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加订单表
     *
     * @param order 订单表
     * @return int
     */
    int add(Order order);

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
     * @param order 订单表
     * @return int
     */
    int updateData(Order order);

    /**
     * id查询数据
     *
     * @param id id
     * @return Order
     */
    Order findById(Long id);
}
