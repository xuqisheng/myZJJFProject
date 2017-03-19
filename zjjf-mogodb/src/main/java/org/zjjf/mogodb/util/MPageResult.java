package org.zjjf.mogodb.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.bson.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MPageResult {

	/**
	 * 当前页码
	 */
	private int curPageNo = 1;
	/**
	 * 每页的数据大小
	 */
	private int pageSize = 10;
	/**
	 * 最大页码
	 */
	private int maxPageNo;
	/**
	 * 总条数
	 */
	private long count;
	
	private List<Document> list = new ArrayList<Document>();
	/**
	 * 构造方法
	 * @param curPageNo 当前页码
	 * @param pageSize 分页大小
	 * @param count 数据条数
	 * @param list2 本页数据对象列表
	 */
	public MPageResult(int curPageNo, int pageSize, long count, List<Document> list2) {
		this.curPageNo = curPageNo;
		this.pageSize = pageSize;
		this.count = count;
		this.list = list2;
		
		if(count > 0 && pageSize > 0){
			maxPageNo = (int) (count/pageSize);
			if(count%pageSize != 0){
				maxPageNo++;
			}
		}else{
			maxPageNo = 0;
		}
		
	}
	
	public int getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(int curPageNo) {
		this.curPageNo = curPageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMaxPageNo() {
		return maxPageNo;
	}

	public void setMaxPageNo(int maxPageNo) {
		this.maxPageNo = maxPageNo;
	}
	
	public List<Document> getList() {
		return list;
	}

	public void setList(List<Document> list) {
		this.list = list;
	}
 
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	
	
	
}
