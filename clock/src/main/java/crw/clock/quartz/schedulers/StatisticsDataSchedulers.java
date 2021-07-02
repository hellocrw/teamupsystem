package crw.clock.quartz.schedulers;

import crw.clock.quartz.job.StatisticsDataJob;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Map;

@Component
public class StatisticsDataSchedulers {

  @Autowired
  private Scheduler scheduler;

  public void startUpJob(Map<String, String> map) {

    String cronExpression = map.get("cronExpression");

    JobDetail jobDetail = JobBuilder.newJob(StatisticsDataJob.class)
      .withIdentity(map.get("name"), map.get("group"))
      .usingJobData("sumClockTime", Integer.parseInt(map.get("sumClockTime")))
      .build();

    CronTriggerImpl cronTrigger = new CronTriggerImpl();
    try {
      cronTrigger.setCronExpression(cronExpression);
      TriggerKey triggerKey = new TriggerKey(map.get("name"), map.get("group"));
      cronTrigger.setKey(triggerKey);
      scheduler.scheduleJob(jobDetail, cronTrigger);
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

}
