/**   
* @Title: ERPManagerContractService.java 
* @Package com.corner.kefu.service.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月3日 下午3:16:46 
* @version V1.0   
*/

package com.corner.kefu.service.erp;

import com.corner.core.beans.ERPManagerContract;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.erp.ERPManagerContractRo;
import com.corner.kefu.beans.vo.erp.ERPManagerContractVo;

/** 
* @ClassName: ERPManagerContractService 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月3日 下午3:16:46 
*  
*/

public interface ERPManagerContractService {

	void save(ERPManagerContract managerContract);

	Pager<ERPManagerContractVo> getContractList(ERPManagerContractRo managerContractRo);

	void delContract(ERPManagerContractRo managerContractRo);

	ERPManagerContract selectByPrimaryKey(String id);

}
