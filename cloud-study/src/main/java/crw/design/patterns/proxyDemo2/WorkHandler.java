package crw.design.patterns.proxyDemo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WorkHandler implements InvocationHandler {

    private Object object;

    public WorkHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke me write code before....");
        Object invoke = method.invoke(object, args);
        System.out.println("invoke me wirte code finish...");
        return invoke;
    }
}
