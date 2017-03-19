package connectionPoolTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hwdd1 on 2017/2/24 0024.
 */
public class ConnectionTest {
    static ConnectionPool pool = new ConnectionPool(10);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        int count = 20;//每个线程获取Connection的次数

        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count,got,notGot),"ConnectionRunnerThread");
            thread.start();
        }
        TimeUnit.SECONDS.sleep(1);
        start.countDown();
        end.await();//确保所有ConnectionRunnerThread 结束后,main程序才继续向下执行
        System.out.println("total invoke: "+(threadCount*count));
        System.out.println("got connection: "+got);
        System.out.println("notGot connection: "+notGot);
    }


    static class ConnectionRunner implements Runnable {
        int cout;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int cout, AtomicInteger got, AtomicInteger notGot) {
            this.cout = cout;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (cout > 0) {
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    cout--;
                }
            }
            end.countDown();
        }
    }
}
