package com.corner.core.pay.alipay.config;

import com.corner.core.config.PropertieNameConts;
import com.corner.core.utils.PropertiesCacheUtil;


/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfigForPC extends AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	//public static String partner = "";
	// 商户的淘宝账号
	//public static String seller_email = "mibo@mibodoctor.com";
	// 商户的私钥
	//public static String key = "";
	//默认支付方式
	//public static String payment_type = "1";
	//默认异步通知地址
	public static String notify_url = PropertiesCacheUtil.getValue("notify_url", PropertieNameConts.PAY);
	//默认同步通知地址
	public static String return_url = PropertiesCacheUtil.getValue("return_url", PropertieNameConts.PAY);
	//默认错误通知地址
	public static String error_notify_url =PropertiesCacheUtil.getValue("error_notify_url", PropertieNameConts.PAY);

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	//public static String log_path = "D:\\";
	
	// log4j logger name
	//public static String LOGGERNAME = "";

	// 字符编码格式 目前支持 gbk 或 utf-8
	//public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";
	
	// 签名方式 不需修改
	public static String TONGBUVO = "tongbuvo";
	
	// 发送方式 不需修改
	//public static String POST = "POST";
	
	// 发送方式 不需修改
	//public static String GET = "GET";
	
	// 发送方式 不需修改
	//public static String BUTTON = "确定";
	
	// 接口名称
	public static String service = "create_direct_pay_by_user";
	
	
	// 同步异常返回页面String
	public static String TBErrorPage = "Error";
	
	// 同步成功返回页面，目前交给前台js处理
	public static String TBSUCCESSPage = "BuySuccess";
	
	//异步通知返回字符串
	public static String YBFAIL= "fail";
	
	//异步通知返回字符串
	public static String YBSUCCESS= "success";

}
