package com.corner.core.beans.msg;

import java.util.List;

public class ModelMsg {

	private boolean success;
	private String message;
	private Object data;
	private String url;
	private String pageIndex;
	private String pageSize;
	private Integer totalSize;
	private List<Object>list;

	public ModelMsg() {
		super();
	}

	public ModelMsg(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public ModelMsg(boolean success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.setData(data);
	}

	public ModelMsg(boolean success, String message, String url) {
		super();
		this.success = success;
		this.message = message;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
}
