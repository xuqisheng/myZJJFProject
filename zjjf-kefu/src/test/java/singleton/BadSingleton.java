package singleton;

public class BadSingleton {
    private static BadSingleton badSingleton;

    private BadSingleton(){

    };

    public synchronized static BadSingleton getInstance(){
        if(badSingleton==null){
            badSingleton =  new BadSingleton();
        }
        return  badSingleton;
    };
}
