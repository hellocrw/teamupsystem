package crw.lock.cloudlock.controller.thread;

import java.util.concurrent.locks.LockSupport;

public class ThreadLock extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===start===" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.park();
        System.out.println("=== end ===" + System.currentTimeMillis());
    }
}
