package com.corner.core.beans.pay.kq;

public class KQBackBean extends KQBaseBean {
	
/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;
//	private String merchantAcctId ;// 人民币账号 数字串
//	private String version ;// 网关版本 固定值：v2.0
//	private String signType ;// 签名类型 固定值：4
//	private String payType ;// 支付方式 固定值：00、10、11、12、13、14、15、17、19、21、22
//	private String bankId ;// 银行代码 固定值：字符串 返回用户在实际支付时所使用的银行代码
//	private String orderId ;// 商户订单号 *************
//	private String orderTime ;// 商户订单提交时间
//	private String orderAmount ;// 商户订单金额 以分为单位
	private String bindCard ;// 信用卡快捷支付绑定卡信息后返回前六后四位信用卡号
	private String bindMobile ;// 信用卡快捷支付绑定卡信息后返回前三位后四位手机号码
	private String dealId ;//该交易在快钱系统中对应的交易号
	private String bankDealId ;//该交易在银行支付时对应的交易号，如果不是通过银行卡支付，则为空
	private String dealTime ;//快钱对交易进行处理的时间
	private String payAmount ;//返回在使用优惠券等情况后，用户实际支付的金额
	private String fee ;//快钱收取商户的手续费，单位为分
//	private String ext1 ;//与提交订单时的扩展字段1保持一致
//	private String ext2 ;//与提交订单时的扩展字段2保持一致
	private String payResult ;//10：支付成功
	private String errCode ;//错误代码
//	private String signMsg ;//签名字符串
	
	//米博平台字段***** 非接口字段
	private String msg ;
	
	
	public String getBindCard() {
		return bindCard;
	}
	public void setBindCard(String bindCard) {
		this.bindCard = bindCard;
	}
	public String getBindMobile() {
		return bindMobile;
	}
	public void setBindMobile(String bindMobile) {
		this.bindMobile = bindMobile;
	}
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public String getBankDealId() {
		return bankDealId;
	}
	public void setBankDealId(String bankDealId) {
		this.bankDealId = bankDealId;
	}
	public String getDealTime() {
		return dealTime;
	}
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getPayResult() {
		return payResult;
	}
	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
