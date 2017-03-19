/**   
* @Title: ScmsItemVo.java 
* @Package com.corner.scms.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月19日 下午2:46:33 
* @version V1.0   
*/

package com.corner.scms.beans.vo;

import java.math.BigDecimal;

import com.corner.core.beans.ScmsItem;

/**
 * @ClassName: ScmsItemVo
 * @Description:经销商商品视图类
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月19日 下午2:46:33
 * 
 */

public class ScmsItemVo extends ScmsItem {

	private Short num;// 购物车商品数量
	private BigDecimal totalPrice;// 购物车商品总量
	private String imgS;// 商品小图
	private String spec;// 商品规格
	private String measure;// 商品单位
	private String cart_id;// 购物车id
	private String pkg;
	private String pkgNum;
	private String itemBaseName;

	public String getItemBaseName() {
		return itemBaseName;
	}

	public void setItemBaseName(String itemBaseName) {
		this.itemBaseName = itemBaseName;
	}

	public String getCart_id() {
		return cart_id;
	}

	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}

	public Short getNum() {
		return num;
	}

	public void setNum(Short num) {
		this.num = num;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getImgS() {
		return imgS;
	}

	public void setImgS(String imgS) {
		this.imgS = imgS;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getPkgNum() {
		return pkgNum;
	}

	public void setPkgNum(String pkgNum) {
		this.pkgNum = pkgNum;
	}
}
