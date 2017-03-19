/**   
* @Title: ERPManagerOrderInfoServiceImpl.java 
* @Package com.corner.kefu.service.erp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月5日 下午3:45:25 
* @version V1.0   
*/

package com.corner.kefu.service.erp.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.kefu.service.callable.CallAbleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.ERPManager;
import com.corner.core.beans.ERPManagerOrderDetail;
import com.corner.core.beans.ERPManagerOrderInfo;
import com.corner.core.beans.ERPPurchaseStockInfo;
import com.corner.core.beans.ERPWarehouse;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ERPManagerMapper;
import com.corner.core.dao.ERPManagerOrderDetailMapper;
import com.corner.core.dao.ERPManagerOrderInfoMapper;
import com.corner.core.dao.ERPPurchaseStockInfoMapper;
import com.corner.core.dao.ERPWarehouseMapper;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.enums.OrderPrefix;
import com.corner.core.enums.SocktOperateType;
import com.corner.core.utils.CreateOrderIdUtil;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.callable.SocktOperateLog;
import com.corner.kefu.beans.ro.erp.ERPManagerOrderInfoRo;
import com.corner.kefu.beans.ro.erp.ERPPurchaseStockInfoRo;
import com.corner.kefu.beans.vo.erp.ERPManagerOrderDetailVo;
import com.corner.kefu.beans.vo.erp.ERPManagerOrderInfoVo;
import com.corner.kefu.beans.vo.erp.ERPPurchaseStockDetailVo;
import com.corner.kefu.beans.vo.erp.ERPPurchaseStockInfoVo;
import com.corner.kefu.dao.erp.ERPManagerOrderInfoMgMapper;
import com.corner.kefu.service.erp.ERPManagerOrderInfoService;

/** 
* @ClassName: ERPManagerOrderInfoServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月5日 下午3:45:25 
*  
*/
@Service
public class ERPManagerOrderInfoServiceImpl implements ERPManagerOrderInfoService {

	@Autowired
	ERPManagerOrderInfoMgMapper eRPManagerOrderInfoMgMapper;
	
	@Autowired
	ERPManagerOrderInfoMapper eRPManagerOrderInfoMapper;
	
	@Autowired
	ERPManagerOrderDetailMapper eRPManagerOrderDetailMapper;
	
	@Autowired	
	SupplierMapper supplierMapper;
	
	@Autowired	
	ERPWarehouseMapper eRPWarehouseMapper;
	
	@Autowired	
	ERPManagerMapper eRPManagerMapper;
	
	@Autowired	
	ERPPurchaseStockInfoMapper eRPPurchaseStockInfoMapper;
	@Autowired
	CallAbleService callAbleService;

