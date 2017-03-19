/**
 * 
 */
package com.corner.auth.beans.vo;

import java.io.Serializable;

/**
 * 
 * @author aimee at 2015年2月3日下午1:35:40
 * @email 1297579898@qq.com
 */
public class ResponseVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success;

	private Object message;

	private String url;
	

	public ResponseVo(boolean success) {
		this.success = success;
	
	}
	public ResponseVo(boolean success, Object message) {
		this.success = success;
		this.message = message;
	}
	public ResponseVo(boolean success, Object message, String url) {
		this(success, message);
		this.url = url;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}