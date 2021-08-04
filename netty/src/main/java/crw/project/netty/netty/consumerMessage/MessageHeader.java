package crw.project.netty.netty.consumerMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义消息格式: 消息头部分
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageHeader {

  private int version;  // 版本号

  private int contentLength; // 消息内容长度

  private String sessionId;  // 服务名称

}
