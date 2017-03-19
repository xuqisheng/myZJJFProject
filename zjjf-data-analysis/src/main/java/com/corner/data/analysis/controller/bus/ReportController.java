package com.corner.data.analysis.controller.bus;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.Supplier;
import com.corner.data.analysis.beans.vo.GlobalStatisVo;
import com.corner.data.analysis.common.export.bigdata.ExportBigData;
import com.corner.data.analysis.controller.ScmsBaseWebController;
import com.corner.data.analysis.service.bus.GlobalStatisService;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.utils.DateUtils;
import com.corner.scms.utils.ExportExcel;
import com.corner.scms.utils.Student;

/**
 * ClassName: GlobalStatisController
 * 
 * @Description: 全局统计 控制器
 * @author 元宝
 * @date 2016年01月8日
 */
@Controller
@RequestMapping(value = "/analysis/report")
public class ReportController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	private final String LOGIN_URL="/login/index";
	
	@Autowired
	private GlobalStatisService statisService;
	
	
	@Autowired
	private ExportBigData exportBigData;
	
	
	/**
	 * 
	 * @Title: toStatisPage 
	 * @Description: 跳转到全局统计页面
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/toReportPage.do")
	public String toStatisPage(Model model,GlobalStatisVo statisVo){
		
		try {
			Subject subject = SecurityUtils.getSubject();
			if(subject.isAuthenticated()){
//				Supplier user =(Supplier)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
				CustomerService user =(CustomerService)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
				if(user == null){
					logger.info("当前用户已经失效，请重新登陆！");
					return LOGIN_URL;
				}
			}else{
				logger.info("当前用户未通过身份验证！");
				return LOGIN_URL;
			}
			 
		} catch (Exception e) {
			logger.error("全局统计 控制器中的toStatisPage方法异常：{}",e.getMessage());
		}
		
		return "analysis/reportExcel";
	}
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value="/export.do")
    public void exportFile2(HttpServletRequest request, HttpServletResponse response) {
		 OutputStream out = null;
		try {
			 String fileName = "用户数据"+DateUtils.getDate("yyyyMMddHHmmss");
			 response.setHeader("content-disposition", "attachment;filename=" + fileName + ".zip");  
             out = response.getOutputStream();
			// 测试学生  
            /*ExportExcel<Student> ex = new ExportExcel<Student>();  
	        String[] headers =  
	        { "学号", "姓名", "年龄", "性别", "出生日期" };  
	        List<Student> dataset = new ArrayList<Student>();  
	        dataset.add(new Student(10000001, "张三", 20, true, new Date()));  
	        dataset.add(new Student(20000002, "李四", 24, false, new Date()));  
	        dataset.add(new Student(30000003, "王五", 22, true, new Date()));  
	        ex.exportExcel(headers, dataset, out); */
	        
	        String[] headers =  
		        { "学号", "姓名", "年龄", "性别", "出生日期" };
            Object[] sqlParams = {"135","1993-08-26"};
	        String sql = "select t.deptId,t.mobile,t.userName,t.`password` from CustomerService t ";
	        exportBigData.exportToZip(headers, out, 20, sql, null);
		} catch (Exception e) {
			//addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    /*@RequestMapping(value="/export.do")
    public String exportFile2(HttpServletRequest request, HttpServletResponse response) {
		 OutputStream out = null;
		try {
			 String fileName = "用户数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			 response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");  
             out = response.getOutputStream();
			// 测试学生  
	        ExportExcel<Student> ex = new ExportExcel<Student>();  
	        String[] headers =  
	        { "学号", "姓名", "年龄", "性别", "出生日期" };  
	        List<Student> dataset = new ArrayList<Student>();  
	        dataset.add(new Student(10000001, "张三", 20, true, new Date()));  
	        dataset.add(new Student(20000002, "李四", 24, false, new Date()));  
	        dataset.add(new Student(30000003, "王五", 22, true, new Date()));  
	        ex.exportExcel(headers, dataset, out); 
            
    		return null;
		} catch (Exception e) {
			//addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "analysis/reportExcel";
    }*/
	
}
