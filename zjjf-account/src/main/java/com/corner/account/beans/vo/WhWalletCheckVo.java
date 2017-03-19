package com.corner.account.beans.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.beans.MaWithDraw;

public class WhWalletCheckVo  extends MaWithDraw{
	
    private String whId;
    private Integer areaId;
    private String name;
    private String houseCode;
    private BigDecimal wallet;
    private Date walletAddTime;
    private Integer walletStauts;
    private Integer payer;
    private Integer geter;
    private BigDecimal whOrderIn;
    private BigDecimal whPayOut;
    private BigDecimal whButieIn;
    private BigDecimal whCouponIn;
    private BigDecimal whWxPoundage;
    private BigDecimal whFreightIn;
    private BigDecimal whWithDraw;
    private BigDecimal whWdPoundage;
    
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getPayer() {
		return payer;
	}
	public void setPayer(Integer payer) {
		this.payer = payer;
	}
	public Integer getGeter() {
		return geter;
	}
	public void setGeter(Integer geter) {
		this.geter = geter;
	}
	public BigDecimal getWallet() {
		return wallet;
	}
	public void setWallet(BigDecimal wallet) {
		this.wallet = wallet;
	}
	public Date getWalletAddTime() {
		return walletAddTime;
	}
	public void setWalletAddTime(Date walletAddTime) {
		this.walletAddTime = walletAddTime;
	}
	public Integer getWalletStauts() {
		return walletStauts;
	}
	public void setWalletStauts(Integer walletStauts) {
		this.walletStauts = walletStauts;
	}
	public BigDecimal getWhOrderIn() {
		return whOrderIn;
	}
	public void setWhOrderIn(BigDecimal whOrderIn) {
		this.whOrderIn = whOrderIn;
	}
	public BigDecimal getWhPayOut() {
		return whPayOut;
	}
	public void setWhPayOut(BigDecimal whPayOut) {
		this.whPayOut = whPayOut;
	}
	public BigDecimal getWhButieIn() {
		return whButieIn;
	}
	public void setWhButieIn(BigDecimal whButieIn) {
		this.whButieIn = whButieIn;
	}
	public BigDecimal getWhCouponIn() {
		return whCouponIn;
	}
	public void setWhCouponIn(BigDecimal whCouponIn) {
		this.whCouponIn = whCouponIn;
	}
	public BigDecimal getWhWxPoundage() {
		return whWxPoundage;
	}
	public void setWhWxPoundage(BigDecimal whWxPoundage) {
		this.whWxPoundage = whWxPoundage;
	}
	public BigDecimal getWhFreightIn() {
		return whFreightIn;
	}
	public void setWhFreightIn(BigDecimal whFreightIn) {
		this.whFreightIn = whFreightIn;
	}
	public BigDecimal getWhWithDraw() {
		return whWithDraw;
	}
	public void setWhWithDraw(BigDecimal whWithDraw) {
		this.whWithDraw = whWithDraw;
	}
	public BigDecimal getWhWdPoundage() {
		return whWdPoundage;
	}
	public void setWhWdPoundage(BigDecimal whWdPoundage) {
		this.whWdPoundage = whWdPoundage;
	}
	public String getHouseCode() {
		return houseCode;
	}
	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	public String getWhId() {
		return whId;
	}
	public void setWhId(String whId) {
		this.whId = whId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
