package com.corner.kefu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemBase;
import com.corner.core.dao.ItemBaseMapper;
import com.corner.kefu.dao.ItemBaseMgMapper;
import com.corner.kefu.service.ItemBaseService;

@Service
public class ItemBaseServiceImpl extends BaseServiceImpl implements ItemBaseService {
	@Autowired
	ItemBaseMgMapper itemBaseMgMapper;
	@Autowired
	ItemBaseMapper itemBaseMapper;
	/**	获取商品信息通过ID	 
	* <p>Title: selectByPrimaryKey</p> 
	* <p>Description: </p> 
	* @param id
	* @return Map<String, Object>	返回类型 ，包含wayCode，barCode
	* @see com.corner.kefu.service.ItemBaseService#selectByPrimaryKey(java.lang.Integer) 
	*/ 
	@Override
	public Map<String, Object> selectByPrimaryKey(Integer id) {
		return itemBaseMgMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 
	* Title: selectItemByBrand 
	* Description: 
	* @param mdseId
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.ItemBaseService#selectItemByBrand(java.lang.String)
	 */
	@Override
	public ItemBase selectItemByBrand(String mdseId) throws Exception {
		ItemBase it = itemBaseMgMapper.selectItemByBrand(mdseId);
		return it;
	}

	@Override
	public void updateItemBase(ItemBase itemBase) throws Exception {
		itemBaseMapper.updateByPrimaryKeySelective(itemBase);
	}

	/**
	 * 
	* Title: getSpVoucherItemBaseList 
	* Description: 优惠劵查询符合条件的itemBase id集合
	* @param itemBase
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.ItemBaseService#getSpVoucherItemBaseList(com.corner.core.beans.ItemBase)
	 */
	@Override
	public List<String> getSpVoucherItemBaseList(ItemBase itemBase) throws Exception {
		return itemBaseMgMapper.getSpVoucherItemBaseList(itemBase);
	}

	@Override
	public List<ItemBase> getItemBaseList(ItemBase itemBase) throws Exception {
		return itemBaseMgMapper.getItemBaseList(itemBase);
	}
}
