package crw.design.patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyDemo  {

    public static void main(String[] args) {
        People people = new Student();
        InvocationHandler handler = new WorkHandler(people);
        People proxy = (People) Proxy.newProxyInstance(people.getClass().getClassLoader(), people.getClass().getInterfaces(), handler);
        People work = proxy.work("work").work("write code").work("study");
        System.out.println("print return Object");
        System.out.println(work.getClass());

        String time = proxy.time();
        System.out.println("return time: " + time);
    }
}
