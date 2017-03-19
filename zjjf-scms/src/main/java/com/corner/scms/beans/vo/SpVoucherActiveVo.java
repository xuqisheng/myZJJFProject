/**   
* @Title: SupplierVo.java 
* @Package com.corner.scms.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月26日 上午10:19:23 
* @version V1.0   
*/

package com.corner.scms.beans.vo;

import com.corner.core.beans.SpVoucherActive;
import com.corner.core.enums.ActivityType;

/**
 * @ClassName: SupplierVo
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月26日 上午10:19:23
 * 
 */

public class SpVoucherActiveVo extends SpVoucherActive {
	
	private Integer number;
	
	private Double money;
	
	private String QName;
	
	private Integer QId;
	
	private Integer ziyuan;
	
	private String plantHalveStr;
	
	private String spHalveStr;
	
	private String ruleTypeStr;

	public String getRuleTypeStr() {
		return this.getRuleType() == null || this.getRuleType() == 0 ? "其他":ActivityType.getName(this.getRuleType());
	}
	public Integer getZiyuan() {
		return ziyuan;
	}

	public void setZiyuan(Integer ziyuan) {
		this.ziyuan = ziyuan;
	}

	public Integer getQId() {
		return QId;
	}

	public void setQId(Integer qId) {
		QId = qId;
	}

	public String getQName() {
		return QName;
	}

	public void setQName(String qName) {
		QName = qName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getPlantHalveStr() {
		return plantHalveStr;
	}

	public void setPlantHalveStr(String plantHalveStr) {
		this.plantHalveStr = plantHalveStr;
	}

	public String getSpHalveStr() {
		return spHalveStr;
	}

	public void setSpHalveStr(String spHalveStr) {
		this.spHalveStr = spHalveStr;
	}
	
	
	
}
