package com.corner.pc.beans.vo;

import java.util.List;
/**
 * 
* @ClassName: Pager
* @Description: TODO( 系统中最基本的List ,主要用于service ，dao 层)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2015年2月4日 下午3:16:27
*
* @param <Model>
 */
public class Pager<Model> {

	private int totalSize;
	
	private List<Model> list;

	public Pager(int totalSize, List<Model> list) {
		super();
		this.totalSize = totalSize;
		this.list = list;
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
}
