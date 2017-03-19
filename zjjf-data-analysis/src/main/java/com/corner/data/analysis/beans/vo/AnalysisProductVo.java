package com.corner.data.analysis.beans.vo;

import com.corner.core.beans.ro.ABaseRo;
/**
 * ClassName: AnalysisProductVo
 * 
 * @Description: 商品数据分析 对象
 * @author 元宝
 * @date 2016年01月8日
 */
public class AnalysisProductVo extends ABaseRo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//商品ID
	private String areaId;//区域编码
	private String areaName;//区域名称
	private String sptm;//商品条码
	private String yjfl;//一级分类
	private String ejfl;//二级分类

	private String spmc;//商品名称
	private String spec;//规格

	private Double scj;//市场价
	private Double hzsjhj;//合作商进货价

	private Double hzschj;//合作商出货价
	private Double maoli;//

	private Integer xdcs;//下单次数
	private Integer xssl;//销售数量

	private Double xsje;//销售金额
	private Double fyje;//费用金额
	private String startTime;//起始时间
	private String endTime;//结束时间
	
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
	public String getSptm() {
		return sptm;
	}
	public void setSptm(String sptm) {
		this.sptm = sptm;
	}
	public String getYjfl() {
		return yjfl;
	}
	public void setYjfl(String yjfl) {
		this.yjfl = yjfl;
	}
	public String getEjfl() {
		return ejfl;
	}
	public void setEjfl(String ejfl) {
		this.ejfl = ejfl;
	}
	public String getSpmc() {
		return spmc;
	}
	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Double getScj() {
		return scj;
	}
	public void setScj(Double scj) {
		this.scj = scj;
	}
	public Double getHzsjhj() {
		return hzsjhj;
	}
	public void setHzsjhj(Double hzsjhj) {
		this.hzsjhj = hzsjhj;
	}
	public Double getHzschj() {
		return hzschj;
	}
	public void setHzschj(Double hzschj) {
		this.hzschj = hzschj;
	}
	public Double getMaoli() {
		return maoli;
	}
	public void setMaoli(Double maoli) {
		this.maoli = maoli;
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
	public Double getFyje() {
		return fyje;
	}
	public void setFyje(Double fyje) {
		this.fyje = fyje;
	}
	
	
}
