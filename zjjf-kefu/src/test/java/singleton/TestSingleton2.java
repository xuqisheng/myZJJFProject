package singleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleton2 {
    public static void main(String[] args) throws InterruptedException {
        int num = 100;
        final Set<String> set = Collections.synchronizedSet(new HashSet<>());
        final CountDownLatch cd1 = new CountDownLatch(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < num; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        cd1.await();
                        //Singleton singleton = Singleton.getInstance();
                        //set.add(singleton.toString());
                        //BadSingleton badSingleton = BadSingleton.getInstance();
                        //set.add(badSingleton.toString());
                        //SynchronizedSingleton synchronizedSingleton = SynchronizedSingleton.getInstance();
                        //set.add(synchronizedSingleton.toString());
                        InnerSingleton innerSingleton = InnerSingleton.getInstance();
                        set.add(innerSingleton.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.out.println("第一次");
        Thread.sleep(5000);
        cd1.countDown();
        System.out.println("第二次");
        Thread.sleep(5000);
        System.out.println("----多线程下获取到的单例实例----");
        System.out.println(set.size());
        for (String string :
                set) {
            System.out.println(string);
        }
        executorService.shutdown();
    }
}
