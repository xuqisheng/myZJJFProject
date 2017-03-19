package proxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * Created by hwdd1 on 2017/2/18 0018.
 */
public class DynamicConnectionProxy implements InvocationHandler {
    private Connection connection;

    public DynamicConnectionProxy(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Connection.class.isAssignableFrom(proxy.getClass()) && method.getName().equals("close")) {
            DataSource.getInstance().recoveryConnection(connection);
            return null;
        }else{
            return method.invoke(connection,args);
        }
    }

    public Connection getConnectionProxy(){
        return (Connection) Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{Connection.class},this);
    }

    //Object
}
