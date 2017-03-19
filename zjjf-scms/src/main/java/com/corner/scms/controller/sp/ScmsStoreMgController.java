package com.corner.scms.controller.sp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ScmsStore;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.ScmsOrderInfoMgCondition;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sp.ScmsStoreMgService;

@Controller
@RequestMapping(value="/scms/storectl")
public class ScmsStoreMgController extends ScmsBaseWebController{

	private static Logger logger = LoggerFactory.getLogger(ScmsStoreMgController.class);

	@Autowired
	ScmsStoreMgService storeMgService;

	/**
	 * 
	* @Title: getListByContact 
	* @Description: 获取用户信息，通过名称
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/getListByContact.do")
	@ResponseBody
	public Object getListByContact(HttpServletRequest request, ScmsOrderInfoMgCondition command) {
		logger.info("获取用户信息");
		//根据最后操作时间进行排序，显示时间最靠前的5条数据
		ScmsStore scmsStore = new ScmsStore();
		Supplier supplier = getCurrentUser(Supplier.class, request);
		
		scmsStore.setSpId(supplier.getId());
		scmsStore.setName(command.getContact());  	//改为用店铺名搜索
		List<Map<String, Object>> list = storeMgService.getListByContact(scmsStore);
		if(list != null && list.size()>0){
			return ResponseUtils.sendMsg(true, list);
		}
		return ResponseUtils.sendMsg(false, null);
	}

	/**
	 * 
	* @Title: inset 
	* @Description: 插入一条新的用户信息 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/insert.do")
	@ResponseBody
	public Object inset(HttpServletRequest request, ScmsOrderInfoMgCondition command) {
		logger.info("店主(收货人)姓名为：{}", command.getConsignee());
		logger.info("店主(收货人)手机号为：{}", command.getMobile());
		logger.info("店主(收货人)地址为：{}", command.getAddress());
		if(command == null || StringUtil.stringIsNullOrEmpty(command.getConsignee(),command.getMobile(),command.getAddress(),command.getStoreType(),command.getContact())){
			return ResponseUtils.sendMsg(false,"确少必要参数");
		}
		
		Supplier supplier = getCurrentUser(Supplier.class, request);
		ScmsStore scmsStore = new ScmsStore();
		scmsStore.setAddTime(new Date());
		scmsStore.setStatus((byte) 1);
		scmsStore.setAddress(command.getAddress());
		scmsStore.setMobile(command.getMobile());
		scmsStore.setContact(command.getConsignee());
		scmsStore.setName(command.getStoreName());
		scmsStore.setSuType(new Byte(command.getStoreType()));
		scmsStore.setSpId(supplier.getId());
		ModelMsg msg = storeMgService.insert(scmsStore);
		if(msg != null && msg.isSuccess()){
			return ResponseUtils.sendMsg(true,msg.getData());				
		}else{				
			return ResponseUtils.sendMsg(false,msg.getMessage());				
		}
	}
	
	/**
	 * 
	* @Title: getItemBaseListByName 
	* @Description: 获取商品信息，通过名称 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/getItemBaseListByName.do")
	@ResponseBody
	public Object getItemBaseListByName(HttpServletRequest request, ScmsOrderInfoMgCondition command) {
		//根据最后操作时间进行排序，显示时间最靠前的5条数据
		
		Supplier supplier = getCurrentUser(Supplier.class, request);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", command.getName());
		map.put("spId", supplier.getId());
		map.put("storeType", command.getStoreType());
		List<Map<String, Object>> list = storeMgService.getItemBaseListByName(map);
		if(list != null && list.size()>0){
			return ResponseUtils.sendMsg(true, list);
		}
		return ResponseUtils.sendMsg(false, null);
	}
	
	@RequestMapping(value = "/addScmsSpSalePrice.do")
	@ResponseBody
	public Object addScmsSpSalePrice(HttpServletRequest request, ScmsOrderInfoMgCondition command) {
		//根据最后操作时间进行排序，显示时间最靠前的5条数据
		
		Supplier supplier = getCurrentUser(Supplier.class, request);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", command.getName());
		map.put("spId", supplier.getId());
		map.put("storeType", command.getStoreType());
		List<Map<String, Object>> list = storeMgService.getItemBaseListByName(map);
		if(list != null && list.size()>0){
			return ResponseUtils.sendMsg(true, list);
		}
		return ResponseUtils.sendMsg(false, null);
	}
	
	@RequestMapping(value = "/updateItemBasePrice.do")
	@ResponseBody
	public Object updateItemBasePrice(HttpServletRequest request, ScmsOrderInfoMgCondition command) {
		//根据最后操作时间进行排序，显示时间最靠前的5条数据
		
		Supplier supplier = getCurrentUser(Supplier.class, request);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Map<String, Object>> list = storeMgService.getItemBaseListByName(map);
		if(list != null && list.size()>0){
			return ResponseUtils.sendMsg(true, list);
		}
		return ResponseUtils.sendMsg(false, null);
	}
}


