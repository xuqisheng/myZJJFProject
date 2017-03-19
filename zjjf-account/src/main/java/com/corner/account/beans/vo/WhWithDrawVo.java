package com.corner.account.beans.vo;

import java.math.BigDecimal;

import com.corner.core.beans.MaWithDraw;

public class WhWithDrawVo  extends MaWithDraw{

	private String whId;
	private Integer county;
	private String name;
    private String houseCode;

    private String walletId;
    private BigDecimal wallet;
    
	

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public BigDecimal getWallet() {
		return wallet;
	}

	public void setWallet(BigDecimal wallet) {
		this.wallet = wallet;
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

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public Integer getCounty() {
		return county;
	}

	public void setCounty(Integer county) {
		this.county = county;
	}
}
