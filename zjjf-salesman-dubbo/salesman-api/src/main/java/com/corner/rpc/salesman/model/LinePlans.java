package com.corner.rpc.salesman.model;

import java.util.HashMap;
import java.util.List;

import com.corner.salesman.commons.persistence.BaseEntity;

public class LinePlans extends BaseEntity<LinePlans> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lineId;

    private String lineName;

    //private String salesmanId;

    private String salesmanId;

    private Integer shopTotal;

    private Integer visitTotal;

    private String week;
    
    private String lineStr;
    
	private String shopNo;
	
	private List<HashMap<String,Object>> shopList;

    public String getLineStr() {
		return lineStr;
	}

	public void setLineStr(String lineStr) {
		this.lineStr = lineStr;
	}

	public List<HashMap<String, Object>> getShopList() {
		return shopList;
	}

	public void setShopList(List<HashMap<String, Object>> shopList) {
		this.shopList = shopList;
	}

	public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId == null ? null : lineId.trim();
    }

    public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName == null ? null : lineName.trim();
    }

    public String getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}

	public Integer getShopTotal() {
        return shopTotal;
    }

    public void setShopTotal(Integer shopTotal) {
        this.shopTotal = shopTotal;
    }

    public Integer getVisitTotal() {
        return visitTotal;
    }

    public void setVisitTotal(Integer visitTotal) {
		this.visitTotal = visitTotal;
	}

	public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }
}