/**  
 * @Title: package-info.java
 * @Package com.corner.mobile.common.utils.http
 * @Description: TODO(用一句话描述该文件做什么)
 * @author luke    
 * @email   luke@mibodoctor.com  
 * @date 2015年3月8日 下午4:43:20
 * @version V1.0  
 */
/**
 * @ClassName: package-info
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author luke
 * @email   luke@mibodoctor.com  
 * @date 2015年3月8日 下午4:43:20
 *
 */
package com.corner.core.utils.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReponseHandlerString implements ResponseHandler<String>{
	private static Logger logger = LoggerFactory.getLogger(ReponseHandlerString.class);
	@Override
	public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } else {
        	logger.info("【HTTPCLIENT】Unexpected response status: {}",status);
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
	}

}