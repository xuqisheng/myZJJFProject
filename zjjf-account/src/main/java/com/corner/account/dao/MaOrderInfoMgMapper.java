package com.corner.account.dao;

import java.util.List;
import java.util.Map;

import com.corner.account.beans.ro.MaOrderMgCondition;
import com.corner.account.beans.vo.MaOrderInfoMgVo;
import com.corner.core.beans.PlantWalletLog;
import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.WhWalletLog;


public interface MaOrderInfoMgMapper {
	
	/**
	 * 获取仓库订单
	* @Title
	* @Description: TODO 
	* @param @param command
	* @param @return
	* @2016年1月25日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
    public List<MaOrderInfoMgVo> getMaOrderInfoPageList(MaOrderMgCondition order);
    int getMaOrderInfoPageListSize(MaOrderMgCondition order);
    
    /**
	 * 
	* @Title: findOrderDetailList 
	* @Description: 查询商品详情可以通过 
	* @param 
	* @return List<ScOrderDetail>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScOrderDetail> findOrderDetailList(Map<String, Object> map);
	
	Integer updatePlantWalletByLog(PlantWalletLog plantWalletLog);
	Integer updateWhWalletByLog(WhWalletLog whWalletLog);
}
