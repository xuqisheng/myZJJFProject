/**   
* @Title: SpWalletLogVo.java 
* @Package com.corner.scms.beans.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月7日 上午10:13:45 
* @version V1.0   
*/
package com.corner.scms.beans.vo;

import com.corner.core.beans.SpWalletLog;

/** 
 * @ClassName: SpWalletLogVo 
 * @Description: 交易明细视图类
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月7日 上午10:13:45  
 */
public class SpWalletLogVo extends SpWalletLog {
    private String orderInfoId;//存SpOrderInfo 表中的订单编号
    
    private String orderInfoId2;//存ScOrderInfO 表中的订单编号
    
    private Byte supportmetho;//存SpOrderInfo 表中的supportmetho
    
    private Byte supportmetho2;//存ScOrderInfo2 表中的supportmetho
    
    private String mingcheng;//用于页面显示;
    
    private String dingdang;//订单类型(订单or提现)
    
    private String shouru;//收入
    
    private String zhichu;//支出
    
    private String zhifuzhanhu;//支付账户
    
    
	
	public Byte getSupportmetho() {
		return supportmetho;
	}

	public void setSupportmetho(Byte supportmetho) {
		this.supportmetho = supportmetho;
	}

	public String getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(String orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public String getOrderInfoId2() {
		return orderInfoId2;
	}

	public void setOrderInfoId2(String orderInfoId2) {
		this.orderInfoId2 = orderInfoId2;
	}

	public Byte getSupportmetho2() {
		return supportmetho2;
	}

	public void setSupportmetho2(Byte supportmetho2) {
		this.supportmetho2 = supportmetho2;
	}

	public String getMingcheng() {
		return mingcheng;
	}

	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}

	public String getDingdang() {
		return dingdang;
	}

	public void setDingdang(String dingdang) {
		this.dingdang = dingdang;
	}

	public String getShouru() {
		return shouru;
	}

	public void setShouru(String shouru) {
		this.shouru = shouru;
	}

	public String getZhichu() {
		return zhichu;
	}

	public void setZhichu(String zhichu) {
		this.zhichu = zhichu;
	}

	public String getZhifuzhanhu() {
		return zhifuzhanhu;
	}

	public void setZhifuzhanhu(String zhifuzhanhu) {
		this.zhifuzhanhu = zhifuzhanhu;
	}
}
