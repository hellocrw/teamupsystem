package crw.clock.controller;

import crw.clock.service.QuartzService;
import crw.clock.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"调度接口"})
@RequestMapping("/abi/admin/quartz")
public class QuartzController {

  @Autowired
  private QuartzService quartzService;

  @PostMapping("startUp")
  public ResponseEntity<ResultVo> startUpStatisticsInfo(@RequestParam("cronExpression") String cronExpression) {
    Boolean result = quartzService.startUpStatisticsInfo(cronExpression);
    return new ResponseEntity<>(new ResultVo(200, "启动", result), HttpStatus.OK);
  }

  @DeleteMapping("deleteScheduleInfo")
  public ResponseEntity<ResultVo> deleteScheduleInfo(@RequestParam("name") String name, @RequestParam("group") String group){
    quartzService.deleteScheduleInfo(name, group);
    return new ResponseEntity<>(new ResultVo(200, "删除调度"), HttpStatus.OK);
  }

}
