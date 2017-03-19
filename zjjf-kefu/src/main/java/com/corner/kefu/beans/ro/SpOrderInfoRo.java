package com.corner.kefu.beans.ro;

import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;

/**
 * 确认订单接受参数
 * @author aimee at 2015年2月6日下午1:14:30
 * @email 1297579898@qq.com
 */
public class SpOrderInfoRo extends AmazeUIListRo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid;//店主用户id
	private Byte status;
	private Byte statustype;//0店主查看已下单，1店主查看待收货
	private String orderid;//订单编号
	private String orderId;//订单编号
	private String consignee;//店主/收货人
	private String mobile;//手机号
	private String storename;//店名
	private String address;//店铺的详细地址
	private Date senddate;//送货日期
	private Byte sendType;//送货日期类型	
	private Byte supportmetho;//支付方式
	private String userremark;//备注
	private String supplierTel;
	private Integer areaid;
	private Integer storeid; //店铺编号
	private String supplierCode;//供应商编号
	private String supplierName; //供应商名称
	private Integer acStatus;//财务状态 
	private Byte  kfStatus;//客服状态
	private Date starDate;//开始时间
	private Date endDate;//结束时间
	private Date ackTime;//送达日期
	private Short level;
	
	/*******include checkbillcondition*******/
	//for all supplier list
	private Integer province;
	private Integer city;
	private Integer areaId;
	private Date beginTime;
	private Date endTime;
	private String spKeyword;
	
	//for supplier detail list
	private String supplierId;
	//private Integer acStatus;//重复一个
	
	//订单审核入参
	private String spOrderIds;
	private String[] spOrderIdArray;
	private String userRemark;
	//whoStatus
	private String whoStatus;
	//private String kfStatus;
	private Integer spStatus;
	
	/*******定格需求*******/
	private String pId;//父订单
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getSenddate() {
		return senddate;
	}
	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	public Byte getSupportmetho() {
		return supportmetho;
	}
	public void setSupportmetho(Byte supportmetho) {
		this.supportmetho = supportmetho;
	}
	public String getUserremark() {
		return userremark;
	}
	public void setUserremark(String userremark) {
		this.userremark = userremark;
	}
	public Byte getStatustype() {
		return statustype;
	}
	public void setStatustype(Byte statustype) {
		this.statustype = statustype;
	}
	public Byte getSendType() {
		return sendType;
	}
	public void setSendType(Byte sendType) {
		this.sendType = sendType;
	}
	public String getSupplierTel() {
		return supplierTel;
	}
	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getAcStatus() {
		return acStatus;
	}
	public void setAcStatus(Integer acStatus) {
		this.acStatus = acStatus;
	}
	public Byte getKfStatus() {
		return kfStatus;
	}
	public void setKfStatus(Byte kfStatus) {
		this.kfStatus = kfStatus;
	}
	public Date getStarDate() {
		return starDate;
	}
	public void setStarDate(Date starDate) {
		this.starDate = starDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getAckTime() {
		return ackTime;
	}
	public void setAckTime(Date ackTime) {
		this.ackTime = ackTime;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getSpKeyword() {
		return spKeyword;
	}
	public void setSpKeyword(String spKeyword) {
		this.spKeyword = spKeyword;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSpOrderIds() {
		return spOrderIds;
	}
	public void setSpOrderIds(String spOrderIds) {
		this.spOrderIds = spOrderIds;
	}
	public String[] getSpOrderIdArray() {
		return spOrderIdArray;
	}
	public void setSpOrderIdArray(String[] spOrderIdArray) {
		this.spOrderIdArray = spOrderIdArray;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getWhoStatus() {
		return whoStatus;
	}
	public void setWhoStatus(String whoStatus) {
		this.whoStatus = whoStatus;
	}
	public Integer getSpStatus() {
		return spStatus;
	}
	public void setSpStatus(Integer spStatus) {
		this.spStatus = spStatus;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
}
