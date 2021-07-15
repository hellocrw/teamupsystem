package person.crw.springdatajpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorksDto {
  private Long worksId;
  private String worksName;
  private String worksContent;
  private String worksUrl;
}
