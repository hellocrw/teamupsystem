package crw.project.netty.IODemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIODemo01 {

  private static final Logger logger = LoggerFactory.getLogger(BIODemo01.class);

  private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

  public static void main(String[] args) throws IOException {

    // 创建一个ServerSocket
    ServerSocket serverSocket = new ServerSocket(6666);
    logger.info("服务器启动...");

    while (true) {
      // 监听，等待客户端连接
      Socket socket = serverSocket.accept();    // 线程阻塞
      logger.info("连接到一个客户端========");

      // 创建线程，与之通信
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          // 和客户端通信
          handlerMessage(socket);
        }
      });
    }

  }

  // 编写一个handler方法，和客户端通信
  public static void handlerMessage(Socket socket) {
    byte[] bytes = new byte[1024];

    try {
      InputStream inputStream = socket.getInputStream();

      // 循环读取客户端的数据
      while (true) {
        logger.info("监听读取数据...");
        int read = inputStream.read(bytes);    // 线程阻塞
        if (read != -1) {
          logger.info(new String(bytes, 0, read));
        } else {
          break;
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
