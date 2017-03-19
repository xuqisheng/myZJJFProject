package com.corner.core.beans.msg;

import java.io.Serializable;

/**
* @ClassName: PayBackMsg
* @Description: TODO(支付模块，返回的实体，采用mvc模式构造支付模块)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2014年12月23日 上午11:36:15
*
*/ 
public class MsgBean  implements Serializable{
	private static final long serialVersionUID = 1L;
	private  Boolean success;
	private  String  message;
	private  Object  date;
	public MsgBean(Boolean success,String  message){
		this.success = success;
		this.message = message;
	}
	public MsgBean(Boolean success,String  message,Object  date){
		this.success = success;
		this.message = message;
		this.date = date;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
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
