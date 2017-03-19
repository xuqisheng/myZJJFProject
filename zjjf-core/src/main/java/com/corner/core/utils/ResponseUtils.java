package com.corner.core.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.ServletRequestUtils;

import com.corner.core.beans.mobile.ResponseMobileVo;
import com.corner.core.beans.vo.PageListOfAmazeUI;
import com.corner.core.beans.vo.PageListOfEasyUI;
import com.corner.core.beans.vo.Pager;
import com.corner.core.beans.vo.ResponseVo;

/**
 * 
* @ClassName: ResponseUtils
* @Description: TODO(这里用一句话描述这个类的作用)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2015年2月4日 下午3:22:56
*
 */
public class ResponseUtils {
	
	public static final boolean SUCCESS = true;

	public static final boolean FAILURE = false;

	public static ResponseVo sendMsg(boolean success) {
		return new ResponseVo(success);
	}
	
	public static ResponseVo sendMsg(boolean success,Object message) {
		return new ResponseVo(success, message);
	}
	
	
	public static ResponseVo sendMsg(boolean success,Object message, String url) {
		return new ResponseVo(success, message, url);
	}
	/**
	 * 添加bCode,bData扩展返回值。
	 * @Title: sendMsg
	 * @date 2016年8月24日  下午5:05:24
	 * @author 小武
	 * @version  七彩虹
	 * @param success
	 * @param desc
	 * @param url
	 * @param bCode
	 * @param bData
	 * @return
	 */
	public static ResponseVo sendMsg(boolean success,String desc, String url,String bCode,List<Object> bData) {
		return new ResponseVo(success, desc, url,bCode,bData);
	}
	
	public static ResponseMobileVo sendMobileMsg(boolean success,String msg,Object data) {
		return new ResponseMobileVo(success, msg, data);
	}
	
	public static <T> PageListOfAmazeUI<T> sendPagination(Pager<T> pager) {
		return new PageListOfAmazeUI<T>(pager.getTotalSize(),pager.getList());
	}
	
	public static <T> PageListOfEasyUI<T> sendEasyUIPagination(Pager<T> pager) {
		return new PageListOfEasyUI<T>(pager);
	}

	/**
	 * 跨域JSONP方案
	 * 
	 * @param code
	 * @param request
	 * @return
	 */
	public static JSONPObject sendMsg(boolean success,Object message, HttpServletRequest request) {
		String jsonpCallback = ServletRequestUtils.getStringParameter(request, "callback", "");
		return new JSONPObject(jsonpCallback, new ResponseVo(success,message));
	}

	/**
	 * 跨域JSONP方案
	 * 
	 * @param code
	 * @param msg
	 * @param request
	 * @return
	 */
	public static JSONPObject sendMsg(boolean success,Object message, String url, HttpServletRequest request) {
		String jsonpCallback = ServletRequestUtils.getStringParameter(request, "callback", "");
		return new JSONPObject(jsonpCallback, new ResponseVo(success,  message,  url));
	}
	
}
