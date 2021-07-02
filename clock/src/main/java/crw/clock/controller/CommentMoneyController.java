package crw.clock.controller;

import com.alibaba.fastjson.JSON;
import crw.clock.dto.CommentMoneyDTO;
import crw.clock.entity.CommentMoneyPO;
import crw.clock.service.CommentMoneyService;
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
@RequestMapping("abi/admin/comment-money")
@Api(tags = {"公款接口"})
public class CommentMoneyController {

  private static final Logger logger = LoggerFactory.getLogger(CommentMoneyController.class);

  @Autowired
  private CommentMoneyService commentMoneyService;

  /**
   * 查询公款信息
   *
   * @return
   */
  @GetMapping("queryCommentMoney")
  public ResponseEntity<ResultVo> queryCommentMoney() {
    List<CommentMoneyPO> commentMoneyPOList = commentMoneyService.queryCommentMoney();
    return new ResponseEntity<>(new ResultVo(200, "查询公款信息", commentMoneyPOList), HttpStatus.OK);
  }

  /**
   * 修改公款信息
   *
   * @return
   */
  @PostMapping("alterCommentMoney")
  public ResponseEntity<ResultVo> alterCommentMoney(@RequestBody CommentMoneyDTO commentMoneyDto) {
    logger.info("alterCommentMoney: {}", JSON.toJSONString(commentMoneyDto));
    Long result = commentMoneyService.alterCommentMoney(commentMoneyDto);
    return new ResponseEntity<>(new ResultVo(200,"修改成功", result), HttpStatus.OK);
  }

  /**
   * 自动计算公款信息
   * @return
   */
  @PostMapping("autoCalculate")
  public ResponseEntity<ResultVo> autoCalculate() {
    return null;
  }
}
