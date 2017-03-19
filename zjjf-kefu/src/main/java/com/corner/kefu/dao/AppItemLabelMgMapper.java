package com.corner.kefu.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.AppItemLabel;
import com.corner.kefu.beans.ro.AppItemLabelRo;
import com.corner.kefu.beans.vo.AppItemLabelVo;

public interface AppItemLabelMgMapper {
	
	public List<AppItemLabel> getAllTag();

	public List<AppItemLabelVo> getAllAppItemLabel(AppItemLabelRo itemLabel);

	public int getAllAppItemLabelCount(AppItemLabelRo itemLabel);
	
	public int delOrReco(Map<String, Object> map);
}
