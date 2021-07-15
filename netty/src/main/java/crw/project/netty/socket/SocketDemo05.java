package crw.project.netty.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket Server
 */
public class SocketDemo05 {

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(8888);

      System.out.println("SocketServer start...");

      Socket clientSocket = serverSocket.accept();

      InputStream clientInputStream = clientSocket.getInputStream();  // 获取客户端的输入流

      InputStreamReader cliInputStreamReader = new InputStreamReader(clientInputStream);  // 将客户端的字符流转为字节流

      BufferedReader bufferedReader = new BufferedReader(cliInputStreamReader);  // 为流添加buffer缓冲

      String info = null;

      while ((info = bufferedReader.readLine()) != null) {

        System.out.println(" 获取到客户端的数据: " + info);
      }

      clientSocket.shutdownInput();  // 关闭客户端的输入流

      OutputStream clientOutputStream = clientSocket.getOutputStream();  // 获取客户端的输出流

      PrintWriter clientPrintWriter = new PrintWriter(clientOutputStream);  // 封装为打印流

      clientPrintWriter.write("欢迎你！");

      clientPrintWriter.flush();   // 刷新缓冲

      clientSocket.shutdownOutput();  // 关闭输出流
      clientPrintWriter.close();
      clientOutputStream.close();
      bufferedReader.close();
      cliInputStreamReader.close();
      clientInputStream.close();
      clientSocket.close();
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
