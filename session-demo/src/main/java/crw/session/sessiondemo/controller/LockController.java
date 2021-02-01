package crw.session.sessiondemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
public class LockController extends Thread{

    private static int i = 0;

    @Value("${server.port}")
    private String port;

    @GetMapping("/lock")
    public String lockTest(){
        System.out.println("start ---------" + Thread.currentThread());
        Boolean flag = true;
        synchronized (LockController.class) {
            try {
                Thread.sleep(5000);
                flag = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "-->" + this.port);
        }

        System.out.println("end --------" + Thread.currentThread());
        System.out.println(i);
        return this.port;
    }

    @Override
    public void run() {
        i = i+1;
    }
}
