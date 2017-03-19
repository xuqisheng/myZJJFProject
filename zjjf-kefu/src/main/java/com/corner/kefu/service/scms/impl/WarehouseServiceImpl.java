package com.corner.kefu.service.scms.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsFreightTpl;
import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.beans.WhWallet;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ScmsWarehouseMapper;
import com.corner.core.dao.WhWalletMapper;
import com.corner.kefu.beans.ro.scms.ScmsWarehouseRo;
import com.corner.kefu.beans.vo.ScmsWarehouseVo;
import com.corner.kefu.dao.ScmsFreightTplMgMapper;
import com.corner.kefu.dao.ScmsGroupMgMapper;
import com.corner.kefu.dao.ScmsWarehouseMgMapper;
import com.corner.kefu.service.scms.WarehouseService;



@Service
public class WarehouseServiceImpl implements WarehouseService{
	
	@Autowired
	private ScmsWarehouseMapper mapper;
	
	@Autowired
	private ScmsWarehouseMgMapper MgMapper;
	@Autowired
	private ScmsGroupMgMapper scmsGroupMgMapper;
	
	@Autowired
	private ScmsFreightTplMgMapper scmsFreightTplMgMapper;
	@Autowired
	private WhWalletMapper whWalletMapper;

	@Override
	public Pager<ScmsWarehouseVo> findAllOrderInfo(ScmsWarehouseRo condition) {
		
		if(condition.getPageIndex()<0){
			condition.setPageIndex(0);
		}
		
		if(condition.getPageSize()<=0){
			condition.setPageSize(10);
		}
		List<ScmsWarehouseVo> list=this.MgMapper.getPageList(condition);
		int size=this.MgMapper.getPageListSize(condition);
		
		return new Pager<ScmsWarehouseVo>(size,list);
	
	}

	@Override
	public ModelMsg addScmsWarehonse(ScmsWarehouse condition) {
		if(this.mapper.insertSelective(condition) == 0)
			return new ModelMsg(false, "新增仓库失败");
		WhWallet whWallet = new WhWallet();
		whWallet.setId(condition.getId());
		whWallet.setStatus(Byte.valueOf("1"));
		whWallet.setWallet(new BigDecimal("0"));
		whWallet.setWalletAddTime(new Date());
		whWallet.setWalletUpdateTime(new Date());
		whWallet.setIsDelete(false);
		if(whWalletMapper.insertSelective(whWallet) == 0)
			return new ModelMsg(false, "新增钱包失败");
		return new ModelMsg(false, "新增仓库成功");
	}

	@Override
	public Integer checkName(String userName) {
		ScmsWarehouse warehouse=this.MgMapper.checkName(userName);
		if(warehouse!=null){
			return 1;
		}
		return 0;
	}

	@Override
	public Integer deleteObject(String warehouseId) {
		List<ScmsGroup> list=this.scmsGroupMgMapper.findBywarehouseId(warehouseId);
		if(list.size()>0){
			return 3;
		}
		return this.MgMapper.updateObject(warehouseId);
	}

	@Override
	public ScmsWarehouse findWareHouseById(String id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer edit(ScmsWarehouse hoScmsWarehouse) {
		return this.mapper.updateByPrimaryKeySelective(hoScmsWarehouse);
	}

	@Override
	public List<ScmsWarehouse> findWareHouseNoPage() {
		
		return this.MgMapper.findWareHouseNoPage();
	}

	@Override
	public List<ScmsFreightTpl> findTpl() {
		return this.scmsFreightTplMgMapper.findTpl();
	}

	@Override
	public List<ScmsWarehouse> checkhouseCode(String houseCode) {
		List<ScmsWarehouse> wareHouse=this.MgMapper.checkhouseCode(houseCode);
		return wareHouse;
	}

	@Override
	public List<ScmsWarehouse> checkbranderTel(String branderTel) {
		List<ScmsWarehouse> wareHouse=this.MgMapper.checkbranderTel(branderTel);
		return wareHouse;
	}

	
}
