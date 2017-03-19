package com.corner.kefu.beans.vo;

import java.io.Serializable;

import com.corner.core.beans.Advertisement;


/**
 * 
 * @ClassName: AdvertisementVo
 * 
 * @Description: 广告视图类
 * 
 * @author: 杨开泰
 * 
 * @date: 2015年11月10日 下午8:22:24
 */
public class AdvertisementVo extends Advertisement implements Serializable,Cloneable{
	private static final long serialVersionUID = 1L;
	private String adboardName;// 广告位名称
	private String spGroupName;// 定格名称
	private String staName;//状态名称

	public String getAdboardName() {
		return adboardName;
	}

	public void setAdboardName(String adboardName) {
		this.adboardName = adboardName;
	}

	public String getSpGroupName() {
		return spGroupName;
	}

	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}

	public String getStaName() {
		return staName;
	}

	public void setStaName(String staName) {
		this.staName = staName;
	}

}
