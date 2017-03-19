package com.zjjf.analysis.services.store;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.Region;
import com.zjjf.analysis.beans.analysis.base.SpGroup;
import com.zjjf.analysis.beans.analysis.store.StoreSpgroupSaleGoal;
import com.zjjf.analysis.beans.analysis.supplier.Supplier;
import com.zjjf.analysis.beans.vo.ParamMapVo;
import com.zjjf.analysis.mapper.analysis.StoreSpgroupSaleGoalMapper;
import com.zjjf.analysis.mapper.origin.RegionMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.producer.authority.IAuthorityKeyArray;
import com.zjjf.analysis.producer.store.IStoreGoalService;
import com.zjjf.analysis.services.AbstractBaseService;

@Service(version = "1.0.0")
public class StoreGoalServiceImpl extends AbstractBaseService<StoreSpgroupSaleGoal> implements IStoreGoalService{
	
	@Autowired
	private StoreSpgroupSaleGoalMapper storeSpgroupSaleGoalMapper;
	
	@Autowired
	private IAuthorityKeyArray authorityKeyArray;
	
	@Autowired
	private RegionMapper regionMapper;
	
	@Autowired
	private SpGroupMapper spGroupMapper;
	
	@Autowired
	private SupplierMapper supplierMapper;
	
	@Override
	public List<Object[]> query(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		Object[][] keyArray = authorityKeyArray.getAuthorityKeyArray(userName, menuId);
		authorityKeyArray.getAuthorityCityOrArea(paramMap, userName, menuId);
		Object[] titleEn = this.getOrderTitleEn(keyArray);
		//按合作商查询
		List<StoreSpgroupSaleGoal> storeSaleGoalList = storeSpgroupSaleGoalMapper.query(paramMap);
		
		for (StoreSpgroupSaleGoal goal : storeSaleGoalList) {
			ParamMapVo vo = this.queryCnName(goal.getCityId(), goal.getAreaId(), goal.getSpGroupId(), null);
			goal.setCityName(vo.getCityName());
			goal.setAreaName(vo.getAreaName());
			goal.setSpGroupName(vo.getSpGroupName());
		}
		return storeSaleGoalList.stream().map(s -> sort_by_viewTitle(s, titleEn)).collect(Collectors.toList());
	}

	@Override
	public Integer batchUpdate(HashMap<String, Object> paramMap) {
		
		return storeSpgroupSaleGoalMapper.batchUpdate(paramMap);
	}
	
	private ParamMapVo queryCnName(Integer cityId, Integer areaId, Integer spGroupId, String supplierId) {
		ParamMapVo vo = new ParamMapVo();
		Region city = regionMapper.getById(cityId);
		Region area = regionMapper.getById(areaId);
		SpGroup spGroup = spGroupMapper.getById(spGroupId);
		Supplier supplier = supplierMapper.getById(supplierId);
		
		vo.setCityName(city == null ? null : city.getName());
		vo.setAreaName(area == null ? null : area.getName());
		vo.setSpGroupName(spGroup == null ? null : spGroup.getName());
		vo.setSupplierName(supplier == null ? null : supplier.getSupplierName());
		return vo;
	}
}
