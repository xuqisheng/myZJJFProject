package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;
import java.util.Date;

import com.corner.core.beans.SpFeedback;


public class SpFeedbackVo extends SpFeedback implements Serializable{

	private static final long serialVersionUID = 1L;

	private String storeName;

	private String storeMobile;
	
	private Date lastTime;
	
	private String pic1;
	private String pic2;
	private String pic3;
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreMobile() {
		return storeMobile;
	}

	public void setStoreMobile(String storeMobile) {
		this.storeMobile = storeMobile;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
}
