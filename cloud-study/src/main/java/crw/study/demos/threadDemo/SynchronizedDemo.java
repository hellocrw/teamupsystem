package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SynchronizedDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizedDemo.class);

    public static void main(String[] args) {
        new Thread(new SyncDemo(), "Thread01").start();
        new Thread(new SyncDemo(), "Thread02").start();
        new Thread(new SyncDemo(), "Thread03").start();
    }

}

class SyncDemo implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncDemo.class);

    @Override
    public void run() {
        synchronized (SynchronizedDemo.class) {
            try {
                LOGGER.info(Thread.currentThread().getName());
                Thread.sleep(1000);
                LOGGER.info(Thread.currentThread().getName() + "end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
