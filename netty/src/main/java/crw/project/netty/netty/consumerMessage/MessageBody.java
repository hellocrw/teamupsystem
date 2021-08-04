package crw.project.netty.netty.consumerMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageBody {

  private MessageHeader messageHeader;

  private String content;

}
