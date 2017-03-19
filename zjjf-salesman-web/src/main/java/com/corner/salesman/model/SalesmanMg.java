package com.corner.salesman.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.corner.core.beans.ro.ABaseRo;

public class SalesmanMg extends ABaseRo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
    private String userIds;

    private String userName;

    private String nickName;

    private Integer gender;

    private String password;

    private String mobile;

    private String email;

    private Date birthday;

    private Integer province;

    private Integer city;

    private Integer area;

    private Integer credit;

    private Date regTime;

    private Date lastTime;

    private String regIP;

    private String lastIP;

    private Byte status;

    private String token;

    private String url;

    private Integer isDelete;

    private Date registration;

    private Integer regDays;

    private Integer monthCredit;

    private Date updateTime;

    private Integer intensity;

    private Boolean isManager;

    private Byte isModify;

    private String col1;

    private String col2;

    private String col3;

    private String col4;

    private String col5;

    private String col6;

    private String identitycard;

    private String positiveidentitycard;

    private String negativeidentitycard;

    private String address;

    private String deviceUUID;
    
    private String deviceName;

    private String postType;
    
    private String postName;

    private Integer userType;

    private String createBy;

    private Date createTime;

    private String updateBy;
    
    private String deptId;
    
    private String deptName;
    
    private String oldDeptId;
    private Integer oldIsDelete;
    private Integer oldUserType;
    private String oldMobile;
    private String oldUserName;
    private Integer oldGender;
    private String oldPostType;
    private String isCheck;
    private String version;
    
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getOldMobile() {
		return oldMobile;
	}

	public void setOldMobile(String oldMobile) {
		this.oldMobile = oldMobile;
	}

	public String getOldUserName() {
		return oldUserName;
	}

	public void setOldUserName(String oldUserName) {
		this.oldUserName = oldUserName;
	}

	public Integer getOldGender() {
		return oldGender;
	}

	public void setOldGender(Integer oldGender) {
		this.oldGender = oldGender;
	}

	public String getOldPostType() {
		return oldPostType;
	}

	public void setOldPostType(String oldPostType) {
		this.oldPostType = oldPostType;
	}

	public Integer getOldIsDelete() {
		return oldIsDelete;
	}

	public void setOldIsDelete(Integer oldIsDelete) {
		this.oldIsDelete = oldIsDelete;
	}

	public Integer getOldUserType() {
		return oldUserType;
	}

	public void setOldUserType(Integer oldUserType) {
		this.oldUserType = oldUserType;
	}

	public String getOldDeptId() {
		return oldDeptId;
	}

	public void setOldDeptId(String oldDeptId) {
		this.oldDeptId = oldDeptId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getRegIP() {
        return regIP;
    }

    public void setRegIP(String regIP) {
        this.regIP = regIP == null ? null : regIP.trim();
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP == null ? null : lastIP.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    public Integer getRegDays() {
        return regDays;
    }

    public void setRegDays(Integer regDays) {
        this.regDays = regDays;
    }

    public Integer getMonthCredit() {
        return monthCredit;
    }

    public void setMonthCredit(Integer monthCredit) {
        this.monthCredit = monthCredit;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    public Byte getIsModify() {
        return isModify;
    }

    public void setIsModify(Byte isModify) {
        this.isModify = isModify;
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

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4 == null ? null : col4.trim();
    }

    public String getCol5() {
        return col5;
    }

    public void setCol5(String col5) {
        this.col5 = col5 == null ? null : col5.trim();
    }

    public String getCol6() {
        return col6;
    }

    public void setCol6(String col6) {
        this.col6 = col6 == null ? null : col6.trim();
    }

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard == null ? null : identitycard.trim();
    }

    public String getPositiveidentitycard() {
        return positiveidentitycard;
    }

    public void setPositiveidentitycard(String positiveidentitycard) {
        this.positiveidentitycard = positiveidentitycard == null ? null : positiveidentitycard.trim();
    }

    public String getNegativeidentitycard() {
        return negativeidentitycard;
    }

    public void setNegativeidentitycard(String negativeidentitycard) {
        this.negativeidentitycard = negativeidentitycard == null ? null : negativeidentitycard.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDeviceUUID() {
        return deviceUUID;
    }

    public void setDeviceUUID(String deviceUUID) {
        this.deviceUUID = deviceUUID == null ? null : deviceUUID.trim();
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

}