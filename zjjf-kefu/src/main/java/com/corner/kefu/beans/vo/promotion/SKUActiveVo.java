/**   
* @Title: SKUActiveVo.java 
* @Package com.corner.kefu.beans.vo.promotion 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月30日 下午3:18:46 
* @version V1.0   
*/

package com.corner.kefu.beans.vo.promotion;

import com.corner.core.beans.SKUActive;

/** 
* @ClassName: SKUActiveVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月30日 下午3:18:46 
*  
*/

public class SKUActiveVo extends SKUActive {

	private SKUActiveGoodInfoVo skuActiveGoodInfoVo;

	private PlantItemGroupVo plantItemGroupVo;

	public SKUActiveGoodInfoVo getSkuActiveGoodInfoVo() {
		return skuActiveGoodInfoVo;
	}

	public void setSkuActiveGoodInfoVo(SKUActiveGoodInfoVo skuActiveGoodInfoVo) {
		this.skuActiveGoodInfoVo = skuActiveGoodInfoVo;
	}

	public PlantItemGroupVo getPlantItemGroupVo() {
		return plantItemGroupVo;
	}

	public void setPlantItemGroupVo(PlantItemGroupVo plantItemGroupVo) {
		this.plantItemGroupVo = plantItemGroupVo;
	}
}
