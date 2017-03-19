package com.corner.scms.beans.vo;

import java.util.Date;

public class ScmsStockLogVo {
    public ScmsStockLogVo() {
		super();
	}

	private String id;

    private String plantItemId;

    private String spId;

    private Byte xtype;

    private Integer quantity;

    private Integer curStock;

    private Date addTime;

    private Byte status;

    private Boolean isDelete;
  	//商品名称
  	private String name;
  	//商品规格
  	private String spec;
  	//商品计量单位
  	private String measure;
  	//商品编号
  	private String mdseId;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPlantItemId() {
        return plantItemId;
    }

    public void setPlantItemId(String plantItemId) {
        this.plantItemId = plantItemId == null ? null : plantItemId.trim();
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    public Byte getXtype() {
        return xtype;
    }

    public void setXtype(Byte xtype) {
        this.xtype = xtype;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCurStock() {
        return curStock;
    }

    public void setCurStock(Integer curStock) {
        this.curStock = curStock;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getMdseId() {
		return mdseId;
	}

	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}

	
}