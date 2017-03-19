package com.corner.core.pay.kqpay.config;

import com.corner.core.config.PropertieNameConts;
import com.corner.core.pay.config.PayConfig;
import com.corner.core.utils.PropertiesCacheUtil;


public class KqpayConfig extends PayConfig {

	public static String  PAY_GATEWAY ="http://www.99bill.com/mobilegateway/recvMerchantInfoAction.htm";
	//public static String  PAY_GATEWAY ="https://sandbox.99bill.com/mobilegateway/recvMerchantInfoAction.htm";
	public static String  merchantAcctId ="1002358301301";
	//public static String  merchantAcctId ="1002418442701";
	public static String  inputCharset ="1";//编码方式，1代表 UTF-8; 2 代表 GBK; 3代表 GB2312 默认为1,该参数必填。
	
	public static String version =  "mobile1.0";//网关版本，固定值：v2.0,该参数必填。
	public static String mobileGateway="phone";
	
	//接收支付结果的页面地址，该参数一般置为空即可。
	public static String pageUrl = "";
	//服务器接收支付结果的后台地址，该参数务必填写，不能为空。
	public static String bgUrl =  PropertiesCacheUtil.getValue("kqpay_wap_rmbport_bgurl", PropertieNameConts.PAY);
	//服务器接收支付结果防护地址
	public static String showUrl = PropertiesCacheUtil.getValue("kqpay_wap_rmbport_showurl", PropertieNameConts.PAY);
	//服务器接收支付结果防护地址(同步)
	public static String TBSuccessPage = "BuySuccess";
	//服务器接收支付结果防护地址(同步)
	public static String TBFailurePage = "Error";
	
	//语言种类，1代表中文显示，2代表英文显示。默认为1,该参数必填。
	public static String language =  "1";
	//签名类型,该值为4，代表PKI加密方式,该参数必填。
	public static String signType =  "4";
	//支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10，必填。
	public static String payType = "21";
	
	

}
