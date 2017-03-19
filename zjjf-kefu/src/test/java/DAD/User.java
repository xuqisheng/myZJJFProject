package DAD;

import com.corner.core.utils.StringUtil;

import java.util.Date;

/**
 * @ClassName: User
 * @Description: lamda测试User对象
 * @author: 杨开泰
 * @date: 2016年 10月04 15:51
 */
public class User {
    private String id = StringUtil.getUUID();

    private Date addDate;

    private String name;

    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
