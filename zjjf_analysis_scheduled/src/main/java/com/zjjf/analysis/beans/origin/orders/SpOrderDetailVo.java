package com.zjjf.analysis.beans.origin.orders;

import java.math.BigDecimal;

import com.zjjf.analysis.beans.analysis.items.PlantItemDaily;

public class SpOrderDetailVo extends PlantItemDaily {

	private BigDecimal totalPrice = new BigDecimal(0);

	private BigDecimal plantMemPrice = new BigDecimal(0);

	private BigDecimal price = new BigDecimal(0);

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getPlantMemPrice() {
		return plantMemPrice;
	}

	public void setPlantMemPrice(BigDecimal plantMemPrice) {
		this.plantMemPrice = plantMemPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}