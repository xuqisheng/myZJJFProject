/**   
* @Title: SendVoucherCondition.java 
* @Package com.corner.kefu.beans.ro.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月9日 下午2:59:34 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.sp;

import java.util.Map;

import com.corner.kefu.enums.spGroup.SpGroupCondition;
import com.corner.kefu.enums.spVoucher.SendVoucherCondition;

/** 
* @ClassName: SendVoucherCondition 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月9日 下午2:59:34 
*  
*/

public class SendVoucherConditionRo {
	
	private SendVoucherCondition sendVoucherCondition;

	private SpGroupCondition spGroupCondition;

	private Integer id;// 优惠劵id

	private Integer customerNum;// 多少月未下单
	
	private String[] storeArr;//指定用户店铺id集合
	
	private String[] spGroupArr;//指定定格id集合
	
	private String flag;//用于excel导入标示符
	

	public void setCondition(Integer condition) {
		for (Map.Entry<Integer, SendVoucherCondition> entry : SendVoucherCondition.map.entrySet()) {
			if (entry.getKey().intValue() == condition.intValue()) {
				this.sendVoucherCondition = entry.getValue();
				break;
			}
		}
	}

	public void setSpgroupRadio(Integer spGroupRadio) {
		for (Map.Entry<Integer, SpGroupCondition> entry : SpGroupCondition.map.entrySet()) {
			if (entry.getKey().intValue() == spGroupRadio.intValue()) {
				this.spGroupCondition = entry.getValue();
				break;
			}
		}
	}
	
	public Integer getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(Integer customerNum) {
		this.customerNum = customerNum;
	}

	public SpGroupCondition getSpGroupCondition() {
		return spGroupCondition;
	}

	public void setSpGroupCondition(SpGroupCondition spGroupCondition) {
		this.spGroupCondition = spGroupCondition;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	//@JSONField(serialize=false)
	public SendVoucherCondition getSendVoucherCondition() {
		return sendVoucherCondition;
	}

	//@JSONField(serialize=false)
	public void setSendVoucherCondition(SendVoucherCondition sendVoucherCondition) {
		this.sendVoucherCondition = sendVoucherCondition;
	}

	public String[] getStoreArr() {
		return storeArr;
	}

	public void setStoreArr(String[] storeArr) {
		this.storeArr = storeArr;
	}

	public String[] getSpGroupArr() {
		return spGroupArr;
	}

	public void setSpGroupArr(String[] spGroupArr) {
		this.spGroupArr = spGroupArr;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
