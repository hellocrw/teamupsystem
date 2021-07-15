package crw.project.netty.socket;

import java.io.*;
import java.net.Socket;

/**
 * socket client: Socket客户端
 */
public class SocketThreadDemo01 {
  public static void main(String[] args) throws IOException {
    Socket socket = new Socket("localhost", 9999);
    System.out.println("启动客户端...");
    OutputStream outputStream = socket.getOutputStream();
    PrintWriter writer = new PrintWriter(outputStream);
    InputStream inputStream = socket.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    InputStreamReader in = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(in);
    String str = null;
    System.out.println("请输入数据：");
    while ((str = reader.readLine()) != null) {
      System.out.println(str);
      writer.println(str);
      writer.flush();

      String readLine = bufferedReader.readLine();
      System.out.println("服务器发送过来的响应消息：" + readLine);
    }
    reader.close();
    inputStreamReader.close();
  }
}
