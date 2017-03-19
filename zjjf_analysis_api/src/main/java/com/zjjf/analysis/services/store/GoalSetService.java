package com.zjjf.analysis.services.store;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.store.GoalSet;
import com.zjjf.analysis.mapper.analysis.GoalSetMapper;
import com.zjjf.analysis.producer.authority.IAuthorityKeyArray;
import com.zjjf.analysis.producer.store.IGoalSetService;
import com.zjjf.analysis.services.AbstractBaseService;

@Service(version="1.0.0")
public class GoalSetService extends AbstractBaseService<GoalSet> implements IGoalSetService{
	
	@Autowired
	private GoalSetMapper goalSetMapper;
	
	@Autowired
	private IAuthorityKeyArray authorityKeyArray;
	
	@Override
	public List<Object[]> getList(String userName, Integer menuId, HashMap<String, Object> paramMap) {
		Object[][] keyArray = authorityKeyArray.getAuthorityKeyArray(userName, menuId);
		authorityKeyArray.getAuthorityCityOrArea(paramMap, userName, menuId);
		Object[] titleEn = this.getOrderTitleEn(keyArray);
		return goalSetMapper.getList(paramMap).stream().map(s -> sort_by_viewTitle(s, titleEn)).collect(Collectors.toList());
	}
	
	@Override
	public int insert(HashMap<String, Object> paramMap) {
		return goalSetMapper.insert(paramMap);
	}

	@Override
	public List<HashMap<String, Object>> getByMap(
			HashMap<String, Object> paramMap) {
		return goalSetMapper.getByMap(paramMap);
	}

	@Override
	public Integer getCount(HashMap<String, Object> paramMap) {
		
		return goalSetMapper.getCount(paramMap);
	}

}
