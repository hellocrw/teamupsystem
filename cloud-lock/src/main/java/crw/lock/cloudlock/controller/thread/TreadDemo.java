package crw.lock.cloudlock.controller.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.LockSupport;

@Component
public class TreadDemo {

    @Async()
    public void demo() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadName: " + Thread.currentThread().getName() +  "===start===: " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.park();
        System.out.println(Thread.currentThread().getName() + "=== end ===" + System.currentTimeMillis());
    }
}
