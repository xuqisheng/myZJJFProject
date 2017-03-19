package com.corner.scms.service.sp.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsSpSalePrice;
import com.corner.core.beans.ScmsStore;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.ScmsSpSalePriceMapper;
import com.corner.core.dao.ScmsStoreMapper;
import com.corner.scms.beans.ro.ScmsOrderInfoMgCondition;
import com.corner.scms.controller.sp.ScmsStoreMgController;
import com.corner.scms.dao.ScmsPlantItemMgMapper;
import com.corner.scms.dao.ScmsStoreMgMapper;
import com.corner.scms.service.sp.ScmsStoreMgService;

/**
 * 
* @ClassName: ScmsStoreMgServiceImpl 
* @Description: 商铺信息 
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2015年12月6日 上午11:26:51 
*
 */
@Service
public class ScmsStoreMgServiceImpl implements ScmsStoreMgService{

	private static Logger logger = LoggerFactory.getLogger(ScmsStoreMgServiceImpl.class);
	@Autowired
	ScmsStoreMapper storeMapper;
	
	@Autowired
	ScmsStoreMgMapper storeMgMapper;
	
	@Autowired
	ScmsPlantItemMgMapper scmsPlantItemMgMapper;
	@Override
	public ModelMsg deleteObjects(String tableName, String[] array) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getListByContact(ScmsStore command) {
		List<Map<String , Object>> list = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : storeMgMapper.getListByContact(command)) {
			map.put("nameAddress", map.get("name") +"--"+  map.get("address"));
			list.add(map);
		}
		return list;
	}

	@Override
	public ModelMsg insert(ScmsStore command) {
		int result = storeMapper.insertSelective(command);
		if(result == 1){
			return new ModelMsg(true,"商铺新增成功",storeMgMapper.findBySpidAndMobaile(command));	
		}else{
			return new ModelMsg(false,"商铺新增失败,请联系管理员");	
		}
	}

	@Override
	public List<Map<String, Object>> getItemBaseListByName(Map<String, Object> command) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> itemBases = storeMgMapper.getItemBaseListByName(command);
		for (Map<String, Object> map : itemBases) {
			/**获取商品出货价**/
			logger.error("获取商品出货价");
			command.put("itemBaseId", map.get("id"));
			try {
				ScmsSpSalePrice price = scmsPlantItemMgMapper.selectSalePrice(command);
				if(price != null){
					if(price.getSalePrice() != null){
						map.put("price", price.getSalePrice());
					}
				}
			} catch (Exception e) {
				logger.error("获取商品出货价格失败!");
			}
			list.add(map);
		}
		return list;
	}
}
