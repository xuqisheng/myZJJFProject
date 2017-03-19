package com.corner.core.beans.pay.pc;

import java.io.Serializable;

public class BillSendVO extends BillOfOneProduct  implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;
	
	//private String service;//接口名称	create_direct_pay_by_user 
	//private String partner;//合作者身份ID 以2088开头的16位纯数字组成。 2088101011913539 
	//private String _input_charset ;// 参数编码 utf-8、gbk、gb2312
	//private String sign_type ;//签名方式	DSA、RSA、MD5三个值可选，必须大写
	//private String sign ;//签名 
	//private String notify_url ;//服务器异步通知路径 可空  190
	//private String return_url ;//服务器同步通知路径  可空 200 
	private String error_notify_url ;//请求出错时的异步通知页面路径，需要联系支付宝开通 可空 200 
	
	
	//private String out_trade_no ;//支付宝合作商户网站唯一订单号。64位
	//private String subject ;//商品名称 256 最长为128个汉字
	//private String payment_type ;//支付类型  4 默认值为：1（商品购买）
	//private String total_fee ;//交易金额 100  取值范围为[0.01，100000000.00]单位为RMB-Yuan 
	//private String seller_id ;//卖家支付	宝用户号 16 以2088开头的纯16位数字
	
	//private String seller_email ;//卖家支付宝账号 100 	格式为邮箱或手机号。 可空
	private String seller_account_name ;//卖家支付宝账号 100  可空
	//private String buyer_id ;//买家支付宝用户号	宝用户号 16 以2088开头的纯16位数字 可空
	//private String buyer_email ;//买家支付宝用户号	100 	格式为邮箱或手机号。 可空
	private String buyer_account_name ;//买家支付宝用户号	100 	格式为邮箱或手机号。 可空
	private String price ;//商品单价 可空
	private String quantity ;//购买数量 price、quantity能代替total_fee 可空
	//private String body ;//商品描述 1000 可空
	//private String show_url ;//商品展示urL 400 可空
	private String paymethod ;//默认支付方式   directPay  creditPay 可空
	private String enable_paymethod ;//支付渠道 控制收银台支付渠道显示 可空
	private String need_ctu_check ;// 网银支付时是否做CTU校验 Y N  可空
	private String royalty_type ;//提成类型 2 可空
	private String royalty_parameters  ;//分润账号集 1000 可空
	private String anti_phishing_key  ;//防钓鱼时	间戳 可空
	private String exter_invoke_ip;//防钓鱼时 客户端IP	 可空
	
	private String extra_common_param;//公用回传参数 送什么回来什么 可空
	private String extend_param;//公用业务扩展参数 可空
	private String it_b_pay;//公用业务扩展参数 可空
	private String default_login;//自动登录标识 Y/N 可空
	private String product_type ;//商户申请	的产品类型  50 用于针对不同的产品，采取不同的计费策略 可空
	private String token ;//快捷登录授权令牌  40 可空
	
	private String item_orders_info;//买家通过etao购买的商品的详情清单  40000 可空
	private String sign_id_ext;//商户买家	签约号  50 可空
	private String sign_name_ext ;//商户买家	签约名  128 可空
	private String qr_pay_mode ;//扫码支付方式	  1 可空
	public String getError_notify_url() {
		return error_notify_url;
	}
	public void setError_notify_url(String error_notify_url) {
		this.error_notify_url = error_notify_url;
	}
	public String getSeller_account_name() {
		return seller_account_name;
	}
	public void setSeller_account_name(String seller_account_name) {
		this.seller_account_name = seller_account_name;
	}
	public String getBuyer_account_name() {
		return buyer_account_name;
	}
	public void setBuyer_account_name(String buyer_account_name) {
		this.buyer_account_name = buyer_account_name;
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
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getEnable_paymethod() {
		return enable_paymethod;
	}
	public void setEnable_paymethod(String enable_paymethod) {
		this.enable_paymethod = enable_paymethod;
	}
	public String getNeed_ctu_check() {
		return need_ctu_check;
	}
	public void setNeed_ctu_check(String need_ctu_check) {
		this.need_ctu_check = need_ctu_check;
	}
	public String getRoyalty_type() {
		return royalty_type;
	}
	public void setRoyalty_type(String royalty_type) {
		this.royalty_type = royalty_type;
	}
	public String getRoyalty_parameters() {
		return royalty_parameters;
	}
	public void setRoyalty_parameters(String royalty_parameters) {
		this.royalty_parameters = royalty_parameters;
	}
	public String getAnti_phishing_key() {
		return anti_phishing_key;
	}
	public void setAnti_phishing_key(String anti_phishing_key) {
		this.anti_phishing_key = anti_phishing_key;
	}
	public String getExter_invoke_ip() {
		return exter_invoke_ip;
	}
	public void setExter_invoke_ip(String exter_invoke_ip) {
		this.exter_invoke_ip = exter_invoke_ip;
	}
	public String getExtra_common_param() {
		return extra_common_param;
	}
	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}
	public String getExtend_param() {
		return extend_param;
	}
	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}
	public String getIt_b_pay() {
		return it_b_pay;
	}
	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}
	public String getDefault_login() {
		return default_login;
	}
	public void setDefault_login(String default_login) {
		this.default_login = default_login;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getItem_orders_info() {
		return item_orders_info;
	}
	public void setItem_orders_info(String item_orders_info) {
		this.item_orders_info = item_orders_info;
	}
	public String getSign_id_ext() {
		return sign_id_ext;
	}
	public void setSign_id_ext(String sign_id_ext) {
		this.sign_id_ext = sign_id_ext;
	}
	public String getSign_name_ext() {
		return sign_name_ext;
	}
	public void setSign_name_ext(String sign_name_ext) {
		this.sign_name_ext = sign_name_ext;
	}
	public String getQr_pay_mode() {
		return qr_pay_mode;
	}
	public void setQr_pay_mode(String qr_pay_mode) {
		this.qr_pay_mode = qr_pay_mode;
	}

}
