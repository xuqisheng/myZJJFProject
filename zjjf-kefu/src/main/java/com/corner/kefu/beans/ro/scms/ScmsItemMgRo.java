package com.corner.kefu.beans.ro.scms;

import java.util.List;

import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.ScmsItem;

/**
 * 
* @ClassName: ScmsItemMgRo 
* @Description: 联合采购商品信息
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2015年12月28日 上午11:36:35 
*
 */
public class ScmsItemMgRo extends ScmsItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7791262934330106165L;
	
 	private String[] ids;

    private String[] groupIds;
    
    private String[] areaPrices;

    private String[] areaNames;

    private String[] zjjfPrices;

    private String[] marketPrices;

    private String[] specs;
    
    private String name;
    
    private String spec;
    private String pkgNum;
    private String pkg;
    private String managerName;
    private String measure;

    private String mdseId;
    
    private String wuliu;
    private String isUpdate;
    
    public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getPkgNum() {
		return pkgNum;
	}

	public void setPkgNum(String pkgNum) {
		this.pkgNum = pkgNum;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getWuliu() {
		return wuliu;
	}

	public void setWuliu(String wuliu) {
		this.wuliu = wuliu;
	}

	private List<ScmsGroup> scmsGroups;
    
	public List<ScmsGroup> getScmsGroups() {
		return scmsGroups;
	}

	public void setScmsGroups(List<ScmsGroup> scmsGroups) {
		this.scmsGroups = scmsGroups;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String[] getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String[] groupIds) {
		this.groupIds = groupIds;
	}

	public String[] getAreaPrices() {
		return areaPrices;
	}

	public void setAreaPrices(String[] areaPrices) {
		this.areaPrices = areaPrices;
	}

	public String[] getAreaNames() {
		return areaNames;
	}

	public void setAreaNames(String[] areaNames) {
		this.areaNames = areaNames;
	}

	public String[] getZjjfPrices() {
		return zjjfPrices;
	}

	public void setZjjfPrices(String[] zjjfPrices) {
		this.zjjfPrices = zjjfPrices;
	}

	public String[] getMarketPrices() {
		return marketPrices;
	}

	public void setMarketPrices(String[] marketPrices) {
		this.marketPrices = marketPrices;
	}

	public String[] getSpecs() {
		return specs;
	}

	public void setSpecs(String[] specs) {
		this.specs = specs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getMdseId() {
		return mdseId;
	}

	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}
	
}