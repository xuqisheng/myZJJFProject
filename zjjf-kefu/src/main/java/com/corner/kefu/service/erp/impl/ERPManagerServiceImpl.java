package com.corner.kefu.service.erp.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ERPManager;
import com.corner.core.beans.ERPManagerSpWarehouseMap;
import com.corner.core.beans.ERPWarehouse;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ERPManagerMapper;
import com.corner.core.dao.ERPManagerSpWarehouseMapMapper;
import com.corner.kefu.beans.ro.erp.ERPManagerRo;
import com.corner.kefu.beans.ro.erp.ERPmanagerItemRo;
import com.corner.kefu.beans.vo.erp.ERPManagerItemVo;
import com.corner.kefu.beans.vo.erp.ERPManagerVo;
import com.corner.kefu.dao.ERPWarehouseMgMapper;
import com.corner.kefu.dao.erp.ERPManagerMgMapper;
import com.corner.kefu.service.erp.ERPManagerService;
@Service
public class ERPManagerServiceImpl implements ERPManagerService {

	@Autowired ERPManagerMgMapper eRPManagerMgMapper;
	@Autowired ERPManagerMapper managerMapper;
	@Autowired
	ERPWarehouseMgMapper eRPWarehouseMgMapper;
	@Autowired
	ERPManagerSpWarehouseMapMapper eRPManagerSpWarehouseMapMapper;
	
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
		if(manager.getRegionLevel()!=null&&manager.getRegionLevel().intValue()==0){
			manager.setRegionLevel(null);
			manager.setRegionId(null);
		}
		List<ERPManagerVo> managerList = eRPManagerMgMapper.getAllERPManager(manager);
		int num = eRPManagerMgMapper.getAllERPManagerCount(manager);
		return new Pager<>(num, managerList);
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
		//后台读取最大的编号值
		/*if(manager.getCleaningDay()==1){
			manager.setCleaningDay(manager.getText_cleaningDay());
		}*/
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
		/*if(manager.getCleaningDay()==1){
			manager.setCleaningDay(manager.getText_cleaningDay());
		}*/
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
		// TODO Auto-generated method stub
		return managerMapper.selectByPrimaryKey(id);
	}


	/**
	 * 
	* @Title: saveManagerSupplier 
	* @Description:保存供应商和批发商关系
	* @param @param managerRo
	* @param @return
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public ModelMsg saveManagerSupplier(ERPManagerRo managerRo) {
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		String spIdArr[] = managerRo.getSpIdArr();
		if(spIdArr==null||spIdArr.length==0){
          msg.setMessage("缺少批发商id数据!");			
		}
		List<ERPManagerSpWarehouseMap> mapList = new ArrayList<>();
		String errorMsg = "";
		//根据批发商id查询仓库数据
		for (String spId : spIdArr) {
			List<ERPWarehouse> wareHostList = eRPWarehouseMgMapper.getSupplierAllERPWarehouseById(spId);
			if(wareHostList!=null&&wareHostList.size()!=0){
				for (ERPWarehouse erpWarehouse : wareHostList) {
					ERPManagerSpWarehouseMap map = new ERPManagerSpWarehouseMap();
					map.setSupplierId(spId);
					map.seteRPWarehouseId(erpWarehouse.getId());
					map.seteRPManagerId(managerRo.getId());
					mapList.add(map);
				}
			}else {
				errorMsg+= spId+"没有对应仓库数据\n  ";
			}
		}
		if(StringUtils.isNotEmpty(errorMsg)){
			return msg;
		}
		eRPManagerMgMapper.deleteManagerAndSupplier(managerRo);
		for (ERPManagerSpWarehouseMap erpManagerSpWarehouseMap : mapList) {
			eRPManagerSpWarehouseMapMapper.insertSelective(erpManagerSpWarehouseMap);
		}
		msg.setSuccess(true);
		return msg;
	}



	@Override
	public Pager<ERPManagerItemVo> getJsonErpManagerItem(ERPmanagerItemRo itemRo) {
		List<ERPManagerItemVo> list = eRPManagerMgMapper.getJsonErpManagerItem(itemRo);
		Integer count =0 ;
		if(list!=null&&list.size()!=0){
			count = eRPManagerMgMapper.getCountJsonErpManagerItem(itemRo);
		}
		return new Pager<>(count,list);
	}



	@Override
	public ERPManager selectByPrimaryKey(String erpManagerId) {
		return managerMapper.selectByPrimaryKey(erpManagerId);
	}



	@Override
	public Pager<ERPManagerVo> getERPManagerByWhId(ERPManagerRo manager) {
		List<ERPManagerVo> list = eRPManagerMgMapper.getERPManagerByWhId(manager);
		Integer count = 0;
		if(list!=null&&list.size()!=0){
			count = eRPManagerMgMapper.getCountERPManagerByWhId(manager);
		}
		return new Pager<>(count,list);
	}

}
