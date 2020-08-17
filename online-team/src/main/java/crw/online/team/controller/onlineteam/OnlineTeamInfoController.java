package crw.online.team.controller.onlineteam;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import crw.online.team.service.onlineteam.IOnlineTeamInfoService;
import crw.online.team.entity.onlineteam.OnlineTeamInfo;
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
 * @since 2020-08-18
 */
@Api(tags = {""})
@RestController
@RequestMapping("//online-team-info")
public class OnlineTeamInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IOnlineTeamInfoService onlineTeamInfoService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody OnlineTeamInfo onlineTeamInfo){
        return onlineTeamInfoService.add(onlineTeamInfo);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return onlineTeamInfoService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody OnlineTeamInfo onlineTeamInfo){
        return onlineTeamInfoService.updateData(onlineTeamInfo);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<OnlineTeamInfo> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return onlineTeamInfoService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public OnlineTeamInfo findById(@PathVariable Long id){
        return onlineTeamInfoService.findById(id);
    }

}
