package com.corner.kefu.beans.ro;

import com.corner.core.beans.DBAppConfig;
import com.corner.kefu.config.CommonPageConfig;

public class DBAppConfigRo extends DBAppConfig {

     private Integer pageIndex = CommonPageConfig.asyncPageIndex;
     
     private Integer pageSize = CommonPageConfig.asyncPageSize;

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
}
