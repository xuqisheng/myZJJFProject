/**   
* @Title: ScOrderDetailVo.java 
* @Package com.corner.scms.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月25日 上午11:46:27 
* @version V1.0   
*/

package com.corner.scms.beans.ro.sc;

import java.io.Serializable;

import com.corner.core.beans.ScOrderDetail;



public class ScOrderDetailRo extends ScOrderDetail implements Serializable{
	private int pageIndex ;
	//默认加载10条
	private int pageSize = 10;
	
	public int getPageIndex() {
		return pageSize * ((pageIndex - 1) > 0 ? (pageIndex - 1) : 0);
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
