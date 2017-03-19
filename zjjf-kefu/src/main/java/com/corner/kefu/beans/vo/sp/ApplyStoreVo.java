package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;
import java.util.Date;

import com.corner.core.beans.StoreInfo;

public class ApplyStoreVo extends StoreInfo implements Serializable{
	/**
	 * store表的数据
	 */
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String mobile;
	private String name;
	private String contact;
	private String tel;
	private String bankAccount;
	private String bankName;
	private String bankCard;
	private String img;
	private String address;
	private Date addtime;
	private Byte status;
	private String lng;
	private String lat;
	private String suppliercode;
	private Integer provinceId;
	private Integer cityId;
	private Integer areaId;
	private Integer streetId;
	private Integer spGroupId;
	private Integer fromWho;
	
	/**
	 * SpAdminVerifyRecord表的数据
	 */
	private String verifyadminId;

    private String verifyAdminNm;

    private String verifyObjectId;

    private String verifyObjectNm;

    private String actionNm;

    private Date actionTime;
    
    private String kefuUserName;
    
	public Integer getSpGroupId() {
		return spGroupId;
	}
	public void setSpGroupId(Integer spGroupId) {
		this.spGroupId = spGroupId;
	}
	public String getSuppliercode() {
		return suppliercode;
	}
	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addTime) {
		this.addtime = addTime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getVerifyadminId() {
		return verifyadminId;
	}
	public void setVerifyadminId(String verifyadminId) {
		this.verifyadminId = verifyadminId;
	}
	public String getVerifyAdminNm() {
		return verifyAdminNm;
	}
	public void setVerifyAdminNm(String verifyAdminNm) {
		this.verifyAdminNm = verifyAdminNm;
	}
	public String getVerifyObjectId() {
		return verifyObjectId;
	}
	public void setVerifyObjectId(String verifyObjectId) {
		this.verifyObjectId = verifyObjectId;
	}
	public String getVerifyObjectNm() {
		return verifyObjectNm;
	}
	public void setVerifyObjectNm(String verifyObjectNm) {
		this.verifyObjectNm = verifyObjectNm;
	}
	public String getActionNm() {
		return actionNm;
	}
	public void setActionNm(String actionNm) {
		this.actionNm = actionNm;
	}
	public Date getActionTime() {
		return actionTime;
	}
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getKefuUserName() {
		return kefuUserName;
	}
	public void setKefuUserName(String kefuUserName) {
		this.kefuUserName = kefuUserName;
	}
	public Integer getFromWho() {
		return fromWho;
	}
	public void setFromWho(Integer fromWho) {
		this.fromWho = fromWho;
	}
	public Integer getStreetId() {
		return streetId;
	}
	public void setStreetId(Integer streetId) {
		this.streetId = streetId;
	}
	
	
}
