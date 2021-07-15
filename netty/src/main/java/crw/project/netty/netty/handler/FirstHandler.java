package crw.project.netty.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class FirstHandler extends SimpleChannelInboundHandler<String> {

  /**
   * 接收数据
   * @param channelHandlerContext
   * @param s
   * @throws Exception
   */
  @Override
  protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
    System.out.println("first handler get message");
  }

  /**
   * 发送数据
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();
    channel.writeAndFlush("收到");
  }
}
