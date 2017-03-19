/**   
* @Title: SendPhoneMsgUtil.java 
* @Package com.corner.scms.utils 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月18日 下午4:27:53 
* @version V1.0   
*/

package com.corner.scms.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.utils.http.HttpUtils;
import com.corner.core.utils.http.ReponseHandlerString;
import com.corner.core.utils.msg.PhoneMsgConfig;

/** 
* @ClassName: SendPhoneMsgUtil 
* @Description:发送短信
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年1月18日 下午4:27:53 
*  
*/

public class SendPhoneMsgUtil {
	private static Logger logger = LoggerFactory.getLogger(SendPhoneMsgUtil.class);
	private static CloseableHttpClient httpclient = HttpUtils.getHttpClient();
	
	
	public static void sendPhoneMsg(String mobile, String content) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(PhoneMsgConfig.weburl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("apikey", PhoneMsgConfig.apikey));
		params.add(new BasicNameValuePair("username", PhoneMsgConfig.username));
		params.add(new BasicNameValuePair("password", PhoneMsgConfig.password));
		params.add(new BasicNameValuePair("mobile", mobile));
		params.add(new BasicNameValuePair("content", content));
		UrlEncodedFormEntity uecf = new UrlEncodedFormEntity(params, "GBK");
		post.setEntity(uecf);
		logger.info("【短信通知】手机号{}，发送短信：{}", mobile, content);
		String resultString = httpclient.execute(post, new ReponseHandlerString());
		logger.info("【短信通知】手机号{}，获取返回：{}", mobile, resultString);
	}
	
	public static void main(String[] args) {
		try {
			sendPhoneMsg("13602741552","dfdsfsa");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
