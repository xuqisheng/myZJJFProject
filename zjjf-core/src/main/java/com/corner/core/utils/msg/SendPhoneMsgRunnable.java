package com.corner.core.utils.msg;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.utils.http.HttpUtils;
import com.corner.core.utils.http.ReponseHandlerString;

public class SendPhoneMsgRunnable implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(SendPhoneMsgRunnable.class);

	private String mobile;
	private String content;
	private String apiKey;
	private String username;
	private String password;
	private CloseableHttpClient httpclient = HttpUtils.getHttpClient();

	public SendPhoneMsgRunnable(String mobile, String content) {
		this.mobile = mobile;
		this.content = content;
		this.apiKey = PhoneMsgConfig.apikey;// 通知类型类短信
		this.username = PhoneMsgConfig.username;// 通知类型类短信
		this.password = PhoneMsgConfig.password;// 通知类型类短信
	}

	public SendPhoneMsgRunnable(String mobile, String content, String apiKey, String username, String password) {
		this.mobile = mobile;
		this.content = content;
		this.apiKey = apiKey;
		this.username = username;
		this.password = password;
	}

	@Override
	public void run() {
		boolean success = false;
		int count = 0;
		while (!success && count++ < PhoneMsgConfig.retrySeconds) {
			try {
				HttpPost post = new HttpPost(PhoneMsgConfig.weburl);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("apikey", this.apiKey));
				params.add(new BasicNameValuePair("username", this.username));
				params.add(new BasicNameValuePair("password", this.password));
				params.add(new BasicNameValuePair("mobile", this.mobile));
				params.add(new BasicNameValuePair("content", this.content));
				UrlEncodedFormEntity uecf = new UrlEncodedFormEntity(params, "GBK");
				post.setEntity(uecf);
				logger.info("【短信通知】手机号{}，发送短信：{}", this.mobile, this.content);
				String resultString = httpclient.execute(post, new ReponseHandlerString());
				logger.info("【短信通知】手机号{}，获取返回：{}", this.mobile, resultString);
				success = true;
			} catch (Exception ex) {
				success = false;
				logger.error("【短信通知】短信通知#{}:{}异常，信息{}", this.mobile, this.content, ex);
			} finally {
				if (!success) {
					try {
						logger.info("【短信通知】通知异常线，程休眠60秒后重新发送，手机号：{}", mobile);
						Thread.sleep(PhoneMsgConfig.retrySeconds * 1000);
					} catch (InterruptedException e) {
						logger.error("【短信通知】短信通知线程睡眠异常{} ,手机号：{}", e, mobile);
					}
				}
			}
		}

	}

}
