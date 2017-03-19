/**   
* @Title: ScOrderInfoVo.java 
* @Package com.corner.scms.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月29日 下午3:23:24 
* @version V1.0   
*/

package com.corner.scms.beans.vo;

import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.ScmsOrderInfo;
import com.corner.scms.beans.vo.sc.ScOrderDetailVo;

/**
 * @ClassName: ScOrderInfoVo
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月29日 下午3:23:24
 * 
 */

public class ScOrderInfoVo extends ScmsOrderInfo {

	List<ScOrderDetailVo> list = new ArrayList<ScOrderDetailVo>();

	public List<ScOrderDetailVo> getList() {
		return list;
	}

	public void setList(List<ScOrderDetailVo> list) {
		this.list = list;
	}

}
