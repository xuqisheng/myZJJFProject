/**   
* @Title: ScOrderDetailVo.java 
* @Package com.corner.scms.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月25日 上午11:46:27 
* @version V1.0   
*/

package com.corner.scms.beans.vo.sc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.beans.ScOrderDetail;

/** 
* @ClassName: ScOrderDetailVo 
* @Description:订单详情视图类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年1月25日 上午11:46:27 
*  
*/

public class ScOrderDetailVo extends ScOrderDetail implements Serializable{
	//总箱数
	private Integer countNum;
	//自提箱数
	private Integer visit;
	//送货上门
	private Integer own;
	//配送方式
	private String ordertype;
	//配送单中单个商品的状态
	private Byte ordStatus;
	
	
	//小计
	private BigDecimal xiaoji;
	//订单区域
	private String groupName;
	//批发商名称
	private String supplierName;
	//批发商地址
	private String address;
	//批发商手机号
	private String mobile;
	//批发商提单时间
	private Date supportTime;
	
	private int itemBaseId;//基本商品id
	
	private String pkg;//单位
	

	//商品名字
//	private String name;
	
	//商品图片
//	private String name;
	
	
	
    public int getItemBaseId() {
		return itemBaseId;
	}
	public void setItemBaseId(int itemBaseId) {
		this.itemBaseId = itemBaseId;
	}
	private String branderName;//仓库负责人名字
    
    private String branderTel;//仓库负责人电话
    
    private String houseAddress;//仓库地址
    
    private String warehouseId;//仓库id
    
    private String wareHouseName;//仓库名
	
	public String getWareHouseName() {
		return wareHouseName;
	}
	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getBranderName() {
		return branderName;
	}
	public void setBranderName(String branderName) {
		this.branderName = branderName;
	}
	public String getBranderTel() {
		return branderTel;
	}
	public void setBranderTel(String branderTel) {
		this.branderTel = branderTel;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public Integer getCountNum() {
		return countNum;
	}
	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}
	public BigDecimal getXiaoji() {
		return xiaoji;
	}
	public void setXiaoji(BigDecimal xiaoji) {
		this.xiaoji = xiaoji;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getSupportTime() {
		return supportTime;
	}
	public void setSupportTime(Date supportTime) {
		this.supportTime = supportTime;
	}
	public Integer getVisit() {
		return visit;
	}
	public void setVisit(Integer visit) {
		this.visit = visit;
	}
	public Integer getOwn() {
		return own;
	}
	public void setOwn(Integer own) {
		this.own = own;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public Byte getOrdStatus() {
		return ordStatus;
	}
	public void setOrdStatus(Byte ordStatus) {
		this.ordStatus = ordStatus;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
}
