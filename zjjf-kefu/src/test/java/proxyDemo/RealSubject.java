package proxyDemo;

/**
 * Created by hwdd1 on 2017/2/18 0018.
 */
public class RealSubject implements Subject {
    @Override
    public void rent() {
        System.out.println("I want to rent my house");
    }

    @Override
    public void hell(String str) {
        System.out.println("hello: "+ str);
    }
}
