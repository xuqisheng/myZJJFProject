package com.corner.scms.dao.erp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ItemBase;
import com.corner.scms.beans.ro.erp.ERPManagerItemRo;
import com.corner.scms.beans.vo.ScmsItemBaseVo;
import com.corner.scms.beans.vo.erp.ERPManagerItemVo;

public interface ERPManagerItemMgMapper {

	/**
	 * 获取批发商下某个供应商的所有商品
	* @Title
	* @Description: TODO 
	* @param @param managerItemRo
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public List<ERPManagerItemVo> getAllManagerItem(ERPManagerItemRo managerItemRo);
	public int getAllManagerItemCount(ERPManagerItemRo managerItemRo);
	
	public Integer getMaxNum();
	public int getRepeatItem(Map<String, Object> map);
	
	/**
	 * 根据商品条形码收索商品
	* @Title
	* @Description: TODO 
	* @param @param mdseId
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月2日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public List<ItemBase> getitemByMdseId(Map<String, Object> map);
	
	public ScmsItemBaseVo getitemByMdseIdx(Map<String, Object> map);
	
	public ERPManagerItemVo getManagerItemById(Map<String, Object> map);
	
	
}
