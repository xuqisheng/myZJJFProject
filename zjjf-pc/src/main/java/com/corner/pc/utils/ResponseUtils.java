package com.corner.pc.utils;

import com.corner.pc.beans.vo.PageListOfEasyUI;
import com.corner.pc.beans.vo.Pager;
import com.corner.pc.beans.vo.ResponseVo;

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
	
	public static <T> PageListOfEasyUI<T> sendEasyUIPagination(Pager<T> pager) {
		return new PageListOfEasyUI<T>(pager);
	}

}
