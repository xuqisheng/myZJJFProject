/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.corner.rpc.salesman.api.service.DeptService;
import com.corner.rpc.salesman.model.Dept;
import com.corner.salesman.common.config.Global;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.common.web.BaseController;
import com.corner.salesman.modules.sys.entity.Office;
import com.corner.salesman.modules.sys.entity.User;
import com.corner.salesman.modules.sys.utils.DictUtils;
import com.corner.salesman.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 机构Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dept")
public class DeptController extends BaseController {

	@Autowired
	private DeptService deptService;
	
	
	/**
	 * 获取机构JSON数据。
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(Dept dept, HttpServletResponse response) throws Exception {
		List<Map<String, Object>> mapList = deptService.getDeptLevelHashMap(dept);
		return mapList;
	}
}
