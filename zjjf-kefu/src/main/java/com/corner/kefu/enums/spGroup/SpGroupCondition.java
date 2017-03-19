/**   
* @Title: SpGroupCondition.java 
* @Package com.corner.kefu.enums.spGroup 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月8日 下午6:24:34 
* @version V1.0   
*/

package com.corner.kefu.enums.spGroup;

import java.util.HashMap;
import java.util.Map;

/** 
* @ClassName: SpGroupCondition 
* @Description: 定格条件
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月8日 下午6:24:34 
*  
*/

public enum SpGroupCondition {

	ALL_SPGROUP("所有定格", 0), DESIGNATED_SPGROUP("指定定格", 1);

	private String name;

	private Integer index;

	public static final Map<Integer, SpGroupCondition> map = new HashMap<Integer,SpGroupCondition>();
	
	static{
		if(map.size()==0){
			map.put(SpGroupCondition.ALL_SPGROUP.getIndex(), ALL_SPGROUP);
			map.put(SpGroupCondition.DESIGNATED_SPGROUP.getIndex(), DESIGNATED_SPGROUP);
		}
	}
	
	private SpGroupCondition(String name, Integer index) {
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
