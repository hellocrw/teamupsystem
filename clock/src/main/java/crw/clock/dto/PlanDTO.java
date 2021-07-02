package crw.clock.dto;

import lombok.Data;

@Data
public class PlanDTO {
  /*类型 1标识每天循环任务 2标识每月计划*/
  private String planType;
  /*计划内容*/
  private String planContent;

}
