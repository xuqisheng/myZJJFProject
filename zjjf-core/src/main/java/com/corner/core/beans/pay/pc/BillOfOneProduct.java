package com.corner.core.beans.pay.pc;

import java.util.Map;


/**
* @ClassName: BillRevMust
* @Description: TODO( 购买当个商品的必要字段)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2014年12月19日 下午4:28:35
*
*/ 
public class BillOfOneProduct extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	protected String service;//接口名称	create_direct_pay_by_user 
	protected String partner;//合作者身份ID 以2088开头的16位纯数字组成。 2088101011913539 
	protected String _input_charset ;// 参数编码 utf-8、gbk、gb2312
	//private String sign_type ;//签名方式	DSA、RSA、MD5三个值可选，必须大写
	//private String sign ;//签名 
	protected String notify_url ;//服务器异步通知路径 可空  190
	protected String return_url ;//服务器同步通知路径  可空 200
	protected String show_url ;//商品展示urL 400 可空
	//private String out_trade_no ;//支付宝合作商户网站唯一订单号。64位
	//private String seller_email ;//卖家支付宝账号 100 	格式为邮箱或手机号。 可空
	//private String subject ;//商品名称 256 最长为128个汉字
	//private String body ;//商品描述 1000 可空
	//private String payment_type ;//支付类型  4 默认值为：1（商品购买）
	//private String total_fee ;//交易金额 100  取值范围为[0.01，100000000.00]单位为RMB-Yuan 
	//private String seller_id ;//卖家支付	宝用户号 16 以2088开头的纯16位数字
	
	public String get_input_charset() {
		return _input_charset;
	}
	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getShow_url() {
		return show_url;
	}
	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
}
