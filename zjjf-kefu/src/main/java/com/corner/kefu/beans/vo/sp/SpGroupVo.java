package com.corner.kefu.beans.vo.sp;

import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.SpGroup;
import com.corner.core.beans.Store;
import com.corner.core.beans.Supplier;

/**
 * 
 * @ClassName: SpGroupVo
 * 
 * @Description: 批发商组视图类
 * 
 * @author: 杨开泰
 * 
 * @date: 2015年10月12日 上午9:57:45
 */
public class SpGroupVo extends SpGroup {

	private List<Supplier> supplierList = new ArrayList<Supplier>();

	private List<Store> storeList = new ArrayList<Store>();

	private String areaName;// 区域名称

	private Integer areaId;// 区域id
	
	private Integer areaPid;//此定格所在区域的pId

	private Byte isSelected;// 是否被选中

	private Integer totalSupplier;// 定格所包含的批发商数

	private Integer totalStore;// 定格所包含的店铺数

	public SpGroupVo() {
	}

	public SpGroupVo(SpGroup spGroup) {
		super();
		super.setId(spGroup.getId());
		super.setAreaId(spGroup.getAreaId());
		super.setCityId(spGroup.getCityId());
		super.setCode(spGroup.getCode());
		super.setIsDelete(spGroup.getIsDelete());
		super.setName(spGroup.getName());
		super.setProvinceId(spGroup.getProvinceId());
		super.setStatus(spGroup.getStatus());
	}

	public List<Supplier> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<Supplier> supplierList) {
		this.supplierList = supplierList;
	}

	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Byte getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Byte isSelected) {
		this.isSelected = isSelected;
	}

	public Integer getTotalSupplier() {
		return totalSupplier;
	}

	public void setTotalSupplier(Integer totalSupplier) {
		this.totalSupplier = totalSupplier;
	}

	public Integer getTotalStore() {
		return totalStore;
	}

	public void setTotalStore(Integer totalStore) {
		this.totalStore = totalStore;
	}

	@Override
	public Integer getAreaId() {
		return areaId;
	}

	@Override
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getAreaPid() {
		return areaPid;
	}

	public void setAreaPid(Integer areaPid) {
		this.areaPid = areaPid;
	}
}
