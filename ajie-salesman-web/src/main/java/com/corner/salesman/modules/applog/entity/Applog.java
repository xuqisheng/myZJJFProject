/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.applog.entity;

import com.corner.salesman.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * App日志监控Entity
 * @author 小金刚
 * @version 2016-08-29
 */
public class Applog extends DataEntity<Applog> {
	
	private static final long serialVersionUID = 1L;
	private String logId;		// 日志ID
	private String errorMsg;		// 错误信息
	private Date errorTime;		// 异常发生时间
	private String deviceType;		// 设备类型
	private String version;		// version    u.mobile,
	private Date createTime;		// 创建时间
	private String userName ;
	private String deviceUUID;
	private String deviceName;
	private String mobile;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeviceUUID() {
		return deviceUUID;
	}

	public void setDeviceUUID(String deviceUUID) {
		this.deviceUUID = deviceUUID;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Applog() {
		super();
	}

	public Applog(String id){
		super(id);
	}

	@Length(min=1, max=11, message="日志ID长度必须介于 1 和 11 之间")
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(Date errorTime) {
		this.errorTime = errorTime;
	}
	
	@Length(min=0, max=1, message="设备类型长度必须介于 0 和 1 之间")
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	@Length(min=0, max=10, message="version长度必须介于 0 和 10 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}