package com.corner.rpc.shop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: RegionVo
 * 
 * @Description: 区域视图类,存放在区域上有的定格集合
 * 
 * @author: 杨开泰
 * 
 * @date: 2015年10月30日 下午4:13:56
 */
public class RegionVo implements Serializable{
	private static final long serialVersionUID = 564001942792178491L;
	private int id;// 定格id
	private String name;// 定格name
	private Integer pId;
	private Byte regionLevel;
	private List<RegionVo> regionList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RegionVo> getRegionList() {
		return regionList;
	}

	public void setRegionList(List<RegionVo> regionList) {
		this.regionList = regionList;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Byte getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Byte regionLevel) {
		this.regionLevel = regionLevel;
	}
}