package com.corner.core.beans.pay.wap;

import java.io.Serializable;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class BussinesServicYBRecParams extends BussinesServicTBRecParams  implements Serializable{

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;

	private String service;//接口名称
	
	private String v;//接口版本
	
	private String sec_id;//签名方式 0001：RSA签名算法 MD5：MD5签名算法
	
	private String sign;//签名
	
	private String notify_data ;//通知业务参数
	
	//通知业务参数子参数
	private String payment_type;
	private String subject;
	private String trade_no;
	private String buyer_email;
	private String gmt_create;
	private String notify_type;
	private String quantity;
	private String out_trade_no;
	private String notify_time;
	private String seller_id;
	private String trade_status;
	private String is_total_fee_adjust ;
	private String total_fee ;
	private String gmt_payment ;
	private String seller_email;
	private String gmt_close;
	private String price;
	private String buyer_id;
	private String notify_id;
	private String use_coupon;
	private String refund_status;
	private String gmt_refund ;
 

	//该方法只初始化异步返回接口的参数
	public void  MapToBean( Map<String, String> map ) throws DocumentException {
		
		this.service = map.get("service");
		this.v = map.get("v");
		this.sec_id = map.get("sec_id");
		this.sign = map.get("sign");
		this.notify_data = map.get("notify_data");

		Document doc_notify_data = DocumentHelper.parseText(map.get("notify_data"));//data数据
		this.payment_type =  doc_notify_data.selectSingleNode( "//notify/payment_type" )==null?"":doc_notify_data.selectSingleNode( "//notify/payment_type" ).getText();
		this.subject =  doc_notify_data.selectSingleNode( "//notify/subject" )==null?"":doc_notify_data.selectSingleNode( "//notify/subject" ).getText();
		this.trade_no =  doc_notify_data.selectSingleNode( "//notify/trade_no" )==null?"":doc_notify_data.selectSingleNode( "//notify/trade_no" ).getText();
		this.trade_status =  doc_notify_data.selectSingleNode( "//notify/trade_status" )==null?"":doc_notify_data.selectSingleNode( "//notify/trade_status" ).getText();
		this.buyer_email =  doc_notify_data.selectSingleNode( "//notify/buyer_email" )==null?"":doc_notify_data.selectSingleNode( "//notify/buyer_email" ).getText();
		this.gmt_create =  doc_notify_data.selectSingleNode( "//notify/gmt_create" )==null?"":doc_notify_data.selectSingleNode( "//notify/gmt_create" ).getText();
		this.notify_type =  doc_notify_data.selectSingleNode( "//notify/notify_type" )==null?"":doc_notify_data.selectSingleNode( "//notify/notify_type" ).getText();
		this.quantity =  doc_notify_data.selectSingleNode( "//notify/quantity" )==null?"":doc_notify_data.selectSingleNode( "//notify/quantity" ).getText();
		this.out_trade_no =  doc_notify_data.selectSingleNode( "//notify/out_trade_no" )==null?"":doc_notify_data.selectSingleNode( "//notify/out_trade_no" ).getText();
		this.notify_time =  doc_notify_data.selectSingleNode( "//notify/notify_time" )==null?"":doc_notify_data.selectSingleNode( "//notify/notify_time" ).getText();
		this.seller_id =  doc_notify_data.selectSingleNode( "//notify/seller_id" )==null?"":doc_notify_data.selectSingleNode( "//notify/seller_id" ).getText();
		this.is_total_fee_adjust =  doc_notify_data.selectSingleNode( "//notify/is_total_fee_adjust" )==null?"":doc_notify_data.selectSingleNode( "//notify/is_total_fee_adjust" ).getText();
		this.total_fee =  doc_notify_data.selectSingleNode( "//notify/total_fee" )==null?"":doc_notify_data.selectSingleNode( "//notify/total_fee" ).getText();
		this.gmt_payment =  doc_notify_data.selectSingleNode( "//notify/gmt_payment" )==null?"":doc_notify_data.selectSingleNode( "//notify/gmt_payment" ).getText();
		this.seller_email =  doc_notify_data.selectSingleNode( "//notify/seller_email" )==null?"":doc_notify_data.selectSingleNode( "//notify/seller_email" ).getText();
		this.gmt_close = doc_notify_data.selectSingleNode( "//notify/gmt_close" )==null?"":doc_notify_data.selectSingleNode( "//notify/gmt_close" ).getText();
		this.price = doc_notify_data.selectSingleNode( "//notify/price" )==null?"":doc_notify_data.selectSingleNode( "//notify/price" ).getText();
		this.buyer_id = doc_notify_data.selectSingleNode( "//notify/buyer_id" )==null?"":doc_notify_data.selectSingleNode( "//notify/buyer_id" ).getText();
		this.notify_id = doc_notify_data.selectSingleNode( "//notify/notify_id" )==null?"":doc_notify_data.selectSingleNode( "//notify/notify_id" ).getText();
		this.use_coupon = doc_notify_data.selectSingleNode( "//notify/use_coupon" )==null?"":doc_notify_data.selectSingleNode( "//notify/use_coupon" ).getText();
		this.refund_status = doc_notify_data.selectSingleNode( "//notify/refund_status" )==null?"":doc_notify_data.selectSingleNode( "//notify/refund_status" ).getText();
		this.gmt_refund = doc_notify_data.selectSingleNode( "//notify/gmt_refund" )==null?"":doc_notify_data.selectSingleNode( "//notify/gmt_refund" ).getText();
		
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getSec_id() {
		return sec_id;
	}

	public void setSec_id(String sec_id) {
		this.sec_id = sec_id;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNotify_data() {
		return notify_data;
	}

	public void setNotify_data(String notify_data) {
		this.notify_data = notify_data;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(String gmt_create) {
		this.gmt_create = gmt_create;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public String getIs_total_fee_adjust() {
		return is_total_fee_adjust;
	}

	public void setIs_total_fee_adjust(String is_total_fee_adjust) {
		this.is_total_fee_adjust = is_total_fee_adjust;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getGmt_payment() {
		return gmt_payment;
	}

	public void setGmt_payment(String gmt_payment) {
		this.gmt_payment = gmt_payment;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getGmt_close() {
		return gmt_close;
	}

	public void setGmt_close(String gmt_close) {
		this.gmt_close = gmt_close;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getUse_coupon() {
		return use_coupon;
	}

	public void setUse_coupon(String use_coupon) {
		this.use_coupon = use_coupon;
	}

	public String getRefund_status() {
		return refund_status;
	}

	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}

	public String getGmt_refund() {
		return gmt_refund;
	}

	public void setGmt_refund(String gmt_refund) {
		this.gmt_refund = gmt_refund;
	}
	
	
}
