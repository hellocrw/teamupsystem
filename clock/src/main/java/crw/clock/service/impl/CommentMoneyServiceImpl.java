package crw.clock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import crw.clock.dto.CommentMoneyDTO;
import crw.clock.entity.CommentMoneyPO;
import crw.clock.mapper.CommentMoneyMapper;
import crw.clock.service.CommentMoneyService;
import crw.clock.utils.CommonEntityUtil;
import crw.clock.utils.LoginInfoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentMoneyServiceImpl extends ServiceImpl<CommentMoneyMapper, CommentMoneyPO> implements CommentMoneyService {

  @Autowired
  private CommentMoneyMapper commentMoneyMapper;

  @Override
  public Long alterCommentMoney(CommentMoneyDTO commentMoneyDto) {
    CommentMoneyPO commentMoneyPO = new CommentMoneyPO();
    // 获取当前余额信息
    CommentMoneyPO queryCommentMoneyPO = commentMoneyMapper.queryCommentMoney();
    BeanUtils.copyProperties(CommonEntityUtil.createCommonEntity(LoginInfoUtil.getLoginUserId()), commentMoneyPO);
    commentMoneyPO.setOperationUser(Long.parseLong(LoginInfoUtil.getLoginUserId()));
    commentMoneyPO.setCurrentMoney(queryCommentMoneyPO.getRemainMoney());
    commentMoneyPO.setSunsumeMoney(commentMoneyDto.getSunsumeMoney());
    commentMoneyPO.setRemainMoney(queryCommentMoneyPO.getRemainMoney() - commentMoneyDto.getSunsumeMoney());
    commentMoneyPO.setRemark(commentMoneyDto.getRemark());
    baseMapper.insert(commentMoneyPO);
    return commentMoneyPO.getRemainMoney();
  }

  @Override
  public List<CommentMoneyPO> queryCommentMoney() {
    List<CommentMoneyPO> commentMoneyPOList = baseMapper.selectList(new QueryWrapper<CommentMoneyPO>().orderByDesc("created_time"));
    return commentMoneyPOList;
  }
}
