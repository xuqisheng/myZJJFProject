package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;
import java.util.Date;

public class SpCommentVo implements Serializable {

	public SpCommentVo() {
		super();
	}
	//评论id
	private String id;
	//综合评分
	private Byte unionFen;
	//订单id
	private String orderId2;
	//订单编号
	private String orderNo2; 
	//评论人
	private String userNm;
	//评论人所在的店铺名
	private String storeNm;
	//批发商
	private String spNm;
	//评论内容
	private String info; 
	//评论时间
	private Date addTime; 
	//处理状态
	private Byte csDealstat;
	
	private String csDealInfo;
	
	public Byte getUnionFen() {
		return unionFen;
	}
	public void setUnionFen(Byte unionFen) {
		this.unionFen = unionFen;
	}
	public String getOrderNo2() {
		return orderNo2;
	}
	public void setOrderNo2(String orderNo2) {
		this.orderNo2 = orderNo2;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getSpNm() {
		return spNm;
	}
	public void setSpNm(String spNm) {
		this.spNm = spNm;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Byte getCsDealstat() {
		return csDealstat;
	}
	public void setCsDealstat(Byte csDealstat) {
		this.csDealstat = csDealstat;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCsDealInfo() {
		return csDealInfo;
	}
	public void setCsDealInfo(String csDealInfo) {
		this.csDealInfo = csDealInfo;
	}
	public String getOrderId2() {
		return orderId2;
	}
	public void setOrderId2(String orderId2) {
		this.orderId2 = orderId2;
	}
	public String getStoreNm() {
		return storeNm;
	}
	public void setStoreNm(String storeNm) {
		this.storeNm = storeNm;
	} 

}
