/**   
* @Title: ScmsMinimumVo.java 
* @Package com.corner.scms.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月19日 下午2:42:54 
* @version V1.0   
*/

package com.corner.scms.beans.vo;

import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.ScmsMinimum;

/**
 * @ClassName: ScmsMinimumVo
 * @Description:起购量视图类
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月19日 下午2:42:54
 * 
 */

public class ScmsMinimumVo extends ScmsMinimum {

	private short lackNum;// 距离起购量还差多少
	private List<ScmsItemVo> list = new ArrayList<ScmsItemVo>();
	private Boolean isShowLackNumMessage = false;

	public short getLackNum() {
		return lackNum;
	}

	public void setLackNum(short lackNum) {
		this.lackNum = lackNum;
	}

	public List<ScmsItemVo> getList() {
		return list;
	}

	public void setList(List<ScmsItemVo> list) {
		this.list = list;
	}

	public Boolean getIsShowLackNumMessage() {
		return isShowLackNumMessage;
	}

	public void setIsShowLackNumMessage(Boolean isShowLackNumMessage) {
		this.isShowLackNumMessage = isShowLackNumMessage;
	}
}
