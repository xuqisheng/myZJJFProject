package com.corner.auth.beans.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: Pager
 * @Description: TODO( 系统中最基本的List ,主要用于service ，dao 层)
 * @author luke
 * @email luke@mibodoctor.com
 * @date 2015年2月4日 下午3:16:27
 *
 * @param <Model>
 */
public class Pager<Model> {

	private int totalSize;

	private List<Model> list;

	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Boolean flag;
	
	private String message;

	public Pager() {
		super();
	}
	

	public Pager(int totalSize, List<Model> list) {
		super();
		this.totalSize = totalSize;
		this.list = list;
	}

	public Pager(int totalSize, List<Model> list, Map<String, Object> map) {
		super();
		this.totalSize = totalSize;
		this.list = list;
		this.map = map;
	}
	

	public Pager(Boolean flag,int totalSize, List<Model> list, Map<String, Object> map) {
		super();
		this.totalSize = totalSize;
		this.list = list;
		this.map = map;
		this.flag = flag;
	}

	public Pager(boolean flag, int totalSize, List<Model> list) {
		super();
		this.totalSize = totalSize;
		this.list = list;
		this.flag = flag;
	}
	
	

	public Pager(Boolean flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}


	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public List<Model> getList() {
		return list;
	}

	public void setList(List<Model> list) {
		this.list = list;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
