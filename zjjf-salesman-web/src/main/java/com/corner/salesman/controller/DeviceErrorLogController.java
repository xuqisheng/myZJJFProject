package com.corner.salesman.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.salesman.model.AppMonitorLogs;
import com.corner.salesman.model.DeviceErrorLog;
import com.corner.salesman.service.DeviceErrorLogService;
import com.corner.salesman.utils.excel.ExcelUtils;
import com.corner.salesman.utils.excel.JsGridReportBase;
import com.corner.salesman.utils.excel.TableData;
import com.corner.scms.beans.CustomerService;
import com.corner.scms.controller.ScmsBaseWebController;

@Controller
@RequestMapping(value="/log")
public class DeviceErrorLogController extends ScmsBaseWebController {
	
	//private static Logger logger = LoggerFactory.getLogger(DeviceErrorLogController.class);
	@Autowired
	private DeviceErrorLogService appLogService;
	
	//转角业务员  首页 
	@RequestMapping(value = "/index.do")
	public String toListPage(HttpServletRequest request, Model model) {
		return "salesman/errorlog-list";
	}
	
	//转角业务员  首页 
	@RequestMapping(value = "/appLog.do")
	public String toAppLogPage(HttpServletRequest request, Model model) {
		return "salesman/appLog-list";
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
	public Object listPage(HttpServletRequest request, DeviceErrorLog errorLog) {
		try {
			Pager<DeviceErrorLog> pager = appLogService.getErrorLogPageList(errorLog);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "新增业务员异常："+e);
		}
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
	@RequestMapping(value = "/appLogList.do")
	@ResponseBody
	public Object appLogListPage(HttpServletRequest request, AppMonitorLogs appLogs) {
		try {
			Pager<AppMonitorLogs> pager = appLogService.getAppLogPageList(appLogs);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "查询app运行日志异常："+e);
		}
	}
	
	/**
	 * 普通Excel导出，获取的数据格式是List<JavaBean>
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response,DeviceErrorLog errorLog) throws Exception{
        response.setContentType("application/msexcel;charset=UTF-8");
        String userName = null;
        CustomerService customer = getCurrentUser(CustomerService.class, request);
        if(null != customer){
        	userName = customer.getUserName();
        }else{
        	userName = "admin";
        }
        List<DeviceErrorLog> list = appLogService.findErrorLogList(errorLog);
        String title = "APP异常监控日志";
        String[] hearders = new String[] {"账号","姓名","设备名称","设备标识","版本号","异常时间","异常信息"};//表头数组
        String[] fields = new String[] {"mobile", "userName", "deviceName", "deviceUUID", "version", "errorTime","errorMsg"};//People对象属性数组
        TableData td = ExcelUtils.createTableData(list, 
    							ExcelUtils.createTableHeader(hearders),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, userName, td);
	}
	
	/**
	 * 普通Excel导出，获取的数据格式是List<JavaBean>
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/exportAppLog")
	public void exportAppLogExcel(HttpServletRequest request,
			HttpServletResponse response,AppMonitorLogs appLogs) throws Exception{
        response.setContentType("application/msexcel;charset=UTF-8");
        
        String userName = null;
        CustomerService customer = getCurrentUser(CustomerService.class, request);
        if(null != customer){
        	userName = customer.getUserName();
        }else{
        	userName = "admin";
        }
        List<AppMonitorLogs> list = appLogService.findAppLogList(appLogs);
        String title = "APP运行日志信息";
        String[] hearders = new String[] {"用户","设备类型","设备名称","是否活着","上传状态","网络","GPS","App时间","服务器时间","数据信息"};//表头数组
        String[] fields = new String[] {"createBy","deviceType","deviceName", "keepAlive", "uploadState", "network","gps","appTime","createTime","dataInfo"};//People对象属性数组
        TableData td = ExcelUtils.createTableData(list, 
    							ExcelUtils.createTableHeader(hearders),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, userName, td);
	}
	

}
