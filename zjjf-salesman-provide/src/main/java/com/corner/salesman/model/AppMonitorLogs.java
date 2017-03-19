package com.corner.salesman.model;

import com.corner.salesman.common.persistence.BaseEntity;

public class AppMonitorLogs extends BaseEntity<AppMonitorLogs> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer keepAlive;//是否活着(1、活着；2、杀死；)

    private Integer uploadState;//上传状态(1、上传成功；2、上传失败；3、批量上传成功；4、批量上传失败；)

    private Integer network;//网络（1、无网络；2、手机网络；3、无线网络；）

    private Integer gps;//GPS(1、打开；2、关闭；)

    private String remark;//备注

    private String createBy;//创建人

    private String appTime;//手机时间
    
    private String createTime;//创建时间

    private String dataInfo;//数据信息
    
    private Integer deviceType;//设备类型

    public Integer getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Integer keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Integer getUploadState() {
        return uploadState;
    }

    public void setUploadState(Integer uploadState) {
        this.uploadState = uploadState;
    }

    public Integer getNetwork() {
        return network;
    }

    public void setNetwork(Integer network) {
        this.network = network;
    }

    public Integer getGps() {
        return gps;
    }

    public void setGps(Integer gps) {
        this.gps = gps;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAppTime() {
		return appTime;
	}

	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}

	public String getDataInfo() {
        return dataInfo;
    }

    public void setDataInfo(String dataInfo) {
        this.dataInfo = dataInfo == null ? null : dataInfo.trim();
    }

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
    
}