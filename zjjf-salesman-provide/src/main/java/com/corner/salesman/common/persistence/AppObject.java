/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.common.persistence;

import java.util.List;

/**
 * 将list统一格式化为到对象中（app统一格式）
 * @author ThinkGem
 * @version 2013-7-2
 * @param <T>
 */
public class AppObject<T> {
	
	Object resultObj = new Object();
	
	private List<T> list = null;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Object getResultObj() {
		return resultObj;
	}

	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}

}
