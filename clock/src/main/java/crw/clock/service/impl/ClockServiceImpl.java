package crw.clock.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import crw.clock.dto.ClockDTO;
import crw.clock.entity.ClockPO;
import crw.clock.entity.ClockRecordPO;
import crw.clock.entity.PlanPO;
import crw.clock.mapper.ClockMapper;
import crw.clock.mapper.ClockRecordMapper;
import crw.clock.mapper.PlanMapper;
import crw.clock.service.ClockService;
import crw.clock.utils.CommonEntityUtil;
import crw.clock.utils.TimeUtil;
import crw.clock.vo.ClockVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class ClockServiceImpl extends ServiceImpl<ClockMapper, ClockPO> implements ClockService {

  private static final Logger logger = LoggerFactory.getLogger(ClockServiceImpl.class);

  @Autowired
  private ExecutorService cachedThreadPool;

  @Autowired
  private PlanMapper planMapper;

  @Autowired
  private ClockRecordMapper clockRecordMapper;

  @Autowired
  private ClockMapper clockMapper;

  /**
   * 查看打卡内容
   *  1. 如果今天没有打卡 则查询计划打卡的内容
   *  2. 如果今天已经打卡 则查询今天打卡的内容
   * @param loginUserId
   * @return
   */
  @Override
  public List<ClockVo> queryStartClockInfo(String loginUserId) {
    // 打卡内容
    List<ClockVo> startClockList;

    // 1. 如果今天已经打卡，则查询今天打卡的内容
    logger.info("current time: {}", new Date(System.currentTimeMillis()));
    List<ClockPO> clockPOList = baseMapper.selectList(new QueryWrapper<ClockPO>().like("created_time", new Date(System.currentTimeMillis())));

    if (Objects.nonNull(clockPOList) && clockPOList.size() > 0) {
      startClockList = clockPOList.stream().map(item -> {
        ClockVo clockVo = ClockVo.builder().id(item.getId()).clockContent(item.getClockContent()).status(item.getStatus()).build();
        return clockVo;
      }).collect(Collectors.toList());
    } else {
      // 2. 如果今天还没打卡，则查询计划循环任务的每天打卡内容
      List<PlanPO> planPOList = planMapper.selectList(new QueryWrapper<PlanPO>().eq("plan_type", "1"));
      startClockList = planPOList.stream().map(item -> {
        ClockVo clockVo = ClockVo.builder().id(item.getId()).clockContent(item.getPlanContent()).status(false).build();
        return clockVo;
      }).collect(Collectors.toList());
    }

    return startClockList;
  }

  @Override
  public List<ClockVo> queryEndClockInfo(String loginUserId) {
    return this.queryStartClockInfo(loginUserId);
  }

  @Override
  @Transactional
  public Boolean addStartClockInfo(List<ClockDTO> clockInfoList, String loginUserId) {
    if (TimeUtil.morningClock() && TimeUtil.nightClock()) {
      return false;
    }
    // 判断今天是否已经打卡 selectCount > 0 ? true : false
    Integer selectCount = baseMapper.selectCount(new QueryWrapper<ClockPO>().likeRight("created_time", new Date(System.currentTimeMillis())));
    boolean present = selectCount > 0 ? true : false;
    // 打卡
    try {
      Thread thread = new Thread(() -> {
        clockInfoList.forEach(item -> {
          ClockPO clockPO = new ClockPO();
          BeanUtils.copyProperties(present ? CommonEntityUtil.updateCommonEntity(loginUserId) : CommonEntityUtil.createCommonEntity(loginUserId), clockPO);
          BeanUtils.copyProperties(item, clockPO);
          clockPO.setUserId(Long.parseLong(loginUserId));
          if (present) baseMapper.updateById(clockPO); else baseMapper.insert(clockPO);
        });
      });
      cachedThreadPool.execute(thread);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 添加打卡记录
    ClockRecordPO clockRecordPO = new ClockRecordPO();
    BeanUtils.copyProperties(CommonEntityUtil.createCommonEntity(loginUserId), clockRecordPO);
    clockRecordPO.setClockTime(Calendar.getInstance().getTime());
    clockRecordPO.setUserId(Long.parseLong(loginUserId));
    clockRecordPO.setStatus(1L);
    clockRecordMapper.insert(clockRecordPO);

    return true;
  }

  @Override
  public Boolean addEndClockInfo(List<ClockDTO> clockDTOList, String loginUserId) {
    return this.addStartClockInfo(clockDTOList, loginUserId);
  }

  @Override
  public IPage<ClockRecordPO> queryAllClockInfo(IPage<ClockRecordPO> page) {
    IPage<ClockRecordPO> clockRecordPage = clockRecordMapper.selectPage(page, new QueryWrapper<ClockRecordPO>().orderByDesc("created_time"));
    return clockRecordPage;
  }

  /**
   * 查看所有人的打卡内容 根据打卡事件排序并分页
   * @param page 分页信息
   * @return 所有人的打卡内容
   */
  @Override
  public IPage<ClockPO> queryClockContent(IPage<ClockPO> page) {
    return clockMapper.selectPage(page, new QueryWrapper<ClockPO>().orderByDesc("created_time"));
  }
}
