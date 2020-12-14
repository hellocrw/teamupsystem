package crw.online.team.controller.onlineteam;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import crw.online.team.service.onlineteam.IProjectService;
import crw.online.team.entity.onlineteam.Project;
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
 * @since 2020-12-14
 */
@Api(tags = {""})
@RestController
@RequestMapping("//project")
public class ProjectController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IProjectService projectService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Project project){
        return projectService.add(project);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return projectService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Project project){
        return projectService.updateData(project);
    }

    @ApiOperation(value = "查询分页数据")
    @GetMapping()
    public IPage<Project> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return projectService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Project findById(@PathVariable Long id){
        return projectService.findById(id);
    }

}
