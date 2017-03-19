package com.corner.rpc.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class ConfigShareVo extends ConfigShare implements Serializable {
	private static final long serialVersionUID = 8960423461807458468L;
	private Integer ruleId;
	private String ruleName;
	private BigDecimal useMoney;
	private Byte useDay;
	private BigDecimal startPrice;
	private String ruleRemark;
	private Byte billType;
	private Byte payType;
	private String payStr;
	private Byte useItemFlag;
	private String useItemIds;
	
	
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public BigDecimal getUseMoney() {
		return useMoney;
	}
	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
	public BigDecimal getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}
	public Byte getUseDay() {
		return useDay;
	}
	public void setUseDay(Byte useDay) {
		this.useDay = useDay;
	}
	public String getRuleRemark() {
		return ruleRemark;
	}
	public void setRuleRemark(String ruleRemark) {
		this.ruleRemark = ruleRemark;
	}
	public Byte getBillType() {
		return billType;
	}
	public void setBillType(Byte billType) {
		this.billType = billType;
	}
	public Byte getPayType() {
		return payType;
	}
	public void setPayType(Byte payType) {
		this.payType = payType;
	}
	public String getPayStr() {
		return payStr;
	}
	public void setPayStr(String payStr) {
		this.payStr = payStr;
	}
	public Byte getUseItemFlag() {
		return useItemFlag;
	}
	public void setUseItemFlag(Byte useItemFlag) {
		this.useItemFlag = useItemFlag;
	}
	public String getUseItemIds() {
		return useItemIds;
	}
	public void setUseItemIds(String useItemIds) {
		this.useItemIds = useItemIds;
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

}
