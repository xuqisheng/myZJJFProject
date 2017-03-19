package com.corner.core.beans;

import java.util.Date;

public class AppModule{
    private String id;

    private String code;

    private String name;

    private String remark;

    private Date beginTime;

    private Date stopTime;

    private String viewImg;

    private Byte moduleType;

    private Integer mdWidth;

    private Integer mdHight;

    private String cfgUrl;

    private Integer cfgPageIndex;

    private Integer cfgPageSize;

    private Integer cfgAdBoardId;

    private String cfgTagId;

    private Boolean seeAble;

    private Integer orderId;

    private Date addTime;

    private Date updateTime;

    private Byte status;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getViewImg() {
        return viewImg;
    }

    public void setViewImg(String viewImg) {
        this.viewImg = viewImg == null ? null : viewImg.trim();
    }

    public Byte getModuleType() {
        return moduleType;
    }

    public void setModuleType(Byte moduleType) {
        this.moduleType = moduleType;
    }

    public Integer getMdWidth() {
        return mdWidth;
    }

    public void setMdWidth(Integer mdWidth) {
        this.mdWidth = mdWidth;
    }

    public Integer getMdHight() {
        return mdHight;
    }

    public void setMdHight(Integer mdHight) {
        this.mdHight = mdHight;
    }

    public String getCfgUrl() {
        return cfgUrl;
    }

    public void setCfgUrl(String cfgUrl) {
        this.cfgUrl = cfgUrl == null ? null : cfgUrl.trim();
    }

    public Integer getCfgPageIndex() {
        return cfgPageIndex;
    }

    public void setCfgPageIndex(Integer cfgPageIndex) {
        this.cfgPageIndex = cfgPageIndex;
    }

    public Integer getCfgPageSize() {
        return cfgPageSize;
    }

    public void setCfgPageSize(Integer cfgPageSize) {
        this.cfgPageSize = cfgPageSize;
    }

    public Integer getCfgAdBoardId() {
        return cfgAdBoardId;
    }

    public void setCfgAdBoardId(Integer cfgAdBoardId) {
        this.cfgAdBoardId = cfgAdBoardId;
    }

    public String getCfgTagId() {
        return cfgTagId;
    }

    public void setCfgTagId(String cfgTagId) {
        this.cfgTagId = cfgTagId == null ? null : cfgTagId.trim();
    }

    public Boolean getSeeAble() {
        return seeAble;
    }

    public void setSeeAble(Boolean seeAble) {
        this.seeAble = seeAble;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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