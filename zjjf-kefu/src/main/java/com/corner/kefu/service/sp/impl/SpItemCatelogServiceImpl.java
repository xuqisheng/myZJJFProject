package com.corner.kefu.service.sp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemCatelog;
import com.corner.kefu.dao.sp.SpItemCatelogMgMapper;
import com.corner.kefu.service.sp.SpItemCatelogService;



@Service
public class SpItemCatelogServiceImpl implements SpItemCatelogService {

	@Autowired
	SpItemCatelogMgMapper itemCatelogMgMapper;
	
	@Override
	public List<ItemCatelog> getAllItemCateByPid(Map<String, Object> map){
		return itemCatelogMgMapper.getAllItemCateByPid(map);
	}

	@Override
	public List<ItemCatelog> getSecondCateList() {
		
		return itemCatelogMgMapper.getSecondCateList();
	}
}
