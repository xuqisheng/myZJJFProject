package com.corner.core.beans.pay.pc;

import java.util.Map;

/**
* @ClassName: YBBackvo
* @Description: TODO 异步回传参数bean 支付宝POST方式发送通知信息
* 只有在支付宝的交易管理中存在该笔交易，且发生了交易状态的改变
* 支付宝才会通过该方式发起服务器通知（即时到账中交易状态为“等待买家付款的状态默认是不会发送通知的）
* @author luke
* @email   luke@mibodoctor.com  
* @date 2014年12月19日 上午10:49:44
*
*/ 
public class YBBackVO extends TBBackVO {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;
	private String gmt_create ;//交易创建时间
	private String gmt_payment ;//交易付款时间
	private String gmt_close ;//交易关闭时间
	private String refund_status ;//退款状态
	private String gmt_refund ;//退款时间
	private String price ;//商品单价 可空
	private String quantity ;//购买数量 price、quantity能代替total_fee 可空
	private String discount ;//折扣 Number 本参数为负数 -5 可空
	private String is_total_fee_adjust ;//是否调整总价 N/Y 可空
	private String use_coupon  ;//是否使用红包	买家 可空
	private String out_channel_type;//支付渠道组合信息
	private String out_channel_amount;//支付金	额组合信息
	private String out_channel_inst;//实际支付渠道
	private String business_scene;//是否扫码支付
	
	
	public void mapToBean(Map<String,String> map){
		//base
		this.sign_type = map.get("sign_type");
		this.sign = map.get("sign");
		this.notify_id = map.get("notify_id");
		this.notify_time = map.get("notify_time");
		this.notify_type = map.get("notify_type");
		this.out_trade_no = map.get("out_trade_no");
		this.subject = map.get("subject");
		this.payment_type = map.get("payment_type");
		this.trade_no = map.get("trade_no");
		this.trade_status = map.get("trade_status");
		this.seller_id = map.get("seller_id");
		this.buyer_id = map.get("buyer_id");
		this.seller_email = map.get("seller_email");
		this.buyer_email = map.get("buyer_email");
		this.total_fee = map.get("total_fee");
		this.body = map.get("body");
		//TBBackVO
		this.is_success = map.get("is_success");
		this.exterface = map.get("exterface");
		this.extra_common_param = map.get("extra_common_param");
		this.agent_user_id = map.get("agent_user_id");
		//YBBackVO
		this.gmt_create = map.get("gmt_create");
		this.gmt_payment = map.get("gmt_payment");
		this.gmt_close = map.get("gmt_close");
		this.refund_status = map.get("refund_status");
		this.gmt_refund = map.get("gmt_refund");
		this.price = map.get("price");
		this.quantity = map.get("quantity");
		this.discount = map.get("discount");
		this.is_total_fee_adjust = map.get("is_total_fee_adjust");
		this.use_coupon = map.get("use_coupon");
		this.out_channel_type = map.get("out_channel_type");
		this.out_channel_amount = map.get("out_channel_amount");
		this.out_channel_inst = map.get("out_channel_inst");
		this.business_scene = map.get("business_scene");
	}
	
	public String getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(String gmt_create) {
		this.gmt_create = gmt_create;
	}
	public String getGmt_payment() {
		return gmt_payment;
	}
	public void setGmt_payment(String gmt_payment) {
		this.gmt_payment = gmt_payment;
	}
	public String getGmt_close() {
		return gmt_close;
	}
	public void setGmt_close(String gmt_close) {
		this.gmt_close = gmt_close;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getIs_total_fee_adjust() {
		return is_total_fee_adjust;
	}
	public void setIs_total_fee_adjust(String is_total_fee_adjust) {
		this.is_total_fee_adjust = is_total_fee_adjust;
	}
	public String getUse_coupon() {
		return use_coupon;
	}
	public void setUse_coupon(String use_coupon) {
		this.use_coupon = use_coupon;
	}
	public String getExtra_common_param() {
		return extra_common_param;
	}
	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}
	public String getOut_channel_type() {
		return out_channel_type;
	}
	public void setOut_channel_type(String out_channel_type) {
		this.out_channel_type = out_channel_type;
	}
	public String getOut_channel_amount() {
		return out_channel_amount;
	}
	public void setOut_channel_amount(String out_channel_amount) {
		this.out_channel_amount = out_channel_amount;
	}
	public String getOut_channel_inst() {
		return out_channel_inst;
	}
	public void setOut_channel_inst(String out_channel_inst) {
		this.out_channel_inst = out_channel_inst;
	}
	public String getBusiness_scene() {
		return business_scene;
	}
	public void setBusiness_scene(String business_scene) {
		this.business_scene = business_scene;
	}	
}
