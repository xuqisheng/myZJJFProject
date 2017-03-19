package com.corner.core.pay.alipay.config;


/**
* @ClassName: AlipayErrorConfig
* @Description: TODO 提供阿里支付模块所有的平台错误类型参数配置（模块错误码）
* @author luke
* @email   luke@mibodoctor.com  
* @date 2014年12月23日 上午11:26:42
*
*/ 
public class AlipayErrorConfig {
	
	 
	public static String Bill_NOT_EXIT = "订单不存在，不能进行支付";
	
	public static String PRICE_NOT_RIGHT = "订单金额不能小于0";
 
	public static String DEPEND_IS_NULL = "依赖模块没有成功初始化";

	public static String ALIPAY_MOD_ERROR = "阿里支付模块处理订单Map出现异常";
	
	public static String ERROR_TOKEN_REQUEST = "获取支付支付宝token请求返回参数为空";
	
	public static String ERROR_CREATE_TOKEN = "获取支付支付宝token失败";
	
	public static String REQUEST_GET_PARA_ERROR = "获取request参数失败 或者 参数转码错误";
	
	public static String SIGN_VERIFY_EXCEPTION = "签名验证过程异常";
}
