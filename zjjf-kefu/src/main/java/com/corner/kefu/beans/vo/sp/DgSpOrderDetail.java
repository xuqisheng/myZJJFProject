package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;
import java.math.BigDecimal;

public class DgSpOrderDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String id ;
    private String orderid;
    private String orderid2;
    private String brand;
    private String name;
    private String spec;
    private String img;
    private Short quantity;
    private BigDecimal price;
    private BigDecimal totalprice;
    private String remark;
    private String youhui;
    private Integer restrict;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Short getQuantity() {
		return quantity;
	}
	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getYouhui() {
		return youhui;
	}
	public void setYouhui(String youhui) {
		this.youhui = youhui;
	}
	public Integer getRestrict() {
		return restrict;
	}
	public void setRestrict(Integer restrict) {
		this.restrict = restrict;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderid2() {
		return orderid2;
	}
	public void setOrderid2(String orderid2) {
		this.orderid2 = orderid2;
	}
    
}
