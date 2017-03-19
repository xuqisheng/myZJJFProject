package com.corner.kefu.service.sp.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemBase;
import com.corner.core.beans.Region;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ItemBaseMapper;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.ItemBaseMgRo;
import com.corner.kefu.beans.ro.ItemBaseRo;
import com.corner.kefu.beans.vo.ItemBaseVo;
import com.corner.kefu.dao.sp.SpItemBaseMgMapper;
import com.corner.kefu.service.sp.SpItemBaseService;

@Service
public class SpItemBaseServiceImpl implements SpItemBaseService {
	@Autowired
	SpItemBaseMgMapper spItemBaseMgMapper;
	@Autowired
	ItemBaseMapper itemBaseMapper; 

	@Override
	public Pager<ItemBaseVo> getAllItemBaseByPatam(ItemBaseRo itemBaseRo) {
		if(!StringUtil.stringIsNullOrEmpty(itemBaseRo.getMdseId())){
			itemBaseRo.setMdseId(itemBaseRo.getMdseId().trim());
		}
		if(!StringUtil.stringIsNullOrEmpty(itemBaseRo.getName())){
			itemBaseRo.setName(itemBaseRo.getName().trim());
		}
		if(!StringUtil.stringIsNullOrEmpty(itemBaseRo.getBrandName())){
			itemBaseRo.setBrandName(itemBaseRo.getBrandName().trim());
		}
		//添加ItemBaseName进行过滤
		if(!StringUtil.stringIsNullOrEmpty(itemBaseRo.getItemBaseName())){
			itemBaseRo.setName(itemBaseRo.getItemBaseName().trim());
		}
		/*itemBaseRo.setStatus((byte)1);*/
		itemBaseRo.setIsDelete(false);
		List<ItemBaseVo> itemBaseList = spItemBaseMgMapper.getAllItemBaseByPatam(itemBaseRo);
		int num = spItemBaseMgMapper.getCountItemBase(itemBaseRo);
		return new Pager<>(num, itemBaseList);
	}


	@Override
	public void addOneItemBase(ItemBaseRo itemBaseRo) {
		spItemBaseMgMapper.addOneItemBase(itemBaseRo);
		
	}


	@Override
	public ItemBaseVo getItemBaseById(Integer id) {
		return spItemBaseMgMapper.getItemBaseById(id);
	}


	@Override
	public List<ItemBaseVo> getLogisticsSpecificationsById(Map<String, Object> map) {
		
		return spItemBaseMgMapper.getLogisticsSpecificationsById(map);
	}


	@Override
	public int okUniqueness(String mdseId) {
		
		return spItemBaseMgMapper.okUniqueness(mdseId);
	}


	@Override
	public void updateItemBaseOneById(ItemBaseRo itemBaseRo) {
		spItemBaseMgMapper.updateItemBaseOneById(itemBaseRo);
		
	}


	@Override
	public int getCountItemBaseById(Map<String, Object> map) {
		
		return spItemBaseMgMapper.getCountItemBaseById(map);
	}


	@Override
	public void deleteItemBase(Integer id) {
		spItemBaseMgMapper.deleteItemBase(id);
	}


	@Override
	public void deleteLogisticsById(Map<String, Object> map) {
		spItemBaseMgMapper.deleteLogisticsById(map);
		
	}


	@Override
	public List<Region> getArea() {
		// TODO Auto-generated method stub
		return null;
	}

	
    /**
     * 
    * Title: getPagingItemBaseList 
    * Description:分页查询基本商品库 
    * @param itemBaseRo
    * @return
    * @throws Exception 
    * @see com.corner.kefu.service.sp.SpItemBaseService#getPagingItemBaseList(com.corner.kefu.beans.ro.ItemBaseRo)
     */
	@Override
	public Pager<ItemBase> getPagingItemBaseList(ItemBaseMgRo itemBaseMgRo) throws Exception {
		List<ItemBase> list = spItemBaseMgMapper.getPagingItemBaseList(itemBaseMgRo);
		Integer totalSize = spItemBaseMgMapper.getCountPagingItemBaseList(itemBaseMgRo);
		return new Pager<>(totalSize, list);
	}


	@Override
	public List<ItemBase> getPaiChuItemBase(Map<String, Object> map) throws Exception {
		String level = (String) map.get("level");
		List<ItemBase> list = null;
		if(1==Integer.parseInt(level)){
			//cateId 为一级分类id
			list = spItemBaseMgMapper.getYijiYixiaItemBase(map);
		}else if (2==Integer.parseInt(level)) {
			//cateId 为二级分类id
			list = spItemBaseMgMapper.getErjiYixiaItemBase(map);
		}else if (3==Integer.parseInt(level)){
			//cateId 为ItemBase id
			list = spItemBaseMgMapper.getTongJiItemBase(map);
		}
		return list;
	}


	@Override
	public void updateUpperLowerRelationshop(Integer id, String mdseId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		// TODO Auto-generated method stub
		map.put("mdseId", mdseId);
		ItemBase itembase = spItemBaseMgMapper.getItemBaseByMdseId(map);
		if(itembase != null){
			ItemBase itemBase = new ItemBase();
			itemBase.setUpdateTime(new Date());
			itemBase.setUpId(itembase.getId());
			itemBase.setTgId(itembase.getTgId());
			itemBase.setId(id);
			itemBaseMapper.updateByPrimaryKeySelective(itemBase);
		}else{
			throw new Exception("条形码不存在");
		}
	}

}
