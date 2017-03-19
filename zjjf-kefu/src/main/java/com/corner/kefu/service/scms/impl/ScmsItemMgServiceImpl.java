package com.corner.kefu.service.scms.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ItemBase;
import com.corner.core.beans.ScmsGroupMapKey;
import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.ScmsMinimum;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ScmsGroupMapMapper;
import com.corner.core.dao.ScmsGroupMapper;
import com.corner.core.dao.ScmsItemMapper;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.scms.ScmsItemMgRo;
import com.corner.kefu.config.SCMSConstants;
import com.corner.kefu.dao.ItemBaseMgMapper;
import com.corner.kefu.dao.ScmsGroupMgMapper;
import com.corner.kefu.dao.ScmsItemMgMapper;
import com.corner.kefu.service.scms.ScmsItemMgService;
import com.corner.kefu.service.scms.ScmsMinimumMgService;
/**
 * 
* @ClassName: ScmsManagerMgServiceImpl 
* @Description: 商品信息处理 
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2015年12月28日 上午11:45:29 
*
 */
@Service
public class ScmsItemMgServiceImpl implements ScmsItemMgService {

	private static Logger logger = LoggerFactory.getLogger(ScmsItemMgServiceImpl.class);

	@Autowired
	ScmsItemMapper scmsItemMapper;
	
	@Autowired
	ScmsItemMgMapper scmsItemMgMapper;
	@Autowired
	ItemBaseMgMapper itemBaseMgMapper;
	@Autowired
	ScmsMinimumMgService scmsMinimumMgService;
	@Autowired
	ScmsGroupMapMapper scmsGroupMapMapper;
	
	@Override
	public Pager<ScmsItemMgRo> getScmsItemPageList(ScmsItemMgRo command) {
		/**排列顺序按照添加日期进行排序**/
		command.setSortName("s.updateTime");
		command.setSortOrder("desc");
		command.setIsDelete(false);
		List<ScmsItemMgRo> list = scmsItemMgMapper.getPageList(command);
		int size = scmsItemMgMapper.getPageListSize(command);
		return new Pager<ScmsItemMgRo>(size,list);
	}

	
	@Override
	public ModelMsg addScmsItem(ScmsItemMgRo command) {
		try {
			/**判断商品信息是否重复**/
			logger.info("判断商品信息是否重复");
			ScmsItemMgRo scmsItemMgRo = new ScmsItemMgRo();
			scmsItemMgRo.setItemBaseId(command.getItemBaseId());
			scmsItemMgRo.setMonth(command.getMonth());
			scmsItemMgRo.setYear(command.getYear());
			if(command.getGroupIds() == null || command.getGroupIds().length == 0){
				logger.info("区域集合无信息单条验证");
				/**区域集合无信息单条验证**/
				scmsItemMgRo.setGroupId(command.getGroupId());
				int size = scmsItemMgMapper.getPageListSize(scmsItemMgRo);
				if(size != 0)
					return new ModelMsg(false , "商品信息重复");
			}
			else{
				/**遍历区域集合、查找是否有重复记录**/
				logger.info("遍历区域集合、查找是否有重复记录");
				for (int i = 0; i < command.getGroupIds().length; i++) {
					scmsItemMgRo.setGroupId(command.getGroupIds()[i]);
					int size = scmsItemMgMapper.getPageListSize(scmsItemMgRo);
					if(size != 0)
						return new ModelMsg(false , "商品信息重复");
				}
			}
			
			ScmsItem item = new  ScmsItem();
			item.setItemBaseId(command.getItemBaseId());
			item.setAddTime(new Date());
			item.setUpdateTime(new Date());
			item.setStatus(command.getStatus());
			item.setIsDelete(false);
			item.setMonth(command.getMonth());
			item.setYear(command.getYear());
			item.setManagerId(command.getManagerId());
			item.setMark(command.getMark());
			item.setGoodName(command.getGoodName());
			if(command.getGroupIds() == null  || command.getGroupIds().length == 0){
				/**区域集合无信息添加单条**/
				logger.info("区域集合无信息添加单条");
				item.setId(StringUtil.getUUID());
				item.setAreaName(command.getAreaName());
				item.setGroupId(command.getGroupId());
				item.setAreaPrice(command.getAreaPrice());
				item.setZjjfPrice(command.getZjjfPrice());
				item.setMarketPrice(command.getMarketPrice());
				ModelMsg modelMsg = scmsMinimumMgService.insertScmsMinimum(item);
				if(!modelMsg.isSuccess())
					return new ModelMsg(false , modelMsg.getMessage());
				ScmsMinimum minimum = (ScmsMinimum)modelMsg.getData();
				item.setMiniumId(minimum.getId());
				int result = scmsItemMapper.insertSelective(item);
				try {
					ScmsGroupMapKey groupMapKey = new ScmsGroupMapKey();
					groupMapKey.setGroupId(Integer.parseInt(item.getGroupId()));
					groupMapKey.setManagerId(item.getManagerId());
					scmsGroupMapMapper.insert(groupMapKey);
				} catch (Exception e) {
					logger.error("该区域信息已添加！！！");
				}
				
				logger.info("id:"+item.getId() +"，插入数据："+ (result == 0 ? "失败" : "成功"));
			}else{
				/**遍历区域集合、添加纪录**/
				logger.info("遍历区域集合、添加纪录");
				for (int i = 0; i < command.getGroupIds().length; i++) {
					item.setId(StringUtil.getUUID());
					item.setAreaName(command.getAreaNames()[i]);
					item.setGroupId(command.getGroupIds()[i]);
					item.setAreaPrice(new BigDecimal(command.getAreaPrices()[i]));
					item.setZjjfPrice(new BigDecimal(command.getZjjfPrices()[i]));
					item.setMarketPrice(new BigDecimal(command.getMarketPrices()[i]));
					ModelMsg modelMsg = scmsMinimumMgService.insertScmsMinimum(item);
					if(!modelMsg.isSuccess())
						return new ModelMsg(false , modelMsg.getMessage());
					ScmsMinimum minimum = (ScmsMinimum)modelMsg.getData();
					item.setMiniumId(minimum.getId());
					int result = scmsItemMapper.insertSelective(item);
					logger.info("id:"+item.getId() +"，插入数据："+ (result == 0 ? "失败" : "成功"));
					
				}
			}
			return new ModelMsg(true , "信息添加成功");
		} catch (Exception e) {
			logger.error("" , e);
			return new ModelMsg(false , "商品信息添加失败，" + e);
		}
	}


