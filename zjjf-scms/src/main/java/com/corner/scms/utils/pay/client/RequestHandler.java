package com.corner.scms.utils.pay.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corner.scms.utils.MD5Util;
import com.corner.scms.utils.pay.wx.TenpayUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RequestHandler {

	private String tokenUrl;
	private String gateUrl;
	private String notifyUrl;
	private String appid;
	private String appkey;
	private String partnerkey;
	private String appsecret;
	private String key;
	private SortedMap parameters;
	private String Token;
	private String charset;
	private String debugInfo;
	private String last_errcode;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public RequestHandler(HttpServletRequest request,	HttpServletResponse response) {
		this.last_errcode = "0";
		this.request = request;
		this.response = response;
		this.charset = "GBK";
		this.parameters = new TreeMap();
		tokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
		gateUrl = "https://api.weixin.qq.com/pay/genprepay";
		notifyUrl = "https://gw.tenpay.com/gateway/simpleverifynotifyid.xml";
	}

	public void init(String app_id, String app_secret, String app_key,String partner, String key) {
		this.last_errcode = "0";
		this.Token = "token_";
		this.debugInfo = "";
		this.appid = app_id;
		this.appsecret = app_secret;
		this.appkey = app_key;
		this.partnerkey = partner;
		this.key = key;
	}

	public void init() {
	}

	public String getLasterrCode() {
		return last_errcode;
	}

	public String getGateUrl() {
		return gateUrl;
	}

	public String getParameter(String parameter) {
		String s = (String) this.parameters.get(parameter);
		return (null == s) ? "" : s;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String GetToken() {
		String requestUrl = tokenUrl + "?grant_type=client_credential&appid="+ appid + "&secret=" + appsecret;
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		if (httpClient.call()) {
			String res = httpClient.getResContent();
			Gson gson = new Gson();
			TreeMap map = gson.fromJson(res, TreeMap.class);
			if (map.containsKey("access_token")) {
				String s = map.get("access_token").toString();
			}
			if (null != map) {
				try {
					if (map.containsKey("access_token")) {
						Token = map.get("access_token").toString();
						return this.Token;
					}
				} catch (Exception e) {
					//System.out.println("errmsg:" + map.get("errmsg"));
				}
			}

		}
		return "";
	}

	public String getTokenReal() {
		String requestUrl = tokenUrl + "?grant_type=client_credential&appid="+ appid + "&secret=" + appsecret;
		try {
			TenpayHttpClient httpClient = new TenpayHttpClient();
			httpClient.setReqContent(requestUrl);
			String resContent = "";
			if (httpClient.callHttpPost(requestUrl, "")) {
				resContent = httpClient.getResContent();
				Gson gson = new Gson();
				Map<String, String> map = gson.fromJson(resContent,new TypeToken<Map<String, String>>() {}.getType());
				if (map.containsKey("access_token")) {
					Token = map.get("access_token");
				} else {
					//System.out.println("get token err ,info =" + map.get("errmsg"));
				}
				//System.out.println("res json=" + resContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Token;
	}

	public String UrlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src, this.charset).replace("+", "%20");
	}

	public String genPackage(SortedMap<String, String> packageParams)
			throws UnsupportedEncodingException {
		String sign = createSign(packageParams);
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + UrlEncode(v) + "&");
		}
		String packageValue = sb.append("sign=" + sign).toString();
		//System.out.println("packageValue=" + packageValue);
		return packageValue;
	}

	public String createSign(SortedMap<String, String> packageParams) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)	&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + this.getKey());
		String sign = MD5Util.MD5Encode(sb.toString(), this.charset).toUpperCase();
		return sign;
	}

	public String sendPrepay(SortedMap packageParams) {
		String prepayid = "";
		Gson gson = new Gson();
		String postData = "{";
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (k != "appkey") {
				if (postData.length() > 1)
					postData += ",";
				postData += "\"" + k + "\":\"" + v + "\"";
			}
		}
		postData += "}";
		String requestUrl = this.gateUrl + "?access_token=" + this.Token;
		System.out.println("post url=" + requestUrl);
		System.out.println("post data=" + postData);
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		if (httpClient.callHttpPost(requestUrl, postData)) {
			resContent = httpClient.getResContent();
			Map<String, String> map = gson.fromJson(resContent,new TypeToken<Map<String, String>>() {}.getType());
			if ("0".equals(map.get("errcode"))) {
				prepayid = map.get("prepayid");
			} else {
				System.out.println("get token err ,info =" + map.get("errmsg"));
			}
			System.out.println("res json=" + resContent);
		}
		return prepayid;
	}
	public boolean createMd5Sign(String signParams) {
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}

		String enc = TenpayUtil.getCharacterEncoding(this.request,	this.response);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

		String tenpaySign = this.getParameter("sign").toLowerCase();
		this.setDebugInfo(sb.toString() + " => sign:" + sign + " tenpaySign:"+ tenpaySign);
		return tenpaySign.equals(sign);
	}


	protected void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}
	public void setPartnerkey(String partnerkey) {
		this.partnerkey = partnerkey;
	}
	public String getDebugInfo() {
		return debugInfo;
	}
	public String getKey() {
		return key;
	}

}
