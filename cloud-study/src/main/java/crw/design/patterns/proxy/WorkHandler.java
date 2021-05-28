package crw.design.patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WorkHandler implements InvocationHandler {

    private Object object;

    public WorkHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before dynamic invoke");
        System.out.println(proxy.getClass().getName());
        System.out.println(this.object.getClass().getName());
        if (method.getName().equals("work")){
            method.invoke(this.object, args);
            System.out.println("after dynamic invoke");
            return proxy;
        } else {
            System.out.println("after dynamic invoke");
            return method.invoke(this.object, args);
        }
    }
}
