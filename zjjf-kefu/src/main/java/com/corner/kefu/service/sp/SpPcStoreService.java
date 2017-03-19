/**   
* @Title: PcStoreService.java 
* @Package com.corner.mobile.core.service 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月14日 下午5:10:19 
* @version V1.0   
*/

package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpAcGroup;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.sp.SpAcGroupRo;
import com.corner.kefu.beans.vo.sp.SpAcGroupVo;
import com.corner.kefu.beans.vo.sp.StoreVo;

/** 
* @ClassName: PcStoreService 
* @Description:pc 端店铺管理
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月14日 下午5:10:19 
*  
*/
public interface SpPcStoreService {
	
	public ModelMsg insertSelective(SpAcGroup spAcGroup)  throws Exception;

	public ModelMsg updateByPrimaryKeySelective(SpAcGroup spAcGroup)  throws Exception;

	public SpAcGroup selectByPrimaryKey(String id);
	
	public Pager<SpAcGroupVo> getPagerList(SpAcGroupRo spAcGroupRo);
	
	public Pager<StoreVo> getSpAcGroupWithStoreList(SpAcGroupRo spAcGroupRo);
	
	public ModelMsg addStoreIntoSpACGroup(Map<String, Object> map) throws Exception;
	
	public ModelMsg deleteStoreFromSpACGroup(Map<String, Object> map) throws Exception;
	
	public ModelMsg deleteSpAcGroup(String id) throws Exception;
	
	public Pager<StoreVo> getAllNoShopGroupStoreList(SpAcGroupRo spAcGroupRo);
	
	public List<SpAcGroup> getAllStoreGroupList(SpAcGroupRo spAcGroupRo);
	
	public ModelMsg batchAddStore(Map<String, Object> map) throws Exception;
}
