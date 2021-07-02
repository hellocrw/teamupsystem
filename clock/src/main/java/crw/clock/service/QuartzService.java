package crw.clock.service;

public interface QuartzService {

  Boolean startUpStatisticsInfo(String cronExpression);

  Boolean deleteScheduleInfo(String name, String group);
}
