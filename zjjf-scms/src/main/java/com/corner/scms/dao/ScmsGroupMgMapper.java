package com.corner.scms.dao;

import java.util.List;

import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.ScmsGroupMapKey;
import com.corner.scms.beans.ro.sc.ScmsDistributionAreaRo;



public interface ScmsGroupMgMapper {

	List<ScmsGroup> findAllArea();

	List<ScmsGroup> getPageList(ScmsDistributionAreaRo condition);

	int getPageListSize(ScmsDistributionAreaRo condition);

	List<ScmsGroup> findAllAreaByName(String name);


}
