/**   
* @Title: SpGroupMgRo.java 
* @Package com.corner.kefu.beans.ro.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年4月11日 上午9:44:23 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.sp;

import java.util.List;

import com.corner.core.beans.Region;
import com.corner.core.beans.SpGroup;
import com.corner.kefu.config.CommonPageConfig;

/** 
* @ClassName: SpGroupMgRo 
* @Description:定格查询类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年4月11日 上午9:44:23 
*  
*/

public class SpGroupMgRo extends SpGroup {

	private Integer pageIndex = CommonPageConfig.asyncPageIndex;

	private Integer pageSize = CommonPageConfig.asyncPageSize;
	
	private List<Region> list;
	
	private Integer regionId;//区域id
	
	private Integer regionLevel;//区域等级

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

	public List<Region> getList() {
		return list;
	}

	public void setList(List<Region> list) {
		this.list = list;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Integer regionLevel) {
		this.regionLevel = regionLevel;
	}
}
