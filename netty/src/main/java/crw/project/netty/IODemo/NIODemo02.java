package crw.project.netty.IODemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO Channel和Buffer的使用
 *  文件写数据
 */
public class NIODemo02 {

  private static final Logger logger = LoggerFactory.getLogger(NIODemo02.class);

  public static void main(String[] args) throws IOException {

    String str = "hello world";

    FileOutputStream fileOutputStream = new FileOutputStream("D:\\file01.txt");

    FileChannel fileChannel = fileOutputStream.getChannel();

    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    byteBuffer.put(str.getBytes());

    byteBuffer.flip();

    fileChannel.write(byteBuffer);

    fileOutputStream.close();
  }
}
