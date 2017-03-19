package com.corner.core.utils.message.umengpush;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.utils.http.HttpUtils;

public abstract class UmengNotification {
	
	private static final Logger logger = LoggerFactory.getLogger(UmengNotification.class);
	
	// This JSONObject is used for constructing the whole request string.
	protected final JSONObject rootJson = new JSONObject();

	// This object is used for sending the post request to Umeng
	protected HttpClient client = HttpUtils.getHttpClient();

	// The host
	protected static final String host = "http://msg.umeng.com";

	// The upload path
	protected static final String uploadPath = "/upload";

	// The post path
	protected static final String postPath = "/api/send";

	// The app master secret
	protected String appMasterSecret;

	// The user agent
	protected final String USER_AGENT = "Mozilla/5.0";

	// Keys can be set in the root level
	protected static final HashSet<String> ROOT_KEYS = new HashSet<String>(Arrays.asList(new String[] { "appkey", "timestamp", "type", "device_tokens", "alias", "alias_type",
			"file_id", "filter", "production_mode", "feedback", "description", "thirdparty_id" }));

	// Keys can be set in the policy level
	protected static final HashSet<String> POLICY_KEYS = new HashSet<String>(Arrays.asList(new String[] { "start_time", "expire_time", "max_send_num" }));

	// Set predefined keys in the rootJson, for extra keys(Android) or
	// customized keys(IOS) please
	// refer to corresponding methods in the subclass.
	public abstract boolean setPredefinedKeyValue(String key, Object value) throws Exception;

	public void setAppMasterSecret(String secret) {
		appMasterSecret = secret;
	}

	public boolean send() throws Exception {
		String sign = DigestUtils.md5Hex("POSThttp://msg.umeng.com/api/send" +  rootJson + appMasterSecret);
		URI uri = new URIBuilder("http://msg.umeng.com/api/send").setParameter("sign", sign).build();
		logger.info("发送到友盟消息URL：{}",uri.toString());
		logger.info("发送到友盟消息数据：{}",rootJson.toString());
		HttpPost post = new HttpPost(uri);
		post.setHeader("User-Agent", USER_AGENT);
		post.setEntity(new StringEntity(rootJson.toString(), "UTF-8"));
		String resultString = client.execute(post, new UmReponseHandler());
		logger.info("接收友盟消息数据：result:{}",resultString);
		return true;
	}

}
