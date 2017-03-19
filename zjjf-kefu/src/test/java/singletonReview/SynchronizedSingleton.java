package singletonReview;

/**
 * Created by hwdd1 on 2017/2/21 0021.
 */
public class SynchronizedSingleton {
    private static SynchronizedSingleton synchronizedSingleton;

    private SynchronizedSingleton() {
    }

    public static SynchronizedSingleton getInstance(){
        if(synchronizedSingleton==null){
            synchronized (SynchronizedSingleton.class){
                if(synchronizedSingleton==null){
                    synchronizedSingleton = new SynchronizedSingleton();
                }
            }
        }
        return synchronizedSingleton;
    }
}
