package crw.design.patterns.proxyDemo2;

import java.lang.reflect.Proxy;

public class ProxyDemo2 {
    public static void main(String[] args) {
        People programmer = new Programmer();
        WorkHandler workHandler = new WorkHandler(programmer);
        People proxy = (People) Proxy.newProxyInstance(programmer.getClass().getClassLoader(), programmer.getClass().getInterfaces(), workHandler);
        proxy.work();
    }
}
