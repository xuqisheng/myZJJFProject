/**   
* @Title: MyAuthenticator.java 
* @Package com.corner.scms.utils.mail 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月24日 下午4:18:45 
* @version V1.0   
*/

package com.zjjf.analysis.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @ClassName: MyAuthenticator
 * @Description:邮箱验证器
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年2月24日 下午4:18:45
 * 
 */

public class MyAuthenticator extends Authenticator {
	String userName = null;
	String password = null;

	public MyAuthenticator() {
	}

	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
