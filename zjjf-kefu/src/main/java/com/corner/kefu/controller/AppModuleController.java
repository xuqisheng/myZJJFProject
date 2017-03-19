package com.corner.kefu.controller;

import com.alibaba.fastjson.JSON;
import com.corner.core.beans.Adboard;
import com.corner.core.beans.AppItemLabel;
import com.corner.core.beans.AppItemTag;
import com.corner.core.beans.AppModule;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.vo.AppItemTagVo;
import com.corner.kefu.beans.vo.AppModuleVo;
import com.corner.kefu.service.AppItemLabelService;
import com.corner.kefu.service.AppModuleService;
import com.corner.kefu.service.DBAppConfigService;
import com.corner.kefu.service.sp.SpAdbordService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("kefu/appModule")
public class AppModuleController extends KefuBaseWebController{
	private static final Logger logger = LoggerFactory.getLogger(AppModuleController.class);
	
	@Autowired
	AppModuleService appModuleService; 
	
	@Autowired
	AppItemLabelService appItemLabelService;
	
	@Autowired
	SpAdbordService spAdbordService;
	
	@Autowired
	DBAppConfigService dBAppConfigService;
	
	/**
	 * 获取所有模块标签
	* @Title
	* @Description: TODO 
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年7月22日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getAllAppTag.do")
	public String getAllAppTag(HttpServletRequest request,Model model){
		String str = request.getParameter("str");
		if(str!=null && !"".equals(str)){
			model.addAttribute("str", str);
		}
		try {
			List<AppItemTagVo> appTagList = appModuleService.getAllAppTag();
			model.addAttribute("appTagList", appTagList);
			return "/appSetting/tagList";
		} catch (Exception e) {
			logger.error("",e);
			return error("程序出错了", model, request);
		}
		
	}
	
	/**
	 * 返回添加或编辑页面
	* @Title
	* @Description: TODO 
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年7月25日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("returnAddOrEditPage.do")
	public String returnAddOrEditPage(HttpServletRequest request,Model model){
		String action = request.getParameter("action");
		if(action != null && !"".equals(action)){
			//获取所有广告位
			List<Adboard> boardList = spAdbordService.getAllAdboard();
			if(boardList != null && boardList.size() >0){
				model.addAttribute("boardList", boardList);
			}
			try {
				/*String appModuleId = request.getParameter("appModuleId");
				if(appModuleId != null && !"".equals(appModuleId)){
					model.addAttribute("appModuleId", appModuleId);
				}else if(action.equals("2")){
					return error("请求有误,请重试", model, request);
				}*/
				//查出商品标贴
				List<AppItemLabel> labelList = appItemLabelService.getAllTag();
				if(labelList == null || labelList.size() == 0){
					return error("请完善表贴", model, request);
				}else{
					model.addAttribute("labelList", labelList);
				}
				
				/*//获取所有AppItemTag
				List<AppItemTag> tagList = dBAppConfigService.getAllAppItemTag();
				model.addAttribute("tagList", tagList);*/
				
