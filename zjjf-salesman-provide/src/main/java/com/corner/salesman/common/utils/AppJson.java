package com.corner.salesman.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * JSON模型
 * 用户后台向前台返回JSON对象
 * @author 元宝
 *
 */
public class AppJson implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private List list = null;
	
	@SuppressWarnings("rawtypes")
	private List spgList = null;
	
	private Object obj;
	private Object resultObj;
	
	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}

	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	@SuppressWarnings("rawtypes")
	public List getSpgList() {
		return spgList;
	}

	@SuppressWarnings("rawtypes")
	public void setSpgList(List spgList) {
		this.spgList = spgList;
	}

	public Object getResultObj() {
		return resultObj;
	}

	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}

}
