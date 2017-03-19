/**   
* @Title: ScmsItemCatelogMgService.java 
* @Package com.corner.scms.service.sp 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月7日 下午1:57:36 
* @version V1.0   
*/
package com.corner.scms.service.sp;

import java.util.List;

import com.corner.core.beans.ItemCatelog;
import com.corner.core.beans.SpVoucherActiveMiddle;
import com.corner.core.beans.StoreGroup;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.PromotionRo;
import com.corner.scms.beans.vo.ScmsStoreVo;
import com.corner.scms.beans.vo.StoreGroupVo;
import com.corner.scms.service.BaseService;

/** 
 * @ClassName: StoreGruopService 
 * @Description:
 * @author 海灵子 
 */
public interface StoreGruopService extends BaseService {


	Pager<StoreGroup> updateAndgetAllStoreGroupByCondition(StoreGroupVo condition);

	List<StoreGroup> findAllGroups(StoreGroupVo condition);

	void updateXianXiagroup(StoreGroupVo condition);

	void updateNumber(String storeGroupID);

	void updateNumberMis(String storeGroupID);

	void deleteObject(StoreGroupVo condition);

	StoreGroup findById(StoreGroupVo condition);

	void addGroup(StoreGroupVo condition);

	void addCustomer(StoreGroupVo condition);

	int findCountNumer(String id);


	List<StoreGroup> findGroupsByconditionalread(PromotionRo condition);

	List<StoreGroup> findGroupsByconditionother(PromotionRo condition);

	List<StoreGroup> findGroupsByconditionalready(PromotionRo condition);

	List<StoreGroup> findGroupsByconditionalreadyno(PromotionRo v);

	SpVoucherActiveMiddle findSpVoucherActiveMiddle(StoreGroupVo condition);

	String findUrl();


}
