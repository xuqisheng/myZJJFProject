package com.corner.scms.service.erp;

import com.corner.core.beans.ERPManager;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.erp.ERPManagerRo;
import com.corner.scms.beans.vo.erp.ERPManagerVo;

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
}
