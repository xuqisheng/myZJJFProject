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

public class AlipayConfigForWAP extends AlipayConfig {
	
	//手机支付宝网关地址
	public static String  ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	//public static String partner = "";
	
	// 交易安全检验码，由数字和字母组成的32位字符串
	// 如果签名方式设置为“MD5”时，请设置该参数
	//public static String key = "";
	
    // 商户的私钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String private_key = ""
			+"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMmmdCao9cuE+Uf+"
			+"ecSU5DGvUJ0j2/3BElhwpkT8adxosH7x08LtUiPc3q3BmHkeC7ASKBtgqDuTwWVa"
			+"lYWYsmB1NVgACTkVpRG/EjQ7OHBTJ7tTYESEafn37hBeXSVg6PTlS05vABxBvuOn"
			+"0LYPYYT1JLxIwXcJ8t9XoRLYuFvRAgMBAAECgYEAiYWKmz8JekXymAXV15pPUg8l"
			+"JDXrd1BwCX6x9kRKkfWXwMt9xueA6FJeTxPDv34dLHQFhD5UHWV0NIIljNoJc4UI"
			+"FTKbgppNPZ5psma4WbXSJYEkGuSGnnSF87Q00dvtML50F4L5wZekv4p5d6VX+7mX"
			+"xrXPiS4rUkQO53QA3xECQQD/v5txgCbggSWigDpDpPluuW/Dz3AiogIJre6XD9qF"
			+"fZkWTc2JuRfw+MO9AWEwniaU25pSpc86yksMk045yzadAkEAydk5vk5juD4mYyam"
			+"07XlnpIewFP2B1T5LbGryWsNn6ULVhW/xK9pnoaQL065/+xJemayhoCPhQoAlD9J"
			+"cmoZxQJAUZIkmyv7ummWvmDVtJOe9yDwM+0q3/D6Y2n1insFAMTRmhqQrxe5OiOu"
			+"Es+o1x/qQFpWu3ulQCQbkdQ+BBL7xQJATpZQsRN8J8Oq1ne6BDuRxwdFCcSmyXv/"
			+"+BZwt98CUuHDZCTboQxapunlQUX5jPbGNO2/HDb5qeaXCyEIy48bsQJAF4uWtuF2"
			+"cRSJCVupB9vAJ/5N3X5Kjxnp2UnTpl7gk1dVoLVH78GvuadzoYNbvdPxvPCndGw2"
			+"gc913P4QFXVYAg==";

    // 支付宝的公钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNBC5NG6n1iQclVM7Tmpf3ZguCNIGG9YW4bqcEbt/kryCOBXAAlBpb96Kxy873WUPkXoIzLrmg37+rKxJFwylRE66rMiPbsDhk25YRFun0EAGHxgPrYiXpXWiT7jiOmgOq4oWIwLmjPggDUrMRtwGMX0gZla+xu7Rfqm7Qr3tg9wIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
	// 调试用，创建TXT日志文件夹路径
	//public static String log_path = "D:\\";

	// 字符编码格式 目前支持  utf-8
	//public static String input_charset = "utf-8";
	
	// 签名方式，选择项：0001(RSA)、MD5
	public static String sign_type = "0001";
	// 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA
	
	// 接口名称
	public static String serviceToken = "alipay.wap.trade.create.direct";
	
	// 接口名称
	public static String serviceExe = "alipay.wap.auth.authAndExecute";
	
	//参数格式
	public static String format = "xml";
	
	//版本号
	public static String v = "2.0";
	
	//notify_url
	public static String notify_url =PropertiesCacheUtil.getValue("alipay_wap_notify_url", PropertieNameConts.PAY);
	
	//call_back_url
	public static String call_back_url = PropertiesCacheUtil.getValue("alipay_wap_call_back_url", PropertieNameConts.PAY);
	
	//操作中断返回地址
	public static String merchant_url = PropertiesCacheUtil.getValue("alipay_wap_merchant_url", PropertieNameConts.PAY);
	//用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数
	
	// 同步异常返回页面String
	public static String TBErrorPage = "Error";
	
	// 同步成功返回页面，目前交给前台js处理
	public static String TBSUCCESSPage = "BuySuccess";
	
	// 同步异常返回页面String
	public static String YBFAIL= "fail";
	
	// 同步异常返回页面String
	public static String YBSUCCESS= "success";
	

}
