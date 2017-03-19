package com.corner.scms.beans.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.corner.core.beans.ItemBase;

public class ScmsItemBaseVo extends ItemBase implements Serializable{

	private String scmsid;//scmsitem 的id
	
	private BigDecimal zjjfPrice;//转角售价
	
	private Integer year;//生成年份
	
	private Integer month;//生成 月份
	
	private String houseAddress;//仓库的地址
	
	private String goodName;//商品名称
	
	private String mark;
	
	private String upMdseId;
	

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public BigDecimal getZjjfPrice() {
		return zjjfPrice;
	}

	public void setZjjfPrice(BigDecimal zjjfPrice) {
		this.zjjfPrice = zjjfPrice;
	}

	public String getScmsid() {
		return scmsid;
	}

	public void setScmsid(String scmsid) {
		this.scmsid = scmsid;
	}

	public String getUpMdseId() {
		return upMdseId;
	}

	public void setUpMdseId(String upMdseId) {
		this.upMdseId = upMdseId;
	}
	
	
	
}
