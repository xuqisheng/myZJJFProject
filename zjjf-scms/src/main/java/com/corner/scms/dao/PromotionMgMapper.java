package com.corner.scms.dao;

import java.util.List;

import com.corner.core.beans.PlantItem;
import com.corner.core.beans.SpVoucherActive;
import com.corner.core.beans.SpVoucherActiveGift;
import com.corner.core.beans.SpVoucherTemp;
import com.corner.core.beans.Store;
import com.corner.core.beans.StoreGroup;
import com.corner.core.beans.StoreGroupMember;
import com.corner.scms.beans.ro.PromotionRo;
import com.corner.scms.beans.vo.ScmsItemBaseVo;
import com.corner.scms.beans.vo.SpVoucherActiveVo;
import com.corner.scms.beans.vo.SpVoucherTempVo;
import com.corner.scms.beans.vo.SpvoucherVo;


public interface PromotionMgMapper {

	List<SpVoucherActiveVo> findPingTaiActive(PromotionRo condition);

	List<SpVoucherActiveVo> findZiYuanActive(PromotionRo condition);

	List<SpVoucherActiveVo> findAllGroupId(PromotionRo condition);

	List<SpVoucherActiveVo> findmorelist(PromotionRo condition);

	List<ScmsItemBaseVo> findproduct(PromotionRo condition);

	int findCountProduct(PromotionRo condition);

	int findCustomerNumber(PromotionRo condition);

	int findAlreadyExists(PromotionRo condition);

	int update(PromotionRo condition);

	int updateGoodsStock(PromotionRo condition);

	int findCount(Integer id);

	void updateActive_supplier(PromotionRo condition);

	SpVoucherTempVo getSpVoucherTemp(SpVoucherTemp spVoucherTemp);

	Double findMoneyByCondition(PromotionRo condition);

	Double findMoneyJByCondition(PromotionRo condition);

	int findCountPeople(PromotionRo condition);

	int findCountJPeople(PromotionRo condition);

	List<SpvoucherVo> getPageList(PromotionRo condition);

	List<PlantItem> findPlantItem(PromotionRo condition);

	ScmsItemBaseVo findVoById(String id);

	int getPageListSize(PromotionRo condition);

	List<SpvoucherVo> getPageList2(PromotionRo condition);

	int getPageListSize2(PromotionRo condition);

	String findOrderId(PromotionRo condition);

	List<SpVoucherActive> findActiveByGroup(StoreGroup grop);

	SpVoucherActiveGift findGift(PromotionRo condition);

	int findmorelistSize(PromotionRo condition);

	SpVoucherActiveGift findApVoucherActiveGift(PromotionRo condition);

	
	
}