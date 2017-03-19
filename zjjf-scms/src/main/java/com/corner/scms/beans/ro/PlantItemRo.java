/**   
 * @Title: PlantItemRo.java 
 * @Package com.corner.scms.beans.ro 
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn   
 * @date 2015年12月8日 下午5:49:14 
 * @version V1.0   
 */
package com.corner.scms.beans.ro;

import com.corner.core.beans.PlantItem;

/**
 * @ClassName: PlantItemRo
 * @Description:商品查询类
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2015年12月8日 下午5:49:14
 */
public class PlantItemRo extends PlantItem {
	private String keyStr;
	private Integer catId;// 二级分类
	private Integer pCatId;// 一级分类

	private int pageIndex ;
	//默认加载5条
	private int pageSize = 10;

	public int getPageIndex() {
		return pageSize * ((pageIndex - 1) > 0 ? (pageIndex - 1) : 0);
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getpCatId() {
		return pCatId;
	}

	public void setpCatId(Integer pCatId) {
		this.pCatId = pCatId;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getKeyStr() {
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}


}
