package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.Region;
import com.corner.core.beans.Supplier;

/**
 * 省市
 * @author aimee at 2015年6月3日下午5:24:12
 * @email 1297579898@qq.com
 */
public class RegionVo extends Region implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int provinceId;// 对应的省的编号
	private int cityId;// 对应的市的编号
	private List<Supplier> list = new ArrayList<Supplier>();// 该区域下包含的批发商列表
	private List<RegionVo> regionList = new ArrayList<RegionVo>();
	
	private boolean nocheck = true;

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public List<Supplier> getList() {
		return list;
	}

	public void setList(List<Supplier> list) {
		this.list = list;
	}

	public List<RegionVo> getRegionList() {
		return regionList;
	}

	public  void setRegionList(List<RegionVo> regionList) {
		this.regionList = regionList;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	
	
}
