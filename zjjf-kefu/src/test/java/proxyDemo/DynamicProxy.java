package proxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hwdd1 on 2017/2/18 0018.
 */
public class DynamicProxy implements InvocationHandler {
    private Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dosomthing before");
        System.out.println("method: "+ method);
        method.invoke(obj,args);
        System.out.println("dosomthing after");
        return null;
    }
}
