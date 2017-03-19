package com.zjjf.analysis.beans.analysis.items;

import java.math.BigDecimal;

public class PlantItemDailyVo extends PlantItemDaily {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String classifi_one;

	private String classifi_two;

	private Integer classOneId;

	private Integer classTwoId;

	private String barCode;

	private String name;

	private String spec;

	private BigDecimal totalTurnover = new BigDecimal(0);

	private BigDecimal averageTurnover = new BigDecimal(0);

	private BigDecimal averagePrice = new BigDecimal(0);

	public String getClassifi_one() {
		return classifi_one;
	}

	public void setClassifi_one(String classifi_one) {
		this.classifi_one = classifi_one;
	}

	public String getClassifi_two() {
		return classifi_two;
	}

	public void setClassifi_two(String classifi_two) {
		this.classifi_two = classifi_two;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public BigDecimal getAverageTurnover() {
		return averageTurnover;
	}

	public void setAverageTurnover(BigDecimal averageTurnover) {
		this.averageTurnover = averageTurnover;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public BigDecimal getTotalTurnover() {
		return totalTurnover;
	}

	public void setTotalTurnover(BigDecimal totalTurnover) {
		this.totalTurnover = totalTurnover;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public Integer getClassOneId() {
		return classOneId;
	}

	public void setClassOneId(Integer classOneId) {
		this.classOneId = classOneId;
	}

	public Integer getClassTwoId() {
		return classTwoId;
	}

	public void setClassTwoId(Integer classTwoId) {
		this.classTwoId = classTwoId;
	}

}