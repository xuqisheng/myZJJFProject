package com.corner.scms.service.erp.impl;

import com.corner.core.beans.*;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ERPProfitDetailMapper;
import com.corner.core.dao.ERPProfitInfoMapper;
import com.corner.core.dao.ItemBaseMapper;
import com.corner.core.enums.OrderPrefix;
import com.corner.core.enums.ProfitType;
import com.corner.core.enums.SocktOperateType;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.ERPProfitRo;
import com.corner.scms.beans.vo.erp.ERPProfitVo;
import com.corner.scms.dao.erp.ERPProfitInfoMgMapper;
import com.corner.scms.service.callable.CallAbleService;
import com.corner.scms.service.erp.ERPManagerItemService;
import com.corner.scms.service.erp.ERPManagerService;
import com.corner.scms.service.erp.ERPProfitService;
import com.corner.scms.service.erp.ERPWarehouseService;
import com.corner.scms.utils.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ERPProfitServiceImpl implements ERPProfitService {
	private static Logger logger = LoggerFactory.getLogger(ERPProfitServiceImpl.class);
	@Autowired
	ERPProfitDetailMapper detailMapper;
	@Autowired
	ERPProfitInfoMapper inInfoMapper;
	@Autowired
	ERPProfitInfoMgMapper inInfoMgMapper;
	@Autowired
	CallAbleService callAbleService;
	@Autowired
	ERPWarehouseService warehouseService;
	@Autowired
	ERPManagerService managerService;
	@Autowired
	ERPManagerItemService erpManagerItemService;
	@Autowired
	ItemBaseMapper itemBaseMapper;


	@Override
	public void insertInfo(ERPProfitInfo info) throws Exception{
		info.setAddTime(new Date());
		if (inInfoMapper.insertSelective(info) != 1)
			throw new Exception("信息新增失败");
	}

	@Override
	public void insertDetail(ERPProfitDetail detail) throws Exception{
		detail.setAddTime(new Date());
		if (detailMapper.insertSelective(detail) != 1)
			throw new Exception("信息新增失败");
	}

	@Override
	public ERPProfitInfo getInfoById(String id) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(id))
			throw new Exception("传入信息有误");
		ERPProfitInfo inInfo = inInfoMapper.selectByPrimaryKey(id);
		if (inInfo == null)
			throw new Exception("查询信息不存在");
		return inInfo;
	}

	@Override
	public ERPProfitDetail getDetailById(String id) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(id))
			throw new Exception("传入信息有误");
		ERPProfitDetail detail = detailMapper.selectByPrimaryKey(id);
		if (detail == null)
			throw new Exception("查询信息不存在");
		return detail;
	}
	@Override
	public void bacthCheck(Byte status, String userId , String userName,String ... id) throws Exception{
		for (int i = 0; i < id.length; i++) {
			ERPProfitInfo erpProfitInfo = getInfoById(id[i]);
			erpProfitInfo.setCheckUser(userId);
			erpProfitInfo.setCheckUserName(userName);
			erpProfitInfo.setCheckTime(new Date());
			erpProfitInfo.setCheckStatus(status);

			try {
				//  调用库存操作存储
				callAbleService.socktOperateLog(erpProfitInfo.getType() == 1 ? SocktOperateType.Operate_10064 : SocktOperateType.Operate_10065 , erpProfitInfo.getOrderId());
			}catch (Exception e){
				logger.error("调用存储错误：" + e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
			this.updateInfo(erpProfitInfo);
		}
	}
	@Override
	public void bacthDelete(String userId , String userName,String ... id) throws Exception{
		ERPProfitInfo ERPProfitInfo = new ERPProfitInfo();
		ERPProfitInfo.setCheckUser(userId);
		ERPProfitInfo.setCheckUserName(userName);
		ERPProfitInfo.setCheckTime(new Date());
		ERPProfitInfo.setIsDelete(true);
		for (int i = 0; i < id.length; i++) {
			ERPProfitInfo.setId(id[i]);
			this.updateInfo(ERPProfitInfo);
		}
	}

	@Override
	public void updateInfo(ERPProfitInfo info) throws Exception{
		inInfoMapper.updateByPrimaryKeySelective(info);
	}

	@Override
	public void updateDetail(ERPProfitDetail detail)throws Exception {
		detailMapper.updateByPrimaryKeySelective(detail);
	}

	@Override
	public List<ERPProfitDetail> getDetailsByOrderId(String orderId) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(orderId))
			throw new Exception("传入信息有误");
		ERPProfitDetailExample example = new ERPProfitDetailExample();
		example.createCriteria().andOrderIdEqualTo(orderId).andIsDeleteEqualTo(false);
		return detailMapper.selectByExample(example);
	}

	@Override
	public Pager<ERPProfitInfo> getInfoListByRo(ERPProfitRo ro) throws Exception {
		List<ERPProfitInfo> list = inInfoMgMapper.getInfoListPageByRo(ro);
		Integer count = inInfoMgMapper.getInfoListPageByRoCOUNT(ro);
		return new Pager<ERPProfitInfo>(count , list);
	}

    @Override
    public ERPProfitVo selectStockListByOrderId(String orderId) throws Exception {
        ERPProfitInfoExample example = new ERPProfitInfoExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<ERPProfitInfo> list = inInfoMapper.selectByExample(example);
        if(list == null || list.size() == 0){
            throw new Exception("无对应数据");
        }
        ERPProfitVo vo = BeanUtil.toObject(ERPProfitVo.class , list.get(0));
        vo.setDetails(getDetailsByOrderId(vo.getOrderId()));
        return vo;
    }

	@Override
	public void addERPProfitInfo(ERPProfitRo ro, String userId, String userName) throws Exception {
		if(ro.getQuantitys() == null || ro.getQuantitys().length == 0)
			throw new Exception("缺少传入参数");
		String orderId;
		ERPProfitInfo info= new ERPProfitInfo();
		ERPProfitDetail detail = new ERPProfitDetail();
		info.setId(ro.getId());
		if(!StringUtil.stringIsNullOrEmpty(info.getId())){
			info = getInfoById(info.getId());
			orderId = info.getOrderId();
			List<ERPProfitDetail> list = getDetailsByOrderId(orderId);
			for (ERPProfitDetail erpProfitDetail : list) {
				boolean isFind = false;
				if(StringUtil.stringIsNullOrEmpty(ro.getIds())){
					erpProfitDetail.setIsDelete(true);
					this.updateDetail(erpProfitDetail);
				}else {
					for (String s : ro.getIds()) {
						if (StringUtil.stringIsNullOrEmpty(s)) {
							continue;
						} else if (s.equals(erpProfitDetail.getId())) {
							isFind = true;
						}
					}
					if(!isFind){
						erpProfitDetail.setIsDelete(true);
						this.updateDetail(erpProfitDetail);
					}

				}
			}
		}else{
			if(ProfitType.IN.getIndex() == ro.getType()) {
				orderId = callAbleService.getStockOrderId(OrderPrefix.ProfitIn);
			}
			else if (ProfitType.OUT.getIndex() == ro.getType()) {
				orderId = callAbleService.getStockOrderId(OrderPrefix.ProfitOut);
			}
			else {
				throw new Exception("单据类型错误");
			}
		}
		detail.setOrderId(orderId);
		detail.setRemark(ro.getRemark());

		info.setSupplierId(ro.getSupplierId());
		info.setSupplierName(ro.getSupplierName());
		info.setOrderId(orderId);
		info.setRemark(ro.getRemark());
		info.setType(ro.getType());
		info.setWhId(ro.getWhId());
		ERPWarehouse warehouse = warehouseService.getWarehouseById(info.getWhId());
		info.setWhName(warehouse.getName());
		info.setAddUser(userId);
		info.setAddUserName(userName);

		info.setTaskTime(ro.getTaskTime());
		info.setItemPrice(new BigDecimal(0));
		info.setItemQuantity(Short.valueOf("0"));
		for (int i = 0; i < ro.getQuantitys().length; i++) {

			ItemBase itemBase = itemBaseMapper.selectByPrimaryKey(ro.getItemBaseIds()[i]);

			detail.setName(itemBase.getName());
			detail.setAddTime(info.getAddTime());
			detail.setBarCode(itemBase.getMdseId());
			detail.setImg(itemBase.getImgB());
			detail.setItemBaseId(ro.getItemBaseIds()[i]);
			detail.setSpec(itemBase.getSpec());
			detail.setPkg(itemBase.getPkg());
			detail.setId(ro.getIds() == null || ro.getIds().length == 0 ? "" : ro.getIds()[i]);
			detail.setPrice(ro.getPrices() == null || ro.getPrices().length == 0 ? new BigDecimal(0) : ro.getPrices()[i]);

//			ERPManagerItem erpManagerItem = erpManagerItemService.getERPManagerItem(info.getManagerId() , itemBase.getId());
//			detail.setAreaPrice(erpManagerItem.getAreaPrice());
//			detail.setItemCode(erpManagerItem.getItemCode());
//			detail.setPrice(detail.getAreaPrice());

			ERPWarehouse warehouse2,warehouse3;
			if(ro.getWhIds() == null ||ro.getWhIds().length < i || StringUtil.stringIsNullOrEmpty(ro.getWhIds()[i])) {
				warehouse2 = warehouseService.getWarehouseByUpId(info.getWhId(), warehouse.getSupplierId()).get(0);
				warehouse3 = warehouseService.getWarehouseByUpId(warehouse2.getId() , warehouse.getSupplierId()).get(0);
			}else{
				warehouse3 = warehouseService.getWarehouseById(ro.getWhIds()[i]);
				warehouse2 = warehouseService.getWarehouseById(warehouse3.getUpId());
			}
			detail.setWh1Name(info.getWhName());
			detail.setWh2Name(warehouse2.getName());
			detail.setWh3Id(warehouse3.getId());
			detail.setWh3Name(warehouse3.getName());
			detail.setRemark(ro.getRemarks()[i]);
			detail.setQuantity(ro.getQuantitys()[i]);
			detail.setTotalPrice(detail.getPrice().multiply(new BigDecimal(detail.getQuantity())));
			if (StringUtil.stringIsNullOrEmpty(detail.getId())) {
				detail.setId(StringUtil.getUUID());
				detail.setpId(detail.getId());
				this.insertDetail(detail);
			}else {
				detail.setpId(detail.getId());
				this.updateDetail(detail);
			}
			info.setItemPrice(detail.getTotalPrice().add(info.getItemPrice()));
			info.setItemQuantity(Short.valueOf(detail.getQuantity() + info.getItemQuantity() + ""));
		}
		if(StringUtil.stringIsNullOrEmpty(info.getId())) {
			info.setId(StringUtil.getUUID());
			this.insertInfo(info);
		}else{
			this.updateInfo(info);
		}


	}
}
