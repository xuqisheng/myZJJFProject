/**   
* @Title: SimpleMailSender.java 
* @Package com.corner.scms.utils.mail 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月24日 下午4:20:40 
* @version V1.0   
*/

package com.corner.scms.utils.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: SimpleMailSender
 * @Description:不带附件邮件发送器
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年2月24日 下午4:20:40
 * 
 */

public class SimpleMailSender {
	private static final Logger logger = LoggerFactory.getLogger(SimpleMailSender.class);

	public boolean sendTextMail(MailSenderInfo mailInfo){
		try {
			// 判断是否需要身份认证
			MyAuthenticator authenticator = null;
			Properties pro = mailInfo.getProperties();
			if (mailInfo.isValidate()) {
				// 如果需要身份认证，则创建一个密码验证器
				authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
			}
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
			Session sendMailSession = Session.getDefaultInstance(pro, authenticator);

			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
		} catch (Exception e) {
			logger.error(e.toString());
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		MailSenderInfo mailInfo = new MailSenderInfo();
		/*mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("longwu@izjjf.cn");
		mailInfo.setPassword("ZhangKun991218");// 您的邮箱密码
		mailInfo.setFromAddress("longwu@izjjf.cn");*/
		mailInfo.setToAddress("yangkaitai@izjjf.cn");
		//mailInfo.setValidate(true);
		//mailInfo.setToAddress("jishu@izjjf.cn");
		mailInfo.setSubject("[合单异常]");
		mailInfo.setContent("]\niii");
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		for (int i = 0; i < 20; i++) {
			sms.sendTextMail(mailInfo);
		}

	}

}
