/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.apprunlog.entity;

/*import org.hibernate.validator.constraints.Length(min=0, max=11, message="是否活着;
import org.hibernate.validator.constraints.Length(min=0, max=11, message="上传状态;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Length(min=0, max=11, message="GPS");*/
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.corner.salesman.common.persistence.DataEntity;

/**
 * App运行日志Entity
 * @author 小金刚
 * @version 2016-08-30
 */
public class AppRunLog extends DataEntity<AppRunLog> {
	
	private static final long serialVersionUID = 1L;
	private String keepAlive;		// 是否活着(1、活着；2、杀死；)
	private String uploadState;		// 上传状态(1、上传成功；2、上传失败；3、批量上传成功；4、批量上传失败；)
	private String network;		// 网络（1、无网络；2、手机网络；3、无线网络；）
	private String gps;		// GPS(1、打开；2、关闭；)
	private String dataInfo;		// 上传的数据信息
	private Date appTime;		// 手机时间
	private String remark;		// 备注
	private Date createTime;		// 创建时间
	private String deviceType;		// 设备类型
	private String userName;
	private String deviceName;
	


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public AppRunLog() {
		super();
	}

	public AppRunLog(String id){
		super(id);
	}

	//@Length(min=0, max=11, message="是否活着(1、活着；2、杀死；)长度必须介于 0 和 11 之间")
	public String getKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(String keepAlive) {
		this.keepAlive = keepAlive;
	}
	
	//@Length(min=0, max=11, message="上传状态(1、上传成功；2、上传失败；3、批量上传成功；4、批量上传失败；)长度必须介于 0 和 11 之间")
	public String getUploadState() {
		return uploadState;
	}

	public void setUploadState(String uploadState) {
		this.uploadState = uploadState;
	}
	
	//@Length(min=0, max=11, message="网络（1、无网络；2、手机网络；3、无线网络；）长度必须介于 0 和 11 之间")
	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}
	
	//@Length(min=0, max=11, message="GPS(1、打开；2、关闭；)长度必须介于 0 和 11 之间")
	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}
	
	public String getDataInfo() {
		return dataInfo;
	}

	public void setDataInfo(String dataInfo) {
		this.dataInfo = dataInfo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAppTime() {
		return appTime;
	}

	public void setAppTime(Date appTime) {
		this.appTime = appTime;
	}
	
	//(min=0, max=500, message="备注长度必须介于 0 和 500 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	//(min=0, max=11, message="设备类型长度必须介于 0 和 11 之间")
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
}