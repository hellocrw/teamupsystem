package crw.study.demos.designPattern.pattern;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyDemo implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(JDKProxyDemo.class);

    private Object target;

    public JDKProxyDemo(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("代理中获取到的参数：" + JSON.toJSONString(args));
        logger.info("代理中获取到的方法：" + method.getName());
        logger.info(Thread.currentThread().getName() + "代理前的操作");
        Thread.sleep(2000);
        Object invoke = method.invoke(target, args);
        Thread.sleep(2000);
        logger.info(Thread.currentThread().getName() + "代理后的操作");
        return invoke;
    }

    public static void main(String[] args) {
        TargetInterface targetInterface = new TargetDemo();
        JDKProxyDemo jdkProxyDemo = new JDKProxyDemo(targetInterface);
        TargetInterface proxyInstance = (TargetInterface) Proxy.newProxyInstance(targetInterface.getClass().getClassLoader(), targetInterface.getClass().getInterfaces(), jdkProxyDemo);
        proxyInstance.proxyMethod("参数1", "参数2");
    }
}

interface TargetInterface {
    void proxyMethod(String arg0, String arg1);
}

class TargetDemo implements TargetInterface {

    private static final Logger logger = LoggerFactory.getLogger(TargetDemo.class);

    @Override
    public void proxyMethod(String arg0, String arg1) {
        logger.info(Thread.currentThread().getName() + " 目标方法的实现 " + this.getClass().getSimpleName());
    }
}
