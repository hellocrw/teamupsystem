package crw.study.demos.threadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读写锁的简单实现
 *      读读共存， 读写不能共存， 写写不能共存
 */
public class ReadWriteLockDemo {

    private static final Logger logger = LoggerFactory.getLogger(ReadWriteLockDemo.class);

    private static int writeRequest = 0;

    private static int read = 0;

    private static int write = 0;

    public void readLock() throws InterruptedException {
        if (write > 0 || writeRequest > 0) {
            wait();
        }
        read++;
    }

    public void unReadLock() {
        read--;
        notifyAll();
    }

    public void writeRequest() throws InterruptedException {
        writeRequest++;
        if (read > 0 || write > 0){
            wait();
        }
        writeRequest--;
        write++;
    }

    public void unWriteLock() {
        write--;
        notifyAll();
    }
}
