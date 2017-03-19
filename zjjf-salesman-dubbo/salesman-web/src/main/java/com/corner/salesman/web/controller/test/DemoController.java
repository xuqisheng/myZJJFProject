package com.corner.salesman.web.controller.test;

import com.alibaba.fastjson.JSON;
import com.corner.rpc.salesman.api.service.SignTimeRecordService;
import com.corner.rpc.salesman.model.SignTimeRecord;
import com.corner.rpc.shop.api.service.RegionService;
import com.corner.rpc.shop.model.Region;
import com.corner.salesman.commons.utils.mail.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/** 
 * 类名称：DemoController
 * 创建人：zs 
 * 创建时间：2016-02-22
 */
@Controller
@RequestMapping(value="/demo")
public class DemoController extends BaseController {
	
	@Autowired
	private SignTimeRecordService signTimeRecordService;
	@Autowired
	private RegionService regionService;
	
	/**
	 * 新增
	 */
	@ResponseBody
	@RequestMapping(value="/queryRegionList")
	public Object queryRegionList(Region region) throws Exception{
		List<Region> list = regionService.queryRegionList(region);
		
		System.err.println(JSON.toJSONString(list.size()));
		return list;
	}
	/**
	 * 新增
	 */
	@RequestMapping(value="/findById")
	public void findById(String id) throws Exception{
		SignTimeRecord record = signTimeRecordService.findSignTimeRecordById(id);
		
		System.err.println(JSON.toJSONString(record));
	}

}
