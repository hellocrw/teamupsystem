package crw.online.team.controller.task;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import crw.online.team.service.task.ITaskService;
import crw.online.team.entity.task.Task;
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
@RequestMapping("//task")
public class TaskController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITaskService taskService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Task task){
        return taskService.add(task);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return taskService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Task task){
        return taskService.updateData(task);
    }

    @ApiOperation(value = "查询分页数据")
    @GetMapping()
    public IPage<Task> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return taskService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Task findById(@PathVariable Long id){
        return taskService.findById(id);
    }

}
