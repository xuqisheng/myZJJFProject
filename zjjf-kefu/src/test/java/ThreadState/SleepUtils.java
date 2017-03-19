package ThreadState;

import java.util.concurrent.TimeUnit;

/**
 * Created by hwdd1 on 2017/2/20 0020.
 */
public class SleepUtils {
    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
