package crw.project.netty.IODemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.IntBuffer;

/**
 * NIO Buffer的使用
 */
public class NIODemo01 {

  private static final Logger logger = LoggerFactory.getLogger(NIODemo01.class);

  public static void main(String[] args) {
    IntBuffer intBuffer = IntBuffer.allocate(5);
    // 向intBuffer存放数据
    for (int i = 0; i < intBuffer.capacity(); i++) {
      intBuffer.put(i * 2);
    }

    // 读取数据， flip读写切换
    intBuffer.flip();

    while (intBuffer.hasRemaining()) {
      logger.info("value : {}", intBuffer.get());
    }
  }

}
