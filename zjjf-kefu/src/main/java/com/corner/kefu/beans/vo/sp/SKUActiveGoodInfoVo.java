package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;
import java.math.BigDecimal;

import com.corner.core.beans.SKUActiveGoodInfo;
/**
 * 
 * @ClassName: SKUActiveGoodInfoVo
 * @Description: 
 * @author 小武
 * @version 飓风
 * @date 2016年9月2日 上午10:06:15
 *
 */
public class SKUActiveGoodInfoVo extends SKUActiveGoodInfo implements Serializable {

	/**
	 * @desc  
	 * @date 2016年9月2日  上午10:05:53
	 * @author 小武
	 * @version  飓风
	 */
	private static final long serialVersionUID = 1L;
	
	private String spGroupName; // 所属定格名称
	
	private String supplierName; //批发商名称
	
	private String mdseId; //条码
	
	private String itemBaseName; //商品名称
	
	private BigDecimal SKUProPrice; // 限购价格
	
	private Integer SKUProLimitNum; //限购数量
	
	public String getSpGroupName() {
		return spGroupName;
	}
	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getMdseId() {
		return mdseId;
	}
	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}
	
	public BigDecimal getSKUProPrice() {
		return SKUProPrice;
	}
	public void setSKUProPrice(BigDecimal sKUProPrice) {
		SKUProPrice = sKUProPrice;
	}
	public Integer getSKUProLimitNum() {
		return SKUProLimitNum;
	}
	public void setSKUProLimitNum(Integer sKUProLimitNum) {
		SKUProLimitNum = sKUProLimitNum;
	}
	public String getItemBaseName() {
		return itemBaseName;
	}
	public void setItemBaseName(String itemBaseName) {
		this.itemBaseName = itemBaseName;
	}
	
}
