package crw.clock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import crw.clock.entity.StatisticsPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StatisticsMapper extends BaseMapper<StatisticsPO> {

  StatisticsPO queryStatisticsInfo(@Param("loginUserId") String loginUserId);

}