	@Override
	public ModelMsg updateScmsItem(ScmsItemMgRo command) {
		try {
			/**判断商品信息是否重复**/
			if(command.getIds() != null || command.getIds().length == 0){
				String itemId = "";
				for (String id : command.getIds()) {
					if(!"".equals(id)){
						itemId = id;
						break;
					}
				}
				ScmsItem scmsItem= scmsItemMapper.selectByPrimaryKey(itemId);
				if(scmsItem == null)
					return new ModelMsg(false , "商品信息有误");
				if(!scmsItem.getYear().equals(command.getYear()) || !scmsItem.getMonth().equals(command.getMonth()) || !scmsItem.getItemBaseId().equals(command.getItemBaseId())){
					logger.info("判断商品信息是否重复");
					ScmsItemMgRo scmsItemMgRo = new ScmsItemMgRo();
					scmsItemMgRo.setItemBaseId(command.getItemBaseId());
					scmsItemMgRo.setMonth(command.getMonth());
					scmsItemMgRo.setYear(command.getYear());
					if(command.getGroupIds() == null || command.getGroupIds().length == 0){
						logger.info("区域集合无信息单条验证");
						/**区域集合无信息单条验证**/
						scmsItemMgRo.setGroupId(command.getGroupId());
						int size = scmsItemMgMapper.getPageListSize(scmsItemMgRo);
						if(size != 0)
							return new ModelMsg(false , "商品信息重复");
					}
					else{
						/**遍历区域集合、查找是否有重复记录**/
						logger.info("遍历区域集合、查找是否有重复记录");
						for (int i = 0; i < command.getGroupIds().length; i++) {
							scmsItemMgRo.setGroupId(command.getGroupIds()[i]);
							int size = scmsItemMgMapper.getPageListSize(scmsItemMgRo);
							if(size != 0)
								return new ModelMsg(false , "商品信息重复");
						}
					}
				}
			}else{
				ScmsItem scmsItem= scmsItemMapper.selectByPrimaryKey(command.getId());
				if(scmsItem == null)
					return new ModelMsg(false , "商品信息有误");
				if(!scmsItem.getYear().equals(command.getYear()) || !scmsItem.getMonth().equals(command.getMonth()) || !scmsItem.getItemBaseId().equals(command.getItemBaseId())){
					logger.info("判断商品信息是否重复");
					ScmsItemMgRo scmsItemMgRo = new ScmsItemMgRo();
					scmsItemMgRo.setItemBaseId(command.getItemBaseId());
					scmsItemMgRo.setMonth(command.getMonth());
					scmsItemMgRo.setYear(command.getYear());
					if(command.getGroupIds() == null || command.getGroupIds().length == 0){
						logger.info("区域集合无信息单条验证");
						/**区域集合无信息单条验证**/
						scmsItemMgRo.setGroupId(command.getGroupId());
						int size = scmsItemMgMapper.getPageListSize(scmsItemMgRo);
						if(size != 0)
							return new ModelMsg(false , "商品信息重复");
					}
					else{
						/**遍历区域集合、查找是否有重复记录**/
						logger.info("遍历区域集合、查找是否有重复记录");
						for (int i = 0; i < command.getGroupIds().length; i++) {
							scmsItemMgRo.setGroupId(command.getGroupIds()[i]);
							int size = scmsItemMgMapper.getPageListSize(scmsItemMgRo);
							if(size != 0)
								return new ModelMsg(false , "商品信息重复");
						}
					}
				}
			}
			
			ScmsItem item = new  ScmsItem();
			item.setUpdateTime(new Date());
			item.setStatus(command.getStatus());
			item.setManagerId(command.getManagerId());
			item.setMark(command.getMark());
			item.setYear(command.getYear());
			item.setMonth(command.getMonth());
			item.setItemBaseId(command.getItemBaseId());
			item.setIsDelete(false);
			item.setGoodName(command.getGoodName());
			if(command.getIds() == null || command.getIds().length == 0){
				item.setId(command.getId());
				item.setAreaName(command.getAreaName());
				item.setGroupId(command.getGroupId());
				item.setAreaPrice(command.getAreaPrice());
				item.setZjjfPrice(command.getZjjfPrice());
				item.setMarketPrice(command.getMarketPrice());
				
				ModelMsg modelMsg = scmsMinimumMgService.insertScmsMinimum(item);
				if(!modelMsg.isSuccess())
					return new ModelMsg(false , modelMsg.getMessage());
				ScmsMinimum minimum = (ScmsMinimum)modelMsg.getData();
				item.setMiniumId(minimum.getId());
				int result =scmsItemMgMapper.updateStatsByItemId(item);
				logger.info("id:"+item.getId() +"，修改数据："+ (result == 0 ? "失败" : "成功"));
				
			}else{
				for (int i = 0; i < command.getGroupIds().length; i++) {
					item.setAreaName(command.getAreaNames()[i]);
					item.setGroupId(command.getGroupIds()[i]);
					item.setAreaPrice(new BigDecimal(command.getAreaPrices()[i]));
					item.setZjjfPrice(new BigDecimal(command.getZjjfPrices()[i]));
					item.setMarketPrice(new BigDecimal(command.getMarketPrices()[i]));
					int result = 0;
					ModelMsg modelMsg = scmsMinimumMgService.insertScmsMinimum(item);
					if(!modelMsg.isSuccess())
						return new ModelMsg(false , modelMsg.getMessage());
					ScmsMinimum minimum = (ScmsMinimum)modelMsg.getData();
					item.setMiniumId(minimum.getId());
					if("".equals(command.getIds()[i])){
						item.setId(StringUtil.getUUID());
						item.setAddTime(new Date());
						result = scmsItemMapper.insertSelective(item);
					}else{
						item.setId(command.getIds()[i]);
						result = scmsItemMapper.updateByPrimaryKeySelective(item);
					}
					logger.info("id:"+item.getId() +"，修改数据："+ (result == 0 ? "失败" : "成功"));
				}
			}
			return new ModelMsg(true , "信息修改成功");
		} catch (Exception e) {
			return new ModelMsg(false , "商品信息修改失败，" + e);
		}
	}

