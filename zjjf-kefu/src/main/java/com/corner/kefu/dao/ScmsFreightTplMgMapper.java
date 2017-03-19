package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.ScmsFreightTpl;
import com.corner.core.beans.ScmsFreightTplMap;

public interface ScmsFreightTplMgMapper {

	List<ScmsFreightTpl> getPageList(ScmsFreightTpl condition);

	int getPageListSize(ScmsFreightTpl condition);

	Integer updateObject(String id);

	List<ScmsFreightTplMap> findAllTplMapByUid(String id);

	void deleteobject(String id);

	List<ScmsFreightTpl> findTpl(); 
	
}