package crw.cloud.cloudstudy.TestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadTest implements Callable<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallableThreadTest.class);
    @Override
    public Integer call() throws Exception {
        LOGGER.info("ThreadName: " + Thread.currentThread().getName());
        return 1;
    }

    public static void main(String[] args) {
        CallableThreadTest ctt = new CallableThreadTest();
        FutureTask futureTask = new FutureTask(ctt);
        new Thread(futureTask,"CallableThreadTest").start();
        try {
            LOGGER.info("result : " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
