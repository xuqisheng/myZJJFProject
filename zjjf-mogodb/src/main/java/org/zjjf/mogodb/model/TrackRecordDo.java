package org.zjjf.mogodb.model;

/**
 * 用户轨迹对象，外工程调用的时候需要Bean
 * @author 小武
 *
 */
public class TrackRecordDo {

	private String id;//编码
    private String groupId;//组编码
    private String deviceId;//设备ID
    private String timePoint;//时间段（9:00 9:10）
    private Double longitude;//经度
    private Double latitude;//纬度
    private String localtimes;//抓取时间
    private Integer type;//抓取类型
    private Integer deviceType;//设备类型
    private String positionName;//位置名称
    private String week;//星期
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getTimePoint() {
		return timePoint;
	}
	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getLocaltimes() {
		return localtimes;
	}
	public void setLocaltimes(String localtimes) {
		this.localtimes = localtimes;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
}
