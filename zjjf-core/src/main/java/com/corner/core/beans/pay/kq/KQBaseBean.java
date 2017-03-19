package com.corner.core.beans.pay.kq;

import java.io.Serializable;

public class KQBaseBean implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;
	
	protected String merchantAcctId ;// 人民币账号 数字串
	protected String version ;// 网关版本 固定值：v2.0
	protected String signType ;// 签名类型 固定值：4
	protected String payType ;// 支付方式 固定值：00、10、11、12、13、14、15、17、19、21、22
	protected String bankId ;// 银行代码 固定值：字符串 返回用户在实际支付时所使用的银行代码
	protected String orderId ;// 商户订单号 *************
	protected String orderTime ;// 商户订单提交时间
	protected String orderAmount ;// 商户订单金额 以分为单位
	protected String ext1 ;//与提交订单时的扩展字段1保持一致
	protected String ext2 ;//与提交订单时的扩展字段2保持一致
	protected String signMsg ;//签名字符串
	
	public String getMerchantAcctId() {
		return merchantAcctId;
	}
	public void setMerchantAcctId(String merchantAcctId) {
		this.merchantAcctId = merchantAcctId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	
}
