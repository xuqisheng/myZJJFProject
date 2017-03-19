package singletonReview;

/**
 * Created by hwdd1 on 2017/2/21 0021.
 */
public class BadSingleton {
    private static BadSingleton badSingleton;

    private BadSingleton() {
    }

    public synchronized static BadSingleton getInstance(){
        if(badSingleton==null){
            badSingleton = new BadSingleton();
        }
        return badSingleton;
    };
}
