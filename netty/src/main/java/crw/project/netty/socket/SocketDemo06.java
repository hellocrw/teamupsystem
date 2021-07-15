package crw.project.netty.socket;

import java.io.*;
import java.net.Socket;

/**
 * Socket Client
 */
public class SocketDemo06 {

  public static void main(String[] args) throws IOException {

    Socket socket = new Socket("localhost", 8888);
    // 获取输出流，向服务器端发送信息
    OutputStream outputStream = socket.getOutputStream();  // 获取输出流

    PrintWriter printWriter = new PrintWriter(outputStream); // 封装为打印流

    printWriter.write("用户：admin ; 密码： 123");

    printWriter.flush();

    socket.shutdownOutput();

    // 获取输入流，读取服务端的响应消息
    InputStream inputStream = socket.getInputStream();

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    String info = null;

    if ((info = bufferedReader.readLine())!= null) {
      System.out.println("得到服务端的响应消息： " + info);
    }

    socket.shutdownInput();

    bufferedReader.close();
    inputStream.close();
    printWriter.close();
    outputStream.close();
    socket.close();

  }
}
