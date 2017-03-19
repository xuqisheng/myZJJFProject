package ThreadDemo;

/**
 * @Title:
 * @Description:
 * @param 
 * @return 
 * @throws
 * @author 汉武大帝
 * @date 2017/2/10 0010 11:46
 */
public class SynchronizedDemo1 {
    private static int i=0;
    
    public synchronized static void foo1(){
        System.out.println("foo1:"+i++);

    }

    public synchronized static void  foo2(){
        System.out.println("foo2:"+i++);
    }
}
