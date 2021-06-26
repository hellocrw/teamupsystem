package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SynchronizedDemo3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizedDemo3.class);

    private int state = 0;

    public static void main(String[] args) {
        SynchronizedDemo3 synchronizedDemo3 = new SynchronizedDemo3();
        new Thread(() -> {
            synchronizedDemo3.incState();
        }).start();

        new Thread(() -> {
            synchronizedDemo3.incState();
        }).start();

        try {
            Thread.sleep(5000);
            int state = synchronizedDemo3.getState();
            LOGGER.info(state + "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void incState() {
        for (int i = 0; i < 1000; i++) {
            state++;
        }
    }

    public int getState() {
        return this.state;
    }
}
