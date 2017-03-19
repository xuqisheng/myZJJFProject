package com.corner.scms.beans.vo;

import java.math.BigDecimal;
import java.util.Date;

public class SpvoucherVo {

	private String name;
	
	private String orderId;
	
	private BigDecimal money;
	
	private BigDecimal plantHalve;
	
	private Date time;

	
	
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	

	public BigDecimal getPlantHalve() {
		return plantHalve;
	}

	public void setPlantHalve(BigDecimal plantHalve) {
		this.plantHalve = plantHalve;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
