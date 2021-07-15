package crw.project.netty.IODemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO Channel 和 Buffer 的使用
 *   读取文件数据
 */
public class NIODemo03 {

  private static final Logger logger = LoggerFactory.getLogger(NIODemo03.class);

  public static void main(String[] args) throws IOException {

    File file = new File("D:\\file01.txt");

    FileInputStream fileInputStream = new FileInputStream(file);

    FileChannel fileChannel = fileInputStream.getChannel();

    ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

    fileChannel.read(byteBuffer);

    logger.info("result: {}", new String(byteBuffer.array()));

    fileInputStream.close();
  }

}
