package singleton;

/**
 * Created by hwdd1 on 2017/2/17 0017.
 */
public class InnerSingleton {
    private InnerSingleton(){

    };

    private static class SingletonInstance{
        private static InnerSingleton innerSingleton = new InnerSingleton();
    }

    public static InnerSingleton getInstance(){
        return SingletonInstance.innerSingleton;
    }
}
