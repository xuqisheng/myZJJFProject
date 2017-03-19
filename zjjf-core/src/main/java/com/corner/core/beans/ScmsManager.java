package com.corner.core.beans;

import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;
import com.corner.core.utils.StringUtil;

public class ScmsManager extends AmazeUIListRo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id = StringUtil.getUUID();
	
    private String managerName;

    private String userName;

    private String managerCode;

    private String password;

    private String token;

    private String salt;

    private String job;

    private Date lastTime;

    private String loginIP;

    private String mobile;

    private String callNum;

    private String email;

    private String fax;

    private Integer province;

    private Integer city;

    private Integer county;

    private Integer areaId;

    private Integer bsCircleId;

    private String managerAddress;

    private String managerMark;

    private String branderName;

    private String branderTel;

    private Date regTime;

    private Date updateTime;

    private String updateMark;

    private String bankCardurl;

    private String bankNum;

    private String bankUserName;

    private String bankName;

    private Byte status;

    private Boolean isDelete;

    private String col1;

    private String col2;

    private String col3;

    private Short minimum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode == null ? null : managerCode.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP == null ? null : loginIP.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCallNum() {
        return callNum;
    }

    public void setCallNum(String callNum) {
        this.callNum = callNum == null ? null : callNum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getCounty() {
        return county;
    }

    public void setCounty(Integer county) {
        this.county = county;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getBsCircleId() {
        return bsCircleId;
    }

    public void setBsCircleId(Integer bsCircleId) {
        this.bsCircleId = bsCircleId;
    }

    public String getManagerAddress() {
        return managerAddress;
    }

    public void setManagerAddress(String managerAddress) {
        this.managerAddress = managerAddress == null ? null : managerAddress.trim();
    }

    public String getManagerMark() {
        return managerMark;
    }

    public void setManagerMark(String managerMark) {
        this.managerMark = managerMark == null ? null : managerMark.trim();
    }

    public String getBranderName() {
        return branderName;
    }

    public void setBranderName(String branderName) {
        this.branderName = branderName == null ? null : branderName.trim();
    }

    public String getBranderTel() {
        return branderTel;
    }

    public void setBranderTel(String branderTel) {
        this.branderTel = branderTel == null ? null : branderTel.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateMark() {
        return updateMark;
    }

    public void setUpdateMark(String updateMark) {
        this.updateMark = updateMark == null ? null : updateMark.trim();
    }

    public String getBankCardurl() {
        return bankCardurl;
    }

    public void setBankCardurl(String bankCardurl) {
        this.bankCardurl = bankCardurl == null ? null : bankCardurl.trim();
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum == null ? null : bankNum.trim();
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName == null ? null : bankUserName.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
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

    public Short getMinimum() {
        return minimum;
    }

    public void setMinimum(Short minimum) {
        this.minimum = minimum;
    }
}