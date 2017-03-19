package com.corner.scms.service.erp;

import com.corner.core.beans.ERPManagerItem;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.erp.ERPManagerItemRo;
import com.corner.scms.beans.vo.erp.ERPManagerItemVo;

public interface ERPManagerItemService {

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
	public Pager<ERPManagerItemVo> getAllManagerItem(ERPManagerItemRo managerItemRo);

	/**
	 * 添加商品
	* @Title
	* @Description: TODO 
	* @param @param managerId
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public ModelMsg addERPManagerItem(String managerId, String id, String[] mdseIds, String[] areaPrices,String[] taxRates);
	
	/**
	 * 修改商品
	* @Title
	* @Description: TODO 
	* @param @param managerItemRo
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public ModelMsg updateERPManagerItem(ERPManagerItemRo managerItemRo);
	
	/**
	 * 删除商品
	* @Title
	* @Description: TODO 
	* @param @param id
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public boolean deleteERPManagerItem(String id);

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
	public ModelMsg getitemByMdseId(String mdseId);

	public ModelMsg getitemByMdseIdx(String mdseId);
	
	public ERPManagerItemVo getManagerItemById(String id);

	ERPManagerItem getMangerItemById(String id);

	ERPManagerItemVo getManagerItemByItemCode(String itemCode , String supplierId);

	ERPManagerItem getERPManagerItem(String mangerId, Integer itemBaseId) throws Exception;
}