	@Override
	public List<ScmsItemMgRo> findByItemIdAndMonthAndYear(ScmsItemMgRo command) {
		return scmsItemMgMapper.findByItemIdAndMonthAndYear(command);
	}
	@Override
	public ModelMsg updateScmsItemStatusOrIsDelete(ScmsItemMgRo command) {
		ScmsItem scmsItem = new ScmsItem();
		scmsItem.setIsDelete(command.getIsDelete());
		scmsItem.setStatus(command.getStatus());
		scmsItem.setItemBaseId(command.getItemBaseId());
		scmsItem.setMonth(command.getMonth());
		scmsItem.setYear(command.getYear());
		int result = scmsItemMgMapper.updateStatsByItemId(scmsItem);
		if(result == 0)
			return new ModelMsg(false , "商品信息删除失败");
		else
			return new ModelMsg(true , "商品信息删除成功");
	}


	@Override
	public ModelMsg updateScmsItemById(ScmsItemMgRo command) {
		ScmsItem item = new ScmsItem();
		item.setId(command.getId());
		if(command.getStatus() != null &&!"".equals(command.getStatus())){
			if(command.getStatus() == 0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("scmsItemId", command.getId());
				scmsItemMgMapper.deleteScmsShoppingCareBySpId(map);
			}
			item.setStatus(command.getStatus());
		}else if(command.getIsDelete() != null &&!"".equals(command.getIsDelete())){
			if(command.getIsDelete()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("scmsItemId", command.getId());
				scmsItemMgMapper.deleteScmsShoppingCareBySpId(map);
			}
			item.setIsDelete(command.getIsDelete());
		}else{
			item.setAreaPrice(command.getAreaPrice());
			item.setZjjfPrice(command.getZjjfPrice());
			item.setMarketPrice(command.getMarketPrice());
		}
		int result = scmsItemMapper.updateByPrimaryKeySelective(item);
		
		if(result == 0)
			return new ModelMsg(false , "商品信息添加失败");
		else
			return new ModelMsg(true , "商品信息添加成功");
	}
	@Override
	public ScmsItemMgRo findById(ScmsItemMgRo command) {
		return scmsItemMgMapper.findById(command.getId());
	}
	@Override
	public ModelMsg deleteById(ScmsItemMgRo command) {
		int result = scmsItemMapper.deleteByPrimaryKey(command.getId());
		if(result == 0)
			return new ModelMsg(false , "商品信息删除失败");
		else
			return new ModelMsg(true , "商品信息删除成功");
	}


