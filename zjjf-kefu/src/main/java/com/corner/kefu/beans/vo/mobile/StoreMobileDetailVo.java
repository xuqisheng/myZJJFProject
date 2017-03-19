package com.corner.kefu.beans.vo.mobile;

import java.util.List;

import com.corner.core.beans.SpGroup;

public class StoreMobileDetailVo {
	private Integer id;
	private String name;
	private String contact;
	private String mobile;
	private String areaStr;
	private String address;
	private String licenseNum;//营业执照
	private String idCardUpPic;//身份证正面照
	private String idCardDownPic;//身份证背面照
	private String licensePic;
	private String tobaccoPic;
    private String backCardPic;
	private List<SpGroup> spGroupList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAreaStr() {
		return areaStr;
	}
	public void setAreaStr(String areaStr) {
		this.areaStr = areaStr;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public String getIdCardUpPic() {
		return idCardUpPic;
	}
	public void setIdCardUpPic(String idCardUpPic) {
		this.idCardUpPic = idCardUpPic;
	}
	public String getIdCardDownPic() {
		return idCardDownPic;
	}
	public void setIdCardDownPic(String idCardDownPic) {
		this.idCardDownPic = idCardDownPic;
	}
	public String getLicensePic() {
		return licensePic;
	}
	public void setLicensePic(String licensePic) {
		this.licensePic = licensePic;
	}
	public List<SpGroup> getSpGroupList() {
		return spGroupList;
	}
	public void setSpGroupList(List<SpGroup> spGroupList) {
		this.spGroupList = spGroupList;
	}
	public String getTobaccoPic() {
		return tobaccoPic;
	}
	public void setTobaccoPic(String tobaccoPic) {
		this.tobaccoPic = tobaccoPic;
	}
	public String getBackCardPic() {
		return backCardPic;
	}
	public void setBackCardPic(String backCardPic) {
		this.backCardPic = backCardPic;
	}
}
