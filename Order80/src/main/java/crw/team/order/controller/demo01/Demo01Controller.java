package crw.team.order.controller.demo01;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import crw.team.order.service.demo01.IDemo01Service;
import crw.team.order.entity.demo01.Demo01;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caorongwu
 * @since 2021-02-01
 */
@Api(tags = {""})
@RestController
@RequestMapping("//demo01")
public class Demo01Controller {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IDemo01Service demo01Service;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Demo01 demo01){
        return demo01Service.add(demo01);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return demo01Service.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Demo01 demo01){
        return demo01Service.updateData(demo01);
    }

    @ApiOperation(value = "查询分页数据")
    @GetMapping()
    public IPage<Demo01> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return demo01Service.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Demo01 findById(@PathVariable Long id){
        return demo01Service.findById(id);
    }

}
