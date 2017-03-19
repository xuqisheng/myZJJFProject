package com.corner.task.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.corner.task.config.SessionConfig;
import com.corner.task.util.JSONUtil;


public abstract class TaskBaseWebController{

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	protected <T> T getCurrentUser(Class<T> t,HttpServletRequest request) {
		logger.debug("enter in getCurrentUser function");
		Object object = request.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
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
	 * @param totalCount
	 * @param pageSize
	 * @param request
	 * @param model
	 */
	protected void pageUtil(int pageIndex, int totalCount, int pageSize, HttpServletRequest request, Model model) {

		int allpagecount = totalCount % pageSize > 0 ? totalCount / pageSize + 1 : totalCount / pageSize;
		//把request里面的参数转换成map
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("totalCount", totalCount);
		m.put("pageIndex", pageIndex == 0 ? 1 : pageIndex / pageSize + 1);
		m.put("pageSize", pageSize);
		m.put("allpagecount", allpagecount);
		model.addAttribute("page", m);
	}

}


