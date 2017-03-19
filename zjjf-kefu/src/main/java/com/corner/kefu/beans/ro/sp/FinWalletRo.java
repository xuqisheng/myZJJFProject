/**   
* @Title: FinWalletRo.java 
* @Package com.corner.kefu.beans.ro.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年7月18日 下午8:02:08 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.sp;

import com.corner.core.beans.FinWallet;
import com.corner.kefu.config.CommonPageConfig;

/** 
* @ClassName: FinWalletRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年7月18日 下午8:02:08 
*  
*/

public class FinWalletRo extends FinWallet {
	private Integer pageIndex = CommonPageConfig.asyncPageIndex;

	private Integer pageSize = CommonPageConfig.asyncPageSize;
	
	private String searchKey;

	public Integer getPageIndex() {
		return pageIndex*pageSize;
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
