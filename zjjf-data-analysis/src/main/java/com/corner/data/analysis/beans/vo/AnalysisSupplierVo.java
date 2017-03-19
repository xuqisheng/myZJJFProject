package com.corner.data.analysis.beans.vo;

import com.corner.core.beans.ro.ABaseRo;

public class AnalysisSupplierVo extends ABaseRo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//商铺编号
	private String areaId;//区域编码
	private String areaName;//区域名称
	private String supplierCode;//商铺编号
	private String supplierName;//合作商名称
	private Integer pssps;//配送商铺数
	private Integer ddsl;//订单数量
	private Integer xssl;//销售数量
	private Integer xdcs;//下单次数
	private Double xsje;//销售金额

	private Double ylsplr;//盈利商品利润
	private Double fyje;//费用金额
	private String startTime;//起始时间
	private String endTime;//结束时间
	private Double fyspfy;//商品费用（费用商品）
	private Double fyspxse;//商品销售金额（费用商品）
	private Double pjspxse;//销售金额（平价商品）
	private Double ylspfy;//商品费用（盈利商品）
	private Double ylspxse;//销售额（盈利商品）
	private Integer xdsku;
	
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
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getPssps() {
		return pssps;
	}
	public void setPssps(Integer pssps) {
		this.pssps = pssps;
	}
	public Integer getDdsl() {
		return ddsl;
	}
	public void setDdsl(Integer ddsl) {
		this.ddsl = ddsl;
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
	public Double getYlspfy() {
		return ylspfy;
	}
	public void setYlspfy(Double ylspfy) {
		this.ylspfy = ylspfy;
	}
	public Double getFyspfy() {
		return fyspfy;
	}
	public void setFyspfy(Double fyspfy) {
		this.fyspfy = fyspfy;
	}
	public Double getPjspxse() {
		return pjspxse;
	}
	public void setPjspxse(Double pjspxse) {
		this.pjspxse = pjspxse;
	}
	public Double getFyspxse() {
		return fyspxse;
	}
	public void setFyspxse(Double fyspxse) {
		this.fyspxse = fyspxse;
	}
	public Integer getXdsku() {
		return xdsku;
	}
	public void setXdsku(Integer xdsku) {
		this.xdsku = xdsku;
	}
	public Integer getXdcs() {
		return xdcs;
	}
	public void setXdcs(Integer xdcs) {
		this.xdcs = xdcs;
	}
	
}
