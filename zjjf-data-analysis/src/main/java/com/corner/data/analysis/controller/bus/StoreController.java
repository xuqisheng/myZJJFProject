package com.corner.data.analysis.controller.bus;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.data.analysis.beans.vo.StoreVo;
import com.corner.data.analysis.common.export.bigdata.ExportBigData;
import com.corner.data.analysis.controller.ScmsBaseWebController;
import com.corner.data.analysis.service.bus.StoreService;
import com.corner.data.analysis.utils.DateUtil;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.utils.DateUtils;

/**
 * ClassName: StoreController
 * 
 * @Description: 店铺信息 控制器
 * @author 元宝
 * @date 2016年01月8日
 */
@Controller
@RequestMapping(value = "/analysis/store")
public class StoreController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(StoreController.class);
	
	private final String LOGIN_URL="/login/index";
	
	@Autowired
	private ExportBigData exportBigData;
	
	@Autowired
	private StoreService storeService;
	
	/**
	 * 
	 * @Title: toStatisPage 
	 * @Description: 跳转到店铺信息页面
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/toStorePage.do")
	public String toStatisPage(Model model,StoreVo storeVo){
		
		try {
			Subject subject = SecurityUtils.getSubject();
			if(subject.isAuthenticated()){
				//Supplier user =(Supplier)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
				CustomerService user =(CustomerService)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
				if(user == null){
					logger.info("当前用户已经失效，请重新登陆！");
					return LOGIN_URL;
				}
			}else{
				logger.info("当前用户未通过身份验证！");
				return LOGIN_URL;
			}
			//1、准备当前查询起始时间的年份，如果为空则取当前时间的年份
			String currentDate =DateUtil.fmtDateToStr(new Date(), "yyyy-MM-dd");
			storeVo.setStartTime(currentDate);
			storeVo.setEndTime(currentDate);
			model.addAttribute("storeVo", storeVo);
		} catch (Exception e) {
			logger.error("店铺信息 控制器中的toStorePage方法异常：{}",e.getMessage());
		}
		
		return "analysis/storeList";
	}
	
	/**
	 * @Title: getPagerList 
	 * @Description: 查询店铺列表（分页查询） 
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/getPagerList.do")
	@ResponseBody
	public Object getPagerList(HttpServletRequest request, Model model,StoreVo storeVo){
		
		try {
			logger.info("查询店铺信息起始时间：{},结束时间：{}",storeVo.getStartTime(),storeVo.getEndTime());
			Pager<StoreVo> pager = storeService.findPagerList(storeVo);
			pager.getMap().put("storeVo", storeVo);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "查询店铺信息异常"+e);
		}
	}
	
	
	/**
	 * 导出店铺信息数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value="/export.do")
    public void exportStoreList(HttpServletRequest request, HttpServletResponse response,StoreVo storeVo) {
		 OutputStream out = null;
		try {
			 String fileName = DateUtils.getDate("yyyyMMddHHmmss");
			 response.setHeader("content-disposition", "attachment;filename=" + fileName + ".zip");  
             out = response.getOutputStream();						
             
	         String[] headers = { "区域","店铺编码", "店铺名称", "店铺老板", "店铺地址", "店铺电话", "注册时间", "商铺定格", "业务员电话", "业务员名称", "店铺状态", "营业执照"};
	         List<String>  list = new ArrayList<String>();
	         String startTime = storeVo.getStartTime();
	         String endTime = storeVo.getEndTime();
	         if(StringUtils.isNotBlank(startTime)){
	        	 list.add(startTime);
	         }
	         if(StringUtils.isNotBlank(endTime)){
	        	 list.add(endTime);
	         }
	         
	         //将list转化为数组格式
	         final int size = list.size();
	         Object[] sqlParams = (Object[])list.toArray(new Object[size]);
	         StringBuffer sbSql = new StringBuffer();
			 sbSql.append(" SELECT ");
			 sbSql.append(" r.NAME AS areaName,s.id,s. NAME,s.contact,s.address,s.mobile AS shopTel, ");
			 sbSql.append(" DATE_FORMAT(s.addTime, '%Y-%m-%d') AS addTime,sp. NAME AS shopRatedName, ");
			 sbSql.append(" si.yewuRenTel,tt.userName AS yewuRenName, case when s.`status` = 0 then '关闭' ");
			 sbSql.append(" when s.`status` = 1 then '正常' when s.`status` = 2 then '审核中' ");
			 sbSql.append(" when s.`status` = 3 then '审核不通过' else '其他' end status, si.licenseNum ");
			 sbSql.append(" FROM Store s ");
			 sbSql.append(" LEFT JOIN StoreInfo si ON si.id = s.id ");
			 sbSql.append(" LEFT JOIN SpGroup sp ON s.spGroupId = sp.id ");
			 sbSql.append(" LEFT JOIN Region r ON s.areaId = r.id ");
			 sbSql.append(" LEFT JOIN Salesman tt ON tt.mobile = si.yewuRenTel ");
			 sbSql.append(" WHERE 1=1  ");
	         if(StringUtils.isNotBlank(startTime)){
	        	 sbSql.append(" and s.addTime >=DATE_FORMAT(?, '%Y-%m-%d') ");
	         }
	         if(StringUtils.isNotBlank(endTime)){
	        	 sbSql.append(" AND s.addTime <=DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') ");
	         }
			 sbSql.append(" ORDER BY s.addTime desc ");
	         System.err.println(sbSql.toString());
	         exportBigData.exportToZip(headers, out, 50000, sbSql.toString(), sqlParams);
		} catch (Exception e) {
			logger.error("店铺信息 控制器中的exportStoreList方法异常：{}",e.getMessage());
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				logger.error("店铺信息中的export方法IO关闭异常：{}",e.getMessage());
			}
		}
    }
    
}
