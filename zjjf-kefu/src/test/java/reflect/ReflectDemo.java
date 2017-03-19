package reflect;

/**
 * Created by hwdd1 on 2017/2/18 0018.
 */
public class ReflectDemo {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("reflect.User");
            Object obj = cls.newInstance();
            User user = (User) obj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
