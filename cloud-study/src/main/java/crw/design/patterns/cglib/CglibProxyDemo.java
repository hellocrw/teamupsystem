package crw.design.patterns.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyDemo implements MethodInterceptor {

    private Object target;   // need proxy target object

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB proxy , listening start");
        Object invoke = method.invoke(target, objects);
        System.out.println("CGLIB proxy , listening end");
        return invoke;
    }

    public Object getCglibProxy(Object objectTarget) {
        // Assignment target object
        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);
        Object result = enhancer.create();
        return result;
    }
    public static void main(String[] args) {
        CglibProxyDemo cglibProxy = new CglibProxyDemo();
        UserManager userManager = (UserManager) cglibProxy.getCglibProxy(new UserManagerImpl());
        userManager.delUser("admin");
    }
}
