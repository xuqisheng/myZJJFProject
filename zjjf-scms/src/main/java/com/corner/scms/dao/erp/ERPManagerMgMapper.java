package com.corner.scms.dao.erp;

import java.util.List;

import com.corner.scms.beans.ro.erp.ERPManagerRo;
import com.corner.scms.beans.vo.erp.ERPManagerVo;

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
}
