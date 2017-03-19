package com.corner.kefu.beans.ro.sp;

import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;



public class SpFeedbackRo extends AmazeUIListRo {
 
	private static final long serialVersionUID = 1L;
	private String text;
	
	private Integer storeId;
	private String title;
	private String content;
	private Date today;
	private Date beginTime;
	private Date endTime;
	private String appVersion;
	
	private String checkerId;
	private String checkerNm;
	private Byte sendUser;
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}

	public String getCheckerNm() {
		return checkerNm;
	}

	public void setCheckerNm(String checkerNm) {
		this.checkerNm = checkerNm;
	}

	public Byte getSendUser() {
		return sendUser;
	}

	public void setSendUser(Byte sendUser) {
		this.sendUser = sendUser;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

}
