package com.corner.kefu.beans.ro.sp;

import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;


public class SpVoucherRo extends AmazeUIListRo {
 
	private static final long serialVersionUID = 1L;
	
	private Integer storeId;
	private Integer getType;
	private Integer pubStatus;
	private String preOrderId;
	private Integer useType;//1查看我的优惠券 2下单前过滤 3二次支付前过滤
	private Byte supportmetho;//useType=2/3时传值
	private String userId;
	private String orderId;//订单id useType=3时传值
	private Integer ruleId;
	private Date createTime;
	private Integer oneDay;
    private Boolean isDelete;
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getGetType() {
		return getType;
	}
	public void setGetType(Integer getType) {
		this.getType = getType;
	}
	public Integer getPubStatus() {
		return pubStatus;
	}
	public void setPubStatus(Integer pubStatus) {
		this.pubStatus = pubStatus;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getUseType() {
		return useType;
	}
	public void setUseType(Integer useType) {
		this.useType = useType;
	}
	public Byte getSupportmetho() {
		return supportmetho;
	}
	public void setSupportmetho(Byte supportmetho) {
		this.supportmetho = supportmetho;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public String getPreOrderId() {
		return preOrderId;
	}
	public void setPreOrderId(String preOrderId) {
		this.preOrderId = preOrderId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getOneDay() {
		return oneDay;
	}
	public void setOneDay(Integer oneDay) {
		this.oneDay = oneDay;
	}

}
