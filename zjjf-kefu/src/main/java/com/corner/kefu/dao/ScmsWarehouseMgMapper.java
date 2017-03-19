package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.ScmsWarehouse;
import com.corner.kefu.beans.ro.scms.ScmsWarehouseRo;
import com.corner.kefu.beans.vo.ScmsWarehouseVo;

public interface ScmsWarehouseMgMapper {

	List<ScmsWarehouseVo> getPageList(ScmsWarehouseRo condition);

	int getPageListSize(ScmsWarehouseRo condition);

	ScmsWarehouse checkName(String userName);

	Integer updateObject(String id);

	List<ScmsWarehouse> findWareHouseNoPage();

	List<ScmsWarehouse> findwarehouseBytplId(String id);

	List<ScmsWarehouse> checkhouseCode(String houseCode);

	List<ScmsWarehouse> checkbranderTel(String branderTel); 
	
}