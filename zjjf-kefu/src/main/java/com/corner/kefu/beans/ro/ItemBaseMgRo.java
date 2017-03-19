/**   
* @Title: ItemBaseMgRo.java 
* @Package com.corner.kefu.beans.ro 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月30日 上午11:27:12 
* @version V1.0   
*/

package com.corner.kefu.beans.ro;

import com.corner.core.beans.ItemBase;
import com.corner.kefu.config.CommonPageConfig;

/** 
* @ClassName: ItemBaseMgRo 
* @Description:ItemBase 查询类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月30日 上午11:27:12 
*  
*/

public class ItemBaseMgRo extends ItemBase {

	private Integer pageIndex = CommonPageConfig.asyncPageIndex;
	private Integer pageSize = CommonPageConfig.asyncPageSize;
	private String searchKey;

	public Integer getPageIndex() {
		return pageIndex * pageSize;
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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}
