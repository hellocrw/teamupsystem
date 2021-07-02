package crw.clock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import crw.clock.dto.CommentMoneyDTO;
import crw.clock.entity.CommentMoneyPO;

import java.util.List;

public interface CommentMoneyService extends IService<CommentMoneyPO> {

  Long alterCommentMoney(CommentMoneyDTO commentMoneyDto);

  List<CommentMoneyPO> queryCommentMoney();
}
