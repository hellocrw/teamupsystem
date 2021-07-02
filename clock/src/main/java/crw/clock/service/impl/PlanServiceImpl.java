package crw.clock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import crw.clock.dto.PlanDTO;
import crw.clock.entity.PlanPO;
import crw.clock.mapper.PlanMapper;
import crw.clock.service.PlanService;
import crw.clock.utils.CommonEntityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, PlanPO> implements PlanService {

  private static final Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);

  @Autowired
  private ExecutorService cachedThreadPool;

  @Override
  public List<PlanPO> queryPlanInfo(String loginUserId) {
    List<PlanPO> planPOList = baseMapper.selectList(new QueryWrapper<PlanPO>().eq("created_by", loginUserId));
    return planPOList;
  }

  @Override
  public Boolean addPlanInfo(List<PlanDTO> planDTOList, String loginUserId) {
    logger.info("planDTOList: {} , loginUserId: {}", planDTOList, loginUserId);
    Thread thread = new Thread(() -> {
      planDTOList.stream().forEach(planDTO -> {
        PlanPO planPO = new PlanPO();
        BeanUtils.copyProperties(planDTO, planPO);
        BeanUtils.copyProperties(CommonEntityUtil.createCommonEntity(loginUserId), planPO);
        baseMapper.insert(planPO);
      });
    });
    cachedThreadPool.execute(thread);
    return true;
  }
}
