package demo.crw.rocketmqversion3.client;

import java.io.OutputStream;
import java.net.Socket;

public class NettyClientDemo03 {
    public static void main(String[] args) throws Exception{
        Socket client = new Socket("localhost", 9999);
        OutputStream outputStream = client.getOutputStream();
        try{
            String message = "hello crw！";
            outputStream.write(message.getBytes());
            outputStream.flush();
        }finally {
            outputStream.close();
            if (client != null){
                client.close();
            }
        }
    }
}
