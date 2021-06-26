package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Synchronized的应用
 */
public class SynchronizedDemo2 implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizedDemo2.class);

    private static int i = 0;

    public synchronized void increase() {
        i++;
    }

    public static void increase2(){
        i++;
    }

    @Override
    public void run() {
        synchronized (SynchronizedDemo2.class) {
            for (int j = 0; j < 100; j++) {
                LOGGER.info(Thread.currentThread().getName());
                // this.increase();
                increase2();
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo2 synchronizedDemo1 = new SynchronizedDemo2();
        SynchronizedDemo2 synchronizedDemo2 = new SynchronizedDemo2();
        // 作用在同一对象中
        new Thread(synchronizedDemo1, "Thread01").start();
        new Thread(synchronizedDemo2, "Thread02").start();
        try {
            Thread.sleep(5000);
            LOGGER.info(i + "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
