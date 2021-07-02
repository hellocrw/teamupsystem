package crw.clock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import crw.clock.dto.PlanDTO;
import crw.clock.entity.PlanPO;

import java.util.List;

public interface PlanService extends IService<PlanPO> {

  List<PlanPO> queryPlanInfo(String loginUserId);

  Boolean addPlanInfo(List<PlanDTO> planDTOList, String loginUserId);
}
