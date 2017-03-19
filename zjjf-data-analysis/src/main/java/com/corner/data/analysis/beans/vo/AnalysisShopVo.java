package com.corner.data.analysis.beans.vo;

import com.corner.core.beans.ro.ABaseRo;
/**
 * ClassName: AnalysisShopVo
 * 
 * @Description: 商铺数据分析 对象
 * @author 元宝
 * @date 2016年01月8日
 */
public class AnalysisShopVo extends ABaseRo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String areaId;//区域ID
	private String areaName;//区域名称
	private Integer zdsl;//商铺数量
	private Integer xd1;//1次下单
	private Integer xd2;//2次下单
	private Integer xd3;//3次下单
	private Integer xd4;//4次下单
	private Integer xse1;//销售额1
	private Integer xse2;//销售额2
	private Integer xse3;//销售额3
	private Integer xse4;//销售额4
	private String startTime;//起始时间
	private String endTime;//结束时间
	private String shopNo;//商铺编码
	
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
	public Integer getZdsl() {
		return zdsl;
	}
	public void setZdsl(Integer zdsl) {
		this.zdsl = zdsl;
	}
	public Integer getXd1() {
		return xd1;
	}
	public void setXd1(Integer xd1) {
		this.xd1 = xd1;
	}
	public Integer getXd2() {
		return xd2;
	}
	public void setXd2(Integer xd2) {
		this.xd2 = xd2;
	}
	public Integer getXd3() {
		return xd3;
	}
	public void setXd3(Integer xd3) {
		this.xd3 = xd3;
	}
	public Integer getXd4() {
		return xd4;
	}
	public void setXd4(Integer xd4) {
		this.xd4 = xd4;
	}
	public Integer getXse1() {
		return xse1;
	}
	public void setXse1(Integer xse1) {
		this.xse1 = xse1;
	}
	public Integer getXse2() {
		return xse2;
	}
	public void setXse2(Integer xse2) {
		this.xse2 = xse2;
	}
	public Integer getXse3() {
		return xse3;
	}
	public void setXse3(Integer xse3) {
		this.xse3 = xse3;
	}
	public Integer getXse4() {
		return xse4;
	}
	public void setXse4(Integer xse4) {
		this.xse4 = xse4;
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
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	
}
