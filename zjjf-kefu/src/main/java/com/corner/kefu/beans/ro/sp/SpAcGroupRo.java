/**   
* @Title: SpAcGroupRo.java 
* @Package com.corner.mobile.common.beans.ro 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月14日 下午5:55:44 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.sp;

import com.corner.core.beans.ro.AmazeUIListRo;

/** 
* @ClassName: SpAcGroupRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月14日 下午5:55:44 
*  
*/

public class SpAcGroupRo extends AmazeUIListRo {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1513504736071615316L;
	
	private String id;
	
	private String keyStr;

	public String getKeyStr() {
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

}
