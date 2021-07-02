package crw.clock.quartz.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import crw.clock.config.RedisConfig;
import crw.clock.entity.ClockRecordPO;
import crw.clock.entity.StatisticsPO;
import crw.clock.entity.UserInfoPO;
import crw.clock.mapper.ClockRecordMapper;
import crw.clock.mapper.StatisticsMapper;
import crw.clock.mapper.UserMapper;
import crw.clock.utils.CommonEntityUtil;
import crw.clock.utils.TimeUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

public class StatisticsDataJob implements Job {

  private static final Logger logger = LoggerFactory.getLogger(StatisticsDataJob.class);

  @Autowired
  private ClockRecordMapper clockRecordMapper;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private StatisticsMapper statisticsMapper;

  @Autowired
  private ExecutorService cachedThreadPool;

  @Autowired
  private JedisPool jedisPool;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    logger.info("执行计算统计数据的任务");
    JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    int sumClockTime = jobDataMap.getInt("sumClockTime");

    // 统计上周的缺勤数量
    Date currentDateTime = TimeUtil.getCurrentDateTime();
    Date lastWeekDateTime = TimeUtil.getLastWeekDateTime();
    List<UserInfoPO> userInfoPOList = userMapper.selectList(null);

    CyclicBarrier cyclicBarrier = new CyclicBarrier(userInfoPOList.size());
    AtomicReference<Integer> sumExpenses = new AtomicReference<>(0);

    userInfoPOList.forEach(userInfoPO -> {
      Thread thread = new Thread(() -> {
        logger.info(Thread.currentThread().getName() + " execute...");
        // 上周的统计情况
        StatisticsPO lastWeekStatisticsPO = statisticsMapper.selectOne(new QueryWrapper<StatisticsPO>()
          .eq("user_id", userInfoPO.getUserId())
          .orderByDesc("created_time").last("limit 1"));
        if (Objects.isNull(lastWeekStatisticsPO)){
          lastWeekStatisticsPO = new StatisticsPO();
          lastWeekStatisticsPO.setLastweekEarn(0);
          lastWeekStatisticsPO.setAllEarn(0);
          lastWeekStatisticsPO.setAllExpenses(0);
          lastWeekStatisticsPO.setLastweekExpenses(0);
          lastWeekStatisticsPO.setLastweekMissclockTimes(0);
          lastWeekStatisticsPO.setAllMissclockTimes(0);
          lastWeekStatisticsPO.setUserId(userInfoPO.getUserId());
        }

        StatisticsPO statisticsPO = new StatisticsPO();
        // 上周打卡次数
        Integer clockedTimes = clockRecordMapper.selectCount(new QueryWrapper<ClockRecordPO>()
          .eq("created_by", userInfoPO.getCreatedBy())
          .lt("created_time", currentDateTime)
          .gt("created_time", lastWeekDateTime)
          .eq("status", 1)
        );
        statisticsPO.setLastweekMissclockTimes(sumClockTime - clockedTimes);
        statisticsPO.setAllMissclockTimes(lastWeekStatisticsPO.getAllMissclockTimes() + statisticsPO.getLastweekMissclockTimes());
        statisticsPO.setLastweekExpenses(statisticsPO.getLastweekMissclockTimes() * 5);
        BeanUtils.copyProperties(CommonEntityUtil.createCommonEntity("SYSTEM"), statisticsPO);
        statisticsPO.setUserId(userInfoPO.getUserId());
        sumExpenses.updateAndGet(v -> v + statisticsPO.getLastweekExpenses());
        // 计算上周收入和总收入
        try {
          cyclicBarrier.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }
        // 上周收入 （人均）
        statisticsPO.setLastweekEarn(sumExpenses.get() / userInfoPOList.size());
        // 总收入 = 之前总收入 + 上周收入 - 上周支出
        statisticsPO.setAllEarn(lastWeekStatisticsPO.getAllEarn() + statisticsPO.getLastweekEarn() - statisticsPO.getLastweekExpenses());
        statisticsPO.setAllExpenses(lastWeekStatisticsPO.getAllExpenses() + statisticsPO.getLastweekExpenses() - statisticsPO.getLastweekEarn());

        statisticsMapper.insert(statisticsPO);

        Jedis jedis = jedisPool.getResource();
        jedis.set(String.valueOf(userInfoPO.getUserId()), JSON.toJSONString(statisticsPO));
        String statisticsPo = jedis.get(String.valueOf(userInfoPO.getUserId()));
        logger.info("redis statisticsPo: {}", statisticsPo);

      });
      cachedThreadPool.execute(thread);
    });

  }
}
