package com.zjjf.analysis.beans.vo;

import java.io.Serializable;

public class ModelMsg implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private boolean success;
	private String message;
	private Object data;
	
	public ModelMsg(boolean success,String message){
		this.success=success;
		this.message=message;
	}
	public ModelMsg(boolean success,String message,Object data){
		this.success=success;
		this.message=message;
		this.setData(data);
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
