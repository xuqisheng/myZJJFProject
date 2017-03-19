package com.corner.scms.beans.ro;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;

public class PromotionRo extends AmazeUIListRo {
	
	private Date ruleStart;
	
	private Date ruleEnd;
	
	private Integer ruleType;
	
	private String ruleName;
	
	private String supplierId;
	
	private Integer spVoucherActiveId;
	
	private String number;
	
	private String count;
	
	private String []ids;
	
	private String scmsItemId;
	
	private Integer numberInt;
	
	private Integer status;
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private Integer countInt;
	
	private String id;
	
	private String itemBaseName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemBaseName() {
		return itemBaseName;
	}

	public void setItemBaseName(String itemBaseName) {
		this.itemBaseName = itemBaseName;
	}

	public String getScmsItemId() {
		return scmsItemId;
	}

	public void setScmsItemId(String scmsItemId) {
		this.scmsItemId = scmsItemId;
	}

	public Integer getSpVoucherActiveId() {
		return spVoucherActiveId;
	}

	public void setSpVoucherActiveId(Integer spVoucherActiveId) {
		this.spVoucherActiveId = spVoucherActiveId;
	}

	public Integer getNumberInt() {
		return numberInt;
	}

	public void setNumberInt(Integer numberInt) {
		this.numberInt = numberInt;
	}

	public Integer getCountInt() {
		return countInt;
	}

	public void setCountInt(Integer countInt) {
		this.countInt = countInt;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}


	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Date getRuleStart() {
		return ruleStart;
	}

	public void setRuleStart(Date ruleStart) {
		this.ruleStart = ruleStart;
	}

	public Date getRuleEnd() {
		return ruleEnd;
	}

	public void setRuleEnd(Date ruleEnd) {
		this.ruleEnd = ruleEnd;
	}

	public Integer getRuleType() {
		return ruleType;
	}

	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	
	
	
}
