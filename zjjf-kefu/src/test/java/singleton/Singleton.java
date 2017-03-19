package singleton;

/**
 * @Title:
 * @Description:
 * @param 
 * @return 
 * @throws
 * @author 汉武大帝
 * @date 2017/2/17 0017 10:18
 */
public class Singleton {
    private static Singleton singleton;

    private Singleton() {
        this.singleton = singleton;
    }

    public static Singleton getInstance(){
       if(singleton==null){
           singleton =  new Singleton();
       }
       return singleton;
    }
}
