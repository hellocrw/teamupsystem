package crw.project.netty.netty.handler;

import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;

public class NettyServerOutHandler extends ChannelOutboundHandlerAdapter {

  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    System.out.println("===> NettyServerOutHandler");
    // 执行下一个OutboundHandler
    super.write(ctx, msg, promise);
  }
}
