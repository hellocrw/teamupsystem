package crw.project.netty.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {

  private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

  @Override
  protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
    Channel inChannel = ctx.channel();
    for (Channel channel : channelGroup) {
      if (channel != inChannel) {
        channel.writeAndFlush("[" + inChannel.remoteAddress() + "]" + msg + "\n");
      } else {
        channel.writeAndFlush("[you]" + msg + "\n");
      }
    }
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    Channel inChannel = ctx.channel();
    for (Channel channel : channelGroup) {
      channel.writeAndFlush("[SERVER] - " + inChannel.remoteAddress() + " 加入 \n");
    }
    channelGroup.add(ctx.channel());
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    Channel inChannel = ctx.channel();
    for (Channel channel : channelGroup) {
      channel.writeAndFlush("[SERVER] - " + inChannel.remoteAddress() + "离开 \n");
    }
    channelGroup.remove(ctx.channel());
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    Channel inChannel = ctx.channel();
    System.out.println("simple chat client:" + inChannel.remoteAddress() + "在线");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    Channel inChannel = ctx.channel();
    System.out.println("simple chat client:" + inChannel.remoteAddress() + "掉线");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    Channel inChannel = ctx.channel();
    System.out.println("simple chat client:" + inChannel.remoteAddress() + "异常");
    cause.printStackTrace();
    ctx.close();
  }
}
