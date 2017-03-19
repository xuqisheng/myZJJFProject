package com.corner.kefu.controller.sp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.corner.kefu.utils.excel.ExcelProcessException;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.Region;
import com.corner.core.beans.vo.ResponseVo;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.vo.sp.RegionSpGroupVo;
import com.corner.kefu.beans.vo.sp.RegionVo;
import com.corner.kefu.config.SessionConfig;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.sp.SpRegionService;

@Controller
@RequestMapping("/Corner/Region")
public class PcRegionController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(PCApplyStoreController.class);
	
	@Autowired
	SpRegionService regionService;
	
	

    /**
     * @Title: 获取市,区,定格三级联动数据
     * @Description:
     * @param 
     * @return 
     * @throws
     * @author 杨开泰 yangkaitai@izjjf.cn
     * @date 2016/10/18 0018 12:30
     */
    @RequestMapping("/getShiQuDingGeData.do")
	@ResponseBody
	public Object getShiQuDingGeData(){
    	try {
            List<RegionVo> list = regionService.getShiQuDingGeData();
    		return ResponseUtils.sendMsg(true,list);
		}catch (Exception e){
			logger.error("",e);
			return ResponseUtils.sendMsg(false, e);
		}
	}

	
	/**
	 * 
	* @Title: getAllEnabledRegion 
	* @Description:获取所有启用的区域列表,不带查询
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
    @RequestMapping("/getAllEnabledRegion.do")
	@ResponseBody
	public Object getAllEnabledRegion() {
		try {
			List<RegionVo> list = regionService.getAllEnabledRegion();
			//String jsonStr = JSONUtil.objectToJSONString(list);
			return ResponseUtils.sendMsg(true, list);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}
	
	
	
	
	
	
	

	@ResponseBody
	@RequestMapping("getRegionByPidOrRegionLevel.do")
	public Object getShenzhenDistric(Model model,HttpServletRequest request,Integer pId,Integer regionLevel){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("pId", pId);
		try {
			List<Region> regionList = regionService.getRegionByPidOrRegionLevel(map);
			if(regionList != null && regionList.size()>0){
				return ResponseUtils.sendMsg(true,regionList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
	}
	
	//跳转到区域管理页面
	@RequestMapping("returnRegionManager.do")
	public String returnRegionManager(Model model,HttpServletRequest request){
		try {
			//拉取所有组装后的省市区
			List<RegionVo> regionList = regionService.getAllRegion();
			if(regionList != null && regionList.size() > 0){
				model.addAttribute("regionList", JSONUtil.objectToJSONString(regionList));
			}
			
			//获取省，市，区，定格的集合
			List<RegionVo> regionListSPGroup = regionService.getAllAreaAndSpGroup();
			if(regionList != null && regionList.size()>0){
				model.addAttribute("regionListSPGroup", JSONUtil.objectToJSONString(regionListSPGroup));
			}
			
			return "/system/region";
		} catch (Exception e) {
			logger.error("",e);
			return error("", model, request);
		}
	}
	
	@ResponseBody
	@RequestMapping("getRegionById.do")
	public Object getRegionById(Model model,HttpServletRequest request,Integer id){
		try {
			Region region = regionService.getRegionById(id);
			if(region != null ){
				return ResponseUtils.sendMsg(true,region);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
	}

	
	/**
	 * 递归修改区域状态
	* @Title
	* @Description: TODO 
	* @param @param region
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年4月15日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("updateRegionStatus.do")
	public Object updateRegionStatus(Region region,Model model,HttpServletRequest request){
		try {
			regionService.updateRegionStatus(region);
			return ResponseUtils.sendMsg(true);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
	/**
	 * 组装区域和定格
	* @Title
	* @Description: TODO 
	* @param @return
	* @2016年4月15日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("getAllAreaAndSpGroup.do")
	public Object getAllAreaAndSpGroup() {
		// TODO Auto-generated method stub
		try {
			List<RegionVo> regionList = regionService.getAllAreaAndSpGroup();
			if(regionList != null && regionList.size()>0){
				return ResponseUtils.sendMsg(true,regionList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
	/**
	 * 查询区域的定格信息
	 * @Title: getRegionById4SpGroupId
	 * @date 2016年9月7日  上午11:36:08
	 * @author 小武
	 * @version  飓风
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getRegionById4SpGroupId.do")
	public Object getRegionById4SpGroupId(Model model,HttpServletRequest request,Integer id){
		try {
			RegionSpGroupVo region = regionService.getRegionSpGroupVoById(id);
			
			if(region != null ){
				return ResponseUtils.sendMsg(true,region);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
	}
	
	/**
	 * 根据区域id，设置默认的定格信息。
	 * @Title: updateRegionDefaultSpGroupId
	 * @date 2016年9月7日  上午10:56:57
	 * @author 小武
	 * @version  飓风
	 * @param model
	 * @param request
	 * @param id
	 * @param oldSpGroupId
	 * @param newSpGroupId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("setRegionDefaultSpGroupId.do")
	public Object updateRegionDefaultSpGroupId(Model model,HttpServletRequest request,Integer id,Integer spGroupId){
		if(null == id || null == spGroupId ){
			return ResponseUtils.sendMsg(false,"参数异常");
		}
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		try {
			ResponseVo vo = regionService.updateRegionDefaultSpGroupId(id,spGroupId,user.getUserName());
			if(vo.isSuccess()){
				RegionSpGroupVo region = regionService.getRegionSpGroupVoById(id);if(region != null ){
					return ResponseUtils.sendMsg(true,region);
				}else{
					return ResponseUtils.sendMsg(false);
				}
			}
			return vo;
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
	}
	
	@ResponseBody
	@RequestMapping("clearRegionDefaultSpGroupId.do")
	public Object clearRegionDefaultSpGroupId(Model model,HttpServletRequest request,Integer id){
		if(null == id){
			return ResponseUtils.sendMsg(false,"参数异常");
		}
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		try {
			ResponseVo vo = regionService.clearRegionDefaultSpGroupId(id,user.getUserName());
			if(vo.isSuccess()){
				RegionSpGroupVo region = regionService.getRegionSpGroupVoById(id);if(region != null ){
					return ResponseUtils.sendMsg(true,region);
				}else{
					return ResponseUtils.sendMsg(false);
				}
			}
			return vo;
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
	}
	
	
}
