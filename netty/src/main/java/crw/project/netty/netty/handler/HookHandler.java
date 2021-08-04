package crw.project.netty.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HookHandler extends ChannelInboundHandlerAdapter {

  /**
   * 当NettyClient connect到NettyServer触发，transfer channelRegistered method before channelActive
   * @param ctx {@link ChannelHandlerContext}
   * @throws Exception
   */
  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("HookHandler.channelRegistered");
  }

  /**
   * when the NettyClient unregistered the NettyServer , trigger this method
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("HookHandler.channelUnregistered");
  }

  /**
   * NettyClient connect到NettyServer触发
   * @param ctx {@link ChannelHandlerContext} 通道Handler上下文, 需要研究下ChannelHandlerContext中存储那些信息
   * @throws Exception
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("HookHandler.channelActive");
  }

  /**
   * 当客户端断线的时候，可以通过这个方法实现断线重连
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("HookHandler.channelInactive");
  }

  /**
   * when NettyClient send message to NettyServer, trigger this method
   *  TODO the different of ChannelRead and messageReceived ?
   *  from internet: 需要编解码的才会用messageReceived,一般都是使用ChannelRead读取；
   *  看{@link io.netty.channel.SimpleChannelInboundHandler} 可以发现，泛型不匹配，不会调用messageReceived
   *  如果没有任何的编解码，要使用SimpleChannelInboundHandler，可以这样写： public MyServerHandler extends SimpleChannelInboundHandler<ByteBuf>{...}
   * @param ctx {@link ChannelHandlerContext}
   * @param msg
   * @throws Exception
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("HookHandler.channelRead");
    ByteBuf byteBuf = (ByteBuf) msg;
    byte[] bytes = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bytes);
    System.out.println(new String(bytes));

    String result = "OK";
    ByteBuf buffer = ctx.channel().alloc().buffer();
    buffer.writeBytes(result.getBytes());
    ctx.channel().write(buffer);

    // 传递给下一个Handler执行
    ctx.fireChannelRead(msg);
  }

  /**
   * message read complete trigger this method
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    ctx.channel().flush();
    System.out.println("HookHandler.channelReadComplete");
  }

  /**
   *
   * @param ctx
   * @param evt
   * @throws Exception
   */
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent event = (IdleStateEvent) evt;
      if (event.state() == IdleState.READER_IDLE) {
        System.out.println("HookHandler.userEventTriggered IdleState.READER_IDLE");
      }else if (event.state() == IdleState.WRITER_IDLE) {
        System.out.println("HookHandler.userEventTriggered IdleState.WRITER_IDLE");
      }else if (event.state() == IdleState.ALL_IDLE) {
        System.out.println("HookHandler.userEventTriggered IdleState.ALL_IDLE");
      }else {
        System.out.println("HookHandler.userEventTriggered other");
      }
    } else {
      System.out.println("HookHandler.userEventTriggered");
      super.userEventTriggered(ctx, evt);
    }
  }

  /**
   * deal with exception, like nettyClient connect exception
   * @param ctx
   * @param cause
   * @throws Exception
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    System.out.println("HookHandler.exceptionCaught");
  }

}
