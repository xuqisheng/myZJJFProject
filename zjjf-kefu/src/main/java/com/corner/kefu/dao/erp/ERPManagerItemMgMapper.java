/**   
* @Title: ERPManagerItemMgMapper.java 
* @Package com.corner.kefu.dao.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月7日 下午2:34:39 
* @version V1.0   
*/

package com.corner.kefu.dao.erp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ItemBase;
import com.corner.kefu.beans.ro.erp.ManagerItemRo;
import com.corner.kefu.beans.vo.erp.ERPManagerItemVo;
import com.corner.kefu.beans.vo.erp.ScmsItemBaseVo;

/** 
* @ClassName: ERPManagerItemMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月7日 下午2:34:39 
*  
*/

public interface ERPManagerItemMgMapper {

	List<ERPManagerItemVo> getAllManagerItem(ManagerItemRo managerItemRo);

	int getAllManagerItemCount(ManagerItemRo managerItemRo);

	ERPManagerItemVo getManagerItemById(Map<String, Object> map);

	int getRepeatItem(Map<String, Object> map);

	Integer getMaxNum();

	List<ItemBase> getitemByMdseId(Map<String, Object> map);

	ScmsItemBaseVo getitemByMdseIdx(Map<String, Object> map);

}
