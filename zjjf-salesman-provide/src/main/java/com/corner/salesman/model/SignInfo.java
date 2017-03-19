package com.corner.salesman.model;

import java.util.List;

import com.corner.salesman.common.persistence.DataEntity;
/**
 * 签到对象
 * @author Longx
 *
 */
public class SignInfo extends DataEntity<SignInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//签到ID
	private String groupId;//所属组
	private String picUrl;//证明照片地址
	private String address;//签到地址
	private String subject;//会员主题
	private String userId;//用户ID（登陆人在用户ID）
	private String signTime;//签到时间
	private Integer type;//类型（1：上班；2：下班；）
	private String markType;//签到标记(1:到店签；2、离店签)
	private String typeName;//类型名称
	private String imgFile;//上传文件字符串
	private String week;//星期
	private String queryType;//查询类型（0：不分页；1：分页）
	private List picList = null;//图片路径集合
	private String visitLine;//拜访路线
	private String visitCust;//拜访客户
	private String outWorkStart;//外勤开始时间
	private String outWorkEnd;//外勤结束时间
	private String shopNo;//客户编号
	
	private AppSignInfo appSignInfo;
	
	//=============满足签到记录轨迹信息添加 start=======
    private Double longitude;//经度
    private Double latitude;//纬度
    private String localtimes;//抓取时间
    private Integer deviceType;//设备类型
    private String status;
    private String lineId;
  //=============满足签到记录轨迹信息添加 end===========
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMarkType() {
		return markType;
	}
	public void setMarkType(String markType) {
		this.markType = markType;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getImgFile() {
		return imgFile;
	}
	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public AppSignInfo getAppSignInfo() {
		return appSignInfo;
	}
	public void setAppSignInfo(AppSignInfo appSignInfo) {
		this.appSignInfo = appSignInfo;
	}
	public List getPicList() {
		return picList;
	}
	public void setPicList(List picList) {
		this.picList = picList;
	}
	public String getVisitLine() {
		return visitLine;
	}
	public void setVisitLine(String visitLine) {
		this.visitLine = visitLine;
	}
	public String getVisitCust() {
		return visitCust;
	}
	public void setVisitCust(String visitCust) {
		this.visitCust = visitCust;
	}
	public String getOutWorkStart() {
		return outWorkStart;
	}
	public void setOutWorkStart(String outWorkStart) {
		this.outWorkStart = outWorkStart;
	}
	public String getOutWorkEnd() {
		return outWorkEnd;
	}
	public void setOutWorkEnd(String outWorkEnd) {
		this.outWorkEnd = outWorkEnd;
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
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	
}
