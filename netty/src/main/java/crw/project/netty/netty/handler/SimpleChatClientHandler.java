package crw.project.netty.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SimpleChatClientHandler extends SimpleChannelInboundHandler<String> {

  /**
   * 接收数据
   * @param channelHandlerContext
   * @param s
   * @throws Exception
   */
  @Override
  protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
    System.out.println("=========receive message========");
    System.out.println(s);
  }

}
