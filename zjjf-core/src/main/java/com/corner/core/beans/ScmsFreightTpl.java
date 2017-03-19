package com.corner.core.beans;

import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;
import com.corner.core.utils.StringUtil;

public class ScmsFreightTpl extends AmazeUIListRo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id = StringUtil.getUUID();

    private String name;

    private String tplMark;

    private Byte paidMethods;

    private Date addTime;

    private Byte status;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTplMark() {
        return tplMark;
    }

    public void setTplMark(String tplMark) {
        this.tplMark = tplMark == null ? null : tplMark.trim();
    }

    public Byte getPaidMethods() {
        return paidMethods;
    }

    public void setPaidMethods(Byte paidMethods) {
        this.paidMethods = paidMethods;
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
}