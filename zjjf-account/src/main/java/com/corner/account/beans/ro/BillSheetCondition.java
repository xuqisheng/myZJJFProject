package com.corner.account.beans.ro;

import java.util.Date;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;

public class BillSheetCondition extends EasyUIQueryModel{

	//分页查询
	private String id;
	private Integer xtype;
	private Integer status;
	private Boolean isDelete;
	private String sheetNum;
	private String keyword;
	
	//生成对账单的入参
	private String supplierId;
    private String sheetnum;
    private String sheetname;
    private String sheetremark;
    private Byte ordid;
    //private Byte xtype;
	private String spOrderIds;
	private String[] spOrderIdArray;
	
	//录入回执入参
	//private String id;
    private String paybanknum;
    private String paybankname;
    private Date paytime;
    private Date filltime;
    private String filluserid;
    private String fillremark;
    
    //下载对账单入参
    //private String id;
    private String sheetpath;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getXtype() {
		return xtype;
	}

	public void setXtype(Integer xtype) {
		this.xtype = xtype;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(String sheetNum) {
		this.sheetNum = sheetNum;
	}

	@Override
	public String toHqlString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toHqlObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSheetnum() {
		return sheetnum;
	}

	public void setSheetnum(String sheetnum) {
		this.sheetnum = sheetnum;
	}

	public String getSheetname() {
		return sheetname;
	}

	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}

	public String getSheetremark() {
		return sheetremark;
	}

	public void setSheetremark(String sheetremark) {
		this.sheetremark = sheetremark;
	}

	public Byte getOrdid() {
		return ordid;
	}

	public void setOrdid(Byte ordid) {
		this.ordid = ordid;
	}

	public String getSpOrderIds() {
		return spOrderIds;
	}

	public void setSpOrderIds(String spOrderIds) {
		this.spOrderIds = spOrderIds;
	}

	public String[] getSpOrderIdArray() {
		return spOrderIdArray;
	}

	public void setSpOrderIdArray(String[] spOrderIdArray) {
		this.spOrderIdArray = spOrderIdArray;
	}

	public String getPaybanknum() {
		return paybanknum;
	}

	public void setPaybanknum(String paybanknum) {
		this.paybanknum = paybanknum;
	}

	public Date getFilltime() {
		return filltime;
	}

	public void setFilltime(Date filltime) {
		this.filltime = filltime;
	}

	public String getFilluserid() {
		return filluserid;
	}

	public void setFilluserid(String filluserid) {
		this.filluserid = filluserid;
	}

	public String getFillremark() {
		return fillremark;
	}

	public void setFillremark(String fillremark) {
		this.fillremark = fillremark;
	}

	public String getSheetpath() {
		return sheetpath;
	}

	public void setSheetpath(String sheetpath) {
		this.sheetpath = sheetpath;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPaybankname() {
		return paybankname;
	}

	public void setPaybankname(String paybankname) {
		this.paybankname = paybankname;
	}

	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	
}
