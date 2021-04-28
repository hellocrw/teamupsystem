package crw.lock.cloudlock.controller;

import crw.lock.cloudlock.controller.thread.ThreadLock;
import crw.lock.cloudlock.controller.thread.TreadDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

@RestController
@RequestMapping("/lock")
public class LockController {

    private static Logger LOGGER = LoggerFactory.getLogger(LockController.class);

    ThreadLocal<String> threadLocal = new ThreadLocal();

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    private TreadDemo treadDemo;

    @GetMapping("/lock-demo")
    public String lockDemo() {
        return "lockDemo";
    }

    @GetMapping("threadLocal")
    public String threadLocal() {
        String name = Thread.currentThread().getName();
        threadLocal.set(name);
        LOGGER.info("threadLocal: " + threadLocal.get());
        return String.valueOf(Thread.currentThread().getId());
    }

    @GetMapping("lockSupport")
    public String lockSupport() {
        ThreadLock t1 = new ThreadLock();
        t1.start();
        ThreadLock t2 = new ThreadLock();
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        return String.valueOf(System.currentTimeMillis());
    }

    @GetMapping("async")
    public String async() {
        treadDemo.demo();
        LockSupport.unpark(Thread.currentThread());
        return String.valueOf(System.currentTimeMillis());
    }

    @GetMapping("future")
    public String futrue() {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "test";
            }
        });
        executorService.submit(futureTask);

        System.out.println("do otherThing");
        try {
            String resut = futureTask.get(5000, TimeUnit.MICROSECONDS);
            System.out.println("========" + resut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
        return "=======end=========";
    }
}
