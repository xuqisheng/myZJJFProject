package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpAcGroup;
import com.corner.kefu.beans.ro.sp.SpAcGroupRo;
import com.corner.kefu.beans.vo.sp.SpAcGroupVo;
import com.corner.kefu.beans.vo.sp.StoreVo;


public interface SpAcGroupMgMapper {
	public List<SpAcGroupVo> getSpAcGroupList(SpAcGroupRo spAcGroupRo);
	public Integer getSpAcGroupListCount(SpAcGroupRo spAcGroupRo);
	
	public List<StoreVo> getSpAcGroupWithStoreList(SpAcGroupRo spAcGroupRo);
	public Integer getSpAcGroupWithStoreListCount(SpAcGroupRo spAcGroupRo);
	
	public Integer addStoreIntoSpACGroup(Map<String, Object> map);
	public Integer deleteStoreFromSpACGroup(Map<String, Object> map);
	public Integer deleteStoreFromAcStore(String id);
	
	public List<StoreVo> getAllNoShopGroupStoreList(SpAcGroupRo spAcGroupRo);
	public Integer getAllNoShopGroupStoreListCount(SpAcGroupRo spAcGroupRo);
	
	public List<SpAcGroup> getAllStoreGroupList(SpAcGroupRo spAcGroupRo);
	
	public Integer batchAddStore(Map<String, Object> map);
}