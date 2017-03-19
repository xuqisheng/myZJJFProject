package DAD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: LambdaDemo
 * @Description: Lambda测试类
 * @author: 杨开泰
 * @date: 2016年 10月04 15:54
 */
public class LambdaDemo {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setId("1");
        user.setAge(25);
        user.setName("jack");
        user.setAddDate(new Date());
        list.add(user);

        /*User user1 = new User();
        user1.setId("2");
        user1.setAge(24);
        user1.setName("Tom");
        user1.setAddDate(new Date());
        list.add(user1);


        User user2 = new User();
        user2.setId("3");
        user2.setAge(19);
        user2.setName("Daniel");
        user2.setAddDate(new Date());
        list.add(user2);*/

        System.out.println(list.size());

        list.removeIf(s->s.getId().equals("1"));
        System.out.println(list.size());

    }
}
