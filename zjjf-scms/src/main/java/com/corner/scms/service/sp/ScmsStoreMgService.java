package com.corner.scms.service.sp;


import java.util.List;
import java.util.Map;

import com.corner.core.beans.ScmsStore;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.scms.beans.ro.ScmsOrderInfoMgCondition;
import com.corner.scms.service.BaseService;

public interface ScmsStoreMgService extends BaseService{

	/**
	 * 
	* @Title: getScmsOrderInfoPageList 
	* @Description: 订单列表查询 
	* @param @return    设定文件
	* @return List<ScmsStore>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<Map<String, Object>> getListByContact(ScmsStore command);
	
	/**
	 * 
	* @Title: getItemBaseListByName 
	* @Description: 获取商品信息，根据店主名字
	* @param @param command
	* @param @return    设定文件
	* @return List<Map<String,Object>>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<Map<String, Object>> getItemBaseListByName(Map<String, Object> command);
	
	/**
	 * 
	* @Title: insert 
	* @Description: 插入一条新的订单信息 
	* @param @param command
	* @param @return    设定文件
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg insert(ScmsStore command);
	
	
}
