package proxyDemo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hwdd1 on 2017/2/18 0018.
 */
public class DynamicProxy2 implements InvocationHandler {
    private Object obj;

    public DynamicProxy2(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //System.out.println("123");
        //method.invoke(obj,args);
        System.out.println("before");
        Method objMethod = obj.getClass().getDeclaredMethod(method.getName(),method.getParameterTypes());
        objMethod.setAccessible(true);
        Object result = objMethod.invoke(obj,args);
        return result;
    }

    public static void main(String[] args) {
        TestInterface object = (TestInterface) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class[]{TestInterface.class},new DynamicProxy2(new TestClass()));
         object.method1();
         object.method2();
         object.method3();
    }
}
