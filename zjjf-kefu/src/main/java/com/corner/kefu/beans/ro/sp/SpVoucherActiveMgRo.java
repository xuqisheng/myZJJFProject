/**   
* @Title: SpVoucherActiveMgRo.java 
* @Package com.corner.kefu.beans.ro.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年4月16日 上午10:48:49 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.sp;

import com.corner.core.beans.SpVoucherActive;
import com.corner.kefu.config.CommonPageConfig;

/** 
* @ClassName: SpVoucherActiveMgRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年4月16日 上午10:48:49 
*  
*/

public class SpVoucherActiveMgRo extends SpVoucherActive {
	private Integer pageIndex = CommonPageConfig.asyncPageIndex;
	
	private Integer pageSize = CommonPageConfig.asyncPageSize;

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

}
