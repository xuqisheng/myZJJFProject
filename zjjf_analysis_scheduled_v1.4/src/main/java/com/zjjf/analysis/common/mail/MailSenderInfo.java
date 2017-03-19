/**   
* @Title: MailSenderInfo.java 
* @Package com.corner.scms.utils.mail 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月24日 下午3:39:57 
* @version V1.0   
*/

package com.zjjf.analysis.common.mail;

import java.util.Properties;

/**
 * @ClassName: MailSenderInfo
 * @Description:发送邮件需要使用的基本信息
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年2月24日 下午3:39:57
 * 
 */

public class MailSenderInfo {
	// 发送邮件的服务器的IP和端口
	private String mailServerHost = PropertiesCacheUtil.getValue("mail.smtp.host", "conf/mail/mail.properties");
	private String mailServerPort = PropertiesCacheUtil.getValue("mail.smtp.port", "conf/mail/mail.properties");
	// 邮件发送者的地址
	private String fromAddress = PropertiesCacheUtil.getValue("fromAddress", "conf/mail/mail.properties");
	// 邮件接收者的地址
	private String toAddress = PropertiesCacheUtil.getValue("toAddress", "conf/mail/mail.properties");;
	// 登录邮件发送服务器的用户名和密码
	private String userName = PropertiesCacheUtil.getValue("userName", "conf/mail/mail.properties");
	private String password = PropertiesCacheUtil.getValue("password", "conf/mail/mail.properties");
	// 是否需要身份验证
	private boolean validate = true;
	// 邮件主题
	private String subject;
	// 邮件的文本内容
	private String content;

	/**
	 * 
	 * @Title: getProperties @Description:获得邮件会话属性 @param @return @return
	 * Properties 返回类型 @author 杨开泰 yangkaitai@izjjf.cn @throws
	 */
	public Properties getProperties() {
		Properties p = new Properties();

		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}
}
