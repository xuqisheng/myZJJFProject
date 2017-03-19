package com.corner.kefu.beans.vo;

import java.io.Serializable;

import com.corner.core.beans.AppItemTag;
/**
 * 
 * @ClassName: SKUActiveTagVo
 * @Description: 单品促销活动，标签对象
 * @author 小武
 * @version 飓风
 * @date 2016年9月8日 上午10:30:03
 *
 */
public class SKUActiveTagVo extends AppItemTag implements Serializable {

	/**
	 * @desc  
	 * @date 2016年9月8日  上午10:28:34
	 * @author 小武
	 * @version  飓风
	 */
	private static final long serialVersionUID = 1L;
	
	public Integer checked = 0;

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}
	
	
	

}
