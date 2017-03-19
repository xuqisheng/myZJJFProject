/**   
* @Title: RegionRegistVo.java 
* @Package com.corner.scms.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年6月27日 下午2:51:47 
* @version V1.0   
*/

package com.corner.scms.beans.vo;

import java.util.List;

/** 
* @ClassName: RegionRegistVo 
* @Description:用于邀请注册页面封装区域数据
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年6月27日 下午2:51:47 
*  
*/

public class RegionRegistVo {
	private Integer id;
	
	private Integer pId;

	private String name;

	private List<RegionRegistVo> sub;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RegionRegistVo> getSub() {
		return sub;
	}

	public void setSub(List<RegionRegistVo> sub) {
		this.sub = sub;
	}

}
