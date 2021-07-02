package crw.clock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import crw.clock.entity.ClockPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClockMapper extends BaseMapper<ClockPO> {
}
