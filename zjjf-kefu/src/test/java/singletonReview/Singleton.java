package singletonReview;

/**
 * Created by hwdd1 on 2017/2/21 0021.
 */
public class Singleton {
    private static Singleton singleton;

    private Singleton() {
        this.singleton = singleton;
    }

    public static Singleton getInstance(){
        if(singleton==null){
            singleton=new Singleton();
        }
        return singleton;
    }
}
