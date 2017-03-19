package com.corner.account.beans.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.beans.MaWithDraw;

public class MaWalletCheckVo  extends MaWithDraw{
	
    private String maId;
    private Integer areaId;
    private String managerCode;
    private String managerName;
    private BigDecimal wallet;
    private Date walletAddTime;
    private Integer walletStauts;
    private Integer payer;
    private Integer geter;
    private BigDecimal maOrderIn;
    private BigDecimal maPayOut;
    private BigDecimal maButieIn;
    private BigDecimal maCouponIn;
    private BigDecimal maWxPoundage;
    private BigDecimal maFreightIn;
    private BigDecimal maWithDraw;
    private BigDecimal maWdPoundage;
    
	public String getMaId() {
		return maId;
	}
	public void setMaId(String maId) {
		this.maId = maId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
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
	public BigDecimal getMaOrderIn() {
		return maOrderIn;
	}
	public void setMaOrderIn(BigDecimal maOrderIn) {
		this.maOrderIn = maOrderIn;
	}
	public BigDecimal getMaPayOut() {
		return maPayOut;
	}
	public void setMaPayOut(BigDecimal maPayOut) {
		this.maPayOut = maPayOut;
	}
	public BigDecimal getMaButieIn() {
		return maButieIn;
	}
	public void setMaButieIn(BigDecimal maButieIn) {
		this.maButieIn = maButieIn;
	}
	public BigDecimal getMaCouponIn() {
		return maCouponIn;
	}
	public void setMaCouponIn(BigDecimal maCouponIn) {
		this.maCouponIn = maCouponIn;
	}
	public BigDecimal getMaWxPoundage() {
		return maWxPoundage;
	}
	public void setMaWxPoundage(BigDecimal maWxPoundage) {
		this.maWxPoundage = maWxPoundage;
	}
	public BigDecimal getMaFreightIn() {
		return maFreightIn;
	}
	public void setMaFreightIn(BigDecimal maFreightIn) {
		this.maFreightIn = maFreightIn;
	}
	public BigDecimal getMaWithDraw() {
		return maWithDraw;
	}
	public void setMaWithDraw(BigDecimal maWithDraw) {
		this.maWithDraw = maWithDraw;
	}
	public BigDecimal getMaWdPoundage() {
		return maWdPoundage;
	}
	public void setMaWdPoundage(BigDecimal maWdPoundage) {
		this.maWdPoundage = maWdPoundage;
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

}
