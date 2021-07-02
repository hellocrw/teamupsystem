package crw.clock.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import crw.clock.dto.ClockDTO;
import crw.clock.entity.ClockRecordPO;
import crw.clock.service.ClockService;
import crw.clock.utils.LoginInfoUtil;
import crw.clock.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("abi/user/clock")
@Api(tags = {"打卡接口"})
public class ClockController {

  private static final Logger logger = LoggerFactory.getLogger(ClockController.class);

  @Autowired
  private ClockService clockService;

  /**
   * 查询开始打卡信息
   *
   * @return
   */
  @ApiOperation(value = "查询开始打卡信息")
  @GetMapping("queryStartClockInfo")
  public ResponseEntity<ResultVo> queryStartClockInfo() {
    String loginUserId = LoginInfoUtil.getLoginUserId();
    List<String> startClockInfoList = clockService.queryStartClockInfo(loginUserId);
    return new ResponseEntity<>(new ResultVo(200, "查询开始打卡信息", startClockInfoList), HttpStatus.OK);
  }

  /**
   * 开始打卡
   *
   * @return
   */
  @ApiOperation(value = "打卡")
  @PostMapping("addStartClockInfo")
  public ResponseEntity<ResultVo> addStartClockInfo(@RequestBody List<ClockDTO> clockInfoList) {
    Boolean clockInfo = clockService.addStartClockInfo(clockInfoList, LoginInfoUtil.getLoginUserId());
    return new ResponseEntity<>(new ResultVo(200, "打卡", clockInfo), HttpStatus.OK);
  }

  /**
   * 查询结束打卡信息
   *
   * @return
   */
  @GetMapping("queryEndClockInfo")
  public ResponseEntity<ResultVo> queryEndClockInfo() {
    String loginUserId = LoginInfoUtil.getLoginUserId();
    List<String> list = clockService.queryEndClockInfo(loginUserId);
    return new ResponseEntity<>(new ResultVo(200, "查询结束打卡信息", list), HttpStatus.OK);
  }

  /**
   * 结束打卡
   *
   * @return
   */
  @ApiOperation(value = "打卡")
  @PostMapping("addEndClockInfo")
  public ResponseEntity<ResultVo> addEndClockInfo(@RequestBody List<ClockDTO> clockDTOList) {
    Boolean clockInfo = clockService.addEndClockInfo(clockDTOList, LoginInfoUtil.getLoginUserId());
    return new ResponseEntity<>(new ResultVo(200, "打卡", clockInfo), HttpStatus.OK);
  }

  /**
   * 查看所有人的打卡记录
   * @param currentPage 当前页
   * @param pageSize 页数量
   * @return
   */
  @ApiOperation("查看所有人的打卡记录")
  @GetMapping("queryAllClockInfo")
  public ResponseEntity<ResultVo> queryAllClockInfo(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
    IPage<ClockRecordPO> page = new Page<>(currentPage, pageSize);
    IPage<ClockRecordPO> clockRecordPOIPage = clockService.queryAllClockInfo(page);
    return new ResponseEntity<>(new ResultVo(200, "查看所有人的打卡记录", clockRecordPOIPage), HttpStatus.OK);
  }
}
