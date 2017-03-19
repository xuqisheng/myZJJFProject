package com.corner.kefu.resolver;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CommonsMultiparResolver extends CommonsMultipartResolver {
	/** 
	 * {@inheritDoc} 
	 *  
	 * @see org.springframework.web.multipart.commons.CommonsMultipartResolver#isMultipart(javax.servlet.http.HttpServletRequest) 
	 */
	@Override
	public boolean isMultipart(javax.servlet.http.HttpServletRequest request) {

		String uri = request.getRequestURI();
		// 过滤使用百度EmEditor的URI
		if (uri.indexOf("upload") > 0) {
			return false;
		}
		return super.isMultipart(request);
	}
}
