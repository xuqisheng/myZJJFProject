package singleton;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleton {

    volatile boolean lock;

    public boolean isLock(){
      return lock;
    };

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 10;
        final Set<String> set = Collections.synchronizedSet(new HashSet<String>());
        final TestSingleton testSingleton = new TestSingleton();
        testSingleton.setLock(true);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < num; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        if(!testSingleton.isLock()){
                           Singleton singleton = Singleton.getInstance();
                           set.add(singleton.toString());
                           break;
                        }
                    }
                }
            });
        }
        System.out.println("第一次");
        Thread.sleep(5000);
        testSingleton.setLock(false);
        System.out.println("第二次");
        Thread.sleep(5000);

        System.out.println("----并发情况下取到的实例----");
        System.out.println(set.size());
        for (String string:
             set) {
            System.out.println(string);
        }
    }



}
