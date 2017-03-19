package com.corner.kefu.service.erp;

import com.corner.core.beans.ERPManager;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.erp.ERPManagerRo;
import com.corner.kefu.beans.ro.erp.ERPmanagerItemRo;
import com.corner.kefu.beans.vo.erp.ERPManagerItemVo;
import com.corner.kefu.beans.vo.erp.ERPManagerVo;

public interface ERPManagerService {

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
	public Pager<ERPManagerVo> getAllERPManager(ERPManagerRo manager);

	/**
	 * 批发商添加供应商
	* @Title
	* @Description: TODO 
	* @param @param manager
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public ModelMsg addERPManager(ERPManagerRo manager);

	/**
	 * 批发商编辑供应商
	* @Title
	* @Description: TODO 
	* @param @param manager
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public ModelMsg updateERPManager(ERPManagerRo manager);

	public boolean deleteERPManager(String id);

	public ERPManager getERPManagerById(String id);

	/**
	 * 
	* @Title: saveManagerSupplier 
	* @Description:保存供应商和批发商关系
	* @param @param managerRo
	* @param @return
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public ModelMsg saveManagerSupplier(ERPManagerRo managerRo);

	public Pager<ERPManagerItemVo> getJsonErpManagerItem(ERPmanagerItemRo itemRo);

	public ERPManager selectByPrimaryKey(String erpManagerId);

	/**
	 * 
	* @Title: getERPManagerByWhId 
	* @Description:根据仓库id选择对应的经销商
	* @param @param manager
	* @param @return
	* @return Pager<ERPManagerVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Pager<ERPManagerVo> getERPManagerByWhId(ERPManagerRo manager);
}
