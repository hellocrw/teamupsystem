package crw.project.netty.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端ServerSocket
 */
public class SocketThreadDemo02 {
  public static void main(String[] args) throws IOException {
    System.out.println("服务器启动...");
    ServerSocket socket = new ServerSocket(9000);
    Socket client = socket.accept();
    System.out.println("有client连接: " + client.hashCode());
    InputStream inputStream = client.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader reader = new BufferedReader(inputStreamReader);

    OutputStream outputStream = client.getOutputStream();
    PrintWriter writer = new PrintWriter(outputStream);

    while (true) {

      String readLine = reader.readLine();
      System.out.println("接收到的数据： " + readLine);

      writer.println("我收到了");
      writer.flush();

    }
  }
}
