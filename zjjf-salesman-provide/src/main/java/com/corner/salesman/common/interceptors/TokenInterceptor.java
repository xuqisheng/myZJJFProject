package com.corner.salesman.common.interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.corner.salesman.common.utils.JedisUtils;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.model.User;

/**
 * 权限拦截器
 * 
 * @author longxian
 * 
 */
public class TokenInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(TokenInterceptor.class);

	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		
		if ((StringUtils.isNotBlank(password) && url.indexOf("/mobile/user/login.do") > -1) || url.indexOf("/mobile/user/loginFailed.do") > -1) {// 如果要访问的资源是不需要验证的
			return true;
		}
		
		User user = (User)JedisUtils.getObject(Constants.TOKEN_PREFIX_KEY+token);
		//如果token为空表示会话已经过期，则跳转到登陆失败方法返回app
		if(null == user){
			logger.info("token="+token+"的会话已经过期，需要重新登陆！");
			request.getRequestDispatcher("/mobile/user/loginFailed.do").forward(request, response);
			return false;
		}

		return true;
	}
}