package com.corner.salesman.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * JSON模型
 * 用户后台向前台返回JSON对象
 * @author 元宝
 *
 */
public class Json implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean success = false;
	
	private String msg = "";
	
	private Object data = null;
	
	List<Object> list = null;
	
	private String code = "200";
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	} 

}
