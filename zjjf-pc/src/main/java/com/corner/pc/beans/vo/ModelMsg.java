package com.corner.pc.beans.vo;

public class ModelMsg {
	
	private boolean success;
	private String message;
	private Object date;
	
	public ModelMsg(boolean success,String message){
		this.success=success;
		this.message=message;
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

	public Object getDate() {
		return date;
	}

	public void setDate(Object date) {
		this.date = date;
	} 
	
	
}
