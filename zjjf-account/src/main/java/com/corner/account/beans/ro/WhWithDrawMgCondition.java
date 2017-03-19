package com.corner.account.beans.ro;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;

public class WhWithDrawMgCondition extends EasyUIQueryModel{

	private String whId;
	private String houseCode;
	private String name;
	private String spKeyword;
	private Integer status;
	private Date beginTime;
	private Date endTime;
	
	//private String status;
	private String withDrawIds;
	private String[] withDrawIdsArray;
	private Date denyTime;
	private Date payTime;
	private Date checkTime;
	private String userRemark;//给页面使用
	private String checkId;
	private String checkRemark;
	private String denyId;
	private String denyRemark;
	private String payerId;
	private String payRemark;
	
	//生成结算单
	private String id;
	private String SheetName;
	private String SheetRemark;
	private Byte xtype;
	
	//结算单查询
	private Boolean isDelete;
	private String keyword;
	
	//录入回执
    //private String payerId;
    //private Date payTime;
    //private String payRemark;
    private Byte payState;
    private String bankcode;
    private String bankDealNo;
    private String tradePlant;
    private String tradeNo;
    private BigDecimal payment;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toHqlString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object[] toHqlObjects() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getSpKeyword() {
		return spKeyword;
	}
	public void setSpKeyword(String spKeyword) {
		this.spKeyword = spKeyword;
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
	public String getWithDrawIds() {
		return withDrawIds;
	}
	public void setWithDrawIds(String withDrawIds) {
		this.withDrawIds = withDrawIds;
	}
	public String[] getWithDrawIdsArray() {
		return withDrawIdsArray;
	}
	public void setWithDrawIdsArray(String[] withDrawIdsArray) {
		this.withDrawIdsArray = withDrawIdsArray;
	}
	public Date getDenyTime() {
		return denyTime;
	}
	public void setDenyTime(Date denyTime) {
		this.denyTime = denyTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getCheckRemark() {
		return checkRemark;
	}
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	public String getDenyRemark() {
		return denyRemark;
	}
	public void setDenyRemark(String denyRemark) {
		this.denyRemark = denyRemark;
	}
	public String getPayRemark() {
		return payRemark;
	}
	public void setPayRemark(String payRemark) {
		this.payRemark = payRemark;
	}
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public String getDenyId() {
		return denyId;
	}
	public void setDenyId(String denyId) {
		this.denyId = denyId;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public String getSheetName() {
		return SheetName;
	}
	public void setSheetName(String sheetName) {
		SheetName = sheetName;
	}
	public String getSheetRemark() {
		return SheetRemark;
	}
	public void setSheetRemark(String sheetRemark) {
		SheetRemark = sheetRemark;
	}
	public Byte getXtype() {
		return xtype;
	}
	public void setXtype(Byte xtype) {
		this.xtype = xtype;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Byte getPayState() {
		return payState;
	}
	public void setPayState(Byte payState) {
		this.payState = payState;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getBankDealNo() {
		return bankDealNo;
	}
	public void setBankDealNo(String bankDealNo) {
		this.bankDealNo = bankDealNo;
	}
	public String getTradePlant() {
		return tradePlant;
	}
	public void setTradePlant(String tradePlant) {
		this.tradePlant = tradePlant;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public String getWhId() {
		return whId;
	}
	public void setWhId(String whId) {
		this.whId = whId;
	}
	public String getHouseCode() {
		return houseCode;
	}
	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
