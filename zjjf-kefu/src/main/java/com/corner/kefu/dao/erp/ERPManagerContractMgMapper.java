/**   
* @Title: ERPManagerContractMgMapper.java 
* @Package com.corner.kefu.dao.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月3日 下午5:05:25 
* @version V1.0   
*/

package com.corner.kefu.dao.erp;

import java.util.List;

import com.corner.kefu.beans.ro.erp.ERPManagerContractRo;
import com.corner.kefu.beans.vo.erp.ERPManagerContractVo;

/** 
* @ClassName: ERPManagerContractMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月3日 下午5:05:25 
*  
*/

public interface ERPManagerContractMgMapper {

	/**
	 * 
	* @Title: getContractList 
	* @Description:获取合同列表
	* @param @param managerContractRo
	* @param @return
	* @return List<ERPManagerContract>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<ERPManagerContractVo> getContractList(ERPManagerContractRo managerContractRo);

	/**
	 * 
	* @Title: getCountContractList 
	* @Description:获取合同列表总量
	* @param @param managerContractRo
	* @param @return
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Integer getCountContractList(ERPManagerContractRo managerContractRo);

	/**
	 * 
	* @Title: delContract 
	* @Description:删除合同
	* @param @param managerContractRo
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void delContract(ERPManagerContractRo managerContractRo);

}
