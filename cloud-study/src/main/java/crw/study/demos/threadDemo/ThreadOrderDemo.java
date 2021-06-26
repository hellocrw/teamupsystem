package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过yield实现线程同步
 *      注意： wait()方法只能用在同步方法或者同步代码块中，这里不能用wait去阻塞线程
 */
public class ThreadOrderDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadOrderDemo.class);

    private static volatile Integer state = 1;

    public static void main(String[] args) {
        new Thread(new R1()).start();
        new Thread(new R2()).start();
        new Thread(new R3()).start();
    }

    static class R1 extends Thread {
        @Override
        public void run() {
            try {
                while (state != 1){
                    Thread.yield();
                }
                Thread.sleep(1000);
                LOGGER.info(Thread.currentThread().getName() + this.getClass().getSimpleName());

                state = 2;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class R2 extends Thread {
        @Override
        public void run() {
            try {
                while (state != 2){
                    Thread.yield();
                }
                Thread.sleep(1000);
                LOGGER.info(Thread.currentThread().getName() + this.getClass().getSimpleName());
                state = 3;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class R3 extends Thread {
        @Override
        public void run() {
            try {
                while (state != 3){
                    Thread.yield();
                }
                Thread.sleep(1000);
                LOGGER.info(Thread.currentThread().getName() + this.getClass().getSimpleName());
                state = 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
