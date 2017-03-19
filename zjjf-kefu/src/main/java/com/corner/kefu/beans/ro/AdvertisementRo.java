package com.corner.kefu.beans.ro;

import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;


public class AdvertisementRo extends AmazeUIListRo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer boardId;

    private Integer spGroupId;

    private Boolean asDefault;

    private String name;

    private String url;

    private String extimg;

    private String desc;

    private Integer classId;

    private String itemTagId;

    private String linkUrl;

    private Date startTime;

    private Date endTime;

    private Date addTime;

    private Boolean isClick;

    private Byte clickType;

    private Integer ordId;

    private Byte status;

    private Boolean isDelete;

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
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExtimg() {
		return extimg;
	}

	public void setExtimg(String extimg) {
		this.extimg = extimg;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
		this.itemTagId = itemTagId;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	


}
