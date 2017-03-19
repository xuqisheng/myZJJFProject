package com.corner.kefu.controller.scms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.scms.ScmsItemMgRo;
import com.corner.kefu.beans.ro.scms.ScmsManagerMgRo;
import com.corner.kefu.config.SCMSConstants;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.scms.ScmsDistributionAreaService;
import com.corner.kefu.service.scms.ScmsGroupItemService;
import com.corner.kefu.service.scms.ScmsItemMgService;
import com.corner.kefu.service.scms.ScmsManagerMgService;

@Controller
@RequestMapping(value="/scms/gorupItem")
public class ScmsGroupItemController extends KefuBaseWebController{
	@Autowired
	ScmsGroupItemService scmsGroupItemService;
	@Autowired
	ScmsItemMgService scmsItemMgService;
	@Autowired
	ScmsDistributionAreaService distributionAreaService;
	@Autowired
	ScmsManagerMgService scmsManagerMgService;
	private static Logger logger = LoggerFactory.getLogger(ScmsGroupItemController.class);
	
	@RequestMapping(value = "/toIndex.do")
	public String toGoodsIndex(HttpServletRequest request, ScmsItemMgRo command){
		if("edit".equals(command.getIsUpdate())){
			if(StringUtil.stringIsNullOrEmpty(command.getGroupId())){
				return "error";
			}
			ScmsGroup group = distributionAreaService.findById(command.getGroupId());
			ScmsGroup group2 = distributionAreaService.findById(group.getUpId().toString());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", group.getId());
			map.put("code", group.getCode());
			map.put("name", group.getName());
			if(group2 != null)
				map.put("name2", group2.getName());
			else
				map.put("name2", "全部");
			request.setAttribute("group", map);
			return SCMSConstants.DEALER_GROUP_MANAGE_GOODS_SP;
		}else{
			return SCMSConstants.DEALER_GROUP_MANAGE;
		}
	}
	/**
	 * 
	* @Title: listPage 
	* @Description: 分页查询商品信息 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/getGorupItemlistPage.do")
	public String getGorupItemlistPage(HttpServletRequest request, ScmsItemMgRo command, Model model) {
		logger.info("列表查询开始："+JSONUtil.objectToJSONString(command));
		Map<String, Object> map = new  HashMap<String, Object>();
		map.put("pageIndex", command.getPageIndex());
		map.put("pageSize", command.getPageSize());
		map.put("name", command.getAreaName());
		/**排序**/
		map.put("sortOrder", "desc");
		map.put("sortName", "sg.id");
		
		Pager<Map<String, Object>> pager = scmsGroupItemService.getGorupItemlistPage(map);
		request.setAttribute("list", pager.getList());
		request.setAttribute("size", pager.getTotalSize());
		request.setAttribute("areaName", command.getAreaName());
		this.pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
		return SCMSConstants.DEALER_GROUP_MANAGE;
	}
	/**
	 * 
	* @Title: listPage 
	* @Description: 分页查询商品信息 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/listPage.do")
	@ResponseBody
	public Object listPage(HttpServletRequest request, ScmsItemMgRo command) {
		logger.info("列表查询开始："+JSONUtil.objectToJSONString(command));
		Pager<ScmsItemMgRo> pager = scmsItemMgService.getScmsItemPageList(command);
		return ResponseUtils.sendPagination(pager);
	}
	/**
	 * 
	 * @Title: getSupplierPageList 
	 * @Description: 经销区域添加删除批发商列表
	 * @param @param request
	 * @param @param command
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/getSupplierPageList.do")
	@ResponseBody
	public Object getSupplierPageList(HttpServletRequest request, ScmsManagerMgRo command) {
		logger.info("经销区域ID：{"+command.getBsCircleId()+"} ，查询条件：{"+command.getSupplierCode()+"}");
		Pager<Supplier> pager = scmsManagerMgService.getSupplierPageList(command);
		return ResponseUtils.sendPagination(pager);
	}
}

