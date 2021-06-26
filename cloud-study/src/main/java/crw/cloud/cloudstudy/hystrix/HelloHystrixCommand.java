package crw.cloud.cloudstudy.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class HelloHystrixCommand extends HystrixCommand<String> {

    private final String name;

    public HelloHystrixCommand(String name) {
        //最少配置：指定命令组名(CommandGroup)
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        //依赖逻辑封装在run方法中
        return "Hello " + name + "thread: " + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        HelloHystrixCommand helloHystrixCommand = new HelloHystrixCommand("Synchronous-hystrix");
        String execute = helloHystrixCommand.execute();
        System.out.println("result : " + execute);

        helloHystrixCommand = new HelloHystrixCommand("Asynchronous-hystrix");
        Future<String> future = helloHystrixCommand.queue();
        String result = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println("result=" + result);
        System.out.println("mainThread=" + Thread.currentThread().getName());
    }
}
