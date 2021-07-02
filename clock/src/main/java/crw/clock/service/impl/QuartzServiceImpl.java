package crw.clock.service.impl;

import crw.clock.quartz.schedulers.StatisticsDataSchedulers;
import crw.clock.service.QuartzService;
import crw.clock.utils.TimeUtil;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuartzServiceImpl implements QuartzService {

  @Autowired
  private StatisticsDataSchedulers statisticsDataSchedulers;

  @Autowired
  private Scheduler scheduler;

  @Override
  public Boolean startUpStatisticsInfo(String cronExpression) {
    if (!StringUtils.isEmpty(cronExpression)) {
      Map<String, String> map = new HashMap<>();
      map.put("sumClockTime", "12");
      map.put("name", TimeUtil.parseCurrentDate());
      map.put("group", "statistics");
      map.put("cronExpression", cronExpression);
      statisticsDataSchedulers.startUpJob(map);
      return true;
    }
    return null;
  }

  @Override
  public Boolean deleteScheduleInfo(String name, String group) {
    JobKey jobKey = new JobKey(name, group);
    try {
      return scheduler.deleteJob(jobKey);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
    return false;
  }
}
