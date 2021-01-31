package crw.online.team.controller.user_info;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import crw.online.team.service.user_info.IUserInfoService;
import crw.online.team.entity.user_info.UserInfo;
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
 * @since 2021-01-09
 */
@Api(tags = {""})
@RestController
@RequestMapping("//user-info")
public class UserInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IUserInfoService userInfoService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody UserInfo userInfo){
        return userInfoService.add(userInfo);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return userInfoService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody UserInfo userInfo){
        return userInfoService.updateData(userInfo);
    }

    @ApiOperation(value = "查询分页数据")
    @GetMapping()
    public IPage<UserInfo> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return userInfoService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public UserInfo findById(@PathVariable Long id){
        return userInfoService.findById(id);
    }

}
