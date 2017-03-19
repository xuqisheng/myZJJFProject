package com.corner.core.beans;

import java.util.Date;
import java.util.List;

import com.corner.core.beans.ro.AmazeUIListRo;
import com.corner.core.utils.StringUtil;

public class StoreGroup extends AmazeUIListRo{
    private String id=StringUtil.getUUID();

    private Integer number;

    private String supplierId;

    private Date date;

    private String remark;

    private String name;
    
    
    
    
    

   


	public String getId() {
        return id;
    }
    
    

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}