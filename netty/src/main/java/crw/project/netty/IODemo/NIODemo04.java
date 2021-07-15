package crw.project.netty.IODemo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO 文件拷贝
 * TODO 文件拷贝失败？
 */
public class NIODemo04 {

  public static void main(String[] args) throws IOException {

    FileInputStream inputStream = new FileInputStream("D:\\project\\temp\\teamupsystem\\netty\\1.txt");

    FileChannel inputStreamChannel = inputStream.getChannel();

    FileOutputStream outputStream = new FileOutputStream("D:\\project\\temp\\teamupsystem\\netty\\2.txt");

    FileChannel outputStreamChannel = outputStream.getChannel();

    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    while (true) {

      byteBuffer.clear();   // 清空

      int read = inputStreamChannel.read(byteBuffer);
      if (read == -1) {
        break;
      }
    }

    System.out.println(new String(byteBuffer.array()));

    byteBuffer.flip();
    outputStreamChannel.write(byteBuffer);

    inputStream.close();
    outputStream.close();

  }
}
