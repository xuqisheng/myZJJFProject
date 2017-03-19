package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.ItemCatelog;
import com.corner.scms.beans.ro.sc.ScmsProcurementCenterRo;
import com.corner.scms.beans.vo.ScmsItemBaseVo;

public interface ScmsProcurementCenterMgMapper {

	List<Map<String, Object>> findAllItems(Integer groupId);

	List<ItemCatelog> findSonItem(Integer id);

	List<ScmsItemBaseVo> getPageList(ScmsProcurementCenterRo condition);

	int getPageListSize(ScmsProcurementCenterRo condition);

	ScmsItemBaseVo findItemVoById(String id);
}
