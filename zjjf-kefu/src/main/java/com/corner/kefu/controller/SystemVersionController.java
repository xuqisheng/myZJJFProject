package com.corner.kefu.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.FastDFSUploadUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.beans.ro.SystemVersionRo;
import com.corner.kefu.beans.vo.SystemVersionVo;
import com.corner.kefu.service.SystemVersionService;

@Controller
@RequestMapping("/Kefu/systemVersion")
public class SystemVersionController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(SystemVersionController.class);
	
	@Autowired
	SystemVersionService versionService;
	
	@RequestMapping(value = "/upload.do")
	@ResponseBody
	public String upload(String fileName,HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		String filepath = request.getSession().getServletContext().getContextPath()+"/"+fileName;
		
		System.out.println(filepath);
		
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			for (String iterable_element : map.keySet()) {
				MultipartFile uploadFile = map.get(iterable_element);
				String name = FastDFSUploadUtil.saveFile(uploadFile, null);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put("filename", name);
				list.add(resultMap);
			}
		} catch (Exception e) {
			return "错误";
		}
		model.addAttribute("img", list.get(0));
		return list.get(0).get("filename");
	}
	
	/**
	 * 获取所有版本信息
	* @Title
	* @Description: TODO 
	* @param @param versionRo
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年4月6日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getAllVersionInfo.do")
	public String getAllVersionInfo(SystemVersionRo versionRo,Model model,HttpServletRequest request){
		Date endtime = versionRo.getEndTime();
//		String pageIndex1 = request.getParameter("pageIndex");
//		Integer pageIndex = 1;
//		if(pageIndex1 != null && !("").equals(pageIndex1)){
//			pageIndex = Integer.parseInt(pageIndex1);
//		}
		//去空格
		if(versionRo.getVersionNo() != null && !("").equals(versionRo.getVersionNo())){
			versionRo.setVersionNo(versionRo.getVersionNo().trim());
		}
		//结束时间加一天
		if(versionRo.getEndTime() != null){
			Date endTime = versionRo.getEndTime();
			endTime = DateUtils.addDays(endTime, 1);
			versionRo.setEndTime(endTime);
		}
		try {
			Pager<SystemVersionVo> versionVoList = versionService.getAllVersionInfo(versionRo);
			if(versionVoList != null){
				versionRo.setEndTime(endtime);
				model.addAttribute("versionRo", versionRo);
				model.addAttribute("versionVoList", versionVoList.getList());
				pageUtil(versionRo.getPageIndex(), versionVoList.getTotalSize(), versionRo.getPageSize(), request, model);
			}
			return "/system/app-version";
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
	}
	/**
	 * 根据id获取版本信息
	* @Title
	* @Description: TODO 
	* @param @param id
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年4月6日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getVersionInfoById.do")
	@ResponseBody
	public Object getVersionInfoById(Integer id,Model model,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if(id == null){
			return error("出错了！", model, request);
		}
		try {
			map.put("id", id);
			SystemVersionVo versionVo = versionService.getVersionInfoById(map);
			if(versionVo != null){
				return ResponseUtils.sendMsg(true, versionVo);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
	}
	
	/**
	 * （修改状态）
	* @Title
	* @Description: TODO 
	* @param @param versionRo
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年4月6日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("updateVersionInfoStatusById.do")
	@ResponseBody
	public Object updateVersionInfoStatusById(SystemVersionRo versionRo,Model model,HttpServletRequest request){
		if(versionRo.getId() == null){
			return error("出错了！", model, request);
		}
		try {
			versionService.updateVersionInfoStatusById(versionRo);
			return ResponseUtils.sendMsg(true);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
	/**
	 * 修改版本信息
	* @Title
	* @Description: TODO 
	* @param @param versionRo
	* @param @param model
	* @param @param request
	* @param @return
	* @2016年4月6日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("updateVersionInfoById.do")
	@ResponseBody
	public Object updateVersionInfoById(SystemVersionRo versionRo,Model model,HttpServletRequest request){
		if(versionRo.getId() == null){
			return error("出错了！", model, request);
		}
		try {
			if(versionRo.getFileName()==null || "".equals(versionRo.getFileName())){
				versionRo.setFileName(null);
			}
			versionRo.setUpdateTime(new Date());
			versionService.updateVersionInfoById(versionRo);
			return ResponseUtils.sendMsg(true,"修改成功！");
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false,"修改失败！");
		}
	}
	
	@RequestMapping("addVersionInfo.do")
	@ResponseBody
	public Object addVersionInfo(SystemVersionRo versionRo,Model model,HttpServletRequest request){
		//参数校验
		if(StringUtils.isEmpty(versionRo.getVersionNo()) || 
		StringUtils.isEmpty(versionRo.getIosLowest()) || 
		StringUtils.isEmpty(versionRo.getAndroidLowest()) || 
		StringUtils.isEmpty(versionRo.getPublishTime()) ||
		StringUtils.isEmpty(versionRo.getFileLocation())){
			return ResponseUtils.sendMsg(false,"请完善信息！！");
		}
		try {
			CustomerService user = getCurrentUser(CustomerService.class, request);
			versionRo.setAddTime(new Date());
			versionRo.setUpdateTime(new Date());
			versionRo.setCreatorId(user.getId());
			versionRo.setCreatorName(user.getUserName());
			versionService.addVersionInfo(versionRo);
			return ResponseUtils.sendMsg(true,"添加成功！");
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false,"添加失败！");
		}
	}
}
