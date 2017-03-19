package com.corner.scms.service.sp;

import com.alibaba.druid.filter.AutoLoad;
import com.corner.core.beans.ScmsStore;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.ScmsStoreCondition;
import com.corner.scms.beans.vo.ScmsStoreVo;
import com.corner.scms.beans.vo.StoreVo;
import com.corner.scms.service.BaseService;

/**
 * ClassName: ScStoreService
 * @Description: 客户管理  service
 * @author 海灵子
 * @date 2015年12月6日
 */


public interface ScStoreService extends BaseService{

	Pager<ScmsStoreVo> getAllStoreByCondition(ScmsStoreCondition condition);

	int addScmsStore(ScmsStore scmsStore);

	ScmsStoreVo findById(String id);

	int updateScmsStore(ScmsStore scmsStore);

	int deleteById(Integer id,String supplierId);

	Pager<StoreVo> getAllDownStoreByCondition(ScmsStoreCondition condition);

}
