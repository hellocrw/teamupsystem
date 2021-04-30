package crw.cloud.cloudstudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/*
 * @Author caorongwu
 * @Description Futrue控制器
 * @Date 1:11 2021/4/30
 **/
@RestController
@RequestMapping("futrue")
public class FutrueController {

    private final static Logger LOGGER = LoggerFactory.getLogger(FutrueController.class);

    @GetMapping("demo")
    public String demo(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // TODO 真正执行任务在这里执行，这里的返回值类型可以任意指定
                // 阻塞
                Thread.sleep(2000);
                LOGGER.info("线程执行1..." + System.currentTimeMillis());
                return Thread.currentThread().getName();
            }
        });
        executorService.submit(futureTask);

        LOGGER.info("执行2..." + System.currentTimeMillis());

        try {
            String result = null;
            try {
                result = futureTask.get(5000, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            LOGGER.info("等待执行完成后的结果result : " + result);
        }finally {
            executorService.shutdown();
        }
        return "excutor : " + System.currentTimeMillis();
    }

    @GetMapping("simpleDemo")
    public String simpleDemo() {
        FutureTask<Integer> futureTask = new FutureTask<>(
                ()-> {
                    LOGGER.info("异步执行...");
                    Thread.sleep(2000);
                    LOGGER.info("异步执行2...");
                    return 1;
                }
        );

        LOGGER.info("执行3...");

        new Thread(futureTask).start();

        try {
            Integer integer = futureTask.get(5000, TimeUnit.MINUTES);
            LOGGER.info("result: " + integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        LOGGER.info("继续其他任务");

        return Thread.currentThread().getThreadGroup().getName();
    }

}
