package com.corner.core.beans.pay.kq;

public class KQPayBean extends KQBaseBean {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;
	
	//协议参数
	private String inputCharset ;//1代表UTF-8; 2代表GBK; 3代表GB2312
	private String pageUrl ;//当bgUrl不为空时，按照bgUrl的方式返回
	private String bgUrl ;//当bgUrl不为空时，按照bgUrl的方式返回
	//private String version ;//固定值：mobile1.0 移动网关：mobile1.0
	private String mobileGateway ;//移动网关版本，当version= mobile1.0时有效  phone/pad
	private String language ;//固定值：1  1代表中文显示
	//private String signType ;//4代表DSA或者RSA签名方式
	
	//买卖双方信息参数
	//private String merchantAcctId ;//本参数用来指定接收款项的人民币账号
	private String payerName ;//支付人姓名
	private String payerContactType ;//1代表电子邮件方式；2代表手机联系方式
	private String payerContact ;//根据payerContactType的方式填写对应字符，邮箱或者手机号码
	private String payerIdType ;//指定付款人
	private String payerId ;//付款人标示
	private String payerIP ;//付款人IP
	
	//业务参数
	//private String orderId ;//商户订单号
	//private String orderAmount ;//商户订单金额
	//private String orderTime ;//商户订单提交时间
	private String orderTimestamp ;//快钱时间戳
	private String productName ;//商品名称
	private String productNum ;//商品数量
	private String productId ;//商品代码
	private String productDesc ;//商品描述
	//private String ext1 ;//扩展字段1
	//private String ext2 ;//扩展字段2
	//private String payType ;//支付方式
	//private String bankId ;//银行代码
	private String cardIssuer ;//发卡机构
	private String cardNum ;//卡号
	private String redoFlag ;//同一订单禁止重复提交标志
	private String submitType ;//00:代表前台提交01：代表后台提交
	private String orderTimeOut ;//交易超时时间
	private String extDataType ;//附加信息类型
	private String extDataContent  ;//附加信息
	//private String signMsg  ;//签名字符串 1024
	
	//商户交易参考信息参数（不包含在报文加签中）
	private String referDataType  ;//参考交易信息类型
	private String referData  ;//参考交易信息
	
	public String getInputCharset() {
		return inputCharset;
	}
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getBgUrl() {
		return bgUrl;
	}
	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}
	public String getMobileGateway() {
		return mobileGateway;
	}
	public void setMobileGateway(String mobileGateway) {
		this.mobileGateway = mobileGateway;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayerContactType() {
		return payerContactType;
	}
	public void setPayerContactType(String payerContactType) {
		this.payerContactType = payerContactType;
	}
	public String getPayerContact() {
		return payerContact;
	}
	public void setPayerContact(String payerContact) {
		this.payerContact = payerContact;
	}
	public String getPayerIdType() {
		return payerIdType;
	}
	public void setPayerIdType(String payerIdType) {
		this.payerIdType = payerIdType;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public String getPayerIP() {
		return payerIP;
	}
	public void setPayerIP(String payerIP) {
		this.payerIP = payerIP;
	}
	public String getOrderTimestamp() {
		return orderTimestamp;
	}
	public void setOrderTimestamp(String orderTimestamp) {
		this.orderTimestamp = orderTimestamp;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getCardIssuer() {
		return cardIssuer;
	}
	public void setCardIssuer(String cardIssuer) {
		this.cardIssuer = cardIssuer;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getRedoFlag() {
		return redoFlag;
	}
	public void setRedoFlag(String redoFlag) {
		this.redoFlag = redoFlag;
	}
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	public String getOrderTimeOut() {
		return orderTimeOut;
	}
	public void setOrderTimeOut(String orderTimeOut) {
		this.orderTimeOut = orderTimeOut;
	}
	public String getExtDataType() {
		return extDataType;
	}
	public void setExtDataType(String extDataType) {
		this.extDataType = extDataType;
	}
	public String getExtDataContent() {
		return extDataContent;
	}
	public void setExtDataContent(String extDataContent) {
		this.extDataContent = extDataContent;
	}
	public String getReferDataType() {
		return referDataType;
	}
	public void setReferDataType(String referDataType) {
		this.referDataType = referDataType;
	}
	public String getReferData() {
		return referData;
	}
	public void setReferData(String referData) {
		this.referData = referData;
	}
	
}
