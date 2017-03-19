package proxyDemo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hwdd1 on 2017/2/18 0018.
 */
public class DynamicProxy implements InvocationHandler {
    private Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    public void before(){
        System.out.println("dosomthing before");
    }

    public void after(){
        System.out.println("dosomting after");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("toString")){
            before();
        }
        Object result = method.invoke(obj,args);
        if(method.getName().equals("toString")){
            after();
        }
        return result;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }
}
