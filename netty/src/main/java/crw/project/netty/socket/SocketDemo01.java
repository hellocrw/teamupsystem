package crw.project.netty.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端：Client
 */
public class SocketDemo01 {
  public static void main(String[] args) {
    try {
      Socket socket = new Socket("localhost", 80);
      // 系统标准输入设备构造BufferReader对象
      BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
      // Socket对象得到输入流，并构造PrintWriter对象
      PrintWriter os = new PrintWriter(socket.getOutputStream());
      // Socket对象得到输入流，并构造相应的BufferedReader对象
      BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // 系统标准输入一段字符串
      String readLine = sin.readLine();
      while (!readLine.equals("bye")){
        // 从系统标准输入读入的字符串输入到server
        os.println(readLine);
        // 刷新输出流，使Server发上收到该字符串
        os.flush();
        // 从系统标准输出上打印到标准输出上
        System.out.println("client say: " + readLine);
        // 从Server读入字符串，并打印到标准输出上
        System.out.println("Server say: " + is.readLine());
        // 从系统标准输入读入字符串
        readLine = sin.readLine();
      }
      os.close();
      is.close();
      socket.close();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
