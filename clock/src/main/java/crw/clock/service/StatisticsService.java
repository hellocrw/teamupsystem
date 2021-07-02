package crw.clock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import crw.clock.entity.StatisticsPO;

public interface StatisticsService extends IService<StatisticsPO> {
    StatisticsPO queryStatisticsInfo(String loginUserId);
}
