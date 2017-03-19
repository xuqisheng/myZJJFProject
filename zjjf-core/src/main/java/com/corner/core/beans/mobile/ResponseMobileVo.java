package com.corner.core.beans.mobile;

public class ResponseMobileVo {
	private Boolean success;

	private String msg;

	private Object data;

	public ResponseMobileVo() {
		super();
	}

	public ResponseMobileVo(Boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
