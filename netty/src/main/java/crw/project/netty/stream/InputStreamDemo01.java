package crw.project.netty.stream;

import java.io.*;

public class InputStreamDemo01 {

  private static void saveFile() throws IOException {

    File file = new File("D:" + File.separator + "inputStream.txt");

    FileOutputStream outputStream = new FileOutputStream(file, true);

    outputStream.write("hello world".getBytes());

    outputStream.flush();

    outputStream.close();
  }

  private static void getFileData() throws IOException {

    File file = new File("D:" + File.separator + "inputStream.txt");

    FileInputStream inputStream = new FileInputStream(file);

    byte[] buf = new byte[(int) file.length()];

    int len = inputStream.read(buf);

    inputStream.close();

    System.out.println(new String(buf, 0, len));
  }

  public static void main(String[] args) throws IOException {
    // saveFile();
    getFileData();
  }
}
