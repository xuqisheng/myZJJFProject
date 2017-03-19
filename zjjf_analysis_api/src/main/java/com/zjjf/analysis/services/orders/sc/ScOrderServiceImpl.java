package com.zjjf.analysis.services.orders.sc;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.login.AuthInfo;
import com.zjjf.analysis.beans.vo.orders.scorder.ScOrderVo;
import com.zjjf.analysis.mapper.analysis.ScOrderMapper;
import com.zjjf.analysis.producer.authority.IAuthorityLevel;
import com.zjjf.analysis.producer.authority.IAuthoritydata;
import com.zjjf.analysis.producer.authority.IUserService;
import com.zjjf.analysis.producer.orders.IScOrdersService;
import com.zjjf.analysis.services.AbstractBaseService;
import com.zjjf.analysis.utils.MapUtils;

@Service(version = "1.0.0")
public class ScOrderServiceImpl extends AbstractBaseService<ScOrderVo> implements IScOrdersService {

	@Autowired
	private ScOrderMapper scOrderMapper;

	@Autowired
	private IAuthorityLevel authorityLevel;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAuthoritydata authoritydata;

	@Override
	public List<Object[]> getOrderData(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return null;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		Object[][] authorityArray = authoritydata.getAuthorityFilter(userName, menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		List<ScOrderVo> dataList = scOrderMapper.getOrderData(paramMap);
		return stand_by_title(dataList, getOrderTitleEn(authorityArray));
	}

	@Override
	public Integer getTotalCount(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return null;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		Object[][] authorityArray = authoritydata.getAuthorityFilter(userName, menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		return scOrderMapper.getTotalCount(paramMap);
	}

	@Override
	public List<Object[]> getExcelData(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return null;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		Object[][] authorityArray = authoritydata.getAuthorityFilter(userName, menuId);
		MapUtils.array_2_map(authorityArray, paramMap);
		List<ScOrderVo> dataList = scOrderMapper.getExcelData(paramMap);
		return stand_by_title(dataList, getOrderTitleEn(authorityArray));
	}
	
	@Override
	public HashMap<String, Object> getOrderByOrderId(String orderId){
		
		return scOrderMapper.getOrderByOrderId(orderId);
	}
	@Override
	public HashMap<String, Object> getOrderMummary(String userName, Integer menuId, HashMap<String, Object> paramMap){
		
		AuthInfo authInfo = userService.getAuthInfoMap(userName);
		if (authInfo == null) {
			return null;
		}
		Integer baseRoleId = authInfo.getBaseRoleId();
		Integer baseRoleUserId = authInfo.getBaseRoleUserId();
		Integer level = authInfo.getLevel();
		paramMap.put(level == 2 ? "areaIdList" : "cityIdList", authorityLevel.getDataIdLevel(baseRoleId, baseRoleUserId, level));
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ScOrderVo vo =scOrderMapper.getOrderMummary(paramMap);
		if(vo != null){
			resultMap.put("orderPriceSum", vo.getOrderPriceSum());
			return resultMap;
		}
		resultMap.put("orderPriceSum", "0.00");
		return resultMap;
	}
}
