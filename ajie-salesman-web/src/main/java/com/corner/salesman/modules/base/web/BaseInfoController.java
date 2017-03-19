package com.corner.salesman.modules.base.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.rpc.shop.api.service.RegionService;
import com.corner.rpc.shop.model.Region;
import com.corner.salesman.commons.persistence.Json;

/**
 * 客户管理Controller
 * @author setsail
 * @version 2016-08-05
 */
@Controller
@RequestMapping(value = "${adminPath}/base")
public class BaseInfoController {
	private static final Logger logger = LoggerFactory.getLogger(BaseInfoController.class);

	@Autowired
	private RegionService regionService;
	
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
	public Object queryRegionList(Region region){
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
	
}
