/**   
* @Title: SKUActiveStatus.java 
* @Package com.corner.kefu.enums 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月4日 下午5:37:00 
* @version V1.0   
*/

package com.corner.kefu.enums.sku;

import java.util.EnumMap;
import java.util.EnumSet;

/** 
* @ClassName: SKUActiveStatus 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月4日 下午5:37:00 
*  
*/

public enum SKUActiveStatus {
    NEW("新建",0),
    APPROVAL("审批",1),
    TAKE_EFFECT("生效",2),
    AUTOMATICALLY_TAKE_EFFECT("自动结束",3),
    UNAUTOMATICALLY_TAKE_EFFECT("手动结束",4);
	
	private String name;
	
	private Byte index;
	
	@SuppressWarnings("unused")
	private final static EnumSet<SKUActiveStatus> enumSet = EnumSet.allOf(SKUActiveStatus.class);
	
	private final static EnumMap<SKUActiveStatus,String> enumMap = new EnumMap<SKUActiveStatus,String>(SKUActiveStatus.class);
	
	static {
		if(enumMap.size()==0){
			enumMap.put(SKUActiveStatus.NEW, "新建");
			enumMap.put(SKUActiveStatus.APPROVAL, "审批");
			enumMap.put(SKUActiveStatus.TAKE_EFFECT, "生效");
			enumMap.put(SKUActiveStatus.AUTOMATICALLY_TAKE_EFFECT, "自动结束");
			enumMap.put(SKUActiveStatus.UNAUTOMATICALLY_TAKE_EFFECT, "手动结束");
		}
	}
	
	public static String getName(Byte index) {
		for (SKUActiveStatus skuActiveStatus : SKUActiveStatus.values()) {
			if(skuActiveStatus.getIndex().intValue() == index.intValue()){
				return skuActiveStatus.getName();
			}
		}
		return "";
	}
	
	SKUActiveStatus(String name, int index){
		this.name = name;
		this.index = (byte)index;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the index
	 */
	public Byte getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Byte index) {
		this.index = index;
	}
	
}
