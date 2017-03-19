/**   
* @Title: ScmsShoppingCartVo.java 
* @Package com.corner.scms.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月21日 下午5:46:19 
* @version V1.0   
*/

package com.corner.scms.beans.vo;

import com.corner.core.beans.ScmsShoppingCart;

/**
 * @ClassName: ScmsShoppingCartVo
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月21日 下午5:46:19
 * 
 */

public class ScmsShoppingCartVo extends ScmsShoppingCart {

	private String managerId;

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}
