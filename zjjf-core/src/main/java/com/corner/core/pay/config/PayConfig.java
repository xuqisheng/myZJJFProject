package com.corner.core.pay.config;

public class PayConfig {
	public static int alipay = 1;
	public static int kuaiqian = 2;
	public static int wx = 3;
	
	// log4j logger name
	public static String LOGGERNAME = "PayLogger";
	
	/*** wap 端  阿里支付   授权接口  日志码***/
	public  static String WapAlipayAuthLogCode = "alipay.wap.trade.create.direct";
	public  static String WapAlipayAuthLogCodeSuccess = "alipay.wap.trade.create.direct:Success";
	public  static String WapAlipayAuthLogCodeFail = "alipay.wap.trade.create.direct:Fail";
	
	/*** wap 端  阿里支付   交易接口  日志码***/
	public  static String WapAlipayBusinessLogCode = "alipay.wap.auth.authAndExecute";
	public  static String WapAlipayBusinessLogCodeSuccess = "alipay.wap.auth.authAndExecute:Success";
	public  static String WapAlipayBusinessLogCodeFail = "alipay.wap.auth.authAndExecute:Fail";
	
	/*** wap 端  快钱支付   交易接口  日志码***/
	public  static String WapKQpayBusinessLogCode = "快钱支付：";
	
	
	/*** 转角街坊商品***/
	public  static String SystemSubjectName = "转角街坊商品";
	
	
	//是否记录支付失败到数据库
	public static Boolean IsLogErrorInDatabase= true;
	
	//是否记录阿里重复通知到数据库
	public static Boolean IsLogSuccessInDatabase= true;
	
}
