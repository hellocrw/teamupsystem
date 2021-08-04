package crw.clock.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import crw.clock.dto.ClockDTO;
import crw.clock.entity.ClockPO;
import crw.clock.entity.ClockRecordPO;
import crw.clock.vo.ClockVo;

import java.util.List;

public interface ClockService extends IService<ClockPO> {

  List<ClockVo> queryStartClockInfo(String loginUserId);

  List<ClockVo> queryEndClockInfo(String loginUserId);

  Boolean addStartClockInfo(List<ClockDTO> clockInfoList, String loginUserId);

  Boolean addEndClockInfo(List<ClockDTO> clockDTOList, String loginUserId);

  IPage<ClockRecordPO> queryAllClockInfo(IPage<ClockRecordPO> page);

  IPage<ClockPO> queryClockContent(IPage<ClockPO> page);
}
