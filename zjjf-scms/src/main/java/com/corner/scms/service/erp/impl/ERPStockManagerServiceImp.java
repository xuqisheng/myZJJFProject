package com.corner.scms.service.erp.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.core.enums.SocktOperateType;
import com.corner.scms.service.callable.CallAbleService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ERPPlantItemLog;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ERPPlantItemLogMapper;
import com.corner.core.utils.CreateOrderIdUtil;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPLogicStockRo;
import com.corner.scms.beans.ro.erp.ERPPhysicsStockLogRo;
import com.corner.scms.beans.ro.erp.ERPPhysicsStockRo;
import com.corner.scms.beans.vo.erp.ERPLogicStockVo;
import com.corner.scms.beans.vo.erp.ERPPhysicsStockLogVo;
import com.corner.scms.beans.vo.erp.ERPPhysicsStockVo;
import com.corner.scms.dao.erp.ERPStockManagerMapper;
import com.corner.scms.service.erp.ERPStockManagerService;
@Service
public class ERPStockManagerServiceImp implements ERPStockManagerService {
	@Autowired
	CallAbleService callAbleService;
	@Autowired
	ERPStockManagerMapper erpStockManagerMapper;
	@Autowired
	ERPPlantItemLogMapper erpPlantItemLogMapper;
	@Override
	public Pager<ERPLogicStockVo> getSupplierStock(ERPLogicStockRo logicStock) {
		if(!StringUtil.stringIsNullOrEmpty(logicStock.getNameAndMdseId())){
			logicStock.setNameAndMdseId(logicStock.getNameAndMdseId().trim());
		}
		List<ERPLogicStockVo> stockList = erpStockManagerMapper.getSupplierStock(logicStock);
		int num = erpStockManagerMapper.getSupplierStockCount(logicStock);
		return new Pager<ERPLogicStockVo>(num, stockList);
	}
	
	@Override
	public List<ERPPhysicsStockVo> getSupplierStockDetail(ERPPhysicsStockRo physicsStock) {
		//判断总数里面是否包含逻辑库存id如果有标记存在（isExist=true）
		List<ERPPhysicsStockVo> list = erpStockManagerMapper.getSupplierStockDetail(physicsStock);
		if(list != null && list.size() >0){
			//获取逻辑库存id
			/*String logicStockId = erpStockManagerMapper.getlogicStockId(physicsStock);
			if(!StringUtil.stringIsNullOrEmpty(logicStockId)){
				for (ERPPhysicsStockVo erpPhysicsStockVo : list) {
					if(erpPhysicsStockVo.getLogicStockId() != null){
						if(erpPhysicsStockVo.getLogicStockId().equals(logicStockId) ){
							erpPhysicsStockVo.setIsExist(true);;
						}
					}
				}
			}*/
			return list;
		}else{
			return null;
		}
		
	}

	@Override
	public Pager<ERPPhysicsStockLogVo> getPhysicsStockLog(ERPPhysicsStockLogRo physicsStockLog) {
		Date endTime = physicsStockLog.getEndTime();
		if(endTime != null){
			endTime = DateUtils.addDays(endTime, 1);
			physicsStockLog.setEndTime(endTime);
		}
		List<ERPPhysicsStockLogVo> physicsStockLogList = erpStockManagerMapper.getPhysicsStockLog(physicsStockLog);
		int num = erpStockManagerMapper.getPhysicsStockLogCount(physicsStockLog);
		return new Pager<ERPPhysicsStockLogVo>(num, physicsStockLogList);
	}

	@Override
	public void updateStock(List<ERPPlantItemLog> erpPlantItemLoglist,Supplier supplier) throws Exception {
		//生成订单号
		String orderId = CreateOrderIdUtil.dateToString();
		
		for (ERPPlantItemLog erpPlantItemLog : erpPlantItemLoglist) {
			erpPlantItemLog.setSupplierId(supplier.getId());
			erpPlantItemLog.setActionUserId(supplier.getId());
			erpPlantItemLog.setActionUserName(supplier.getSupplierName());
			erpPlantItemLog.setId(StringUtil.getUUID());
			erpPlantItemLog.setAddTime(new Date());
			erpPlantItemLog.setOrderId(orderId);
			erpPlantItemLog.setProducingArea("zjjf");
			if(erpPlantItemLog.getTypeMg()==null){
				erpPlantItemLog.setTypeMg((byte)1);
			}
			if(erpPlantItemLog.getProductionDate()==null){
				erpPlantItemLog.setProductionDate(new Date());
			}
			if(erpPlantItemLog.getAreaPrice()==null){
				erpPlantItemLog.setAreaPrice(new BigDecimal(0.00));
			}
			if(erpPlantItemLog.getStatus()==null){
				erpPlantItemLog.setStatus((byte)3);
			}
			erpPlantItemLogMapper.insertSelective(erpPlantItemLog);
		}
		//调用存储过程
		callAbleService.socktOperateLog(SocktOperateType.Operate_10086 , orderId);
	}
}
