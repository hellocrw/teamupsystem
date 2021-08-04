package crw.clock.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClockVo {
  private Long id;
  private String clockContent;
  private Boolean status;
}
