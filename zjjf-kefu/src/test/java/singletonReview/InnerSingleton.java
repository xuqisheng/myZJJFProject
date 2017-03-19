package singletonReview;

/**
 * Created by hwdd1 on 2017/2/21 0021.
 */
public class InnerSingleton {
    private InnerSingleton() {
    }

    private static class SingletonInstance{
        private static InnerSingleton innerSingleton = new InnerSingleton();
    }

    public static InnerSingleton getInstance(){
        return SingletonInstance.innerSingleton;
    }
}
