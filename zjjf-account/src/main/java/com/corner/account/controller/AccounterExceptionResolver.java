package com.corner.account.controller;

import com.corner.core.beans.vo.ResponseVo;
import com.corner.core.utils.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component("accounterExceptionResolver")
public class AccounterExceptionResolver implements HandlerExceptionResolver {

	public static Logger logger = LoggerFactory.getLogger(AccounterExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		logger.error("用户请求发生异常url：{}", request.getRequestURL());
		logger.error("用户请求发生异常param：{}", requestToMap(request));
		logger.error("用户请求发生异常信息：", ex);
		try {
			PrintWriter out = response.getWriter();
			out.print(JSONUtil.objectToJSONString(new ResponseVo(false, "网络好像不给力哦")));
			out.flush();
		} catch (IOException e) {
			logger.error("AppExceptionResolver getWriter error", e);
		}
		return null;
	}

	private Map<String, String> requestToMap(HttpServletRequest request) {
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
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			params.put(name, valueStr);
		}
		return params;
	}

}
