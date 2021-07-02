package crw.clock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import crw.clock.entity.PlanPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanMapper extends BaseMapper<PlanPO> {
}
