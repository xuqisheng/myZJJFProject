package com.corner.core.pay.wx.config;

import com.corner.core.config.PropertieNameConts;
import com.corner.core.utils.PropertiesCacheUtil;

public class WXConfig {

	/*----------------------------------------------统一支付接口 开始--------------------------------------------------------------*/
	public static String AppID="wx1528e677aed256f3";
	
	public static String AppSecret="254f411ea817107711a80359393c2293";
	
	//public static String AppID="wxa9d1bc0ecd0ce345"; //杨开泰——测试
	
	//public static String AppSecret="a19fa3381493511d99870e3903afa429";  //杨开泰——测试
    	
	public static String Partner="10141265";
	
	public static String PartnerKey="miwue2234djdajacmdl23432jjfdsaop";

	public static String TY_ZF_Url="https://api.mch.weixin.qq.com/pay/unifiedorder";//统一支付接口
	
	public static String GET_OPENID_Url="https://open.weixin.qq.com/connect/oauth2/authorize";//授权url
	
	public static String GET_OPENID_reUrl=PropertiesCacheUtil.getValue("GET_OPENID_reUrl", PropertieNameConts.PAY);//授权url跳转
	
	public static String GetTockenUrl="https://api.weixin.qq.com/cgi-bin/token?";//for jssdk验证
	
	public static String GetTicktetUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?";//for jssdk验证
	
	public static String getOpenIdUrl ="https://api.weixin.qq.com/sns/oauth2/access_token?";//for get openid and for 统一支付
	
	public static String Notify_Url =PropertiesCacheUtil.getValue("wxpay_wap_notify_url", PropertieNameConts.PAY);
	
	public static String  TradeTypeJSAPI="JSAPI",TradeTypeAPP="APP",TradeTypeNATIVE="NATIVE",TradeTypeWAP="WAP";//目前就JSAPI，NATIVE，APP，WAP
	
	/*----------------------------------------------统一支付接口 结束--------------------------------------------------------------*/
	
	/*----------------------------------------------微信商城--------------------------------------------------------------*/
	
	public static String WXSC_DEFINE_TOKEN ="izjjf";
	
	public static String WXSC_DEFINE_EncodingAESKey ="bMco6EpNRyOT7xh7nl78QUf6RCOg2z5lXlZ16EUIuqc";
	/*----------------------------------------------微信商城--------------------------------------------------------------*/
	
	//"GBK " ,"UTF-8"
	public static String input_charset ="UTF-8";

	//收款方
	public static String spname = "转角街坊";                                           

	//商户号
	public static String partner = "1220321801";

	//密钥
	public static String partner_key = "ed2ad4189c5dc764b393c1f65bd46f50";

	//appi
	public static String app_id="wx2e475d4d513c4c47";

	public static String app_secret = "0da7785a7fefc730ba506134b66c13e7";

	//appkey
	public static String app_key="ph0G69Z6qTBWySFSXtrpdu71cMHpCVlxeQzfbLA8mprzUEC8z1kRILfylFE9Hds1X4sLeAmylG38wcQH90DHfEEO6l9jjicCWWNQ2DIA2jXmB9RnoE2oNuvIGBV16AUX";

	//支付完成后的回调处理页面网页版
	public static String notify_url =PropertiesCacheUtil.getValue("wxpay_wap_notify_url", PropertieNameConts.PAY);
	//支付完成后的回调处理页面app版
	public static String notifyapp_url =PropertiesCacheUtil.getValue("wxpay_wap_notifyapp_url", PropertieNameConts.PAY);
	
	//调试模式
	public static boolean DEBUG_ = true;

	
	/*----------------------------------------------进销存统一支付接口-----------------------------------------------*/
	public static String scmsAppID="wx1528e677aed256f3";
	public static String scmsMch_id="10141265";
	public static String scmsKey="miwue2234djdajacmdl23432jjfdsaop";
	//addd by tiezhongtang  联合采购  2016-2-25
	public static String scms_app_id="wx6a287e937af50fd4";
	public static String scms_app_secret = "30de776f8b0b9f23d5550894ebf0600e";
	public static String scms_partner = "1315654101";//邮件中取得
	public static String scms_partner_key = "bXs4vLaDx7IMhp25ezlhWBtvmZbs0YeV";//登录后台设置的api密匙
	//public static String scms_notify_url = "http://zjjf.f3322.net:8077/scms/scms/pay/WXPayBackNotify.do";
	public static String scms_notify_url = PropertiesCacheUtil.getValue("scms_notify_url", PropertieNameConts.PAY);
   
	
	
}
