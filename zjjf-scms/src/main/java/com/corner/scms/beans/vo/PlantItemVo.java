package com.corner.scms.beans.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.corner.core.beans.ro.AmazeUIListRo;

public class PlantItemVo extends AmazeUIListRo {
	
	public PlantItemVo() {
		super();
	}
	
	private String id;

	private Integer itemBaseId;

	private Integer spGroupId;

	private String spId;

	private Integer areaId;

	private String areaName;

	private BigDecimal areaPrice;

	private BigDecimal plantMemPrice;

	private BigDecimal plantDisPrice;

	private BigDecimal maoli;

	private BigDecimal fee;

	private Integer ordId;

	private Date addTime;

	private Date updateTime;

	private Boolean tuijian;

	private Integer goodsStock;

	private Integer middleStock;

	private Integer upper;

	private Integer lower;

	private BigDecimal scInPrice;

	private Integer sales;

	private Integer clickRate;

	private String remark;

	private String youHui;

	private Integer restrict;

	private Byte status;

	private Boolean isDelete;
	
	
	//------------------------------------------接收字段
	//商品管理
	private List<ScmsUserTypeVo> userTypes = new ArrayList<ScmsUserTypeVo>();//用户类型
	//商品名称
	private String name;
	//商品规格
	private String spec;
	//商品计量单位/后面没用了/
	private String measure;
	//商品计量单位
	private String pkg;
	//商品条形码/编号
	private String mdseId;
	
	private String imgS;//商品小图片地址
	//库存总量
	private Integer countStock;
	//在途库存
	private Integer sendStock;
	
	private String groupName;
	//------------------------------------------公用字段
	private String cateId;//商品类别
	
	private String cateNameT;
	private String cateNameO;
	//------------------------------------------条件字段
	//条形码或名称
	private String noAndName;
	//商品下线
	private Integer goodsLower;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Integer getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(Integer itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public Integer getSpGroupId() {
		return spGroupId;
	}

	public void setSpGroupId(Integer spGroupId) {
		this.spGroupId = spGroupId;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId == null ? null : spId.trim();
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}

	public BigDecimal getAreaPrice() {
		return areaPrice;
	}

	public void setAreaPrice(BigDecimal areaPrice) {
		this.areaPrice = areaPrice;
	}

	public BigDecimal getPlantMemPrice() {
		return plantMemPrice;
	}

	public void setPlantMemPrice(BigDecimal plantMemPrice) {
		this.plantMemPrice = plantMemPrice;
	}

	public BigDecimal getPlantDisPrice() {
		return plantDisPrice;
	}

	public void setPlantDisPrice(BigDecimal plantDisPrice) {
		this.plantDisPrice = plantDisPrice;
	}

	public BigDecimal getMaoli() {
		return maoli;
	}

	public void setMaoli(BigDecimal maoli) {
		this.maoli = maoli;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Integer getOrdId() {
		return ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	
	public Date getUpdateTime() {
		return updateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMdseId() {
		return mdseId;
	}

	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	

	public Boolean getTuijian() {
		return tuijian;
	}

	public void setTuijian(Boolean tuijian) {
		this.tuijian = tuijian;
	}

	public Integer getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}

	public Integer getMiddleStock() {
		return middleStock;
	}

	public void setMiddleStock(Integer middleStock) {
		this.middleStock = middleStock;
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

	public BigDecimal getScInPrice() {
		return scInPrice;
	}

	public void setScInPrice(BigDecimal scInPrice) {
		this.scInPrice = scInPrice;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getClickRate() {
		return clickRate;
	}

	public void setClickRate(Integer clickRate) {
		this.clickRate = clickRate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getYouHui() {
		return youHui;
	}

	public void setYouHui(String youHui) {
		this.youHui = youHui == null ? null : youHui.trim();
	}

	public Integer getRestrict() {
		return restrict;
	}

	public void setRestrict(Integer restrict) {
		this.restrict = restrict;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}


	public List<ScmsUserTypeVo> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(List<ScmsUserTypeVo> userTypes) {
		this.userTypes = userTypes;
	}

	public String getImgS() {
		return imgS;
	}

	public void setImgS(String imgS) {
		this.imgS = imgS;
	}

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getNoAndName() {
		return noAndName;
	}

	public void setNoAndName(String noAndName) {
		this.noAndName = noAndName;
	}

	public Integer getCountStock() {
		return countStock;
	}

	public void setCountStock(Integer countStock) {
		this.countStock = countStock;
	}

	public Integer getGoodsLower() {
		return goodsLower;
	}

	public void setGoodsLower(Integer goodsLower) {
		this.goodsLower = goodsLower;
	}

	public Integer getSendStock() {
		return sendStock;
	}

	public void setSendStock(Integer sendStock) {
		this.sendStock = sendStock;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCateNameT() {
		return cateNameT;
	}

	public void setCateNameT(String cateNameT) {
		this.cateNameT = cateNameT;
	}

	public String getCateNameO() {
		return cateNameO;
	}

	public void setCateNameO(String cateNameO) {
		this.cateNameO = cateNameO;
	}

	
	
}
