package connectionPoolTest;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by hwdd1 on 2017/2/24 0024.
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    //初始化连接池
    public ConnectionPool(int initialSize) {
        if(initialSize>0){
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection){
       if(connection!=null){
           synchronized (pool){
               //连接释放后需要通知,这样其他消费者能够感知到连接池中已经归还了一个连接
               pool.addLast(connection);
               pool.notifyAll();
           }
       }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            if(mills<=0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis()+mills;
                long remaing = mills;
                while (pool.isEmpty()&&remaing>0){
                    pool.wait(remaing);
                    remaing = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
