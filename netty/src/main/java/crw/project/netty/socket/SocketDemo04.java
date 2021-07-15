package crw.project.netty.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket Server
 */
public class SocketDemo04 {

  public static void main(String[] args) throws IOException {

    ServerSocket server = new ServerSocket(5678);

    System.out.println("server start...");

    Socket client = server.accept();
    System.out.println("有一个请求client连接: " + client.hashCode());

    BufferedReader clineInputBufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

    PrintWriter clientOutPrintWriter = new PrintWriter(client.getOutputStream());

    while (true) {

      System.out.println("等待接收数据中...");

      String readLine = clineInputBufferedReader.readLine();

      System.out.println("我接收到的数据： " + readLine);

      clientOutPrintWriter.println("ack");

      clientOutPrintWriter.flush();

      if(readLine.equals("end")) {
        break;
      }
    }

    client.close();

  }
}
