package com.corner.kefu.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemBase;

@Service
public interface ItemBaseService extends BaseService {
	/**
	 * 
	* @Title: selectByPrimaryKey 
	* @Description: 获取商品信息通过ID
	* @param @param id
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 ，包含wayCode，barCode
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	Map<String, Object> selectByPrimaryKey(Integer id);

	ItemBase selectItemByBrand(String mdseId) throws Exception;

	void updateItemBase(ItemBase itemBase) throws Exception;

	/**
	 * 
	* @Title: getSpVoucherItemBaseList 
	* @Description:优惠劵查询符合条件的itemBase id集合
	* @param @param itemBase
	* @param @return
	* @param @throws Exception
	* @return List<String>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<String> getSpVoucherItemBaseList(ItemBase itemBase) throws Exception;
	
	/**
	 * 查询基础商品库信息
	 * @Title: getItemBaseList
	 * @date 2016年8月24日  下午8:41:56
	 * @author 小武
	 * @param itemBase
	 * @return
	 * @throws Exception
	 */
	List<ItemBase> getItemBaseList(ItemBase itemBase) throws Exception;
	
}
