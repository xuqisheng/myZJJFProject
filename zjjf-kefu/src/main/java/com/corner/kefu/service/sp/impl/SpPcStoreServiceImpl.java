/**   
* @Title: PcStoreService.java 
* @Package com.corner.mobile.core.service 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月14日 下午5:10:19 
* @version V1.0   
*/

package com.corner.kefu.service.sp.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SpAcGroup;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.SpAcGroupMapper;
import com.corner.kefu.beans.ro.sp.SpAcGroupRo;
import com.corner.kefu.beans.vo.sp.SpAcGroupVo;
import com.corner.kefu.beans.vo.sp.StoreVo;
import com.corner.kefu.dao.sp.SpAcGroupMgMapper;
import com.corner.kefu.service.sp.SpPcStoreService;

/** 
* @ClassName: PcStoreService 
* @Description:pc 端店铺管理
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月14日 下午5:10:19 
*  
*/
@Service
public class SpPcStoreServiceImpl implements SpPcStoreService{
	
	private static final Logger logger = LoggerFactory.getLogger(SpPcStoreServiceImpl.class);
	
	@Autowired
	SpAcGroupMapper spAcGroupMapper;
	@Autowired
	SpAcGroupMgMapper spAcGroupMgMapper;
	@Override
	public ModelMsg insertSelective(SpAcGroup spAcGroup) throws Exception{
		int result = spAcGroupMapper.insertSelective(spAcGroup);
		 if(result == 0)
			   throw new Exception("新增数据错误");
		return new ModelMsg(true, "新增成功");
	}
	@Override
	public ModelMsg updateByPrimaryKeySelective(SpAcGroup spAcGroup)  throws Exception{
		int result = spAcGroupMapper.updateByPrimaryKeySelective(spAcGroup);
		if(result == 0)
			   throw new Exception("修改数据错误");
		   return new ModelMsg(true, "修改成功");
	}
	@Override
	public SpAcGroup selectByPrimaryKey(String id){
		return spAcGroupMapper.selectByPrimaryKey(id);
	}
	@Override
	public Pager<SpAcGroupVo> getPagerList(SpAcGroupRo spAcGroupRo) {
		List<SpAcGroupVo> list = spAcGroupMgMapper.getSpAcGroupList(spAcGroupRo);
		Integer count = spAcGroupMgMapper.getSpAcGroupListCount(spAcGroupRo);
		return new Pager<SpAcGroupVo>(count, list);
	}
	@Override
	public Pager<StoreVo> getSpAcGroupWithStoreList(SpAcGroupRo spAcGroupRo) {
		List<StoreVo> list = spAcGroupMgMapper.getSpAcGroupWithStoreList(spAcGroupRo);
		Integer count = spAcGroupMgMapper.getSpAcGroupWithStoreListCount(spAcGroupRo);
		return new Pager<StoreVo>(count, list);
	}
	@Override
	public ModelMsg addStoreIntoSpACGroup(Map<String, Object> map) throws Exception{
		int result = spAcGroupMgMapper.addStoreIntoSpACGroup(map);
		if(result == 0)
			   throw new Exception("新增数据错误");
		return new ModelMsg(true, "新增成功");
	}
	@Override
	public ModelMsg deleteStoreFromSpACGroup(Map<String, Object> map) throws Exception{
		int result = spAcGroupMgMapper.deleteStoreFromSpACGroup(map);
		if(result == 0)
			   throw new Exception("删除数据错误");
		   return new ModelMsg(true, "删除成功");
	}
	@Override
	public ModelMsg deleteSpAcGroup(String id) throws Exception{
		int result = spAcGroupMgMapper.deleteStoreFromAcStore(id);
	   if(result == 0)
		   throw new Exception("删除数据错误");
	   SpAcGroup spAcGroup = new SpAcGroup();
	   spAcGroup.setId(id);
	   spAcGroup.setIsDelete(true);
	   result = spAcGroupMapper.updateByPrimaryKey(spAcGroup);
	   if(result == 0)
		   throw new Exception("删除数据错误");
	   return new ModelMsg(true, "删除成功");
	}
	@Override
	public Pager<StoreVo> getAllNoShopGroupStoreList(SpAcGroupRo spAcGroupRo) {
		List<StoreVo>list = spAcGroupMgMapper.getAllNoShopGroupStoreList(spAcGroupRo);
		Integer count = spAcGroupMgMapper.getAllNoShopGroupStoreListCount(spAcGroupRo);
		return new Pager<StoreVo>(count, list);
	}
	@Override
	public List<SpAcGroup> getAllStoreGroupList(SpAcGroupRo spAcGroupRo) {
		return spAcGroupMgMapper.getAllStoreGroupList(spAcGroupRo);
	}
	@Override
	public ModelMsg batchAddStore(Map<String, Object> map) throws Exception {
		int result = spAcGroupMgMapper.batchAddStore(map);
		if(result == 0)
			   throw new Exception("删除数据错误");
		   return new ModelMsg(true, "删除成功");
	}
}
