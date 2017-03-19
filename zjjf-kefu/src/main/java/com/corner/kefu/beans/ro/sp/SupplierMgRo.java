/**   
* @Title: SupplierMgRo.java 
* @Package com.corner.kefu.beans.ro.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月24日 上午11:16:04 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.sp;

import com.corner.core.beans.Supplier;
import com.corner.kefu.config.CommonPageConfig;

/** 
* @ClassName: SupplierRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月24日 上午11:16:04 
*  
*/

public class SupplierMgRo extends Supplier {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;// 批发商定格id
	
	private String spGroupId;// 批发商定格id

	private String searchKey;// 搜索关键字
	
	private Integer pageIndex = CommonPageConfig.commonPageIndex;
	
	private Integer pageSize = CommonPageConfig.commonPageSize;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSpGroupId() {
		return spGroupId;
	}

	public void setSpGroupId(String spGroupId) {
		this.spGroupId = spGroupId;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Integer getPageIndex() {
		return (pageIndex-1)*pageSize;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
