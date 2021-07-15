package crw.project.netty.IODemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO 文件拷贝
 * transferFrom文件拷贝
 */
public class NIODemo05 {

  public static void main(String[] args) throws IOException {

    FileInputStream inputStream = new FileInputStream("D:\\1.txt");

    FileChannel inputStreamChannel = inputStream.getChannel();

    FileOutputStream outputStream = new FileOutputStream("D:\\2.txt");

    FileChannel outputStreamChannel = outputStream.getChannel();

    outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());

    inputStream.close();
    outputStream.close();

  }
}
