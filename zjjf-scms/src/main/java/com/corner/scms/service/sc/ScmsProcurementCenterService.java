package com.corner.scms.service.sc;


import java.util.List;
import java.util.Map;

import com.corner.core.beans.ItemCatelog;
import com.corner.core.beans.ScmsShoppingCart;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.sc.ScmsProcurementCenterRo;
import com.corner.scms.beans.vo.ScmsItemBaseVo;
import com.corner.scms.service.BaseService;

public interface ScmsProcurementCenterService extends BaseService{

	List<Map<String, Object>> findAllItems(Integer groupId);

	List<ItemCatelog> findSonItem(Integer id);

	Pager<ScmsItemBaseVo> findItemBase(ScmsProcurementCenterRo condition);
	
	List<ScmsItemBaseVo> findItemBase1(ScmsProcurementCenterRo condition);

	ModelMsg addShop(ScmsShoppingCart cart);

	ScmsItemBaseVo findItemVoById(String id);

	int selectCount(ScmsShoppingCart cart);

	ItemCatelog selectByPrimaryKey(Integer cateID1);
}
