package crw.project.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Date;

public class NettyClientSimpleHandler extends SimpleChannelInboundHandler {

  /**
   * 读取数据
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("client channelRead");
    ByteBuf byteBuf = (ByteBuf) msg;
    byte[] bytes = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bytes);
    System.out.println("server said: " + new String(bytes));
  }

  @Override
  protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("client messageReceived");
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("NettyClientSimpleHandler.channelActive");
  }

  /**
   * 捕获IdleState.WRITER_IDLE事件（未在指定时间内向服务器发送数据）,然后向Server端发送一个心跳包
   * @param ctx {@link ChannelHandlerContext}
   * @param evt
   * @throws Exception
   */
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    //System.out.println("心跳检测：" + new Date());
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent event = (IdleStateEvent) evt;
      if (event.state() == IdleState.READER_IDLE) {
        System.out.println("IdleState reader_idle");
      }else if (event.state() == IdleState.WRITER_IDLE) {
        System.out.println("IdleState writer_idle");
      } else if (event.state() == IdleState.ALL_IDLE) {
        System.out.println("IdleState all_idle");
      }else {
        System.out.println("IdleState other idle");
      }
    }else {
      System.out.println("NettyClientSimpleHandler.userEventTriggered");
      super.userEventTriggered(ctx, evt);
    }
  }
}
