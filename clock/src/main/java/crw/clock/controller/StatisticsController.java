package crw.clock.controller;

import crw.clock.entity.StatisticsPO;
import crw.clock.service.StatisticsService;
import crw.clock.utils.LoginInfoUtil;
import crw.clock.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("abi/user/statistics")
@Api(tags = {"统计接口"})
public class StatisticsController {

  @Autowired
  private StatisticsService statisticsService;

  /**
   * 查询用户统计信息
   *
   * @return
   */
  @GetMapping("queryStatisticsInfo")
  public ResponseEntity<ResultVo> queryStatisticsInfo() {
    String loginUserId = LoginInfoUtil.getLoginUserId();
    StatisticsPO statisticsPO = statisticsService.queryStatisticsInfo(loginUserId);
    return new ResponseEntity<>(new ResultVo(200, "查询统计信息", statisticsPO), HttpStatus.OK);
  }
}
