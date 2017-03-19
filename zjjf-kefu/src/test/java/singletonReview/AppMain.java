package singletonReview;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hwdd1 on 2017/2/21 0021.
 */
public class AppMain {
    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        int num = 100;
        final Set<String> set = Collections.synchronizedSet(new HashSet<>());
        final CountDownLatch cd1 = new CountDownLatch(1);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < num; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        cd1.await();
                        //Singleton singleton = Singleton.getInstance();
                        //set.add(singleton.toString());
                        //BadSingleton badSingleton = BadSingleton.getInstance();
                        //set.add(badSingleton.toString());

                        SynchronizedSingleton synchronizedSingleton = SynchronizedSingleton.getInstance();
                        set.add(synchronizedSingleton.toString());

                        //TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.out.println("第一次睡眠,让所有线程都初始化");
        TimeUnit.SECONDS.sleep(5);
        cd1.countDown();
        System.out.println("第二次是吗,让所有线程都开始运行");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(set.size());
        for (String str :
                set) {
            System.out.println(str);
        }
        Long end = System.currentTimeMillis();
        System.out.println("程序运行时间: "+ (end-start) + " ms");
        //TimeUnit.SECONDS.sleep(10);
        service.shutdown();
    }
}
