package com.corner.scms.service.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ERPManager;
import com.corner.core.beans.Region;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ERPManagerMapper;
import com.corner.core.dao.RegionMapper;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPManagerRo;
import com.corner.scms.beans.vo.erp.ERPManagerVo;
import com.corner.scms.dao.erp.ERPManagerMgMapper;
import com.corner.scms.service.erp.ERPManagerService;
@Service
public class ERPManagerServiceImpl implements ERPManagerService {

	@Autowired ERPManagerMgMapper eRPManagerMgMapper;
	@Autowired ERPManagerMapper managerMapper;
	@Autowired RegionMapper regionMapper;

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
	@Override
	public Pager<ERPManagerVo> getAllERPManager(ERPManagerRo manager) {
		/*if(manager.getRegionLevel()!=null){
			if(manager.getRegionLevel().intValue()==2){//省
				
			}else if (manager.getRegionLevel().intValue()==3) {//市
				
			}else if (manager.getRegionLevel().intValue()==4) {//区
				
			}
		}*/
		List<ERPManagerVo> managerList = eRPManagerMgMapper.getAllERPManager(manager);
//		int num = 0;
//		if(managerList!=null&&managerList.size()!=0){
//			manager.setPageIndex(0);
//			num = eRPManagerMgMapper.getAllERPManager(manager).size();
//		}
		int num = eRPManagerMgMapper.getAllERPManagerCount(manager);
		return new Pager<ERPManagerVo>(num, managerList);
	}



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
	@Override
	public ModelMsg addERPManager(ERPManagerRo manager) {
		// TODO Auto-generated method stub
		//后台读取最大的编号值
		if(manager.getCleaningDay()==1){
			manager.setCleaningDay(manager.getText_cleaningDay());
		}
		Integer serialNum = eRPManagerMgMapper.getMaxNum();
		int num1 = 0;
		if(serialNum != null){
			num1 = serialNum.intValue()+1;
		}
		String managerCode = "1"+String.format("%04d", num1);
		manager.setSerialNum(num1);
		manager.setManagerCode(managerCode);
		int num = managerMapper.insertSelective(manager);
		if(num>0){
			return new ModelMsg(true, "添加成功");
		}else{
			return new ModelMsg(false, "添加失败");
		}
	}


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
	@Override
	public ModelMsg updateERPManager(ERPManagerRo manager) {
		// TODO Auto-generated method stub
		if(manager.getCleaningDay()==1){
			manager.setCleaningDay(manager.getText_cleaningDay());
		}
		int num = managerMapper.updateByPrimaryKeySelective(manager);
		if(num>0){
			return new ModelMsg(true, "编辑成功");
		}else{
			return new ModelMsg(false, "编辑失败");
		}
	}



	/**
	 * 批发商删除供应商
	* @Title
	* @Description: TODO 
	* @param @param id
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public boolean deleteERPManager(String id) {
		//查出经销商下有没有商品
		
		ERPManager manager = new ERPManager();
		manager.setId(id);
		manager.setStatus(Byte.parseByte("2"));
		manager.setIsDelete(true);
		int num = managerMapper.updateByPrimaryKeySelective(manager);
		return num>0?true:false;
	}



	@Override
	public ERPManager getERPManagerById(String id) {
		ERPManager manager = managerMapper.selectByPrimaryKey(id);
		//给地址添加省市区
		if(!StringUtil.stringIsNullOrEmpty(manager.getAddress())){
			if(manager.getAreaId() == null || manager.getAreaId() == 0){
				Region region = regionMapper.selectByPrimaryKey(manager.getAreaId());
				manager.setAddress(region.getName() + manager.getAddress());
			}
			if(manager.getCityId() == null || manager.getCityId() == 0){
				Region region = regionMapper.selectByPrimaryKey(manager.getCityId());
				manager.setAddress(region.getName() + manager.getAddress());
			}
			if(manager.getProvinceId() == null || manager.getProvinceId() == 0){
				Region region = regionMapper.selectByPrimaryKey(manager.getProvinceId());
				manager.setAddress(region.getName() + manager.getAddress());
			}
		}
		return manager;
	}

}
