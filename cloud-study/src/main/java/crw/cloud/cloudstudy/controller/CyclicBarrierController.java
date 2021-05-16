package crw.cloud.cloudstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RequestMapping("cyclicBarrier")
@RestController
public class CyclicBarrierController {

    @GetMapping("demo")
    public String demo() {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "完成最后任务");
            }
        });
        for (int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }

        return Thread.currentThread().getName();
    }

    public class TaskThread extends Thread{
        CyclicBarrier barrier;
        public TaskThread(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                try {
                    Thread.sleep(1000);
                    System.out.println(getName() + "到达A");
                    barrier.await();
                    System.out.println(getName() + "冲破A");
                    Thread.sleep(2000);

                    System.out.println(getName() + "到达B");
                    barrier.await();
                    System.out.println(getName() + "冲破B");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("demo2")
    public String demo2(){
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        ThreadFactory factory = r -> new Thread(r, "test-thread-pool");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, queue, factory);
        while (true){
            executor.submit(() -> {
                System.out.println(queue.size());
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            return "demo2";
        }
    }
}
