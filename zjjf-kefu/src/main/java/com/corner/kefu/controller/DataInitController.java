package com.corner.kefu.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.PlantItem;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.service.sp.SpPlantItemService;



/**
 * Handles requests for the application home page.
 */
@Controller
public class DataInitController {
	
	private static final Logger logger = LoggerFactory.getLogger(DataInitController.class);
	
	@Autowired SpPlantItemService plantItemService;

	/**
	 * areaId to spId,
	 * @author luke at  2015年11月2日下午2:04:30
	 * @email tiezhongtang@izjjf.cn
	 * @param Object
	 * @return
	 */
	@RequestMapping(value = "/data/initSpPlantItem.do")
	@ResponseBody
	public Object wxsign(Integer oldSpGroupId,String oldSpId,Integer status,Integer cateId,Integer spGroupId,String spId) {
		if(StringUtils.isEmpty(spId) || StringUtils.isEmpty(oldSpId)
				|| spGroupId==null || oldSpGroupId == null || status==null ){
			return ResponseUtils.sendMsg(false,"请求异常");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("spGroupId", spGroupId);
		map.put("spId", spId);
		map.put("cateId", cateId);//二级分类
		List<PlantItem> listItem=plantItemService.selectPlantItemByCondition(map);
		if(listItem!=null && !listItem.isEmpty()){
			return ResponseUtils.sendMsg(true,"已经初始化");
		}else{
			map.put("spGroupId", oldSpGroupId);
			map.put("spId", oldSpId);
			map.put("status", status);
			map.put("cateId", cateId);
			listItem=plantItemService.selectPlantItemByCondition(map);
		}
		int sum=0;
		if(listItem == null || listItem.isEmpty()){
			return ResponseUtils.sendMsg(false,"没有商品");
		}else{
			for (PlantItem plantItem : listItem) {
				plantItem.setId(StringUtil.getUUID());
				plantItem.setAddTime(new Date());
				plantItem.setUpdateTime(new Date());
				plantItem.setSpGroupId(spGroupId);
				plantItem.setSpId(spId);
				plantItem.setRestrict(0);
				plantItem.setWarehouseId(plantItem.getSpId());
				plantItem.setLogicStockTypeMg(Byte.valueOf("1"));
				sum +=plantItemService.savePlantItem(plantItem);
			}
		}
		return ResponseUtils.sendMsg(true,"共插入"+sum+" 原数据："+listItem.size());
	}

}
