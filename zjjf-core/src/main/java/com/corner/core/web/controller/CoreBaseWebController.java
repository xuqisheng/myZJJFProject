package com.corner.core.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.corner.core.config.PropertieNameConts;
import com.corner.core.utils.FastDFSUploadUtil;
import com.corner.core.utils.PropertiesCacheUtil;
import com.corner.core.utils.safe.DesUtil;


public class CoreBaseWebController {

	public static Logger logger = LoggerFactory.getLogger(CoreBaseWebController.class);
	
	public static String USER_CHECKCODE="USER_CHECKCODE";
	
	 @InitBinder  
	 public void initBinder(WebDataBinder binder) {  
	     binder.registerCustomEditor(Date.class, new AppDateEditor());  
	 }
	
	/**
	 * 
	* @Title: getUserId
	* @Author luke luke@mibodoctor.com
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param request
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String getUserId(HttpServletRequest request) {
		String token = ServletRequestUtils.getStringParameter(request, "token", "null");
		
		if ( StringUtils.hasText(token) && !"null".equals(token) ) {
			try {
				return DesUtil.decrypt(token);
			} catch (Exception e) {
				logger.error("BaseWebController getUserId", e);
			}
		}
		return "";
	}

	/**
	 * 
	* @Title: getUserId
	* @Author luke luke@mibodoctor.com
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param token
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public  String getUserId( String token ) {
		if (StringUtils.hasText(token) && !"null".equals(token)) {
			try {
				return DesUtil.decrypt(token);
			} catch (Exception e) {
				logger.error("BaseWebController getUserId", e);
			}
		}
		return "";
	}
	
	/**
	 * 
	* @Title: getUserToken
	* @Author luke luke@mibodoctor.com
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param originalString
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public  String getUserToken( String originalString ) {
		return DesUtil.crypto(originalString);
	}

	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @return
	 */
//	public String  getCaptcha(String key) {
//		logger.debug("获取用户短信验证码：captcha:{}",key);
//		String  captcha = cacheService.getValueFromPhoneMessageCache(key,String.class);
//		logger.debug("准备获取短信验证码：key:{}, value:{}",key,captcha);
//		return captcha;
//		//return "111111";
//	}

	/**
	 * 将验证码放入缓存
	 * 
	 * @param request
	 * @param captcha
	 */
//	public void saveCaptchaToSession( String captcha,String mobile) {
//		cacheService.putValueInPhoneMessageCache(mobile,captcha);
//	}

	/**
	 * 生成随机的6位数字
	 * 
	 * @return
	 */
	public String generateCode() {
		Random rad = new Random();
		return rad.nextInt(900000) + 100000 + "";
	}
	public void logRequest(HttpServletRequest request) {
		Enumeration<String> enumeration = request.getParameterNames();
		StringBuilder sb = new StringBuilder();
		while (enumeration.hasMoreElements()) {
			String string = (String) enumeration.nextElement();
			sb.append("{name:" + string + ",value:" + request.getParameter(string) + "}|");
		}
		logger.info(sb.toString());
	}
	
	protected boolean isTimeout(String sendTime) {
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		Date old;
		try {
			old = sdf.parse(sendTime);
			if(old ==  null){
				return false;
			}
			
			long timeOld = old.getTime();
			long timeNow = now.getTime();
			//System.out.println(timeOld);
			//System.out.println(timeNow);
			return timeNow - timeOld >= 90*1000 ? true: false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		
	}
	
	public String getTipMsg(String tipKey) {
		return PropertiesCacheUtil.getValue(tipKey,PropertieNameConts.TIP);
	}
	
//	protected void sendMessage(String mobile,String content) {
//		//Map<String, String> params =new HashMap<String ,String>();
//		//String webUrl = PropertiesCacheUtil.getValue(SystemKeys.SMS.WEBURL, PropertieNameConts.System);
//		String url = "http://m.5c.com.cn/api/send/";
//		String apikey = PropertiesCacheUtil.getValue(SystemKeys.SMS.APIKEY, PropertieNameConts.System);
//		String username = PropertiesCacheUtil.getValue(SystemKeys.SMS.USERNAME, PropertieNameConts.System);
//		String password = PropertiesCacheUtil.getValue(SystemKeys.SMS.PASSWORD, PropertieNameConts.System);
//		StringBuffer sb = new StringBuffer("apikey=");
//		//params.put("test", "d");
//		try {
//			//sb.append("apikey=");
//			sb.append(apikey);
//			sb.append("&username=");
//			sb.append(username);
//			sb.append("&password=");
//			sb.append(password);
//			sb.append("&mobile=");
//			sb.append(mobile);
//			sb.append("&content=");
//			sb.append(URLEncoder.encode(content, "GBK"));
//			//String rest = HttpUtils.buildRequest(sb.toString(),params);
//			String result =sendPost(url,sb.toString());
//			
//			logger.info("短信发送响应结果为：{}",result);
//			//return rest ;
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			//return null;
//		}
//	
//	
//
//	}
	
	/**
	 * 
	* @Title: upload
	* @Author luke luke@mibodoctor.com
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param request
	* @param @return
	* @param @throws IOException    设定文件
	* @return List<Map<String,String>>    返回类型
	* @throws
	 */
	public List<Map<String,String>> upload(HttpServletRequest request) throws IOException {
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (String iterable_element : map.keySet()) {
			MultipartFile uploadFile = map.get(iterable_element);
			String name = FastDFSUploadUtil.saveFile(uploadFile, null);
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("filename",name);
			//resultMap.put("typeIndex",1);
			//resultMap.put("url",1);
			list.add(resultMap);
		}
		return list;
	}
	
	/**
	 * 
	* @Title: decode
	* @Author luke luke@mibodoctor.com
	* @Description: TODO( 中文入参 解码)
	* @param @param text
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public String decode(String text) {
		if (StringUtils.isEmpty(text)) {
			return "";
		}
		try {
			return java.net.URLDecoder.decode(text, "UTF-8");
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 
	 * @Title: requestToMap
	 * @Author luke luke@mibodoctor.com
	 * @Description: TODO（获取请求字符参数)
	 * @param @param request
	 * @param @return
	 * @param @throws UnsupportedEncodingException 设定文件
	 * @return Map<String,String> 返回类型
	 * @throws
	 */
	protected Map<String, String> requestToMap(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			params.put(name, valueStr);
		}
		return params;
	}
		
}


