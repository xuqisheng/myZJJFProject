/**   
* @Title: ScmsItemCatelogMgService.java 
* @Package com.corner.scms.service.sp 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月7日 下午1:57:36 
* @version V1.0   
*/
package com.corner.scms.service.sp;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.PromotionRo;
import com.corner.scms.beans.vo.ScmsItemBaseVo;
import com.corner.scms.beans.vo.SpVoucherActiveVo;
import com.corner.scms.beans.vo.SpVoucherTempVo;
import com.corner.scms.beans.vo.SpvoucherVo;
import com.corner.scms.service.BaseService;

import java.util.List;

/** 
 * @ClassName: StoreGruopService 
 * @Description:
 * @author 海灵子 
 */
public interface PromotionService extends BaseService {

	List<SpVoucherActiveVo> findAllVo(PromotionRo condition);

	List<SpVoucherActiveVo> findmorelist(PromotionRo condition);

	List<ScmsItemBaseVo> findproduct(PromotionRo condition);

	int findCountProduct(PromotionRo condition);

	void addPromotion(PromotionRo condition, SpVoucherActive active);

	void updateActive_supplier(PromotionRo condition);

	SpVoucherActive findActiveById(PromotionRo condition);

	SpVoucherTempVo getSpVoucherTemp(SpVoucherTemp spVoucherTemp);

	SpVoucherTemp findTempById(Integer sendId);

	Double findMoneyByCondition(PromotionRo condition);

	Double findMoneyJByCondition(PromotionRo condition);

	int findCountPeople(PromotionRo condition);

	int findCountJPeople(PromotionRo condition);

	Pager<SpvoucherVo> findByCondition(PromotionRo condition);

	List<PlantItem> findPlantItem(PromotionRo condition);

	ScmsItemBaseVo findVoById(String id);

	Pager<SpvoucherVo> findByConditionJ(PromotionRo condition);

	List<SpVoucherActive> findActiveByGroup(StoreGroup grop);

	SpVoucherActiveGift findGift(PromotionRo condition);

	int findmorelistSize(PromotionRo condition);

	SpVoucherActiveGift findApVoucherActiveGift(PromotionRo condition);


    SendTimeConfig getSendTimeConfigById(Byte transportTimeType);

	@Override
	ModelMsg deleteObjects(String tableName, String[] array);
}
