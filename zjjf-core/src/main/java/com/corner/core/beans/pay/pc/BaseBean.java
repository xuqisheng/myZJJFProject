package com.corner.core.beans.pay.pc;

import java.io.Serializable;

public class BaseBean implements Serializable{
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;
	protected String sign_type;//签名方式
	protected String sign;//签名
	protected String notify_id ;//通知校验ID 支付宝通知校验ID
	protected String notify_time ;//通知时间
	protected String notify_type  ;//通知类型  trade_status_sync
	protected String out_trade_no ;//支付宝合作商户网站唯一订单号。64位  可空
	protected String subject ;//商品名称 256 最长为128个汉字	
	protected String payment_type ;//支付类型  4 默认值为：1（商品购买）
	protected String trade_no ;//支付宝交易号  64 
	protected String trade_status ;//交易状态   TRADE_FINISHED TRADE_SUCCESS
	protected String seller_id ;//卖家支付	宝用户号 16 以2088开头的纯16位数字
	protected String buyer_id ;//买家支付宝用户号	宝用户号 16 以2088开头的纯16位数字 可空
	protected String seller_email  ;//卖家支付宝账号
	protected String buyer_email  ;//买家支付宝账号
	protected String total_fee ;//交易金额 100  取值范围为[0.01，100000000.00]单位为RMB-Yuan 
	protected String body ;//商品描述 1000 可空
	
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	public String getNotify_time() {
		return notify_time;
	}
	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}
	public String getNotify_type() {
		return notify_type;
	}
	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
