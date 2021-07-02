package crw.clock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import crw.clock.entity.UserLoginPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLoginMapper extends BaseMapper<UserLoginPO> {

  Integer alterPassword(@Param("loginUserId") long loginUserId, @Param("password") String password);

}
