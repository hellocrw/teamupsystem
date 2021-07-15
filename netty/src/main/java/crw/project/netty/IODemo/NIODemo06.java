package crw.project.netty.IODemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIODemo06 {

  public static void main(String[] args) throws IOException {

    // 创建ServerSocketChannel --> ServerSocket
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

    // 得到Selector对象
    Selector selector = Selector.open();

    // 绑定端口，服务器监听
    serverSocketChannel.socket().bind(new InetSocketAddress(6666));

    // 设置非阻塞模式
    serverSocketChannel.configureBlocking(false);

    // 把serverSocketChannel 注册到 selector ， 关心的事件时 OP_ACCEPT
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    // 循环等待客户端连接
    while (true) {
      // 等待1s, 如果没有事件发生，就返回
      if (selector.select(2000) == 0) {  // 没有事件发送
        System.out.println("服务器等待了2s，无连接");
        continue;
      }

      // 如果 >0, 表示获取到关注的事件， 获取相关的selectionKey集合
      Set<SelectionKey> selectionKeys = selector.selectedKeys();

      // 遍历集合
      Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

      while (keyIterator.hasNext()) {
        // 获取到selectKey
        SelectionKey selectionKey = keyIterator.next();
        // 根据key对应的通道发生的事件做相应的处理
        if (selectionKey.isAcceptable()) {  // 如果是OP_ACCEPT ，有新的客户端连接
          // 给该客户端生成一个SocketChannel
          SocketChannel socketChannel = serverSocketChannel.accept();

          System.out.println("客户端连接成功，生成一个socketChannel : " + socketChannel.hashCode());

          // 将SocketChannel设置为非阻塞模式
          socketChannel.configureBlocking(false);

          // 将socketChannel注册到 selector中, 关注事件为OP_READ ,同时给socketChannel关联一个Buffer
          socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
        }

        if (selectionKey.isReadable()) { // 如果是OP_READ
          // 通过key 反向获取到对应的channel
          SocketChannel channel = (SocketChannel) selectionKey.channel();
          // 获取到该channel的buffer
          ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();

          channel.read(byteBuffer);

          System.out.println("from 客户端 " + new String(byteBuffer.array()));
        }

        // 手动将集合中移除当前的selectionKey, 防止重复操作
        selectionKeys.remove(selectionKey);
      }
    }
  }
}
