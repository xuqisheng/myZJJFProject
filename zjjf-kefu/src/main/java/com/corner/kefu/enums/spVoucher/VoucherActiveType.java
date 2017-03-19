/**   
* @Title: VoucherActiveType.java 
* @Package com.corner.kefu.enums.spVoucher 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月21日 下午2:44:21 
* @version V1.0   
*/

package com.corner.kefu.enums.spVoucher;

import java.util.EnumSet;
import java.util.HashMap;

/** 
* @ClassName: VoucherActiveType 
* @Description: 活动类型
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月21日 下午2:44:21 
*  
*/

public enum VoucherActiveType {
	DEFALULT_ACTIVE("默认活动", 0),
	REGISTER_SEND_COUPON("注册送优惠劵", 1),
	ORDER_SEND__COUPON("满送优惠劵", 2),
	ORDER_REBATE("满减", 3),
	ORDER_SEND_COUPON_PRODUCT("满送优惠劵和商品", 9),
	ORDER_REBATE_PRODUCT("满减+送商品", 10),
	ORDER_PRODUCT("满送商品", 11),
	BUY_SEND_PRODUCT("满购商品送商品", 12),
	ADVANCE_ORDER("提前下单送优惠劵",13),
	ACCUMULATE_SEND_COUPON("累积送劵",14);

	private String name;
	private Byte index;

	public final static EnumSet<VoucherActiveType> enumSet = EnumSet.allOf(VoucherActiveType.class);
	
	public final static HashMap<Byte, VoucherActiveType> map = new HashMap<>();
	
	static{
		if(map.size()==0){
			map.put(DEFALULT_ACTIVE.getIndex(), DEFALULT_ACTIVE);
			map.put(REGISTER_SEND_COUPON.getIndex(), REGISTER_SEND_COUPON);
			map.put(ORDER_SEND__COUPON.getIndex(), ORDER_SEND__COUPON);
			map.put(ORDER_REBATE.getIndex(), ORDER_REBATE);
			map.put(ORDER_SEND_COUPON_PRODUCT.getIndex(), ORDER_SEND_COUPON_PRODUCT);
			map.put(ORDER_REBATE_PRODUCT.getIndex(), ORDER_REBATE_PRODUCT);
			map.put(ORDER_PRODUCT.getIndex(), ORDER_PRODUCT);
			map.put(BUY_SEND_PRODUCT.getIndex(), BUY_SEND_PRODUCT);
			map.put(ADVANCE_ORDER.getIndex(), ADVANCE_ORDER);
			map.put(ACCUMULATE_SEND_COUPON.getIndex(), ACCUMULATE_SEND_COUPON);
		}
	}
	
	private VoucherActiveType(String name,int index) {
		this.name = name;
		this.index = (byte) index;
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
