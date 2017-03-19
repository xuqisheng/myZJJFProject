/**
 * 
 */
package com.corner.core.beans.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

/**
 * 
 * @author aimee at 2015年2月3日下午1:35:40
 * @email 1297579898@qq.com
 */
public class ResponseVo extends ABaseVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success;

	private Object message;

	private String url;
	
	private String desc;
	
	/**
	 * add by 小武  20160824
	 * 业务返回码
	 */
	private String bCode;
	
	/**
	 * 业务数据值
	 */
	private List<Object> bData;

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
	
	/**
	 * 包含业务码和业务数据值  add by xiaowu 20160624 
	 * @param success 是否处理成功
	 * @param desc 简短描述
	 * @param url 系统url
	 * @param bCode 业务返回码
	 * @param bData 业务数据
	 */
	public ResponseVo(boolean success, String desc, String url,String bCode,List<Object> bData) {
		this.success = success;
		this.desc = desc;
		this.url = url;
		this.bCode = bCode;
		this.bData = bData;
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

	public String getbCode() {
		return bCode;
	}

	public void setbCode(String bCode) {
		this.bCode = bCode;
	}

	public List<Object> getbData() {
		return bData;
	}

	public void setbData(List<Object> bData) {
		this.bData = bData;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
}
