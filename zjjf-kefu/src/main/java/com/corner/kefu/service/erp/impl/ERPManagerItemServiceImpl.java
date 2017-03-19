/**   
* @Title: ERPManagerItemServiceImpl.java 
* @Package com.corner.kefu.service.erp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月7日 下午2:32:42 
* @version V1.0   
*/

package com.corner.kefu.service.erp.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ERPManagerItem;
import com.corner.core.beans.ItemBase;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ERPManagerItemMapper;
import com.corner.kefu.beans.ro.erp.ManagerItemRo;
import com.corner.kefu.beans.vo.erp.ERPManagerItemVo;
import com.corner.kefu.beans.vo.erp.ScmsItemBaseVo;
import com.corner.kefu.dao.erp.ERPManagerItemMgMapper;
import com.corner.kefu.service.erp.ERPManagerItemService;

/** 
* @ClassName: ERPManagerItemServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月7日 下午2:32:42 
*  
*/
@Service
public class ERPManagerItemServiceImpl implements ERPManagerItemService {
	
	@Autowired 
	ERPManagerItemMgMapper eRPManagerItemMgMapper;
	@Autowired 
	ERPManagerItemMapper eRPManagerItemMapper;

	@Override
	public Pager<ERPManagerItemVo> getAllManagerItem(ManagerItemRo managerItemRo) {
		List<ERPManagerItemVo> list = eRPManagerItemMgMapper.getAllManagerItem(managerItemRo);
		int num = eRPManagerItemMgMapper.getAllManagerItemCount(managerItemRo);
		return new Pager<ERPManagerItemVo>(num, list);
	}

	@Override
	public ERPManagerItemVo getManagerItemById(String id) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", id);
		return eRPManagerItemMgMapper.getManagerItemById(map);
	}

	@Override
	public ModelMsg addERPManagerItem(String managerId, String id, String[] itemBaseIds, String[] areaPrices,
			String[] taxRates) {
		ManagerItemRo managerItemRo = null;
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
				map.put("supplierId", null);
				map.put("itemBaseId", Integer.parseInt(itemBaseIds[i]));
				int repeat = eRPManagerItemMgMapper.getRepeatItem(map);
				if(repeat>0){
					msg.append("第"+j+"条数据已存在\n");
					continue;
				}else{
					managerItemRo = new ManagerItemRo();
					Integer serialNum = eRPManagerItemMgMapper.getMaxNum();
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
					int num = eRPManagerItemMapper.insertSelective(managerItemRo);
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

	@Override
	public ModelMsg updateERPManagerItem(ManagerItemRo managerItemRo) {
		managerItemRo.setUpdateTime(new Date());
		if(managerItemRo.getAreaPrice()==null){
			managerItemRo.setAreaPrice(new BigDecimal(0));
		}
		int num = eRPManagerItemMapper.updateByPrimaryKeySelective(managerItemRo);
		if(num > 0){
			return new ModelMsg(true,"修改成功");
		}else{
			return new ModelMsg(false,"修改失败");
		}
	}

	@Override
	public boolean deleteERPManagerItem(String id) {
		ERPManagerItem managerItem = new ERPManagerItem();
		managerItem.setId(id);
		managerItem.setIsDelete(true);
		managerItem.setStatus(Byte.parseByte("0"));
		int num = eRPManagerItemMapper.updateByPrimaryKeySelective(managerItem);
		return num>0?true:false;
	}

	@Override
	public ModelMsg getitemByMdseId(String mdseId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("mdseId", mdseId);
		List<ItemBase> itemBaseList = eRPManagerItemMgMapper.getitemByMdseId(map);
		if(itemBaseList !=null && itemBaseList.size()>0){
			return new ModelMsg(true, "", itemBaseList);
		}else{
			return new ModelMsg(false, "获取数据失败");
		}
	}

	@Override
	public ModelMsg getitemByMdseIdx(String mdseId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("mdseId", mdseId);
		ScmsItemBaseVo itemBase = eRPManagerItemMgMapper.getitemByMdseIdx(map);
		if(itemBase !=null ){
			return new ModelMsg(true, "", itemBase);
		}else{
			return new ModelMsg(false, "获取数据失败");
		}
	}

}
