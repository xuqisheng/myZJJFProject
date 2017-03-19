package com.corner.data.analysis.beans.vo;

import java.util.Date;

import com.corner.core.beans.ro.ABaseRo;
/**
 * ClassName: AnalysisProductVo
 * 
 * @Description: 商铺销售明细分析 对象
 * @author 元宝
 * @date 2016年01月8日
 */
public class AnalysisShopSaleVo extends ABaseRo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//商铺编号
	private String areaId;//区域编码
	private String areaName;//区域名称
	private String shopNo;//商铺编号
	private String shopName;//商铺名称
	private Integer xdcs;//下单次数
	private Integer xssl;//销售数量
	private Double xsje;//销售金额
	private Double ylspxse;//盈利商品销售额
	private Double ylsplr;//盈利商品利润
	private Double fyje;//费用金额
	private String startTime;//起始时间
	private String endTime;//结束时间
	private Date addTime;//注册时间
	private Double zfee;//费用金额
	private Double orderPrice;//订单总额
	private Double fly;//费用率
	private String orderId;//商品订单编号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getXdcs() {
		return xdcs;
	}
	public void setXdcs(Integer xdcs) {
		this.xdcs = xdcs;
	}
	public Integer getXssl() {
		return xssl;
	}
	public void setXssl(Integer xssl) {
		this.xssl = xssl;
	}
	public Double getXsje() {
		return xsje;
	}
	public void setXsje(Double xsje) {
		this.xsje = xsje;
	}
	public Double getYlspxse() {
		return ylspxse;
	}
	public void setYlspxse(Double ylspxse) {
		this.ylspxse = ylspxse;
	}
	public Double getYlsplr() {
		return ylsplr;
	}
	public void setYlsplr(Double ylsplr) {
		this.ylsplr = ylsplr;
	}
	public Double getFyje() {
		return fyje;
	}
	public void setFyje(Double fyje) {
		this.fyje = fyje;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Double getZfee() {
		return zfee;
	}
	public void setZfee(Double zfee) {
		this.zfee = zfee;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Double getFly() {
		return fly;
	}
	public void setFly(Double fly) {
		this.fly = fly;
	}
	
}
