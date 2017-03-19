/**   
* @Title: AppModelMsg.java 
* @Package com.corner.kefu.beans 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年6月22日 下午6:14:06 
* @version V1.0   
*/

package com.corner.core.beans.msg;

import java.util.List;

/** 
* @ClassName: AppModelMsg 
* @Description:用于返回阿街足记数据
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年6月22日 下午6:14:06 
*  
*/

public class AppModelMsg<T> {
	private List<T> list;
	private Integer pageNo;
	private Integer pageSize;
	private Integer count;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
