/**   
* @Title: PlantItemVo.java 
* @Package com.corner.kefu.beans.vo.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月24日 上午11:42:14 
* @version V1.0   
*/

package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;
import java.math.BigDecimal;

import com.corner.core.beans.PlantItem;

/** 
* @ClassName: PlantItemVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月24日 上午11:42:14 
*  
*/

public class PlantItemVo extends PlantItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;// 商品名
	private String brand;// 品牌
	private String spec;// 规格
	private String pkg;// 单位
	private Integer goodsLower;
	private BigDecimal price;// 市场价
	private String mdseId;// 条形码
	private Integer cateId;// 商品分类id
	private String imgS;// 商品小图片
	private BigDecimal yhprice;// 优惠百分比
	private BigDecimal pfPrice;// 批发价格
	private Integer sales;
	private String catename;// 一级分类名
	private String secCatename;// 二级分类名
	private String spGroupName;// 所属定格名称
	private BigDecimal disPrice;
	private BigDecimal butieMoney;
	private Integer restrictx;
	private String isNew;

	private Integer goodsCount;// 库存预警数
	private String supplierName;// 批发商名称
	private String mobile;// 批发商手机号

	private String spGroupIdStr;// 定格id字符串

	private Integer brandId;// 品牌Id
	public BigDecimal getButieMoney() {
		return butieMoney;
	}

	public void setButieMoney(BigDecimal butieMoney) {
		this.butieMoney = butieMoney;
	}

	public String getCatename() {
		return catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	@Override
	public Integer getSales() {
		return sales;
	}

	@Override
	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public BigDecimal getPfPrice() {
		return pfPrice;
	}

	public void setPfPrice(BigDecimal pfPrice) {
		this.pfPrice = pfPrice;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getMdseId() {
		return mdseId;
	}

	public BigDecimal getDisPrice() {
		return disPrice;
	}

	public void setDisPrice(BigDecimal disPrice) {
		this.disPrice = disPrice;
	}

	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public String getImgS() {
		return imgS;
	}

	public void setImgS(String imgS) {
		this.imgS = imgS;
	}

	public BigDecimal getYhprice() {
		if (getPlantMemPrice() != null && getAreaPrice() != null && getPlantMemPrice().compareTo(new BigDecimal("0")) > 0  && getAreaPrice().compareTo(new BigDecimal("0")) > 0) {
			if (getPlantMemPrice().compareTo(getAreaPrice()) > -1) {
				yhprice = ((getPlantMemPrice().subtract(getAreaPrice())).divide(getPlantMemPrice(), 2,
						BigDecimal.ROUND_HALF_DOWN)).multiply(new BigDecimal(100));
			}
		}
		return yhprice;
	}

	public void setYhprice(BigDecimal yhprice) {
		this.yhprice = yhprice;
	}

	public String getSecCatename() {
		return secCatename;
	}

	public void setSecCatename(String secCatename) {
		this.secCatename = secCatename;
	}

	public String getSpGroupName() {
		return spGroupName;
	}

	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}

	public Integer getRestrictx() {
		this.restrictx = super.getRestrict();
		return restrictx;
	}

	public void setRestrictx(Integer restrictx) {
		this.restrictx = restrictx;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public Integer getGoodsLower() {
		return goodsLower;
	}

	public void setGoodsLower(Integer goodsLower) {
		this.goodsLower = goodsLower;
	}

	public String getSpGroupIdStr() {
		return spGroupIdStr;
	}

	public void setSpGroupIdStr(String spGroupIdStr) {
		this.spGroupIdStr = spGroupIdStr;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	
}
