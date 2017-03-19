import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.corner.auth.beans.vo.AuthVo;
import com.corner.auth.dao.AdminMapper;
import com.corner.auth.dao.mg.AuthMgMapper;
import com.corner.auth.service.AuthService;


/**   
 * @Title: Demo.java 
 * @Package  
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn   
 * @date 2015年12月15日 下午4:48:20 
 * @version V1.0   
 */

/**
 * @ClassName: Demo
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2015年12月15日 下午4:48:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-config.xml" })
public class Demo {
	@Autowired
	AuthService authService;
	@Test
	public void getPingYin() throws Exception {
		System.out.println("11111111111");
		List<AuthVo> authVos = authService.getAuthByAppIdOrRoleId(null, null);
		System.out.println("22222222222");
	}
}
