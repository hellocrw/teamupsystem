package crw.clock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import crw.clock.entity.CommentMoneyPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMoneyMapper extends BaseMapper<CommentMoneyPO> {

  CommentMoneyPO queryCommentMoney();
}
