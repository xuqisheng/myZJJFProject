package com.zjjf.analysis.services.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.AnaDictionary;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.mapper.analysis.SupplierIsZjMapper;
import com.zjjf.analysis.mapper.corner.ERPWarehouseMapper;
import com.zjjf.analysis.mapper.origin.ItemCatelogMapper;
import com.zjjf.analysis.producer.authority.IAuthorityLevel;
import com.zjjf.analysis.producer.authority.IUserService;
import com.zjjf.analysis.producer.base.IViewOptionList;
import com.zjjf.analysis.services.base.AnaDictionaryService;
import com.zjjf.analysis.services.base.SpGroupService;

@Service(version = "1.0.0")
public class ViewOptionListService implements IViewOptionList {

	@Autowired
	private SpGroupService spGroupService;

	@Autowired
	private SupplierIsZjMapper supplierIsZjMapper;

	@Autowired
	private ERPWarehouseMapper eRPWarehouseMapper;
	
	@Autowired
	private AnaDictionaryService anaDictionaryService;

	@Autowired
	private IAuthorityLevel authorityLevel;

	@Autowired
	private IUserService userService;

	@Autowired
	private ItemCatelogMapper itemCatelogMapper;
	
	@Override
	public List<List<AnaDictionary>> getOptionList(String userName, Integer type) {

		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return null;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		List<List<AnaDictionary>> optionList = new ArrayList<List<AnaDictionary>>();
		switch (type) {
			case 48:
				List<String> supplierList48 = supplierIsZjMapper.getAllZjSupplier().stream().map(s -> s.getCode()).collect(Collectors.toList());
				optionList.add(0, eRPWarehouseMapper.getWhLevelOne(supplierList48));
				optionList.add(1, anaDictionaryService.getByDictId("erp_manager_status"));
				optionList.add(2, anaDictionaryService.getByDictId("erp_manager_coopway"));
				break;
			case 49:
				List<String> supplierList49 = supplierIsZjMapper.getAllZjSupplier().stream().map(s -> s.getCode()).collect(Collectors.toList());
				optionList.add(0, eRPWarehouseMapper.getWhLevelOne(supplierList49));
				break;
		default:
				if (level == 2) {
					optionList.add(0, getDefaultCityId(baseRoleId, baseRoleUserId));
					optionList.add(1, getDefaultUserLevelData(baseRoleId, baseRoleUserId, 2));
					optionList.add(2, spGroupService.getSpGroupCodeList(baseRoleId, baseRoleUserId, level));
				} else if (level == 1) {
					optionList.add(0, getDefaultUserLevelData(baseRoleId, baseRoleUserId, 1));
					optionList.add(1, new ArrayList<AnaDictionary>());
					optionList.add(2, spGroupService.getSpGroupCodeList(baseRoleId, baseRoleUserId, level));
				}
				if (type == 10) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("level", "1");
					optionList.add(3, itemCatelogMapper.getByClassfiOneList(map));
					map.clear();
					map.put("level", "2");
					optionList.add(4, itemCatelogMapper.getByClassfiTwoList(map));
				} else {
					optionList.add(3, anaDictionaryService.getByDictId("sp_order_supportmetho"));
					optionList.add(4, anaDictionaryService.getByDictId("sp_order_supportStatus"));
					optionList.add(5, anaDictionaryService.getByDictId("sp_order_status"));
				}
			break;
		}
		return optionList;
	}

	@Override
	public List<AnaDictionary> getDefaultCityId(Integer baseRoleId, Integer baseRoleUserId) {

		return authorityLevel.getDefaultCityId(baseRoleId, baseRoleUserId);
	}

	@Override
	public List<AnaDictionary> getDefaultUserLevelData(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		return authorityLevel.getDefaultUserLevelData(baseRoleId, baseRoleUserId, level);
	}

	@Override
	public List<Integer> getDataIdLevel(Integer baseRoleId, Integer baseRoleUserId, Integer level) {

		return authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level);
	}
}