	/**
	 * 
	* @Title: save 
	* @Description:保存采购单
	* @param @param paramMap
	* @param @return
	* @return ModelMsg    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public ModelMsg save(HashMap<String, Object> paramMap) {
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		
		ERPManagerOrderInfoRo orderInfoRo = (ERPManagerOrderInfoRo) paramMap.get("orderInfoRo"); 
		String managerItemIdArr[] = (String[]) paramMap.get("managerItemIdArr");
		String managerItemNumArr[] = (String[]) paramMap.get("managerItemNumArr");
		String managerItemPriceArr[] = (String[]) paramMap.get("managerItemPriceArr");
		CustomerService customerService = (CustomerService) paramMap.get("customerService");
		
		ERPWarehouse erpWarehouse = eRPWarehouseMapper.selectByPrimaryKey(orderInfoRo.getWhId());
		Supplier supplier = supplierMapper.selectByPrimaryKey(erpWarehouse.getSupplierId());
		ERPManager erpManager = eRPManagerMapper.selectByPrimaryKey(orderInfoRo.getManagerId());
		
		
		//设置订单orderId
		if(StringUtils.isEmpty(orderInfoRo.getOrderId())){
			orderInfoRo.setOrderId(OrderPrefix.ManagerOrderInfo.getPrefix()+CreateOrderIdUtil.dateToString());
		}
		
		List<ERPManagerOrderDetail> detailList = eRPManagerOrderInfoMgMapper.getDetailListByIdArr(managerItemIdArr);
		if(detailList==null||detailList.size()==0){
			msg.setMessage("没有商品详情!");
			return msg;
		}
		
		//设置ERPManagerOrderDetail
		Date addTime = new Date();
		BigDecimal itemPrice = new BigDecimal(0);
		for(int i = 0 ;i<managerItemIdArr.length;i++){
			for (ERPManagerOrderDetail erpManagerOrderDetail : detailList) {
				 if(erpManagerOrderDetail.getItemId().equals(managerItemIdArr[i])){
					 erpManagerOrderDetail.setAreaPrice(new BigDecimal(managerItemPriceArr[i]));//采购价
					 erpManagerOrderDetail.setQuantity(Short.parseShort(managerItemNumArr[i]));//采购数量
					 erpManagerOrderDetail.setAddTime(addTime);
					 erpManagerOrderDetail.setBarCode(erpManagerOrderDetail.getMdseId());
					 erpManagerOrderDetail.setOrderId(orderInfoRo.getOrderId());//订单id
					 erpManagerOrderDetail.setManagerId(erpManager.getId());
					 BigDecimal total = new BigDecimal(managerItemPriceArr[i]).multiply(new BigDecimal(managerItemNumArr[i]));
					 itemPrice = itemPrice.add(total);
				 }
			}
		}
		//设置ERPManagerOrderInfo
		orderInfoRo.setAddTime(addTime);
		orderInfoRo.setItemPrice(itemPrice);
		orderInfoRo.setPurchaseUser(customerService.getId());
		orderInfoRo.setPurchaseUserName(customerService.getNickName());
		orderInfoRo.setWhName(erpWarehouse.getName());
		orderInfoRo.setManagerName(erpManager.getManagerName());
		orderInfoRo.setSupplierId(supplier.getId());
		ERPManagerOrderInfo info = new ERPManagerOrderInfo();
		BeanUtils.copyProperties(orderInfoRo, info);
		if(StringUtils.isEmpty(info.getId())){
			info.setId(StringUtil.getUUID());
			eRPManagerOrderInfoMapper.insertSelective(info);
		}else {
			eRPManagerOrderInfoMgMapper.delteDetailByOrderId(info);
			eRPManagerOrderInfoMapper.updateByPrimaryKeySelective(info);
		}
		for (ERPManagerOrderDetail erpManagerOrderDetail : detailList) {
			eRPManagerOrderDetailMapper.insertSelective(erpManagerOrderDetail);
		}
		msg.setSuccess(true);
		msg.setData(info.getId());
		return msg;
	}

	@Override
	public Pager<ERPManagerOrderInfoVo> getOrderInfoList(ERPManagerOrderInfoRo orderInfoRo) {
		List<ERPManagerOrderInfoVo> list = eRPManagerOrderInfoMgMapper.getOrderInfoList(orderInfoRo);
		Integer count = 0;
		if(list!=null&&list.size()!=0){
			count = eRPManagerOrderInfoMgMapper.getCountOrderInfoList(orderInfoRo);
		}
		return new Pager<>(count, list);
	}

	@Override
	public ERPManagerOrderInfo selectById(String id) {
		return eRPManagerOrderInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ERPManagerOrderDetailVo> getOrderInfoVoById(ERPManagerOrderInfoRo orderInfoRo) {
		List<ERPManagerOrderDetailVo> detailVos = eRPManagerOrderInfoMgMapper.getOrderInfoVoById(orderInfoRo);
		return detailVos;
	}

	@Override
	public ModelMsg delErpManagerOrderInfo(String delManagerIdStr) {
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		String[] idArr = delManagerIdStr.split(",");
		for (String id : idArr) {
			ERPManagerOrderInfo orderInfo = eRPManagerOrderInfoMapper.selectByPrimaryKey(id);
			eRPManagerOrderInfoMgMapper.deleteOrderInfoById(orderInfo);
			eRPManagerOrderInfoMgMapper.delteDetailByOrderId(orderInfo);
		}
		msg.setSuccess(true);
		return msg;
	}

	@Override
	public ModelMsg checkErpOrderInfo(Map<String, Object> paramMap) {
		
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		String managerIdStr = (String) paramMap.get("managerIdStr");
		String[] idArr = managerIdStr.split(",");
		paramMap.put("idArr", idArr);
		eRPManagerOrderInfoMgMapper.checkErpOrderInfo(paramMap);
		msg.setSuccess(true);
		return msg;
	}

	@Override
	public Pager<ERPPurchaseStockInfoVo> getPurchaseBack(ERPPurchaseStockInfoRo stockInfoRo) {
		List<ERPPurchaseStockInfoVo> list = eRPManagerOrderInfoMgMapper.getPurchaseBack(stockInfoRo);
		Integer count = 0;
		if(list!=null&&list.size()!=0){
			count = eRPManagerOrderInfoMgMapper.getCountPurchaseBack(stockInfoRo);
		}
		return new Pager<>(count, list);
	}

	@Override
	public ERPPurchaseStockInfo getPurchaseStockInfoById(ERPPurchaseStockInfoRo stockInfoRo) {
		return eRPPurchaseStockInfoMapper.selectByPrimaryKey(stockInfoRo.getId());
	}

	@Override
	public List<ERPPurchaseStockDetailVo> getPurcherStockDetailsByOrDerId(ERPPurchaseStockInfo purchaseStockInfo) {
		return eRPManagerOrderInfoMgMapper.getPurcherStockDetailsByOrDerId(purchaseStockInfo);
	}

	@Override
	public ModelMsg updatePurchaseOrder(Map<String, Object> paramMap) {
		CustomerService service = (CustomerService) paramMap.get("service");
		ModelMsg msg  = new ModelMsg();
		msg.setSuccess(false);
		String[] managerIdStrArr = (String[]) paramMap.get("managerIdStrArr");
		String message = "";
		for (String id : managerIdStrArr) {
			ERPPurchaseStockInfo purchaseStockInfo = eRPPurchaseStockInfoMapper.selectByPrimaryKey(id);
			if(purchaseStockInfo==null||purchaseStockInfo.getIsDelete()){
				message+="采购单:"+purchaseStockInfo.getOrderId()+"不存在或已被删除!  ";
				continue;
			}
			if(purchaseStockInfo.getCheckStatus().intValue()==2){
				message+="采购单:"+purchaseStockInfo.getOrderId()+"已经审核通过!  ";
				continue;
			}
			purchaseStockInfo.setCheckStatus((byte)2);
			purchaseStockInfo.setCheckTime(new Date());
			purchaseStockInfo.setCheckUser(service.getId());
			purchaseStockInfo.setCheckUserName(service.getNickName());
			eRPPurchaseStockInfoMapper.updateByPrimaryKeySelective(purchaseStockInfo);
			//调用存储过程
			SocktOperateLog socktOperateLog = new SocktOperateLog();
			socktOperateLog.setBusinessType(SocktOperateType.Operate_10087.getIndex());
			socktOperateLog.setVoucherMain(purchaseStockInfo.getOrderId());
			try {
				callAbleService.socktOperateLog(SocktOperateType.Operate_10087 , purchaseStockInfo.getOrderId());
			}catch (Exception e){
				System.out.println("存储调用错误："+e.getMessage());
				msg.setMessage(e.getMessage());
				return msg;
			}

		}
		//eRPManagerOrderInfoMgMapper.updatePurchaseOrder(paramMap);
		if(message.length()==0){
			msg.setSuccess(true);
		}else{
			msg.setMessage(message);
		}
		return msg;
	}


}