				if(action.equals("2")){
					String id = request.getParameter("id");
					if(id != null && !"".equals(id)){
						//根据id查出数据
						AppItemTag appModuleDetail = appModuleService.getAppModuleDetailById(id);
						if(appModuleDetail != null){
							model.addAttribute("appModuleDetail", appModuleDetail);
						}else{
							return error("数据有误", model, request);
						}
					}else{
						return error("请求有误,请重试", model, request);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return error("程序异常,请联系技术人员", model, request);
			}
			return "/appSetting/tag-edit";
		}else{
			return error("请求有误,请重试", model, request);
		}
		
	}
	
	/**
	 * 添加模块元素
	* @Title
	* @Description: TODO 
	* @param @param moduleDetail
	* @param @return
	* @2016年7月25日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("addModuleDetail.do")
	public Object addModuleDetail(AppItemTag moduleDetail,HttpServletRequest request){
		if(moduleDetail.getId()==null || "".equals(moduleDetail.getId())){
			moduleDetail.setId(StringUtil.getUUID());
		}
		String beginTimeStr = request.getParameter("beginTime");
		String stopTimeStr = request.getParameter("stopTime");
		if(StringUtils.isEmpty(beginTimeStr)||StringUtils.isEmpty(stopTimeStr)){
			return ResponseUtils.sendMsg(false,"请选择时间!");
		}
		moduleDetail.setBeginTime(DateUtil.StringToHHmmssDateSimple(beginTimeStr));
		moduleDetail.setStopTime(DateUtil.StringToHHmmssDateSimple(stopTimeStr));
		try {
			ModelMsg modelMsg = appModuleService.addModuleDetail(moduleDetail);
			if(modelMsg.isSuccess()){
				return ResponseUtils.sendMsg(true, modelMsg.getMessage());
			}else{
				return ResponseUtils.sendMsg(false, modelMsg.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "程序异常,请联系技术人员");
		}
	}
	
	/**
	 * 编辑模块元素
	* @Title
	* @Description: TODO 
	* @param @param moduleDetail
	* @param @return
	* @2016年7月25日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("updateModuleDetail.do")
	public Object updateModuleDetail(AppItemTag moduleDetail,HttpServletRequest request){
		if(moduleDetail.getId()==null || "".equals(moduleDetail.getId())){
			return ResponseUtils.sendMsg(false, "数据有误");
		}
		String beginTimeStr = request.getParameter("beginTime");
		String stopTimeStr = request.getParameter("stopTime");
		if(StringUtils.isEmpty(beginTimeStr)||StringUtils.isEmpty(stopTimeStr)){
			return ResponseUtils.sendMsg(false,"请选择时间!");
		}
		moduleDetail.setBeginTime(DateUtil.StringToHHmmssDateSimple(beginTimeStr));
		moduleDetail.setStopTime(DateUtil.StringToHHmmssDateSimple(stopTimeStr));
		try {
			ModelMsg modelMsg = appModuleService.updateModuleDetail(moduleDetail);
			if(modelMsg.isSuccess()){
				return ResponseUtils.sendMsg(true, modelMsg.getMessage());
			}else{
				return ResponseUtils.sendMsg(false, modelMsg.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "程序异常,请联系技术人员");
		}
	}
	
	@ResponseBody
	@RequestMapping("getAllItemTag.do")
	public Object getAllItemTag(){
		try {
			List<AppItemTag> appItemTagList = appModuleService.getAllItemTag();
			if(appItemTagList != null && appItemTagList.size() >0){
				return ResponseUtils.sendMsg(true, appItemTagList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false);
		}
	}
	
	/**
	 * 获取所有模块
	* @Title
	* @Description: TODO 
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年7月22日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("/getAppModuleList.do")
	public String getAppModuleList(HttpServletRequest request,Model model){
		try {
			List<AppModuleVo> appModuleList = appModuleService.getAppModuleList();
			model.addAttribute("appModuleList", appModuleList);
			return "/appSetting/moduleList";
		} catch (Exception e) {
			e.printStackTrace();
			return error("程序出错了", model, request);
		}
	} 
	
	//
	@RequestMapping("/toAddOrEditPage/{action}")
	public String toAddOrEditPage(@PathVariable Integer action,HttpServletRequest request,Model model){
		if(action != null){
			//获取所有广告位
			List<Adboard> boardList = spAdbordService.getAllAdboard();
			if(boardList != null && boardList.size() >0){
				model.addAttribute("boardList", boardList);
			}
			if(action == 2){
				String id = request.getParameter("id");
				//获取当前模板
				AppModule appModule = appModuleService.getAppModuleById(id);
				if(appModule != null){
					model.addAttribute("appModule", appModule);
					model.addAttribute("jsonModule", JSONUtil.objectToJSONString(appModule));
				}
				
				//获取模板和AppItemTag 关联关系;
				List<Map<String, Object>> tagMapList = appModuleService.getAppModuleTagMapList(appModule);
				if(tagMapList!=null&&tagMapList.size()!=0){
					model.addAttribute("tagMapListJsonStr", JSON.toJSONString(tagMapList));
				}
			}
			
			
			//获取所有AppItemTag
			List<AppItemTag> tagList = dBAppConfigService.getAllAppItemTag();
			model.addAttribute("tagList", tagList);
			
			
			return "/appSetting/module-edit";
		}
		return error("请求有误,请重试", model, request);
	}
	//添加模板
	@RequestMapping("/addModule")
	@ResponseBody
	public Object addModule(AppModule appModule,HttpServletRequest request){
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("appModule", appModule);
			String[] itemTagRadio = request.getParameterValues("itemTagRadio");
			paramMap.put("itemTagRadio", itemTagRadio);
			ModelMsg msg = appModuleService.addModule(paramMap);
			if(msg.isSuccess()){
				return ResponseUtils.sendMsg(true, msg.getMessage());
			}else{
				return ResponseUtils.sendMsg(false, msg.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员");
		}
	}
	//编辑模板
	@RequestMapping("/updateModule/{id}")
	@ResponseBody
	public Object updateModule(@PathVariable String id,AppModule appModule,HttpServletRequest request){
		if(StringUtil.stringIsNullOrEmpty(id)){
			return ResponseUtils.sendMsg(false, "数据有误");
		}
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("appModule", appModule);
			String[] itemTagRadio = request.getParameterValues("itemTagRadio");
			paramMap.put("itemTagRadio", itemTagRadio);
			
			ModelMsg msg = appModuleService.updateModule(paramMap);
			if(msg.isSuccess()){
				return ResponseUtils.sendMsg(true, msg.getMessage());
			}else{
				return ResponseUtils.sendMsg(false, msg.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员");
		}
	}
	
	//删除or恢复
	@RequestMapping("/updateDelOrRecoModule/{id}/{isDelete}")
	@ResponseBody
	public Object updateDelOrRecoModule(@PathVariable String id,@PathVariable Boolean isDelete){
		if(StringUtil.stringIsNullOrEmpty(id) || isDelete == null){
			return ResponseUtils.sendMsg(false, "数据有误");
		}
		try {
			boolean flag = appModuleService.updateDelOrRecoModule(id,isDelete);
			if(flag){
				return ResponseUtils.sendMsg(true, "操作成功");
			}else{
				return ResponseUtils.sendMsg(false, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员");
		}
	}
	
	//删除or恢复
	@RequestMapping("/updateDelOrRecoTag/{id}/{isDelete}")
	@ResponseBody
	public Object updateDelOrRecoTag(@PathVariable String id,@PathVariable Boolean isDelete){
		if(StringUtil.stringIsNullOrEmpty(id) || isDelete == null){
			return ResponseUtils.sendMsg(false, "数据有误");
		}
		try {
			boolean flag = appModuleService.updateDelOrRecoTag(id,isDelete);
			if(flag){
				return ResponseUtils.sendMsg(true, "操作成功");
			}else{
				return ResponseUtils.sendMsg(false, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员");
		}
	}
	
	/**
	 * 进入图片管理
	* @Title
	* @Description: TODO 
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年7月26日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("returnImageUploadPage.do")
	public String returnImageUploadPage(HttpServletRequest request,Model model){
		String str = request.getParameter("str");
		if(str!=null && !"".equals(str)){
			model.addAttribute("str", str);
		}
		//查出原有的图片
		try {
			List<AppItemTag> moduleDetailList = appModuleService.getImage();
			if(moduleDetailList != null && moduleDetailList.size()>0){
				model.addAttribute("moduleDetailList", moduleDetailList);
				return "/appSetting/image";
			}else{
				return error("未设置专区,请设置后重试", model, request);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	/**
	 * 更新图片
	* @Title
	* @Description: TODO 
	* @param @param ids
	* @param @param picUrls
	* @param @return
	* @2016年7月26日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("updateImage.do")
	public Object updateImage(HttpServletRequest request){
		String[] ids = request.getParameterValues("ids");
		String[] picUrls = request.getParameterValues("picUrls");
		if(ids==null || ids.length==0){
			return ResponseUtils.sendMsg(false, "请求有误,请重试");
		}
		try {
			boolean flag = appModuleService.updateImage(ids,picUrls);
			if(flag){
				return ResponseUtils.sendMsg(true, "编辑成功");
			}else{
				return ResponseUtils.sendMsg(false, "编辑失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "程序异常请联系技术人员");
		}
	}
	
}
