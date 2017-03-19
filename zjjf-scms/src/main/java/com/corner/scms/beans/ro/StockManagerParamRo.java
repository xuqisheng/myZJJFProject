package com.corner.scms.beans.ro;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;

public class StockManagerParamRo extends AmazeUIListRo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockManagerParamRo() {
		super();
	}
	//库存量
	private Integer beginGoodsStock;
	private Integer endGoodsStock;
	
	
	//时间（记录表里面的）
	private Date beginTime;
	
	private Date endTime;
	//进货价
	private BigDecimal plantDisPrice;
	//修改时间
	private Date updateTime;
	//动态进货价
	private BigDecimal scInPrice;
	//出入库数量
	private Integer num;
	
	private Integer goodsStock;
	//类型（记录表里面的）
	private Byte xType;
	//库存上限
	private Integer upper;
	//库存下限
	private Integer lower;
	//商品名称
	private String commodityName;
	//商品编号/名称
	private String commodityIdAndName;
	
	private Integer itemBaseId;
	
	private String spId; 

	//备注
	private String remark;
	
	private String id; 

	public String getCommodityIdAndName() {
		return commodityIdAndName;
	}

	public void setCommodityIdAndName(String commodityIdAndName) {
		this.commodityIdAndName = commodityIdAndName;
	}
	

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getUpper() {
		return upper;
	}

	public void setUpper(Integer upper) {
		this.upper = upper;
	}

	public Integer getLower() {
		return lower;
	}

	public void setLower(Integer lower) {
		this.lower = lower;
	}

	public Integer getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(Integer itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public Integer getBeginGoodsStock() {
		return beginGoodsStock;
	}

	public BigDecimal getPlantDisPrice() {
		return plantDisPrice;
	}

	public void setPlantDisPrice(BigDecimal plantDisPrice) {
		this.plantDisPrice = plantDisPrice;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getScInPrice() {
		return scInPrice;
	}

	public void setScInPrice(BigDecimal scInPrice) {
		this.scInPrice = scInPrice;
	}

	public Integer getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}

	public void setBeginGoodsStock(Integer beginGoodsStock) {
		this.beginGoodsStock = beginGoodsStock;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Integer getEndGoodsStock() {
		return endGoodsStock;
	}

	public Byte getxType() {
		return xType;
	}

	public void setxType(Byte xType) {
		this.xType = xType;
	}

	public void setEndGoodsStock(Integer endGoodsStock) {
		this.endGoodsStock = endGoodsStock;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
