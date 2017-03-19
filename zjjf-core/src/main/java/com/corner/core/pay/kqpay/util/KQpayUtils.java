package com.corner.core.pay.kqpay.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.pay.alipay.config.AlipayConfig;
import com.corner.core.pay.alipay.config.AlipayConfigForPC;
import com.corner.core.pay.alipay.sign.MD5;

public class KQpayUtils {
	
	private static Logger logger = LoggerFactory.getLogger(AlipayConfig.LOGGERNAME);
	
    /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("signMsg")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }
    
    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("signMsg", mysign);
        sPara.put("sign_type", AlipayConfigForPC.sign_type);

        return sPara;
    }
    
    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if(AlipayConfigForPC.sign_type.equals("MD5") ) {
        	mysign = MD5.sign(prestr, AlipayConfigForPC.key, AlipayConfigForPC.input_charset);
        }
        return mysign;
    }
	
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

    	String prestr = "";
    	int  size = params.size();
        for (String key : params.keySet()) {
        	   if (  (size--) > 0 ) {//拼接时，不包括最后一个&字符
                   prestr = prestr + key + "=" + params.get(key) + "&";
               } else {
            	   prestr = prestr + key + "=" + params.get(key);
               }
        }
        return prestr;
    }

    public static void main(String[] args) {
    	  Map<String, String> map = new HashMap<String, String>();
    	  map.put("1", "value1");
    	  map.put("2", "value2");
    	  map.put("3", "value3");
    	  System.out.println(createLinkString(map));
	}
}
