package crw.project.netty.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程ServerSocket: 服务端
 */
public class SocketThreadDemo03 {

  public static void main(String[] args) throws IOException {

    ServerSocket serverSocket = new ServerSocket(9999);
    System.out.println("Server Socket start ...");

    while (true) {
      MultiUser multiUser = new MultiUser(serverSocket.accept());
      multiUser.start();
    }
  }
}

class MultiUser extends Thread {

  private Socket socket;

  public MultiUser(Socket client) {

    System.out.println("client hashCode: " + client.hashCode());
    this.socket = client;
  }

  @Override
  public void run() {
    try {
      InputStream inputStream = socket.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

      while (true) {
        String readLine = bufferedReader.readLine();
        System.out.println("client send message: " + readLine);

        printWriter.println("我已经收到数据了");

        printWriter.flush();

      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
