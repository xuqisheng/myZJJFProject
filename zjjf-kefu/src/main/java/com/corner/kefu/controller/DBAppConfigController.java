package com.corner.kefu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.corner.core.beans.AppModule;
import com.corner.core.beans.AppModuleCfgMapKey;
import com.corner.core.beans.AppPayWayCfg;
import com.corner.core.beans.DBAppConfig;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.DBAppConfigRo;
import com.corner.kefu.beans.vo.DBAppConfigVo;
import com.corner.kefu.beans.vo.sp.RegionVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;
import com.corner.kefu.service.AppPayWayCfgService;
import com.corner.kefu.service.DBAppConfigService;
import com.corner.kefu.service.sp.SpRegionService;

@Controller
@RequestMapping(value="/kefu/DBAppConfig")
public class DBAppConfigController extends KefuBaseWebController{
	private static final Logger logger = LoggerFactory.getLogger(DBAppConfigController.class);
	
	@Autowired
	DBAppConfigService dBAppConfigService;
	
	@Autowired
	SpRegionService regionService;
	
	@Autowired
	AppPayWayCfgService appPayWayCfgService;
	
	
	@RequestMapping(value="/toList")
	public String toList(){
		return "/appSetting/schemeList";
	}
	
	//配置方案列表
	@RequestMapping(value="/getAllDBAppConfig")
	@ResponseBody
	public Object getAllDBAppConfig(DBAppConfigRo DBAppConfig,Model model,HttpServletRequest request,Integer pageIndex){
//		if(pageIndex == null){
//			DBAppConfig.setPageIndex(1);
//		}
		try {
			Pager<DBAppConfigVo> page = dBAppConfigService.getAllDBAppConfig(DBAppConfig);
			return page;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/getAllAppConfig")
	@ResponseBody
	public Object getAllAppConfig(){
		try {
			List<DBAppConfigVo> configList = dBAppConfigService.getAllAppConfig();
			if(configList != null && configList.size() >0){
				return ResponseUtils.sendMsg(true, configList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"程序异常，请联系技术人员");
		}
	}
	
	//跳转页面
	@RequestMapping(value="/returnEditPage/{action}")
	public String returnEditPage(@PathVariable("action")Integer action,Model model,HttpServletRequest request){
		//获取省，市，区，定格的集合
		List<RegionVo> regionList = regionService.getAllAreaAndSpGroup();
		if(regionList != null && regionList.size()>0){
			model.addAttribute("regionList", JSONUtil.objectToJSONString(regionList));
		}
		//获取支付方式配置
		List<AppPayWayCfg> payList = appPayWayCfgService.getAllAppPayWayCfg();
		if(payList != null && payList.size() > 0){
			model.addAttribute("payList", payList);
		}
		
		//获取AppItemTag
		/*List<AppItemTag> tagList = dBAppConfigService.getAllAppItemTag();
		model.addAttribute("tagList", tagList);*/
		
		//获取AppModule
		List<AppModule> moduleList = dBAppConfigService.getAllAppModules();
		model.addAttribute("moduleList", moduleList);
		
		if(action == 2){
			String id = request.getParameter("id");
			if(StringUtil.stringIsNullOrEmpty(id)){
				return error("请求有误，请重试", model, request);
			}
			//获取方案
			DBAppConfig appConfig = dBAppConfigService.getDBAppConfigById(Integer.parseInt(id));
			if(appConfig != null){
				model.addAttribute("appConfig", appConfig);
				
				//获取关联的AppModuleCfgMap
				List<AppModuleCfgMapKey> cfgList = dBAppConfigService.getCfgMapByConfigId(appConfig);
				model.addAttribute("cfgListJson", JSON.toJSONString(cfgList));
				//获取定格
				List<SpGroupVo> groupList = dBAppConfigService.getSpGroup(appConfig.getId());
				if(groupList != null && groupList.size() > 0){
					model.addAttribute("groupList", groupList);
				}
			}
			
		}
		return "/appSetting/schemeEdit";
	}
	
	 //modify by 杨开泰 2016/9/16 17:32 去掉定格
	 //添加配置方案
	@RequestMapping(value="/addAppConfig")
	@ResponseBody
	public Object addAppConfig(DBAppConfig appConfig,Integer spGroups[],String itemTagRadio[]){
		
		if(!checkParam(appConfig))
			return ResponseUtils.sendMsg(false, "参数不完整，请重新操作");
		try {
			dBAppConfigService.addAppConfig(appConfig,spGroups,itemTagRadio);
			return ResponseUtils.sendMsg(true, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "添加失败");
		}
	}
	
	//添加标贴
	/*@RequestMapping(value="/addAppConfig")
	@ResponseBody
	public Object addAppConfig(DBAppConfig appConfig,HttpServletRequest request){
		String[] itemTagRadio = request.getParameterValues("itemTagRadio");
		if(itemTagRadio==null||itemTagRadio.length==0){
			return ResponseUtils.sendMsg(false, "请选择AppItemTag!");
		}
		if(!checkParam(appConfig))
			return ResponseUtils.sendMsg(false, "参数不完整，请重新操作");
		try {
			dBAppConfigService.addAppConfig(appConfig,itemTagRadio);
			return ResponseUtils.sendMsg(true, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "添加失败");
		}
	}*/
	

	/**
	 * //编辑标贴
	@RequestMapping(value="/updateAppItemLabel")
	@ResponseBody
	public Object updateAppConfig(DBAppConfig appConfig,Integer spGroups[]){
		if(appConfig.getId() == null)
			return ResponseUtils.sendMsg(false, "请求有误，请重新操作");
		if(!checkParam(appConfig))
			return ResponseUtils.sendMsg(false, "参数不完整，请重新操作");
		try {
			dBAppConfigService.updateAppConfig(appConfig,spGroups);
			return ResponseUtils.sendMsg(true, "编辑成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "编辑失败");
		}
	}*/
	//编辑标贴
	@RequestMapping(value="/updateAppItemLabel")
	@ResponseBody
	public Object updateAppConfig(DBAppConfig appConfig,HttpServletRequest request,Integer spGroups[]){
		String[] itemTagRadio = request.getParameterValues("itemTagRadio");
		if(itemTagRadio==null||itemTagRadio.length==0){
			return ResponseUtils.sendMsg(false, "请选择AppItemTag!");
		}
		if(appConfig.getId() == null)
			return ResponseUtils.sendMsg(false, "请求有误，请重新操作");
		if(!checkParam(appConfig))
			return ResponseUtils.sendMsg(false, "参数不完整，请重新操作");
		try {
			dBAppConfigService.updateAppConfig(appConfig,itemTagRadio,spGroups);
			return ResponseUtils.sendMsg(true, "编辑成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "编辑失败");
		}
	}

	private boolean checkParam(DBAppConfig appConfig) {
		if(StringUtil.stringIsNullOrEmpty(appConfig.getName()) || appConfig.getPayCfgId()==null){
			return false;
		}
		return true;
	}
	
	//删除or恢复
	@ResponseBody
	@RequestMapping(value="/updateDelOrReco/{id}/{isDelete}")
	public Object updateDelOrReco(@PathVariable("id")String id,@PathVariable("isDelete")Boolean isDelete){
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("isDelete", isDelete);
		try {
			dBAppConfigService.delOrReco(map);
			return ResponseUtils.sendMsg(true,"操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"操作失败");
		}
	}
	
	//停用or启用
	@ResponseBody
	@RequestMapping(value="/updateStopOrStart/{id}/{status}")
	public Object updateStopOrStart(@PathVariable("id")String id,@PathVariable("status")Byte status){
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("status", status);
		try {
			dBAppConfigService.delOrReco(map);
			return ResponseUtils.sendMsg(true,"操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"操作失败");
		}
	}
	
	
}
