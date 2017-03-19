package reflect;

/**
 * Created by hwdd1 on 2017/2/18 0018.
 */
public class User {
    public int age;
    public int sex;
    public String name;


    public void doSomething() {
        System.out.println("user do something");
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
