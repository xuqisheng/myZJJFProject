package com.corner.kefu.dao.erp;

import java.util.List;

import com.corner.kefu.beans.ro.erp.ERPManagerRo;
import com.corner.kefu.beans.ro.erp.ERPmanagerItemRo;
import com.corner.kefu.beans.vo.erp.ERPManagerItemVo;
import com.corner.kefu.beans.vo.erp.ERPManagerVo;


public interface ERPManagerMgMapper {
	

	/**
	 * 获取批发商所有的供应商
	* @Title
	* @Description: TODO 
	* @param @param manager
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public List<ERPManagerVo> getAllERPManager(ERPManagerRo manager);
	public int getAllERPManagerCount(ERPManagerRo manager);

	public Integer getMaxNum();
	
	public List<ERPManagerItemVo> getJsonErpManagerItem(ERPmanagerItemRo itemRo);
	
	public Integer getCountJsonErpManagerItem(ERPmanagerItemRo itemRo);
	
	public void deleteManagerAndSupplier(ERPManagerRo managerRo);
	/**
	 * 
	* @Title: getERPManagerByWhId 
	* @Description:根据仓库id获取对应的经销商列表
	* @param @param manager
	* @param @return
	* @return List<ERPManagerVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<ERPManagerVo> getERPManagerByWhId(ERPManagerRo manager);
	
	public Integer getCountERPManagerByWhId(ERPManagerRo manager);
	
}
