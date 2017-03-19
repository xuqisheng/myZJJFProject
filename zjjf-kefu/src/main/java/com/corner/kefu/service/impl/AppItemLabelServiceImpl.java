package com.corner.kefu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.AppItemLabel;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.AppItemLabelMapper;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.AppItemLabelRo;
import com.corner.kefu.beans.vo.AppItemLabelVo;
import com.corner.kefu.dao.AppItemLabelMgMapper;
import com.corner.kefu.service.AppItemLabelService;

@Service
public class AppItemLabelServiceImpl implements AppItemLabelService {

	@Autowired
	AppItemLabelMgMapper appItemLabelMgMapper; 
	
	@Autowired
	AppItemLabelMapper appItemLabelMapper; 
	
	
	@Override
	public List<AppItemLabel> getAllTag() {
		return appItemLabelMgMapper.getAllTag();
	}


	@Override
	public Pager<AppItemLabelVo> getAllAppItemLabel(AppItemLabelRo itemLabel) {
		if(!StringUtil.stringIsNullOrEmpty(itemLabel.getName())){
			itemLabel.setName(itemLabel.getName().trim());
		}
		List<AppItemLabelVo> list = appItemLabelMgMapper.getAllAppItemLabel(itemLabel);
		int totalSize = appItemLabelMgMapper.getAllAppItemLabelCount(itemLabel);
		return new Pager<AppItemLabelVo>(totalSize, list);
	}
	
	@Override
	public int delOrReco(Map<String, Object> map){
		return appItemLabelMgMapper.delOrReco(map);
	}


	@Override
	public AppItemLabel getAppItemLabelById(String id) {
		return appItemLabelMapper.selectByPrimaryKey(id);
	}


	@Override
	public void addAppItemLabel(AppItemLabel itemLabel) {
		appItemLabelMapper.insertSelective(itemLabel);
	}


	@Override
	public void updateAppItemLabel(AppItemLabel itemLabel) {
		appItemLabelMapper.updateByPrimaryKeySelective(itemLabel);
	}

}
