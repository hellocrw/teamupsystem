package crw.project.netty.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Socket Client
 */
public class SocketDemo03 {

  public static void main(String[] args) throws IOException {

    System.out.println("client start...");

    Socket socket = new Socket("localhost", 5678);

    PrintWriter clientOutPrintWriter = new PrintWriter(socket.getOutputStream());

    BufferedReader clientInputBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));


    while (true) {
      System.out.println("请输入要发送的内容：");

      String str = wt.readLine();

      clientOutPrintWriter.println(str);

      clientOutPrintWriter.flush();

      System.out.println("server return :" + clientInputBufferedReader.readLine());

      if (str.equals("end")){
        break;
      }

    }

    socket.close();
  }
}
