package com.corner.core.enums;
/**
 * 推送消息类型
 * @author winston at 2015年2月11日下午5:43:50
 * @Email 330262107@qq.com
 */
public enum MessageType {
	INTEGRATION_MSG("积分", 1),
	COUPON_MSG("优惠券", 2),
	ORDER_MSG("订单", 3),
	LOGISTICS_MSG("物流", 4),
	OTHER_MSG("其他消息", 5);

	private String name;
	private int index;

	private MessageType(String name, int index) {
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
