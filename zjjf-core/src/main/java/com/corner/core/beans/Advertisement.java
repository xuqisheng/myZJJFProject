package com.corner.core.beans;

import java.io.Serializable;
import java.util.Date;

public class Advertisement implements Serializable,Cloneable{
    private Integer id;

    private Integer boardId;

    private Integer spGroupId;

    private Boolean asDefault;

    private String name;

    private String url;

    private String extimg;

    private String desc;

    private Boolean isClick;

    private Byte clickType;

    private Integer classId;

    private String itemTagId;

    private Integer brandId;

    private String linkUrl;

    private Date startTime;

    private Date endTime;

    private Date addTime;

    private Integer ordId;

    private Byte status;

    private Boolean isDelete;

    private String col1;

    private String col2;

    private String col3;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getSpGroupId() {
        return spGroupId;
    }

    public void setSpGroupId(Integer spGroupId) {
        this.spGroupId = spGroupId;
    }

    public Boolean getAsDefault() {
        return asDefault;
    }

    public void setAsDefault(Boolean asDefault) {
        this.asDefault = asDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getExtimg() {
        return extimg;
    }

    public void setExtimg(String extimg) {
        this.extimg = extimg == null ? null : extimg.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Boolean getIsClick() {
        return isClick;
    }

    public void setIsClick(Boolean isClick) {
        this.isClick = isClick;
    }

    public Byte getClickType() {
        return clickType;
    }

    public void setClickType(Byte clickType) {
        this.clickType = clickType;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getItemTagId() {
        return itemTagId;
    }

    public void setItemTagId(String itemTagId) {
        this.itemTagId = itemTagId == null ? null : itemTagId.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getOrdId() {
        return ordId;
    }

    public void setOrdId(Integer ordId) {
        this.ordId = ordId;
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

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1 == null ? null : col1.trim();
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2 == null ? null : col2.trim();
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3 == null ? null : col3.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}