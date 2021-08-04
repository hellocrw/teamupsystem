package crw.xw;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderDemo {
  public static void main(String[] args) throws Exception {
    while (true) {
      File filePath = new File("");
      String path = "file:" + filePath.getAbsoluteFile() + "\\src\\main\\java\\crw\\xw\\";
      URL url = new URL(path);
      URLClassLoader loader = new URLClassLoader(new URL[] {url});
      Class<?> methtClass = loader.loadClass("crw.xw.LoaderDemo");
      Object obj = methtClass.newInstance();
      methtClass.getDeclaredMethod("name").invoke(obj);
      System.out.println(loader);
      Thread.sleep(3000);
    }
  }
}
