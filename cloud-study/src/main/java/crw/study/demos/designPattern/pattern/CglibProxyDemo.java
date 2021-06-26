package crw.study.demos.designPattern.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyDemo implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CglibProxyDemo.class);

    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        logger.info(Thread.currentThread().getName() + " 代理前的操作");
        Object invoke = method.invoke(target, args);
        logger.info(Thread.currentThread().getName() + " 代理后的操作");
        return invoke;
    }

    public Object getCglibProxy(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public static void main(String[] args) {
        CglibProxyDemo cglibProxyDemo = new CglibProxyDemo();
        TargetDemo02 cglibProxy = (TargetDemo02) cglibProxyDemo.getCglibProxy(new TargetDemo02());
        cglibProxy.targetMethod();
    }
}

class TargetDemo02 {

    private static final Logger logger = LoggerFactory.getLogger(TargetDemo.class);

    public void targetMethod() {
        logger.info("目标对象方法执行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


