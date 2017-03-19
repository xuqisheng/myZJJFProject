package com.corner.kefu.service.scms;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsFreightTpl;
import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.scms.ScmsWarehouseRo;
import com.corner.kefu.beans.vo.ScmsWarehouseVo;
import com.corner.kefu.service.BaseService;
@Service

public interface WarehouseService extends BaseService{

	Pager<ScmsWarehouseVo> findAllOrderInfo(ScmsWarehouseRo condition);

	ModelMsg addScmsWarehonse(ScmsWarehouse condition);

	Integer checkName(String userName);

	Integer deleteObject(String id);

	ScmsWarehouse findWareHouseById(String id);

	Integer edit(ScmsWarehouse hoScmsWarehouse); 

	List<ScmsWarehouse> findWareHouseNoPage();//不分页

	List<ScmsFreightTpl> findTpl();

	List<ScmsWarehouse> checkhouseCode(String houseCode);

	List<ScmsWarehouse> checkbranderTel(String branderTel);

	

}
