package com.corner.salesman.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.salesman.model.DeviceLog;
import com.corner.salesman.model.SalesmanMg;
import com.corner.salesman.service.DeviceLogService;
import com.corner.salesman.service.SalesmanMgService;
import com.corner.scms.beans.CustomerService;
import com.corner.scms.controller.ScmsBaseWebController;

@Controller
@RequestMapping(value="/device")
public class DeviceController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(DeviceController.class);
	@Autowired
	private SalesmanMgService salesmanService;
	@Autowired
	private DeviceLogService deviceLogService;
	
	//转角业务员  首页 
	@RequestMapping(value = "/index.do")
	public String toListPage(SalesmanMg salesman, HttpServletRequest request, Model model) {
		return "salesman/device-list";
	}
	
	//转角业务员  首页 
	@RequestMapping(value = "/deviceLogIndex.do")
	public String toLogListPage(SalesmanMg salesman, HttpServletRequest request, Model model) {
		return "salesman/devicelog-list";
	}
	
	/**
	 * @Title: listPage 
	 * @Description: 分页查询设备信息 
	 * @param request
	 * @param salesman
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/listPage.do")
	@ResponseBody
	public Object listPage(HttpServletRequest request, SalesmanMg salesman) {
		try {
			logger.info("列表查询开始："+JSONUtil.objectToJSONString(salesman));
			Pager<SalesmanMg> pager = salesmanService.getDevicePageList(salesman);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "新增业务员异常："+e);
		}
	}
	
	/**
	 * 
	* @Title: unbindDeviceId 
	* @Description:   设备解绑方法 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/unbindDeviceId.do")
	@ResponseBody
	public Object unbindDeviceId(HttpServletRequest request, SalesmanMg salesman) {
		try {
			logger.info("修改业务员："+JSONUtil.objectToJSONString(salesman));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			salesman.setUpdateBy(supplier.getId());
			salesman.setUpdateTime(new Date());
			salesmanService.updateUnbindDeviceId(salesman);
			return ResponseUtils.sendMsg(true, "修改成功");
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "修改异常："+e);
		}
	}
	
	/**
	 * 
	* @Title: delSalesmanMg 
	* @Description:   是否删除业务员信息 
	* @param request
	* @return Object  返回类型
	* @author 元宝	yuanbao@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/delSalesmanMg.do")
	@ResponseBody
	public Object delSalesmanMg(HttpServletRequest request, SalesmanMg salesman) {
		try {
			logger.info("删除业务员："+JSONUtil.objectToJSONString(salesman));
			CustomerService supplier = getCurrentUser(CustomerService.class, request);
			salesman.setUpdateBy(supplier.getId());
			salesman.setUpdateTime(new Date());
			int size = salesmanService.delSalesmanMg(salesman);
			if(size>0){
				return ResponseUtils.sendMsg(true, "修改成功");
			}else{
				return ResponseUtils.sendMsg(false, "修改失败");
			}
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "修改异常："+e);
		}
	}
	
	/**
	 * 
	 * @Title: findById 
	 * @Description: 根据用户ID查询业务员信息
	 * @param @param request
	 * @param @param command
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/findById.do")
	public String findSalesmanById(HttpServletRequest request, SalesmanMg salesman,Model model) {
		try {
			String id = salesman.getId();
			logger.info("查询用户ID={}",id);
			salesman =  salesmanService.findSalesmanMgById(salesman); 
			model.addAttribute("salesman", salesman);
		} catch (Exception e) {
			logger.info("根据用户ID查询业务员信息异常={}",e.getMessage());
		}
		return null;
	}
	
	/**
	 * @Title: deviceLogPage 
	 * @Description: 解绑日志分页查询 
	 * @param request
	 * @param salesman
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/deviceLog.do")
	@ResponseBody
	public Object deviceLogPage(HttpServletRequest request, DeviceLog deviceLog) {
		try {
			logger.info("列表查询开始："+JSONUtil.objectToJSONString(deviceLog));
			Pager<DeviceLog> pager = deviceLogService.getDevicePageList(deviceLog);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "新增业务员异常："+e);
		}
	}

}
