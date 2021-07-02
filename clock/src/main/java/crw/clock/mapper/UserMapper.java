package crw.clock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import crw.clock.entity.UserInfoPO;
import crw.clock.entity.UserLoginPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<UserInfoPO> {

  Integer register(@Param("userLoginPO") UserLoginPO userLoginPO);

}
