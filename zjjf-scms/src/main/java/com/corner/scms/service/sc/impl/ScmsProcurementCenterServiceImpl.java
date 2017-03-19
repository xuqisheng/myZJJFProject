package com.corner.scms.service.sc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemCatelog;
import com.corner.core.beans.ScmsShoppingCart;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ItemCatelogMapper;
import com.corner.core.dao.ScmsShoppingCartMapper;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.sc.ScmsProcurementCenterRo;
import com.corner.scms.beans.vo.ScmsItemBaseVo;
import com.corner.scms.dao.ScmsProcurementCenterMgMapper;
import com.corner.scms.dao.ScmsShoppingCartMgMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sc.ScmsProcurementCenterService;


@Service
public class ScmsProcurementCenterServiceImpl extends BaseServiceImpl implements ScmsProcurementCenterService{

	@Autowired
	private ScmsProcurementCenterMgMapper scmsProcurementCenterMgMapper;
	@Autowired
	private ItemCatelogMapper itemCatelogMapper;
	@Autowired
	private ScmsShoppingCartMapper shopMapper;
	@Autowired
	private ScmsShoppingCartMgMapper scmsShoppingCartMgMapper;
	
	@Override
	public List<Map<String, Object>> findAllItems(Integer groupId) {
		List<Map<String, Object>> items = scmsProcurementCenterMgMapper.findAllItems(groupId);
		for (Map<String, Object> map : items) {
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			String[] cateId2 = map.get("cateId2").toString().split(",");
			String[] cateName2 = map.get("cateName2").toString().split(",");
			for (int i = 0; i < cateName2.length; i++) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("cateId2", cateId2[i]);
				map2.put("cateName2", cateName2[i]);
				list.add(map2);
			}
			map.put("cate2", list);
		}
		return items;
	}


	@Override
	public List<ItemCatelog> findSonItem(Integer id) {
		return this.scmsProcurementCenterMgMapper.findSonItem(id);
	}


	@Override
	public Pager<ScmsItemBaseVo> findItemBase(ScmsProcurementCenterRo condition) {
		//所在区域的id
		List<ScmsItemBaseVo> list=this.scmsProcurementCenterMgMapper.getPageList(condition);
		int size=this.scmsProcurementCenterMgMapper.getPageListSize(condition);
		return new Pager<ScmsItemBaseVo>(size, list);
	}
	
	@Override
	public List<ScmsItemBaseVo> findItemBase1(ScmsProcurementCenterRo condition) {
		//所在区域的id
		return scmsProcurementCenterMgMapper.getPageList(condition);
	}


	@Override
	public ModelMsg addShop(ScmsShoppingCart cart) {
		try {
			if(cart.getNum() == 0)
				return new ModelMsg(false, "添加失败,缺少必要参数");
			else if (StringUtil.stringIsNullOrEmpty(cart.getSupplierId() , cart.getScmsItemId()))
				return new ModelMsg(false, "添加失败,缺少必要参数");
			else if (cart.getNum() > 9999)
				return new ModelMsg(false, "添加失败,购买数据最大是9999");
			int result = scmsShoppingCartMgMapper.save(cart);
			if(result == 0)
				return new ModelMsg(false, "添加失败");
			return new ModelMsg(true, "添加成功");
		} catch (Exception e) {
			return new ModelMsg(false, "添加失败");
		}
	}


	@Override
	public ScmsItemBaseVo findItemVoById(String id) {
		return this.scmsProcurementCenterMgMapper.findItemVoById(id);
	}


	@Override
	public int selectCount(ScmsShoppingCart cart) {
		return this.shopMapper.selectCount(cart);
	}

	@Override
	public ItemCatelog selectByPrimaryKey(Integer cateID1) {
		return this.itemCatelogMapper.selectByPrimaryKey(cateID1);
	}
	
}
