package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.SKUActive;
import com.corner.core.beans.SKUActiveGoodInfo;
import com.corner.kefu.beans.vo.ItemBaseVo;
import com.corner.kefu.beans.vo.SKUActiveTagVo;

/**
 * 
 * @ClassName: SessionObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 小武
 * @version 七彩虹版本
 * @date 2016年8月30日 下午2:46:13
 *
 */
public class SKUActiveSessionObject extends SKUActive implements Serializable {
	/**
	 * @desc  
	 * @date 2016年8月30日  下午2:47:05
	 * @author 小武
	 * @version  七彩虹
	 */
	private static final long serialVersionUID = 1L;

	List<ItemBaseVo> itemBases = new ArrayList<ItemBaseVo>();
	
	List<SupplierVo> suppliers = new ArrayList<SupplierVo>();
	
	List<SKUActiveGoodInfo> goodsList4Old = new ArrayList<SKUActiveGoodInfo>();
	List<SKUActiveGoodInfo> goodsList4New = new ArrayList<SKUActiveGoodInfo>();
	
	List<PlantItemVo> plantItemList = new ArrayList<PlantItemVo>();
	
	List<SKUActiveGoodInfoVo> listDetial = new ArrayList<SKUActiveGoodInfoVo>();
	
	List<SKUActiveTagVo> publishTagList = new ArrayList<SKUActiveTagVo>();
	
	private String activeStartTime;
	
	private String activeEndTime;
	
	private String times;

	public List<ItemBaseVo> getItemBases() {
		return itemBases;
	}

	public void setItemBases(List<ItemBaseVo> itemBases) {
		this.itemBases = itemBases;
	}

	public List<SupplierVo> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<SupplierVo> suppliers) {
		this.suppliers = suppliers;
	}

	public List<SKUActiveGoodInfo> getGoodsList4Old() {
		return goodsList4Old;
	}

	public void setGoodsList4Old(List<SKUActiveGoodInfo> goodsList4Old) {
		this.goodsList4Old = goodsList4Old;
	}

	public List<SKUActiveGoodInfo> getGoodsList4New() {
		return goodsList4New;
	}

	public void setGoodsList4New(List<SKUActiveGoodInfo> goodsList4New) {
		this.goodsList4New = goodsList4New;
	}

	public List<PlantItemVo> getPlantItemList() {
		return plantItemList;
	}

	public void setPlantItemList(List<PlantItemVo> plantItemList) {
		this.plantItemList = plantItemList;
	}

	public List<SKUActiveGoodInfoVo> getListDetial() {
		return listDetial;
	}

	public void setListDetial(List<SKUActiveGoodInfoVo> listDetial) {
		this.listDetial = listDetial;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public List<SKUActiveTagVo> getPublishTagList() {
		return publishTagList;
	}

	public void setPublishTagList(List<SKUActiveTagVo> publishTagList) {
		this.publishTagList = publishTagList;
	}

	public String getActiveStartTime() {
		return activeStartTime;
	}

	public void setActiveStartTime(String activeStartTime) {
		this.activeStartTime = activeStartTime;
	}

	public String getActiveEndTime() {
		return activeEndTime;
	}

	public void setActiveEndTime(String activeEndTime) {
		this.activeEndTime = activeEndTime;
	}
	
	
}
