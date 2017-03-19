/**   
* @Title: SpVoucherConstants.java 
* @Package com.corner.pc.util 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月12日 上午10:10:02 
* @version V1.0   
*/

package com.corner.kefu.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
* @ClassName: SpVoucherConstants 
* @Description:用于发送优惠劵,防止重复发送
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年4月12日 上午11:02:57 
*
 */
public class SpVoucherConstants {

	public static Map<String, AtomicInteger> ConstantsMap = new HashMap<String,AtomicInteger>();
}
