package com.corner.kefu.beans.ro.sp;

import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;

public class StoreRo extends AmazeUIListRo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 根据商铺ID查询
	private Integer storeid;
	// 根据区域id查询
	private Integer areaid;
	// 根据城市id查询
	private Integer cityid;
	// 根据省id查询
	private Integer provinceid;
	// 根据商铺的编码查询
	private String supplierCode;
	// 根据商铺的名称查询
	private String storeName;
	
	private String sendtimebegin;
	
	private String sendtimeend;
	
	private  String address;
	private  String mobile;
	
	private String keywords;//关键字查询
	
	private Date beginTime;
	private Date endTime;
	
	private String verifyadminId;
	
	private Byte status; 
	
    public String getSendtimebegin() {
		return sendtimebegin;
	}

	public void setSendtimebegin(String sendtimebegin) {
		this.sendtimebegin = sendtimebegin;
	}

	public String getSendtimeend() {
		return sendtimeend;
	}

	public void setSendtimeend(String sendtimeend) {
		this.sendtimeend = sendtimeend;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	//用户id
	private String userid;
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public Integer getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getVerifyadminId() {
		return verifyadminId;
	}

	public void setVerifyadminId(String verifyadminId) {
		this.verifyadminId = verifyadminId;
	}


}
