package com.corner.task.util;

import com.corner.task.beans.vo.PageListOfAmazeUI;
import com.corner.task.beans.vo.PageListOfEasyUI;
import com.corner.task.beans.vo.Pager;
import com.corner.task.beans.vo.ResponseVo;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

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
