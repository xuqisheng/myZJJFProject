package com.zjjf.analysis.services;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.producer.base.IViewData;

@Service(version = "1.0.0")
public abstract class AbstractModelService<T> extends AbstractPermissionService<T> implements IViewData<T> {

	public abstract List<T> getDataList(HashMap<String, Object> paramMap);

	public abstract Integer getDataCount(HashMap<String, Object> paramMap);

	public abstract List<T> getDataExcel(HashMap<String, Object> paramMap);

	public abstract void adaptConvert(T s, HashMap<String, Object> paramMap);

	@Override
	public List<Object[]> getData(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		getPermissionsMap(userName, menuId, paramMap);
		Object[][] authorityArray = getPermissionsKeyList(userName, menuId, paramMap);
		System.out.println(this.getClass() + "getData" + "======paramMap: " + paramMap);
		Object[] titleEn = getOrderTitleEn(authorityArray);
		List<T> dataList = getDataList(paramMap);
		return dataList.stream().map(s -> adaptView(s, titleEn, paramMap)).collect(Collectors.toList());
	}

	@Override
	public Integer getTotalCount(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		getPermissionsMap(userName, menuId, paramMap);
		System.out.println(this.getClass() + "getTotalCount" + "======paramMap: " + paramMap);
		return getDataCount(paramMap);
	}

	@Override
	public List<Object[]> getExcelData(String userName, Integer menuId, HashMap<String, Object> paramMap) {

		getPermissionsMap(userName, menuId, paramMap);
		Object[][] authorityArray = getPermissionsKeyList(userName, menuId, paramMap);
		System.out.println(this.getClass() + "getExcelData" + "======paramMap: " + paramMap);
		Object[] titleEn = getOrderTitleEn(authorityArray);
		List<T> dataList = getDataExcel(paramMap);
		return dataList.stream().map(s -> adaptView(s, titleEn, paramMap)).collect(Collectors.toList());
	}

	@Override
	public Object[] adaptView(T s, Object[] titleEn, HashMap<String, Object> paramMap) {
		
		adaptConvert(s, paramMap);
		return sort_by_viewTitle(s, titleEn);
	}
}
