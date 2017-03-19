/**   
* @Title: ERPManagerItemService.java 
* @Package com.corner.kefu.service.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月7日 下午2:32:26 
* @version V1.0   
*/

package com.corner.kefu.service.erp;

import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.erp.ManagerItemRo;
import com.corner.kefu.beans.vo.erp.ERPManagerItemVo;

/** 
* @ClassName: ERPManagerItemService 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月7日 下午2:32:26 
*  
*/

public interface ERPManagerItemService {

	Pager<ERPManagerItemVo> getAllManagerItem(ManagerItemRo managerItemRo);

	ERPManagerItemVo getManagerItemById(String id);

	ModelMsg addERPManagerItem(String managerId, String id, String[] itemBaseIds, String[] areaPrices,
			String[] taxRates);

	ModelMsg updateERPManagerItem(ManagerItemRo managerItemRo);

	boolean deleteERPManagerItem(String id);

	ModelMsg getitemByMdseId(String mdseId);

	ModelMsg getitemByMdseIdx(String mdseId);

}
