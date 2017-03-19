/**   
* @Title: PayMethod.java 
* @Package com.corner.core.enums 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月4日 下午1:51:08 
* @version V1.0   
*/

package com.corner.core.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/** 
* @ClassName: PayMethod 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月4日 下午1:51:08 
*  
*/

public enum PayMethod {
	KUAIQIAN("快捷支付", 1), CASH_ON_DELIVERY("货到付款", 2), ALIPAY("支付宝支付", 3), WEIXIN("微信支付", 4), ZJWALLET("转角钱包", 5);
	private String name;
	private Byte index;

	@SuppressWarnings("unused")
	private final static EnumSet<PayMethod> enumSet = EnumSet.allOf(PayMethod.class);

	private final static Map<PayMethod, String> map = new HashMap<PayMethod,String>();

	static {
		if(map.size()==0){
			map.put(PayMethod.KUAIQIAN, "快捷支付");
			map.put(PayMethod.CASH_ON_DELIVERY, "货到付款");
			map.put(PayMethod.ALIPAY, "支付宝支付");
			map.put(PayMethod.WEIXIN, "微信支付");
			map.put(PayMethod.ZJWALLET, "转角钱包");
		}
	}

	PayMethod(String name, int index) {
		this.name = name;
		this.index = (byte) index;
	}

	public static String getName(Byte index) {
		for (PayMethod payMethod : PayMethod.values()) {
			if (payMethod.getIndex().intValue() == index.intValue()) {
				return payMethod.name;
			}
		}
		return "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getIndex() {
		return index;
	}

	public void setIndex(Byte index) {
		this.index = index;
	}
}
