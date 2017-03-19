/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.appsesalesman.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
//import org.hibernate.validator.constraints.Length(min=0, max=11, message="月度积分额度;

import com.corner.salesman.common.persistence.DataEntity;

/**
 * 账户信息Entity
 * @author 小金刚
 * @version 2016-08-11
 */
public class Applistsalesman extends DataEntity<Applistsalesman> {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String userName;		// 用户名
	private String nickName;		// 昵称
	private String gender;		// 性别（1男、0女）
	private String password;		// 密码
	private String mobile;		// 手机号码
	private String email;		// 电子邮箱
	private Date birthday;		// 生日
	private String province;		// 所在省份
	private String city;		// 所在城市
	private String area;		// 所在区域
	private String credit;		// 积分
	private Date regTime;		// 注册时间
	private Date lastTime;		// 最后登录时间
	private String regIP;		// 注册IP
	private String lastIP;		// 最后登录IP
	private String status;		// 启用用户状态（0关闭、1正常）
	private String token;		// 标志
	private String url;		// 用户头像URL
	private String isDelete;		// 记录是否删除
	private Date registration;		// registration
	private String regDays;		// regDays
	private String monthCredit;		// 月度积分额度(积分使用）
	private Date updateTime;		// updateTime
	private String intensity;		// 密码强度
	private String isManager;		// 是否是管理者（0-否 1-是）
	private String isModify;		// 是否修改过用户名
	private String col1;		// 备用字段1
	private String col2;		// 备用字段2
	private String col3;		// 备用字段3
	private String col4;		// 备用字段4
	private String col5;		// 备用字段5
	private String col6;		// 备用字段6
	private String identitycard;		// 身份证
	private String positiveidentitycard;		// 身份证正面照
	private String negativeidentitycard;		// 身份证反面照
	private String address;		// 用户地址
	private String deviceUUID;		// deviceUUID
	private String deviceName;		// deviceName
	private String userType;		// userType
	private String postType;		// postType
	private Date createTime;		// createTime
	private String version;		// 版本号
	private String names;
	private String labels;
	private String deptId;
	
	
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
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public Applistsalesman() {
		super();
	}

	public Applistsalesman(String id){
		super(id);
	}

	@Length(min=0, max=16, message="用户名长度必须介于 0 和 16 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=16, message="昵称长度必须介于 0 和 16 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Length(min=0, max=2, message="性别（1男、0女）长度必须介于 0 和 2 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=64, message="密码长度必须介于 0 和 64 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=15, message="手机号码长度必须介于 0 和 15 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=50, message="电子邮箱长度必须介于 0 和 50 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=11, message="所在省份长度必须介于 0 和 11 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=11, message="所在城市长度必须介于 0 和 11 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=11, message="所在区域长度必须介于 0 和 11 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=11, message="积分长度必须介于 0 和 11 之间")
	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	
	@Length(min=0, max=15, message="注册IP长度必须介于 0 和 15 之间")
	public String getRegIP() {
		return regIP;
	}

	public void setRegIP(String regIP) {
		this.regIP = regIP;
	}
	
	@Length(min=0, max=15, message="最后登录IP长度必须介于 0 和 15 之间")
	public String getLastIP() {
		return lastIP;
	}

	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}
	
	@Length(min=0, max=4, message="启用用户状态（0关闭、1正常）长度必须介于 0 和 4 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=50, message="标志长度必须介于 0 和 50 之间")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Length(min=0, max=255, message="用户头像URL长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=1, message="记录是否删除长度必须介于 0 和 1 之间")
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}
	
	@Length(min=0, max=6, message="regDays长度必须介于 0 和 6 之间")
	public String getRegDays() {
		return regDays;
	}

	public void setRegDays(String regDays) {
		this.regDays = regDays;
	}
	
	@Length(min=0, max=11, message="月度积分额度(积分使用）长度必须介于 0 和 11 之间")
	public String getMonthCredit() {
		return monthCredit;
	}

	public void setMonthCredit(String monthCredit) {
		this.monthCredit = monthCredit;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=6, message="密码强度长度必须介于 0 和 6 之间")
	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}
	
	@Length(min=0, max=1, message="是否是管理者（0-否 1-是）长度必须介于 0 和 1 之间")
	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}
	
	@Length(min=0, max=2, message="是否修改过用户名长度必须介于 0 和 2 之间")
	public String getIsModify() {
		return isModify;
	}

	public void setIsModify(String isModify) {
		this.isModify = isModify;
	}
	
	@Length(min=0, max=32, message="备用字段1长度必须介于 0 和 32 之间")
	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}
	
	@Length(min=0, max=32, message="备用字段2长度必须介于 0 和 32 之间")
	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}
	
	@Length(min=0, max=32, message="备用字段3长度必须介于 0 和 32 之间")
	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}
	
	@Length(min=0, max=32, message="备用字段4长度必须介于 0 和 32 之间")
	public String getCol4() {
		return col4;
	}

	public void setCol4(String col4) {
		this.col4 = col4;
	}
	
	@Length(min=0, max=32, message="备用字段5长度必须介于 0 和 32 之间")
	public String getCol5() {
		return col5;
	}

	public void setCol5(String col5) {
		this.col5 = col5;
	}
	
	@Length(min=0, max=32, message="备用字段6长度必须介于 0 和 32 之间")
	public String getCol6() {
		return col6;
	}

	public void setCol6(String col6) {
		this.col6 = col6;
	}
	
	@Length(min=0, max=18, message="身份证长度必须介于 0 和 18 之间")
	public String getIdentitycard() {
		return identitycard;
	}

	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}
	
	@Length(min=0, max=255, message="身份证正面照长度必须介于 0 和 255 之间")
	public String getPositiveidentitycard() {
		return positiveidentitycard;
	}

	public void setPositiveidentitycard(String positiveidentitycard) {
		this.positiveidentitycard = positiveidentitycard;
	}
	
	@Length(min=0, max=255, message="身份证反面照长度必须介于 0 和 255 之间")
	public String getNegativeidentitycard() {
		return negativeidentitycard;
	}

	public void setNegativeidentitycard(String negativeidentitycard) {
		this.negativeidentitycard = negativeidentitycard;
	}
	
	@Length(min=0, max=255, message="用户地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=50, message="deviceUUID长度必须介于 0 和 50 之间")
	public String getDeviceUUID() {
		return deviceUUID;
	}

	public void setDeviceUUID(String deviceUUID) {
		this.deviceUUID = deviceUUID;
	}
	
	@Length(min=0, max=50, message="deviceName长度必须介于 0 和 50 之间")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@Length(min=0, max=1, message="userType长度必须介于 0 和 1 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Length(min=0, max=20, message="postType长度必须介于 0 和 20 之间")
	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=10, message="版本号长度必须介于 0 和 10 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}