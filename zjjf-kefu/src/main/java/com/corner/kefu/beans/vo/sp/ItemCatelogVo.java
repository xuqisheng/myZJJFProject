/**   
* @Title: ItemCatelogVo.java 
* @Package com.corner.kefu.beans.vo.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年4月14日 上午11:55:55 
* @version V1.0   
*/

package com.corner.kefu.beans.vo.sp;

import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.ItemCatelog;

/** 
* @ClassName: ItemCatelogVo 
* @Description:分类视图类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年4月14日 上午11:55:55 
*  
*/

public class ItemCatelogVo extends ItemCatelog {

	private static final long serialVersionUID = 699052749937478044L;

	private List<ItemCatelogVo> catelogVoList = new ArrayList<ItemCatelogVo>();

	private Integer treeLevel;// 1表示一级分类 2表示2级分类 3表示商品基本

	public List<ItemCatelogVo> getCatelogVoList() {
		return catelogVoList;
	}

	public void setCatelogVoList(List<ItemCatelogVo> catelogVoList) {
		this.catelogVoList = catelogVoList;
	}

	public Integer getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

}
