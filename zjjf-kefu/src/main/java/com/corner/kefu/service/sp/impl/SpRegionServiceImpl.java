package com.corner.kefu.service.sp.impl;

import com.corner.core.beans.Region;
import com.corner.core.beans.RegionSpGroupMap;
import com.corner.core.beans.vo.ResponseVo;
import com.corner.core.dao.RegionMapper;
import com.corner.core.dao.RegionSpGroupMapMapper;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.beans.vo.sp.RegionSpGroupVo;
import com.corner.kefu.beans.vo.sp.RegionVo;
import com.corner.kefu.dao.sp.SpRegionMgMapper;
import com.corner.kefu.service.sp.SpRegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpRegionServiceImpl implements SpRegionService{

	private static final Logger logger = LoggerFactory.getLogger(SpRegionServiceImpl.class);
	@Autowired
	SpRegionMgMapper spRegionMgMapper;
	
	@Autowired
	RegionMapper regionMapper;
	
	@Autowired
	RegionSpGroupMapMapper regionSpGroupMapMapper;
	
	/**
	 * 根据id获取父级id
	 * @author aimee at 2015年6月3日下午4:19:23
	 * @email 1297579898@qq.com
	 * @param id
	 * @return
	 */
	@Override
	public RegionVo getPID(Integer id){
		return spRegionMgMapper.selectPIDWithID(id);
	}
	@Override
	public Integer getPidByid(Map<String, Object> map){
		return spRegionMgMapper.getPidByid(map);
	}
	@Override
	public List<Region> getRegionByPidOrRegionLevel(Map<String, Object> map){
		return spRegionMgMapper.getRegionByPidOrRegionLevel(map);
	}
	
	//获取所有的省市区
	@Override
	public List<RegionVo> getAllRegion() {
		//所有集合
		List<RegionVo> regions = spRegionMgMapper.getAllRegion();
		//组装的数组
		List<RegionVo> guoList  = getGuoList(regions);
		return guoList;
	}
	
	/**
	 * 
	* @Title: getGuoList 
	* @Description:获得组装好的数据
	* @param @param regions
	* @param @return
	* @return List<RegionVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	private List<RegionVo> getGuoList(List<RegionVo> regions) {
		List<RegionVo> guoList = new ArrayList<RegionVo>();
		List<RegionVo> shengList = new ArrayList<RegionVo>();
		List<RegionVo> shiList = new ArrayList<RegionVo>();
		List<RegionVo> quList = new ArrayList<RegionVo>();
		for (RegionVo regionVo : regions) {
			if(regionVo.getRegionLevel()== (byte)1){
				guoList.add(regionVo);
			}else if(regionVo.getRegionLevel() == (byte)2){
				shengList.add(regionVo);
			}else if(regionVo.getRegionLevel() == (byte)3){
				shiList.add(regionVo);
			}else if(regionVo.getRegionLevel() == (byte)4){
				quList.add(regionVo);
			}else{
				continue;
			}
		}
		
		for (int i=0;i<shiList.size();i++) {
			RegionVo shi  = shiList.get(i);
			for (RegionVo qu : quList) {
				if(qu.getpId().intValue() == shi.getId().intValue()){
					shi.getRegionList().add(qu);
				}else{
					continue;
				}
			}
			shiList.set(i, shi);
		}
		for (int i=0;i<shengList.size();i++) {
			RegionVo sheng = shengList.get(i);
			for (RegionVo shi : shiList) {
				if(shi.getpId() == sheng.getId()){
					sheng.getRegionList().add(shi);
				}else{
					continue;
				}
			}
			shengList.set(i, sheng);
		}
		

		for (int i=0;i<guoList.size();i++) {
			RegionVo guo = guoList.get(i);
			guo.setName("转角街坊");
			for (RegionVo sheng : shengList) {
				if(sheng.getpId() == guo.getId()){
					guo.getRegionList().add(sheng);
				}else{
					continue;
				}
			}
			guoList.set(i, guo);
		}
		return guoList;
	}
	@Override
	public Region getRegionById(Integer id) {
		// TODO Auto-generated method stub
		return regionMapper.selectByPrimaryKey(id);
	}
	/**
	 * 递归修改区域
	 */
	@Override
	public void updateRegionStatus(Region region) {
		if(region.getStatus()==0){
			regionMapper.updateByPrimaryKeySelective(region);
			//递归停用下级区域
			recursionUpdate(region.getId(),0);
		}else{
			regionMapper.updateByPrimaryKeySelective(region);
			//递归启用上级区域
			recursionUpdate(region.getId(),1);
		}
		
	}
	//递归修改
	private void recursionUpdate(Integer id,int num) {
		// TODO Auto-generated method stub
		Map<String, Object> map = null;
		if(num==0){
			map = new HashMap<String, Object>();
			map.put("pId", id);
			List<Region> regionList = spRegionMgMapper.getRegionByPidOrRegionLevel(map);
			if(regionList != null && regionList.size()>0){
				for (Region region : regionList) {
					if(region.getStatus()==1){
						region.setStatus(Byte.parseByte("0"));
						regionMapper.updateByPrimaryKeySelective(region);
					}
					if(region.getRegionLevel()<4){
						recursionUpdate(region.getId(),num);
					}
				}
			}
		}else{
			//递归启用上级区域
			map = new HashMap<String, Object>();
			map.put("id", id);
			Region region = spRegionMgMapper.getUpperByLowerId(map);
			if(!(region.getpId()==0 && region.getRegionLevel()==1)){
				if(region.getStatus()==0){
					region.setStatus(Byte.parseByte("1"));
					regionMapper.updateByPrimaryKeySelective(region);
				}
				if(region.getRegionLevel()>1){
					recursionUpdate(region.getId(),num);
				}
			}
		}
	}
	
	
	/**
	 * 
	* Title: getAllEnabledRegion 
	* Description: 
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpRegionService#getAllEnabledRegion()
	 */
	@Override
	public List<RegionVo> getAllEnabledRegion() throws Exception {
		List<RegionVo> guoList = new ArrayList<RegionVo>();
		RegionVo regionVo = new RegionVo();
		regionVo.setId(1);
		regionVo.setpId(0);
//		regionVo.setRegionCode("0");
		regionVo.setRegionLevel((byte)0);
		regionVo.setStatus((byte)1);
		regionVo.setIsDelete(false);
		regionVo.setName("中国");
		List<RegionVo> allEnabledRegions = spRegionMgMapper.getAllEnabledRegion();
		regionVo.setRegionList(allEnabledRegions);
		guoList.add(regionVo);
		//List<RegionVo> guoList = new ArrayList<guo>();
		//List<RegionVo> guoList = getGuoList(allEnabledRegions);
		return guoList;
	}
	@Override
	public List<RegionVo> getAllAreaAndSpGroup() {
		// TODO Auto-generated method stub
		return spRegionMgMapper.getAllAreaAndSpGroup();
	}
	
	//获取生成的店铺编号字符串
	@Override
	public String getRegionStr(Integer provinceId,Integer cityId,Integer areaId) {
		// TODO Auto-generated method stub
		Region sheng = regionMapper.selectByPrimaryKey(provinceId);
		Region shi = regionMapper.selectByPrimaryKey(cityId);
		Region region = regionMapper.selectByPrimaryKey(areaId);
		StringBuilder str = new StringBuilder("");
		String shengStr= "";
		if(sheng != null && sheng.getRegionCode() != null){
			shengStr = sheng.getRegionCode().toString();
		}
		String shiStr= "";
		if(shi != null && shi.getRegionCode() != null){
			shiStr = shi.getRegionCode().toString();
		}
		String regionStr= "";
		if(region != null && region.getRegionCode() != null){
			regionStr = region.getRegionCode().toString();
		}
		str.append(shengStr).append(shiStr).append(regionStr);
		return str.toString();
	}
	@Override
	public RegionSpGroupVo getRegionSpGroupVoById(Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		return spRegionMgMapper.getRegionSpGroupVoById(map);
	}
	
	
	@Override
	public ResponseVo updateRegionDefaultSpGroupId(Integer id, Integer spGroupId,String opUser) {
		if(null == id || null == spGroupId || null == opUser){
			return ResponseUtils.sendMsg(false,"参数异常");
		}
		RegionSpGroupVo vo = getRegionSpGroupVoById(id);
		if(vo == null){
			return ResponseUtils.sendMsg(false,"定格不存在");
		}
		//新增
		if(null == vo.getRegionSpGroupMapId()){
			RegionSpGroupMap obj = new RegionSpGroupMap();
			obj.setRegionId(id);
			obj.setSpGroupId(spGroupId);
			obj.setAddTime(new Date());
			obj.setAddUser(opUser);
			obj.setIsDelete(false);
			int rs = regionSpGroupMapMapper.insert(obj);
			logger.debug("("+opUser+")设置区域默认定格信息; regionId;" + id+";spGroupId:" + spGroupId + "; rs:" + rs);
			return ResponseUtils.sendMsg(true,"处理成功");
		}else{
			//更新
			RegionSpGroupMap obj = regionSpGroupMapMapper.selectByPrimaryKey(vo.getRegionSpGroupMapId());
			obj.setDelTime(new Date());
			obj.setDelUser(opUser);
			obj.setIsDelete(true);
			int rs = regionSpGroupMapMapper.updateByPrimaryKey(obj);
			logger.debug("("+opUser+")设置区域默认定格信息; 删除默认定格 -> regionId;" + id+";spGroupId:" + spGroupId + "; rs:" + rs);
			
			obj = new RegionSpGroupMap();
			obj.setRegionId(id);
			obj.setSpGroupId(spGroupId);
			obj.setAddTime(new Date());
			obj.setAddUser(opUser);
			obj.setIsDelete(false);
			rs = regionSpGroupMapMapper.insert(obj);
			logger.debug("("+opUser+")设置区域默认定格信息; 新增默认定格 ->  regionId;" + id+";spGroupId:" + spGroupId + "; rs:" + rs);
			return ResponseUtils.sendMsg(true,"处理成功");
		}
	}
	
	public ResponseVo clearRegionDefaultSpGroupId(Integer id,String opUser){
		if(null == id || null == opUser){
			return ResponseUtils.sendMsg(false,"参数异常");
		}
		RegionSpGroupVo vo = getRegionSpGroupVoById(id);
		if(vo == null){
			return ResponseUtils.sendMsg(false,"区域默认定格数据不存在");
		}
		if(null == vo.getRegionSpGroupMapId()){
			return ResponseUtils.sendMsg(false,"区域默认定格数据不存在");
		}else{
			//更新
			RegionSpGroupMap obj = regionSpGroupMapMapper.selectByPrimaryKey(vo.getRegionSpGroupMapId());
			obj.setDelTime(new Date());
			obj.setDelUser(opUser);
			obj.setIsDelete(true);
			int rs = regionSpGroupMapMapper.updateByPrimaryKey(obj);
			logger.debug("("+opUser+")设置区域默认定格信息; 删除默认定格 -> regionId;" + id+"; rs:" + rs);
			return ResponseUtils.sendMsg(true,"处理成功");
		}
	
		
	}

	@Override
	public List<RegionVo> getShiQuDingGeData() {
		return spRegionMgMapper.getShiQuDingGeData();
	}
}
