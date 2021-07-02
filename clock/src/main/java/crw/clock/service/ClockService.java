package crw.clock.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import crw.clock.dto.ClockDTO;
import crw.clock.entity.ClockPO;
import crw.clock.entity.ClockRecordPO;

import java.util.List;

public interface ClockService extends IService<ClockPO> {

  List<String> queryStartClockInfo(String loginUserId);

  List<String> queryEndClockInfo(String loginUserId);

  Boolean addStartClockInfo(List<ClockDTO> clockInfoList, String loginUserId);

  Boolean addEndClockInfo(List<ClockDTO> clockDTOList, String loginUserId);

  IPage<ClockRecordPO> queryAllClockInfo(IPage<ClockRecordPO> page);
}
