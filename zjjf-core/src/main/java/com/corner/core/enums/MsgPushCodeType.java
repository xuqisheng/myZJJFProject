package com.corner.core.enums;
/**
 * 
* @ClassName: MsgPushCodeType
* @Description: TODO(这里用一句话描述这个类的作用)
* @author luke
* @email   luke@mibodoctor.com  
* @date 2015年4月2日 下午1:16:43
*
 */
public enum MsgPushCodeType {
	All("All", 1),
	OrderOverTime("OrderOverTime", 10),
	LessGoods("LessGoods", 100),
	Logistics("Logistics", 1000),
	Voucher("Voucher", 10000),
	Order("Order", 100000);

	private String name;
	private int index;

	private MsgPushCodeType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
