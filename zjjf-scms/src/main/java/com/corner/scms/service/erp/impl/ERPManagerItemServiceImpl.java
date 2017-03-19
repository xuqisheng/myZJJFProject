package com.corner.scms.service.erp.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.core.beans.ERPManagerItemExample;
import com.corner.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ERPManagerItem;
import com.corner.core.beans.ItemBase;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ERPManagerItemMapper;
import com.corner.scms.beans.ro.erp.ERPManagerItemRo;
import com.corner.scms.beans.vo.ScmsItemBaseVo;
import com.corner.scms.beans.vo.erp.ERPManagerItemVo;
import com.corner.scms.dao.erp.ERPManagerItemMgMapper;
import com.corner.scms.service.erp.ERPManagerItemService;
@Service
public class ERPManagerItemServiceImpl implements ERPManagerItemService {
	@Autowired ERPManagerItemMgMapper managerItemMgMapper;
	@Autowired ERPManagerItemMapper managerItemMapper;
	
	/**
	 * 获取批发商下某个供应商的所有商品
	* @Title
	* @Description: TODO 
	* @param @param managerItemRo
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public Pager<ERPManagerItemVo> getAllManagerItem(ERPManagerItemRo managerItemRo) {
		List<ERPManagerItemVo> list = managerItemMgMapper.getAllManagerItem(managerItemRo);
		int num = managerItemMgMapper.getAllManagerItemCount(managerItemRo);
		return new Pager<ERPManagerItemVo>(num, list);
	}

	/**
	 * 添加商品
	* @Title
	* @Description: TODO 
	* @param @param managerId
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public ModelMsg addERPManagerItem(String managerId, String id, String[] itemBaseIds, String[] areaPrices,String[] taxRates) {
		ERPManagerItemRo managerItemRo = null;
		Map<String, Object> map = null;
		StringBuilder msg = new StringBuilder("");
		int j=0;
		for (int i = 0; i < itemBaseIds.length; i++) {
			j=i+1;
			if(itemBaseIds[i] == null || "".equals(itemBaseIds[i]) || areaPrices[i] == null || "".equals(areaPrices[i])){
				msg.append("第"+j+"条数据有误\n");
				continue;
			}else{
				//查询看有没有重复的商品
				map=new HashMap<String, Object>();
				map.put("managerId", managerId);
				map.put("supplierId", id);
				map.put("itemBaseId", Integer.parseInt(itemBaseIds[i]));
				int repeat = managerItemMgMapper.getRepeatItem(map);
				if(repeat>0){
					msg.append("第"+j+"条数据已存在\n");
					continue;
				}else{
					managerItemRo = new ERPManagerItemRo();
					Integer serialNum = managerItemMgMapper.getMaxNum();
					int num1 = 0;
					if(serialNum != null){
						num1=serialNum.intValue()+1;
					}
					String itemCode = String.format("%08d", num1);
					managerItemRo.setSupplierId(id);
					managerItemRo.setManagerId(managerId);
					managerItemRo.setItemBaseId(Integer.parseInt(itemBaseIds[i]));
					managerItemRo.setAreaPrice(new BigDecimal(areaPrices[i]));
					if(taxRates != null && taxRates.length>0){
						if(taxRates[i] != null && !"".equals(taxRates[i])){
							managerItemRo.setTaxRate(new BigDecimal(taxRates[i]));
						}else{
							managerItemRo.setTaxRate(new BigDecimal(0));
						}
						
					}
					managerItemRo.setItemCode(itemCode);
					managerItemRo.setSerialNum(num1);
					managerItemRo.setAddTime(new Date());
					managerItemRo.setUpdateTime(new Date());
					int num = managerItemMapper.insertSelective(managerItemRo);
					if(num>0){
						msg.append("第"+j+"条数据添加成功\n");
					}else{
						msg.append("第"+j+"条数据添加失败\n");
					}
				}
			}
		}
		return new ModelMsg(true, msg.toString());
	}

	/**
	 * 修改商品
	* @Title
	* @Description: TODO 
	* @param @param managerItemRo
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public ModelMsg updateERPManagerItem(ERPManagerItemRo managerItemRo) {
		// TODO Auto-generated method stub
		managerItemRo.setUpdateTime(new Date());
		if(managerItemRo.getAreaPrice()==null){
			managerItemRo.setAreaPrice(new BigDecimal(0));
		}
		int num = managerItemMapper.updateByPrimaryKeySelective(managerItemRo);
		if(num > 0){
			return new ModelMsg(true,"修改成功");
		}else{
			return new ModelMsg(false,"修改失败");
		}
	}

	/**
	 * 删除商品
	* @Title
	* @Description: TODO 
	* @param @param id
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public boolean deleteERPManagerItem(String id) {
		// TODO Auto-generated method stub
		ERPManagerItem managerItem = new ERPManagerItem();
		managerItem.setId(id);
		managerItem.setIsDelete(true);
		managerItem.setStatus(Byte.parseByte("0"));
		int num = managerItemMapper.updateByPrimaryKeySelective(managerItem);
		return num>0?true:false;
	}

	/**
	 * 根据商品条形码收索商品
	* @Title
	* @Description: TODO 
	* @param @param mdseId
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年7月2日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public ModelMsg getitemByMdseId(String mdseId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("mdseId", mdseId);
		List<ItemBase> itemBaseList = managerItemMgMapper.getitemByMdseId(map);
		if(itemBaseList !=null && itemBaseList.size()>0){
			return new ModelMsg(true, "", itemBaseList);
		}else{
			return new ModelMsg(false, "获取数据失败");
		}
	}

	@Override
	public ModelMsg getitemByMdseIdx(String mdseId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("mdseId", mdseId);
		ScmsItemBaseVo itemBase = managerItemMgMapper.getitemByMdseIdx(map);
		if(itemBase !=null ){
			return new ModelMsg(true, "", itemBase);
		}else{
			return new ModelMsg(false, "获取数据失败");
		}
	}
	
	@Override
	public ERPManagerItemVo getManagerItemById(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", id);
		return managerItemMgMapper.getManagerItemById(map);
	}
	@Override
	public ERPManagerItem getMangerItemById(String id) {
		return managerItemMapper.selectByPrimaryKey(id);
	}


	@Override
	public ERPManagerItemVo getManagerItemByItemCode(String itemCode , String supplierId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("itemCode", itemCode);
		map.put("supplierId" , supplierId);
		return managerItemMgMapper.getManagerItemById(map);
	}

	@Override
	public ERPManagerItem getERPManagerItem(String mangerId, Integer itemBaseId) throws Exception{
		if (StringUtil.stringIsNullOrEmpty(mangerId))
			throw new Exception("传入信息有误");
		else if (itemBaseId== null || itemBaseId < 1)
			throw new Exception("传入信息有误");
		ERPManagerItemExample example = new ERPManagerItemExample();
		example.createCriteria().andManagerIdEqualTo(mangerId).andItemBaseIdEqualTo(itemBaseId).andIsDeleteEqualTo(false).andStatusEqualTo(Byte.valueOf("1"));
		List<ERPManagerItem> items =  managerItemMapper.selectByExample(example);
		if (items == null || items.size() == 0){
			throw new Exception("查询结果错误");
		}
		return items.get(0);
	}
}
