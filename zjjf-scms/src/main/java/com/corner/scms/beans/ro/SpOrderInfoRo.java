package com.corner.scms.beans.ro;

import java.util.Date;

import com.corner.core.beans.ro.condition.EasyUIQueryModel;

/**
 * 确认订单接受参数
 * @author aimee at 2015年2月6日下午1:14:30
 * @email 1297579898@qq.com
 */
public class SpOrderInfoRo extends EasyUIQueryModel{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String storeid;//订单编号
	
	private Byte status;
	
	private String supplierTel;
	
	private Date date;
	
	private Integer supportmetho;//支付方式查询
	
	private Date dateFrom;//下单开始时间
	
	private Date dateTo;//下单结束时间
	
	
	private String supplierId;
	

	public Integer getSupportmetho() {
		return supportmetho;
	}

	public void setSupportmetho(Integer supportmetho) {
		this.supportmetho = supportmetho;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getSupplierTel() {
		return supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
}
