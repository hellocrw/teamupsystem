package crw.online.team.controller.order;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import crw.online.team.service.order.IOrderService;
import crw.online.team.entity.order.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author caorongwu
 * @since 2021-01-09
 */
@Api(tags = {"订单表"})
@RestController
@RequestMapping("//order")
public class OrderController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IOrderService orderService;


    @ApiOperation(value = "新增订单表")
    @PostMapping()
    public int add(@RequestBody Order order){
        return orderService.add(order);
    }

    @ApiOperation(value = "删除订单表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return orderService.delete(id);
    }

    @ApiOperation(value = "更新订单表")
    @PutMapping()
    public int update(@RequestBody Order order){
        return orderService.updateData(order);
    }

    @ApiOperation(value = "查询订单表分页数据")
    @GetMapping()
    public IPage<Order> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return orderService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询订单表")
    @GetMapping("{id}")
    public Order findById(@PathVariable Long id){
        return orderService.findById(id);
    }

}
