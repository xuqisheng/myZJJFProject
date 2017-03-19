package ThreadDemo;

import java.util.concurrent.TimeUnit;

/**
 * Created by hwdd1 on 2017/2/28 0028.
 */
public class LiftOff implements Runnable {

    @Override
    public void run() {
        System.out.println("doSomething");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
