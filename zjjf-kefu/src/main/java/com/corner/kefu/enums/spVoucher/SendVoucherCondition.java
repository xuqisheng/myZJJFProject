/**   
* @Title: SendVoucherCondition.java 
* @Package com.corner.kefu.enums.spVoucher 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月8日 下午6:09:11 
* @version V1.0   
*/

package com.corner.kefu.enums.spVoucher;

import java.util.EnumSet;
import java.util.HashMap;

/** 
* @ClassName: SendVoucherCondition 
* @Description:发送优惠劵条件
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月8日 下午6:09:11 
*  
*/

public enum SendVoucherCondition {
	NO_LIMIT("不限制", 0), NO_ORDER("从未下单用户", 1), NO_ORDER_MONTH("几个月内未下单用户", 2), EXCEL("Excel导入",
			3), DESIGNATED_CUSTOMERS("指定用户", 4);

	private String name;

	private Integer index;

	public static final EnumSet<SendVoucherCondition> enumSet = EnumSet.allOf(SendVoucherCondition.class);

	public static final HashMap<Integer, SendVoucherCondition> map = new HashMap<Integer,SendVoucherCondition>();

	static {
		if (map.size() == 0) {
			map.put(NO_LIMIT.getIndex(), NO_LIMIT);
			map.put(NO_ORDER.getIndex(), NO_ORDER);
			map.put(NO_ORDER_MONTH.getIndex(), NO_ORDER_MONTH);
			map.put(EXCEL.getIndex(), EXCEL);
			map.put(DESIGNATED_CUSTOMERS.getIndex(), DESIGNATED_CUSTOMERS);
		}
	}

	public static String getName(Integer index) {
		for (SendVoucherCondition sendVoucherCondition : SendVoucherCondition.values()) {
			if (sendVoucherCondition.getIndex().intValue() == index.intValue()) {
				return sendVoucherCondition.getName();
			}
		}
		return "";
	}

	private SendVoucherCondition(String name, Integer index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
}
