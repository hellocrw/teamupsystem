package crw.clock.controller;

import crw.clock.dto.PlanDTO;
import crw.clock.entity.PlanPO;
import crw.clock.service.PlanService;
import crw.clock.utils.LoginInfoUtil;
import crw.clock.vo.ResultVo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("abi/user/plan")
@Api(tags = {"计划接口"})
public class PlanController {

  private static final Logger logger = LoggerFactory.getLogger(PlanController.class);

  @Autowired
  private PlanService planService;

  /**
   * 查询用户计划信息
   *
   * @return
   */
  @GetMapping("queryPlanInfo")
  public ResponseEntity<ResultVo> queryPlanInfo() {
    logger.info("queryPlanInfo: {}", LoginInfoUtil.getLoginUserId());
    String loginUserId = LoginInfoUtil.getLoginUserId();
    List<PlanPO> planPOList = planService.queryPlanInfo(loginUserId);
    return new ResponseEntity<>(new ResultVo(200, "查询计划信息", planPOList), HttpStatus.OK);
  }

  /**
   * 添加计划
   *
   * @return
   */
  @PostMapping("addPlanInfo")
  public ResponseEntity<ResultVo> addPlanInfo(@RequestBody List<PlanDTO> planDTOList) {
    String loginUserId = LoginInfoUtil.getLoginUserId();
    Boolean planInfo = planService.addPlanInfo(planDTOList, loginUserId);
    return new ResponseEntity<>(new ResultVo(200, "添加计划", planInfo), HttpStatus.OK);
  }
}
