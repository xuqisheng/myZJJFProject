/**   
* @Title: ScmsFreightTplVo.java 
* @Package com.corner.scms.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月21日 上午10:56:18 
* @version V1.0   
*/

package com.corner.scms.beans.vo;

import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.ScmsFreightTpl;
import com.corner.core.beans.ScmsFreightTplMap;

/**
 * @ClassName: ScmsFreightTplVo
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月21日 上午10:56:18
 * 
 */

public class ScmsFreightTplVo extends ScmsFreightTpl {

	private List<ScmsFreightTplMap> list = new ArrayList<ScmsFreightTplMap>();

	public List<ScmsFreightTplMap> getList() {
		return list;
	}

	public void setList(List<ScmsFreightTplMap> list) {
		this.list = list;
	}

}
