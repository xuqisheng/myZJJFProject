package com.corner.data.analysis.beans.vo;

import com.corner.core.beans.ro.ABaseRo;

/**
 * ClassName: GlobalStatisVo
 * 
 * @Description: 全局统计 对象
 * @author 元宝
 * @date 2016年01月8日
 */
public class GlobalStatisVo extends ABaseRo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String areaId;
	private String areaName;
	private Integer xzzd;
	private Integer hyzd;
	private Integer gpzd;
	private Integer xdcs;
	private Integer xdsku;
	private Integer xl;
	private Double xsje;
	private Double ylspxse;
	private Double ylsplr;
	private Double fyje;
	private String startTime;
	private String endTime;
	
	private Integer qnljzd;//全年累计终端
	private Integer ljxl;//累计销量
	private Double fy;//费用
	private Double ljxse;//累计销售额
	private Double ljylspxse;//累计盈利商品销售额
	private String fyl;//费用率
	private String orderPriceTotal;
	
	public String getOrderPriceTotal() {
		return orderPriceTotal;
	}
	public void setOrderPriceTotal(String orderPriceTotal) {
		this.orderPriceTotal = orderPriceTotal;
	}
	public String getFyl() {
		return fyl;
	}
	public void setFyl(String fyl) {
		this.fyl = fyl;
	}
	private String curYear;//当前的年份
	
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
	public Integer getXzzd() {
		return xzzd;
	}
	public void setXzzd(Integer xzzd) {
		this.xzzd = xzzd;
	}
	public Integer getHyzd() {
		return hyzd;
	}
	public void setHyzd(Integer hyzd) {
		this.hyzd = hyzd;
	}
	public Integer getGpzd() {
		return gpzd;
	}
	public void setGpzd(Integer gpzd) {
		this.gpzd = gpzd;
	}
	public Integer getXdcs() {
		return xdcs;
	}
	public void setXdcs(Integer xdcs) {
		this.xdcs = xdcs;
	}
	public Integer getXdsku() {
		return xdsku;
	}
	public void setXdsku(Integer xdsku) {
		this.xdsku = xdsku;
	}
	public Integer getXl() {
		return xl;
	}
	public void setXl(Integer xl) {
		this.xl = xl;
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
	public Integer getQnljzd() {
		return qnljzd;
	}
	public void setQnljzd(Integer qnljzd) {
		this.qnljzd = qnljzd;
	}
	public Integer getLjxl() {
		return ljxl;
	}
	public void setLjxl(Integer ljxl) {
		this.ljxl = ljxl;
	}
	public Double getFy() {
		return fy;
	}
	public void setFy(Double fy) {
		this.fy = fy;
	}
	public Double getLjxse() {
		return ljxse;
	}
	public void setLjxse(Double ljxse) {
		this.ljxse = ljxse;
	}
	public Double getLjylspxse() {
		return ljylspxse;
	}
	public void setLjylspxse(Double ljylspxse) {
		this.ljylspxse = ljylspxse;
	}
	public String getCurYear() {
		return curYear;
	}
	public void setCurYear(String curYear) {
		this.curYear = curYear;
	}
	
}
