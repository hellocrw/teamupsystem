package crw.clock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import crw.clock.entity.StatisticsPO;
import crw.clock.mapper.StatisticsMapper;
import crw.clock.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, StatisticsPO> implements StatisticsService {

  @Autowired
  private StatisticsMapper statisticsMapper;

  @Override
  public StatisticsPO queryStatisticsInfo(String loginUserId) {
    StatisticsPO statisticsPO = statisticsMapper.queryStatisticsInfo(loginUserId);
    return statisticsPO;
  }
}
