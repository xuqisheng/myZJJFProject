/**   
* @Title: ERPMarketStockInfoVo.java 
* @Package com.corner.scms.beans.vo.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月8日 下午2:12:28 
* @version V1.0   
*/

package com.corner.scms.beans.vo.erp;

import java.util.Date;

import com.corner.core.beans.ERPMarketStockInfo;
import com.corner.core.enums.LogisticsStatus;
import com.corner.core.enums.PayMethod;

/** 
* @ClassName: ERPMarketStockInfoVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月8日 下午2:12:28 
*  
*/

public class ERPMarketStockInfoVo extends ERPMarketStockInfo {
	
	
	private Date sendDate;//配送日期
	
	private String mobile;//小店手机号
	
	private String address;//小店收货地址
	
	private String userTel;//小店固定电话
	
	private String userRemark;//小店老板下单备注
	
	@SuppressWarnings("unused")
	private String checkStatusStr;//是否审核字符串
	
	@SuppressWarnings("unused")
	private String logisticsStatusStr;//物流状态字符串
	
	private Byte supportmetho;//支付方式
	
	@SuppressWarnings("unused")
	private String supportmethoStr;//支付方式字符串

	public String getCheckStatusStr() {
		return (this.getCheckStatus().intValue()==1?"未审核":"已审核");
	}

	public void setCheckStatusStr(String checkStatusStr) {
		this.checkStatusStr = checkStatusStr;
	}

	public String getLogisticsStatusStr() {
		return LogisticsStatus.getName(this.getLogisticsStatus());
	}

	public void setLogisticsStatusStr(String logisticsStatusStr) {
		this.logisticsStatusStr = logisticsStatusStr;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public Byte getSupportmetho() {
		return supportmetho;
	}

	public void setSupportmetho(Byte supportmetho) {
		this.supportmetho = supportmetho;
	}

	public String getSupportmethoStr() {
		return PayMethod.getName(this.supportmetho);
	}

	public void setSupportmethoStr(String supportmethoStr) {
		this.supportmethoStr = supportmethoStr;
	}
}
