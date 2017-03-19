package com.corner.salesman.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.corner.core.utils.FastDFSUploadUtil;
import com.corner.rpc.salesman.api.service.SpGroupLineService;
import com.corner.rpc.shop.api.service.RegionService;
import com.corner.rpc.shop.api.service.SpGroupService;
import com.corner.rpc.shop.model.Region;
import com.corner.rpc.shop.model.SpGroup;
import com.corner.salesman.commons.config.Global;
import com.corner.salesman.model.Json;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping(value="/base")
public class BaseInfoController extends BaseAction {
	private static final Logger logger = LoggerFactory.getLogger(BaseInfoController.class);

	@Autowired
	private RegionService regionService;
	@Autowired
	private SpGroupService spGroupService;
	@Autowired
	private SpGroupLineService spgLineService;
	
	/**
	 * 根据pId获取区域信息数据列表
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = {"queryRegionList"})
	public Object queryRegionList(Region region, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Integer pid = region.getpId();
			 if(null == pid){
				json.setSuccess(true);
				return json;
			 }
			
			 List list = regionService.queryRegionList(region);
			 json.setList(list);
			 json.setSuccess(true);
			 json.setMsg("获取区域信息成功！");
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("获取区域信息异常！");
			logger.error("获取区域信息异常{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 根据区域编码获取定格信息列表
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = {"querySpGroupList"})
	public Object querySpGroupList(SpGroup spGroup, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Integer areaId = spGroup.getAreaId();
			 if(null == areaId){
				json.setCode("500");
				json.setSuccess(false);
				json.setMsg("区域编码不能为空！");
				return json;
			 }
			
			 List list = spGroupService.querySpGroupListByAreaId(areaId);
			 json.setList(list);
			 json.setSuccess(true);
			 json.setMsg("获取定格信息列表成功！");
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("获取定格信息列表异常！");
			logger.error("获取定格信息列表异常{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 根据区域编码获取阿街系统定义好的定格信息列表
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = {"getLineSpGroup"})
	public Object getLineSpGroup(String areaId, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 if(StringUtils.isBlank(areaId)){
				json.setCode("500");
				json.setSuccess(false);
				json.setMsg("区域编码不能为空！");
				return json;
			 }
			
			 List list = spgLineService.getSpGroupByAreaList(areaId);
			 json.setList(list);
			 json.setSuccess(true);
			 json.setMsg("获取定格信息列表成功！");
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("获取定格信息列表异常！");
			logger.error("获取定格信息列表异常{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 根据定格编码获取阿街系统定义好的线路信息列表
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = {"getLineBySpGroupList"})
	public Object getLineBySpGroupList(String spGroupId, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 if(StringUtils.isBlank(spGroupId)){
				json.setCode("500");
				json.setSuccess(false);
				json.setMsg("定格编码不能为空！");
				return json;
			 }
			
			 List list = spgLineService.getLineBySpGroupList(spGroupId);
			 json.setList(list);
			 json.setSuccess(true);
			 json.setMsg("获取定格线路列表成功！");
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("获取定格线路列表异常！");
			logger.error("获取定格线路列表异常{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 
	* @Title: upload
	* @Author luke luke@mibodoctor.com
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param request
	* @param @return
	* @param @throws IOException    设定文件
	* @return List<Map<String,String>>    返回类型
	* @throws
	 */
	public List<Map<String,String>> upload(HttpServletRequest request) throws IOException {
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (String iterable_element : map.keySet()) {
			MultipartFile uploadFile = map.get(iterable_element);
			String name = FastDFSUploadUtil.saveFile(uploadFile, null);
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("filename",name);
			//resultMap.put("typeIndex",1);
			//resultMap.put("url",1);
			list.add(resultMap);
		}
		return list;
	}
	
	/**
	 * 根据定格编码获取阿街系统定义好的线路信息列表
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"upload"})
	public Object uploadFile(@RequestParam("file")MultipartFile file,HttpServletRequest request, HttpServletResponse response){
		Json json = new Json();
		try {
			String fileName = null;
			 synchronized(this){
			    fileName = FastDFSUploadUtil.saveFile(file, null);
			 }
			 System.err.println("=============================="+fileName);
			 if(StringUtils.isNotBlank(fileName)){
				 String fileServer= Global.getConfig("fdfs.server.url");
				 json.setData(fileServer + File.separator + fileName);
				 json.setSuccess(true);
				 json.setMsg("图片上传成功！");
			 }else{
				 json.setCode("500");
				 json.setSuccess(false);
				 json.setMsg("图片上传失败！");
			 }
			 
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("获取定格线路列表异常！");
			logger.error("获取定格线路列表异常{}",e.getMessage());
		}
		return json;
	}
	
	@RequestMapping("/toUploadPic")
    public String toUploadPic(HttpServletRequest request,Model model){
		
		return "salesman/uplaodPic";
    }
}
