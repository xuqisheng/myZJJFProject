package com.corner.scms.beans.ro.sc;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;


//采购中心  条件查询的类
public class ScmsProcurementCenterRo extends EasyUIQueryModel{

	public String[] getCateIDs() {
		return cateIDs;
	}

	public void setCateIDs(String[] cateIDs) {
		this.cateIDs = cateIDs;
	}

	//商品名称  或者商品 条形码
	private String name;
	
	//商品分类的id  二级分类的id
	private Integer cateID;
	
	//商品分类的id  一级分类的id
		private Integer cateID1;
		
	//当前登录用户的id
	private String uid;
	
	//区域的id 
	private Integer bscirdedId;
	
	//排序的字段
	private Integer orderNum;
	
	//二级分类的 ids
	private String[] cateIDs;

	
	
	

	public Integer getCateID1() {
		return cateID1;
	}

	public void setCateID1(Integer cateID1) {
		this.cateID1 = cateID1;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getBscirdedId() {
		return bscirdedId;
	}

	public void setBscirdedId(Integer bscirdedId) {
		this.bscirdedId = bscirdedId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCateID() {
		return cateID;
	}

	public void setCateID(Integer cateID) {
		this.cateID = cateID;
	}

	@Override
	public String toHqlString() {
		return null;
	}

	@Override
	public Object[] toHqlObjects() {
		return null;
	}

	

	
}
