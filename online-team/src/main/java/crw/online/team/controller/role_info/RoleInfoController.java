package crw.online.team.controller.role_info;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import crw.online.team.service.role_info.IRoleInfoService;
import crw.online.team.entity.role_info.RoleInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author caorongwu
 * @since 2021-01-09
 */
@Api(tags = {"角色表"})
@RestController
@RequestMapping("//role-info")
public class RoleInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IRoleInfoService roleInfoService;


    @ApiOperation(value = "新增角色表")
    @PostMapping()
    public int add(@RequestBody RoleInfo roleInfo){
        return roleInfoService.add(roleInfo);
    }

    @ApiOperation(value = "删除角色表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return roleInfoService.delete(id);
    }

    @ApiOperation(value = "更新角色表")
    @PutMapping()
    public int update(@RequestBody RoleInfo roleInfo){
        return roleInfoService.updateData(roleInfo);
    }

    @ApiOperation(value = "查询角色表分页数据")
    @GetMapping()
    public IPage<RoleInfo> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return roleInfoService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询角色表")
    @GetMapping("{id}")
    public RoleInfo findById(@PathVariable Long id){
        return roleInfoService.findById(id);
    }

}
