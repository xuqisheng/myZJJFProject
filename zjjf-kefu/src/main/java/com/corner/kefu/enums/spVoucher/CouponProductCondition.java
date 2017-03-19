/**   
* @Title: CouponProductCondition.java 
* @Package com.corner.kefu.enums.spVoucher 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月18日 上午8:57:01 
* @version V1.0   
*/

package com.corner.kefu.enums.spVoucher;

import java.util.EnumSet;
import java.util.HashMap;

/** 
* @ClassName: CouponProductCondition 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月18日 上午8:57:01 
*  
*/

public enum CouponProductCondition {
    ALL_PRODUCT("所有商品",0),
    ASSIGN_PRODUCT("指定商品/类别",1),
    EXCLUDE_PRODUCT("排除商品/类别",2);
	
	private String name;
	
	private Byte index;

	public static final EnumSet<CouponProductCondition> enumSet = EnumSet.allOf(CouponProductCondition.class);
	
	public static final HashMap<Byte, CouponProductCondition> map = new HashMap<>();
	
	static{
		if(map.size()==0){
			map.put(ALL_PRODUCT.getIndex(), ALL_PRODUCT);
			map.put(ASSIGN_PRODUCT.getIndex(), ASSIGN_PRODUCT);
			map.put(EXCLUDE_PRODUCT.getIndex(), EXCLUDE_PRODUCT);
		}
	}

	private CouponProductCondition(String name, int index) {
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
