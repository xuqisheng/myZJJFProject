package com.corner.kefu.beans.vo;

import java.math.BigDecimal;
import java.util.List;

import com.corner.core.beans.ItemBase;
import com.corner.core.beans.PlantItem;
import com.corner.core.beans.StoreItem;


public class ItemBaseVo extends ItemBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal areaPrice;
	private String catename;
	private BigDecimal nan_areaprice;
	private BigDecimal fu_areaprice;
	private BigDecimal luo_areaprice;
	private BigDecimal long_areaprice;
	private BigDecimal ping_areaprice;
	private BigDecimal nan_plantDisPrice;
	private BigDecimal fu_plantDisPrice;
	private BigDecimal luo_plantDisPrice;
	private BigDecimal long_plantDisPrice;
	private BigDecimal ping_plantDisPrice;
	
	private BigDecimal nan_marketprice;
	private BigDecimal luo_marketprice;
	private BigDecimal long_marketprice;
	private BigDecimal ping_marketprice;
	private BigDecimal fu_marketprice;
	
	private List<PlantItem> plantItemList;
	
	private List<StoreItem> storeItemList;
	
	private Integer countNum;
	private Integer yiJiId;
	private String yiJiName;
	private Integer erJiId;
	private String erJiName;
	private String upMdseId;
	private String brandName;
	
	/**
	 * 单品促销价格
	 */
	private BigDecimal proPrice;
	/**
	 * 单品促销限购数量
	 */
	private	Integer proLimitNum;
	/**
	 * 单品促销的tag
	 */
	private String tagId;
	

	public List<PlantItem> getPlantItemList() {
		return plantItemList;
	}

	public void setPlantItemList(List<PlantItem> plantItemList) {
		this.plantItemList = plantItemList;
	}

	public List<StoreItem> getStoreItemList() {
		return storeItemList;
	}

	public void setStoreItemList(List<StoreItem> storeItemList) {
		this.storeItemList = storeItemList;
	}
	
	public String getCatename() {
		return catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	public BigDecimal getAreaPrice() {
		return areaPrice;
	}

	public void setAreaPrice(BigDecimal areaPrice) {
		this.areaPrice = areaPrice;
	}

	public BigDecimal getNan_areaprice() {
		return nan_areaprice;
	}

	public void setNan_areaprice(BigDecimal nan_areaprice) {
		this.nan_areaprice = nan_areaprice;
	}

	public BigDecimal getFu_areaprice() {
		return fu_areaprice;
	}

	public void setFu_areaprice(BigDecimal fu_areaprice) {
		this.fu_areaprice = fu_areaprice;
	}

	public BigDecimal getLuo_areaprice() {
		return luo_areaprice;
	}

	public void setLuo_areaprice(BigDecimal luo_areaprice) {
		this.luo_areaprice = luo_areaprice;
	}

	public BigDecimal getLong_areaprice() {
		return long_areaprice;
	}

	public void setLong_areaprice(BigDecimal long_areaprice) {
		this.long_areaprice = long_areaprice;
	}

	public BigDecimal getNan_plantDisPrice() {
		return nan_plantDisPrice;
	}

	public void setNan_plantDisPrice(BigDecimal nan_plantDisPrice) {
		this.nan_plantDisPrice = nan_plantDisPrice;
	}

	public BigDecimal getFu_plantDisPrice() {
		return fu_plantDisPrice;
	}

	public void setFu_plantDisPrice(BigDecimal fu_plantDisPrice) {
		this.fu_plantDisPrice = fu_plantDisPrice;
	}

	public BigDecimal getLuo_plantDisPrice() {
		return luo_plantDisPrice;
	}

	public void setLuo_plantDisPrice(BigDecimal luo_plantDisPrice) {
		this.luo_plantDisPrice = luo_plantDisPrice;
	}

	public BigDecimal getLong_plantDisPrice() {
		return long_plantDisPrice;
	}

	public void setLong_plantDisPrice(BigDecimal long_plantDisPrice) {
		this.long_plantDisPrice = long_plantDisPrice;
	}

	public BigDecimal getPing_areaprice() {
		return ping_areaprice;
	}

	public void setPing_areaprice(BigDecimal ping_areaprice) {
		this.ping_areaprice = ping_areaprice;
	}

	public BigDecimal getPing_plantDisPrice() {
		return ping_plantDisPrice;
	}

	public void setPing_plantDisPrice(BigDecimal ping_plantDisPrice) {
		this.ping_plantDisPrice = ping_plantDisPrice;
	}

	public BigDecimal getNan_marketprice() {
		return nan_marketprice;
	}

	public void setNan_marketprice(BigDecimal nan_marketprice) {
		this.nan_marketprice = nan_marketprice;
	}

	public BigDecimal getLuo_marketprice() {
		return luo_marketprice;
	}

	public void setLuo_marketprice(BigDecimal luo_marketprice) {
		this.luo_marketprice = luo_marketprice;
	}

	public BigDecimal getLong_marketprice() {
		return long_marketprice;
	}

	public void setLong_marketprice(BigDecimal long_marketprice) {
		this.long_marketprice = long_marketprice;
	}

	public BigDecimal getPing_marketprice() {
		return ping_marketprice;
	}

	public void setPing_marketprice(BigDecimal ping_marketprice) {
		this.ping_marketprice = ping_marketprice;
	}

	public BigDecimal getFu_marketprice() {
		return fu_marketprice;
	}

	public void setFu_marketprice(BigDecimal fu_marketprice) {
		this.fu_marketprice = fu_marketprice;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public Integer getYiJiId() {
		return yiJiId;
	}

	public void setYiJiId(Integer yiJiId) {
		this.yiJiId = yiJiId;
	}

	public String getYiJiName() {
		return yiJiName;
	}

	public void setYiJiName(String yiJiName) {
		this.yiJiName = yiJiName;
	}

	public Integer getErJiId() {
		return erJiId;
	}

	public void setErJiId(Integer erJiId) {
		this.erJiId = erJiId;
	}

	public String getErJiName() {
		return erJiName;
	}

	public void setErJiName(String erJiName) {
		this.erJiName = erJiName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUpMdseId() {
		return upMdseId;
	}

	public void setUpMdseId(String upMdseId) {
		this.upMdseId = upMdseId;
	}

	public BigDecimal getProPrice() {
		return proPrice;
	}

	public void setProPrice(BigDecimal proPrice) {
		this.proPrice = proPrice;
	}

	public Integer getProLimitNum() {
		return proLimitNum;
	}

	public void setProLimitNum(Integer proLimitNum) {
		this.proLimitNum = proLimitNum;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
