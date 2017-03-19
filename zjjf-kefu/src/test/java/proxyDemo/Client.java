package proxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by hwdd1 on 2017/2/18 0018.
 */
public class Client {
    public static void main(String[] args) {
        RealSubject subject = new RealSubject();
        InvocationHandler handler = new DynamicProxy(subject);
        Subject subject1 = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),subject.getClass().getInterfaces(),handler);

        System.out.println(subject1.getClass().getName());
        subject1.rent();
        subject1.hell("world");

    }
}