	@Override
	public ScmsItemMgRo findByMdseIdOrName(ScmsItemMgRo command) {
		return scmsItemMgMapper.findByMdseIdOrName(command);
	}
	@Override
	public ModelMsg getItemBaseList(ScmsItemMgRo command) {
		ItemBase itemBase = new ItemBase();
		itemBase.setMdseId(command.getMdseId());
		itemBase.setName(command.getName());
		List<ItemBase> bases = itemBaseMgMapper.getItemBaseList(itemBase);
		return new ModelMsg(true , "商品信息查询成功",bases);
	}
	@Override
	public ModelMsg findByMdseId(ScmsItemMgRo command) {
		ItemBase itemBase = new ItemBase();
		itemBase.setMdseId(command.getMdseId());
		itemBase = itemBaseMgMapper.getItemBaseByMdseId(itemBase);
		if(itemBase == null || itemBase.getId() == null)
			return new ModelMsg(false ,"无对应的商品信息，请正确录入");
		else if(itemBase.getIsDelete())
			return new ModelMsg(false ,"该商品已删除");
		return new ModelMsg(true , "商品信息查询成功",itemBaseMgMapper.getListByItem(itemBase));
	}

	/*	TODO(用一句话描述这个变量表示什么) 
	* <p>Title: findItemByName</p> 
	* <p>Description: </p> 
	* @param command
	* @return 
	* @see com.corner.kefu.service.scms.ScmsItemMgService#findItemByName(com.corner.kefu.beans.ro.scms.ScmsItemMgRo) 
	*/ 
	@Override
	public ModelMsg findItemByName(Map<String, Object> map) {
		List<Map<String, Object>> list = scmsItemMgMapper.findItemByName(map);
		if(list == null || list.size() == 0){
			return new ModelMsg(false, SCMSConstants.NOT_FOUND);
		}
		return new ModelMsg(true, "" , list);
	}


	/*	TODO(用一句话描述这个变量表示什么) 
	* <p>Title: sele</p> 
	* <p>Description: </p> 
	* @param map
	* @return 
	* @see com.corner.kefu.service.scms.ScmsItemMgService#sele(java.util.Map) 
	*/ 
	@Override
	public ScmsItem selectByPrimaryKey(String id) {
		return scmsItemMapper.selectByPrimaryKey(id);
	}
}
