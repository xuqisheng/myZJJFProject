package com.corner.account.beans.vo;

import java.math.BigDecimal;

import com.corner.core.beans.SpWithDraw;

public class SpWalletCheckVo  extends SpWithDraw{
	
    private String spId;
    private Integer areaId;
    private String supplierCode;
    private String supplierName;
    private BigDecimal spWithDraw;
    private BigDecimal spPayOut;
    private BigDecimal spOrderIn;
    private BigDecimal spButieIn;
    private BigDecimal spCouponIn;
    private BigDecimal spWxPoundage;
    private BigDecimal spWdPoundage;
    private BigDecimal spFreightIn;
    
	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public BigDecimal getSpWithDraw() {
		return spWithDraw;
	}
	public void setSpWithDraw(BigDecimal spWithDraw) {
		this.spWithDraw = spWithDraw;
	}
	public BigDecimal getSpPayOut() {
		return spPayOut;
	}
	public void setSpPayOut(BigDecimal spPayOut) {
		this.spPayOut = spPayOut;
	}
	public BigDecimal getSpOrderIn() {
		return spOrderIn;
	}
	public void setSpOrderIn(BigDecimal spOrderIn) {
		this.spOrderIn = spOrderIn;
	}
	public BigDecimal getSpButieIn() {
		return spButieIn;
	}
	public void setSpButieIn(BigDecimal spButieIn) {
		this.spButieIn = spButieIn;
	}
	public BigDecimal getSpCouponIn() {
		return spCouponIn;
	}
	public void setSpCouponIn(BigDecimal spCouponIn) {
		this.spCouponIn = spCouponIn;
	}
	public BigDecimal getSpFreightIn() {
		return spFreightIn;
	}
	public void setSpFreightIn(BigDecimal spFreightIn) {
		this.spFreightIn = spFreightIn;
	}
	public BigDecimal getSpWxPoundage() {
		return spWxPoundage;
	}
	public void setSpWxPoundage(BigDecimal spWxPoundage) {
		this.spWxPoundage = spWxPoundage;
	}
	public BigDecimal getSpWdPoundage() {
		return spWdPoundage;
	}
	public void setSpWdPoundage(BigDecimal spWdPoundage) {
		this.spWdPoundage = spWdPoundage;
	}
 
    
}
