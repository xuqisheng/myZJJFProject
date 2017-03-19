package com.corner.kefu.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.SystemInfo;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.controller.sp.PcHelpController;
import com.corner.kefu.service.SystemInfoService;

/**
 * 系统信息控制器
 * @author Administrator2015年6月15日
 * @Email  823882651@qq.com
 * @Desc
 */
@RequestMapping(value = "Pc/SystemInfo")
@Controller
public class PcSystemInfoController extends KefuBaseWebController{
	private static final Logger logger = LoggerFactory.getLogger(PcHelpController.class);
	@Autowired
	SystemInfoService systemInfoService;
	/*@Autowired
	HelpService helpService;
	@Autowired
	AdvertisementService advertisementService;
	@Autowired
	SpHelpService spHelpService;*/

	/**
	 * APP帮助页面
	 * @author longwu at  2015年12月2日下午3:50:34
	 * @email tiezhongtang@izjjf.cn
	 * @param Object
	 * @return
	 *//*
	@RequestMapping("/appGetAllHelp.do")
	@ResponseBody
	public Object appGetAllHelp(HttpServletRequest request, Model model){
		try {
			SpHelp spHelp = new SpHelp();
			spHelp.setIsDelete(false);
			List<SpHelpVo> helps= spHelpService.getAllHelpByParameter(spHelp);
			if(helps!=null&&helps.size()>0){
				return ResponseUtils.sendMsg(true, helps);
			}else{
				return ResponseUtils.sendMsg(false);
			}

		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	} 
	
	
	*//**
	 * 跳转到链接页
	 * @param id
	 * @return
	 *//*
	@RequestMapping("/getLinkContent.do")
	@ResponseBody
	public Object getLinkContent(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			if(StringUtil.stringIsNullOrEmpty(id)){
				return ResponseUtils.sendMsg(false, "参数有误");
			}
			AdvertisementVo advertisementVo = advertisementService.getAdvertisementVoById(Integer.parseInt(id));
			return ResponseUtils.sendMsg(true, advertisementVo);
		} catch (Exception e) {
			//logger.error(e.toString());
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}
	
	
	
	*//**
	 * 获取关于阿街
	 * 
	 * @author Dick 2015年3月3日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/AboutAjie.do", produces = "application/json;charset=utf-8")
	public String getAboutAjie(HttpServletRequest request, Model model) {
		SystemInfo info = systemInfoService.getSystemInfo(SystemKeys.Pc_AboutAjie);
		if (info == null) {
			return null;
		}
		model.addAttribute("about", info.getContent());
		return "/Admin/adminAbout";
	}

	*//**
	 * 联系我们
	 * 
	 * @author Dick 2015年3月3日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/ContactUs.do", produces = "application/json;charset=utf-8")
	public String contactUs(HttpServletRequest request, Model model) {
		SystemInfo info = systemInfoService.getSystemInfo(SystemKeys.Pc_ContactUs);
		if (info == null) {
			return null;
		}
		model.addAttribute("contact", info.getContent());
		return "/Admin/adminAbout";
	}

	*//**
	 * 加载帮助信息
	 * 
	 * @author Dick 2015年3月3日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param helpTypeId
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/LoadHelp.do", produces = "application/json;charset=utf-8")
	public String loadHelp(HttpServletRequest request, Model model) {
		List<Help> helps = helpService.getAllHelp(2);
		model.addAttribute("helps", helps);
		model.addAttribute("help", "help");
		return "/Admin/adminAbout";
	}*/
	
	/**
	 * 跟新价格任务的时间
	 * 
	 * @author Dick 2015年3月3日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param helpTypeId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateTaskTime.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object updateTaskTime(HttpServletRequest request,String taskTime,Model model) {
		try {
			//CustomerService service = (CustomerService) request.getSession().getAttribute("service");
			CustomerService service = this.getCurrentUser(CustomerService.class, request);
			if(service == null){
				return ResponseUtils.sendMsg(false, "请先登录后操作");
			}
			SystemInfo systemInfo= new SystemInfo();
			systemInfo.setId("KDB_Price_Task");
			systemInfo.setContent(taskTime);
			if(DateUtil.StringToDateSimple(taskTime) == null){
				return ResponseUtils.sendMsg(false, "日期格式错误");
			}
			int count=systemInfoService.updateSystemInfo(systemInfo);
			if(count ==1 ){
				return ResponseUtils.sendMsg(true, "更新成功");
			}else{
				return ResponseUtils.sendMsg(false, "更新失败");
			}
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, e.toString());
		}
	}
}
