/**   
 * @Title: ScmsUserTypeVo.java 
 * @Package com.corner.scms.beans.vo 
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn   
 * @date 2015年12月7日 下午7:23:46 
 * @version V1.0   
 */
package com.corner.scms.beans.vo;

import com.corner.core.beans.ScmsUserType;

/**
 * @ClassName: ScmsUserTypeVo
 * @Description:用户类型视图类
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2015年12月7日 下午7:23:46
 */
public class ScmsUserTypeVo extends ScmsUserType {
	private Double salePrice;//售价

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

}
