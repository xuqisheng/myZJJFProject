package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemBase;
import com.corner.core.beans.Region;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.ItemBaseMgRo;
import com.corner.kefu.beans.ro.ItemBaseRo;
import com.corner.kefu.beans.vo.ItemBaseVo;

@Service
public interface SpItemBaseService {

	Pager<ItemBaseVo> getAllItemBaseByPatam(ItemBaseRo itemBaseRo);

	void addOneItemBase(ItemBaseRo itemBaseRo);

	ItemBaseVo getItemBaseById(Integer id);

	List<ItemBaseVo> getLogisticsSpecificationsById(Map<String, Object> map);

	int okUniqueness(String mdseId);

	void updateItemBaseOneById(ItemBaseRo itemBaseRo);

	int getCountItemBaseById(Map<String, Object> map);

	void deleteItemBase(Integer id);

	void deleteLogisticsById(Map<String, Object> map);

	List<Region> getArea();

	Pager<ItemBase> getPagingItemBaseList(ItemBaseMgRo itemBaseMgRo) throws Exception;

	List<ItemBase> getPaiChuItemBase(Map<String, Object> map) throws Exception;
	
	void updateUpperLowerRelationshop(Integer id, String mdseId)throws Exception;

}
