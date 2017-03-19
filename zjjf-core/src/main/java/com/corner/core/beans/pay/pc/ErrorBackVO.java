package com.corner.core.beans.pay.pc;


/**
* @ClassName: ErrorBackVO
* @Description: TODO(错误推送参数bean,地址为：error_notify_url，商户需要联系支付宝开通此功能)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2014年12月19日 上午11:13:17
*
*/ 
public class ErrorBackVO extends BaseBean {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = 1L;


	/**
	 * 以下参数为必填参数
	 */
	//private String partner ;//合作者	身份ID 以2088开头的16位纯数字组成。
	//private String out_trade_no ;//支付宝合作商户网站唯一订单号。64位 
	//private String error_code ;//错误码 1024 当出现多个错误时，将错误码用"|”连接起来
	//private String return_url ;// 请求出错时的通知页面路径 200
	/**
	 * 以下参数可空
	 */
	//private String seller_email  ;//卖家支付宝账号
	//private String buyer_email  ;//买家支付宝账号
	//private String seller_id ;//卖家支付	宝用户号 16 以2088开头的纯16位数字
	//private String buyer_id ;//买家支付宝用户号	宝用户号 16 以2088开头的纯16位数字 可空

}
