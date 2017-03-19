package com.corner.auth.controller;

import com.corner.auth.config.SessionConfig;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public abstract class AuthBaseWebController{

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public <T> T getCurrentUser(Class<T> t,HttpServletRequest request) {
		logger.debug("enter in getCurrentUser function");
		Object object = SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		if (object != null) {
			return (T) object;
		}
		return null;
	}
	
	/**
	 * 公用分页封装
	 * 
	 * @author aimee at 2015年4月9日下午1:56:59
	 * @email 1297579898@qq.com
	 * @param pageNum
	 * @param totalCount
	 * @param pageSize
	 * @param map
	 * @param request
	 * @param model
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void pageUtil(int pageIndex, int totalCount, int pageSize, HttpServletRequest request, Model model) {

		int allpagecount = totalCount % pageSize > 0 ? totalCount / pageSize + 1 : totalCount / pageSize;
		//把request里面的参数转换成map
		Map<String, Object> map = getParameterMap(request);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("totalCount", totalCount);
		m.put("pageIndex", pageIndex == 0 ? 1 : pageIndex / pageSize + 1);
		m.put("pageSize", pageSize);
		m.put("allpagecount", allpagecount);
		String requrl = request.getRequestURI();
		if (!map.isEmpty()) {
			requrl += "?";
		}
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			requrl += "&" + key + "=" + val;
		}
		requrl = requrl.replace("?&", "?");
		m.put("reqURI", requrl);
		model.addAttribute("page", m);
	}
	
	/**
     * 将request的参数转换成map
     * @param request
     * @return
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map properties = request.getParameterMap();
		// 返回值Map
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			if (!"pageIndex".equals(name)) {
				Object valueObj = entry.getValue();
				if (null == valueObj) {
					value = "";
				} else if (valueObj instanceof String[]) {
					String[] values = (String[]) valueObj;
					for (int i = 0; i < values.length; i++) {
						value = values[i] + ",";
					}
					value = value.substring(0, value.length() - 1);
				} else {
					value = valueObj.toString();
				}
				returnMap.put(name, value);
			}
		}
		return returnMap;
	}
	/**
	 * kefu pc端错误页面
	 * @param message
	 * @param model
	 * @param request
	 * @return
	 */
	public String error(String message, Model model, HttpServletRequest request) {
		model.addAttribute("message", message);
		return "/common/error";
	}

}


