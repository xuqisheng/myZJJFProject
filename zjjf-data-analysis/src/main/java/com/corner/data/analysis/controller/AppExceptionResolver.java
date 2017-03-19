package com.corner.data.analysis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.corner.core.beans.vo.ResponseVo;
import com.corner.core.utils.JSONPObject;
import com.corner.core.utils.JSONUtil;

@Component("AppExceptionResolver")
public class AppExceptionResolver implements HandlerExceptionResolver {

	public static Logger logger = LoggerFactory.getLogger(AppExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		logger.error("用户请求发生异常url：{}",request.getRequestURL());
		logger.error("用户请求发生异常param：{}",requestToMap(request));
		logger.error("用户请求发生异常信息：{}",ex);
		String callback = ServletRequestUtils.getStringParameter(request,	"callback", "");
		if(!StringUtils.isEmpty(callback)){
			try {
				PrintWriter out = response.getWriter();
				out.print(JSONUtil.objectToJSONString(new JSONPObject(callback, new ResponseVo(false, "网络好像不给力", ""))));
				return null;
			} catch (IOException e) {
				logger.error("AppExceptionResolver getWriter error",e);
			}
		}else{
			
			Map<String,Object> mapresult = new HashMap<String,Object>();
			mapresult.put("message", "请求处理异常！");
			ModelAndView mv = new ModelAndView("/Admin/error",mapresult);
			if(request.getRequestURL().indexOf("Customer")!=-1){
				mv = new ModelAndView("/PcKdianbao/error",mapresult);
			}
			return mv;
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
