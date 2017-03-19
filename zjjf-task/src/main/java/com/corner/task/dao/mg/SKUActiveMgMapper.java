package com.corner.task.dao.mg;

import java.util.List;
import java.util.Map;

import com.corner.task.beans.SKUActive;
import com.corner.task.beans.SKUActiveGoodInfo;
import com.corner.task.beans.SKUActivePublishTagMap;
import com.corner.task.beans.vo.SKUActiveGoodInfoVo;
import com.corner.task.beans.vo.SystemInfoVo;

public interface SKUActiveMgMapper {

	List<SKUActive> getAllSKUActive(Map<String,Object> param);

	int getAllSKUActiveCount(SKUActive sKUActive);

	int updateSKUActiveStatus(Map<String,Object> map);
	
	List<SKUActiveGoodInfoVo> getSKUActiveGoodInfoByMap(Map<String,Object> param);
	
	List<SKUActiveGoodInfo> selectSKUActiveGoodInfoByActiveId(Map<String,Object> param);
	
	int deleteSKUActiveGoodInfoById(Map<String,Object> param);
	
	int effecSKUActive4PlantItem(Map<String,Object> param);
	
	int invalidSKUActive4PlantItem(Map<String,Object> param);
	
	List<SKUActivePublishTagMap> querySKUActivePublishTagMap(Map<String,Object> param);
	
	int updateSKUActiveGoodInfototalBuyNum(Map<String,Object> param);
	
	
	List<SystemInfoVo> selectSKUActiveTaskAuth(Map<String,Object> param);
	
}
