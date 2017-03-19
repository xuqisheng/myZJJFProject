/**  
 * @Title: package-info.java
 * @Package com.corner.mobile.common.utils.http
 * @Description: TODO(用一句话描述该文件做什么)
 * @author luke    
 * @email   luke@mibodoctor.com  
 * @date 2015年3月8日 下午4:43:20
 * @version V1.0  
 */
/**
 * @ClassName: package-info
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author luke
 * @email   luke@mibodoctor.com  
 * @date 2015年3月8日 下午4:43:20
 *
 */
package com.corner.salesman.common.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

	private static CloseableHttpClient httpclient = null;
	
	public static  CloseableHttpClient getHttpClient(){
		return httpclient;
	}

	static {
		if (httpclient == null) {
			cm = new PoolingHttpClientConnectionManager();
		}
		cm.setMaxTotal(HttpEVConfig.CM_MaxTotal);
		cm.setDefaultMaxPerRoute(HttpEVConfig.CM_MaxPerRoute);
		if (httpclient == null) {
			httpclient = HttpClients.custom().setConnectionManager(cm).build();
		}
	}

	/**
	 * 
	 * @Title: sendGetRequest
	 * @Author luke luke@mibodoctor.com
	 * @Description: TODO(默认返回体String)
	 * @param @param url
	 * @param @param sParaTemp
	 * @param @param type POST / GET
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String buildRequest(String url, Map<String, String> sParaTemp, String type) {
		String resultString = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (sParaTemp != null && !sParaTemp.isEmpty()) {
			for (Entry<String, String> entry : sParaTemp.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}	
		}else{
			logger.info("【HTTPCLIENT】发送get请求：{}",url);
			HttpGet get = new HttpGet(url);
			try {
				resultString = httpclient.execute(get, new ReponseHandlerString());
				logger.info("【HTTPCLIENT】发送get请求返回：{}",resultString);
			} catch (ClientProtocolException e) {
				logger.error("【HTTPCLIENT】ClientProtocolException:{}",e);
			} catch (IOException e) {
				logger.error("【HTTPCLIENT】IOException:{}",e);
			}
		}
		if(HttpEVConfig.HTTP_POST.equals(type)){
			try {
				logger.info("【HTTPCLIENT】发送post请求：{}",url);
				HttpPost post = new HttpPost(url);
				post.setEntity(new UrlEncodedFormEntity(params,Consts.UTF_8));
				resultString = httpclient.execute(post, new ReponseHandlerString());
				logger.info("【HTTPCLIENT】发送post请求返回：{}",resultString);
			} catch (UnsupportedEncodingException e) {
				logger.error("【HTTPCLIENT】UnsupportedEncodingException:{}",e);
			} catch (ClientProtocolException e) {
				logger.error("【HTTPCLIENT】ClientProtocolException:{}",e);
			} catch (IOException e) {
				logger.error("【HTTPCLIENT】IOException:{}",e);
			}
		}else {
			try {
				URI uri = new URIBuilder(url).setParameters(params).build();
				logger.info("【HTTPCLIENT】发送get请求：{}",uri.toString());
				HttpGet get = new HttpGet(uri);
				resultString = httpclient.execute(get, new ReponseHandlerString());
				logger.info("【HTTPCLIENT】发送get请求返回：{}",resultString);
			} catch (UnsupportedEncodingException e) {
				logger.error("【HTTPCLIENT】UnsupportedEncodingException:{}",e);
			} catch (ClientProtocolException e) {
				logger.error("【HTTPCLIENT】ClientProtocolException:{}",e);
			} catch (IOException e) {
				logger.error("【HTTPCLIENT】IOException:{}",e);
			} catch (URISyntaxException e) {
				logger.error("【HTTPCLIENT】IOException:{}",e);
			}
		}
		return resultString;
	}

}