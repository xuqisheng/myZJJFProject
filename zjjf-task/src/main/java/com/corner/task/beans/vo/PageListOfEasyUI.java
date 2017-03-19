package com.corner.task.beans.vo;

import java.io.Serializable;
import java.util.List;

/**
* @ClassName: PageListOfAmazeUI
* @Description: TODO( 主要用于 control  层)
* @author luke
* @email   luke@izjjf.com  
* @date 2015年2月4日 下午3:15:51
*
* @param <T>
*/ 
public class PageListOfEasyUI<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int total;
	
	private List<T> rows;

	public PageListOfEasyUI(Pager<T> pager){
		this.total = pager.getTotalSize();
		this.rows  = pager.getList();
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	
}