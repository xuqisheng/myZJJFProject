package com.corner.kefu.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.core.beans.AppItemLabel;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.AppItemLabelRo;
import com.corner.kefu.beans.vo.AppItemLabelVo;

@Service
public interface AppItemLabelService {
	
	public List<AppItemLabel> getAllTag();

	public Pager<AppItemLabelVo> getAllAppItemLabel(AppItemLabelRo itemLabel);
	
	public int delOrReco(Map<String, Object> map);

	public AppItemLabel getAppItemLabelById(String id);

	public void addAppItemLabel(AppItemLabel itemLabel);

	public void updateAppItemLabel(AppItemLabel itemLabel);
}
