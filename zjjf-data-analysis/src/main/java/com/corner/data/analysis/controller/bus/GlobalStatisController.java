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
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.utils.ResponseUtils;
import com.corner.data.analysis.beans.vo.GlobalStatisVo;
import com.corner.data.analysis.common.export.bigdata.ExportBigData;
import com.corner.data.analysis.controller.ScmsBaseWebController;
import com.corner.data.analysis.service.bus.GlobalStatisService;
import com.corner.data.analysis.utils.DateUtil;
import com.corner.data.analysis.utils.excel.ExcelUtils;
import com.corner.data.analysis.utils.excel.JsGridReportBase;
import com.corner.data.analysis.utils.excel.TableData;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.utils.DateUtils;

/**
 * ClassName: GlobalStatisController
 * 
 * @Description: 全局统计 控制器
 * @author 元宝
 * @date 2016年01月8日
 */
@Controller
@RequestMapping(value = "/analysis/statis")
public class GlobalStatisController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(GlobalStatisController.class);
	
	private final String LOGIN_URL="/login/index";
	
	@Autowired
	private ExportBigData exportBigData;
	
	@Autowired
	private GlobalStatisService statisService;
	
	/**
	 * 
	 * @Title: toStatisPage 
	 * @Description: 跳转到全局统计页面
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/toStatisPage.do")
	public String toStatisPage(Model model,GlobalStatisVo statisVo){
		
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
			Date monthStartDate =DateUtil.getMonthStart(new Date());
			statisVo.setStartTime((DateUtil.fmtDateToStr(monthStartDate, "yyyy-MM-dd")));
			statisVo.setEndTime(DateUtil.fmtDateToStr(new Date(), "yyyy-MM-dd"));
			//2、根据查询时间段获取相关统计列表信息
			List<GlobalStatisVo> list = statisService.findGlobalStatisList(statisVo);
			//3、根据年份统计全年的全年累计终端  、累计销量  、累计销售额、累计盈利商品销售额 、费用 等统计数
			GlobalStatisVo fullYearStat = statisService.getFullYearStatisInfo(statisVo);
			 model.addAttribute("list", list);
			 model.addAttribute("statisVo", statisVo);
			 model.addAttribute("fullYearStat",fullYearStat);
			 
		} catch (Exception e) {
			logger.error("全局统计 控制器中的toStatisPage方法异常：{}",e.getMessage());
		}
		
		return "analysis/globalStatisList";
	}
	
	/**
	 * @Title: getStatisList 
	 * @Description: 查询订单列表（分页查询） 
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/getStatisList.do")
	public String getStatisList(HttpServletRequest request, Model model,GlobalStatisVo statisVo){
		
		try {
			logger.info("统计列表查询开始，起始时间：{},结束时间：{}",statisVo.getStartTime(),statisVo.getEndTime());
			/*String startTime = statisVo.getStartTime();
			//1、准备当前查询起始时间的年份，如果为空则取当前时间的年份
			if(StringUtils.isNotBlank(startTime)){
				statisVo.setCurYear(DateUtil.fmtStrToDate(startTime,"yyyy").toString());
			}else{
				statisVo.setCurYear(DateUtil.fmtDateToStr(new Date(), "yyyy"));
			}*/
			//2、根据查询时间段获取相关统计列表信息
			List<GlobalStatisVo> list = statisService.findGlobalStatisList(statisVo);
			//3、根据年份统计全年的全年累计终端  、累计销量  、累计销售额、累计盈利商品销售额 、费用 等统计数
			GlobalStatisVo fullYearStat = statisService.getFullYearStatisInfo(statisVo);
			 model.addAttribute("list", list);
			 model.addAttribute("statisVo", statisVo);
			 model.addAttribute("fullYearStat",fullYearStat);
		} catch (Exception e) {
			logger.error("全局统计 控制器中的getStatisList方法异常：{}",e.getMessage());
		}
		
		return "analysis/globalStatisList";
	}
	
	/**
	 * @Title: getStatisList 
	 * @Description: 查询订单列表（分页查询） 
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/getPagerList.do")
	@ResponseBody
	public Object getPagerList(HttpServletRequest request, Model model,GlobalStatisVo statisVo){
		
		try {
			logger.info("统计列表查询开始，起始时间：{},结束时间：{}",statisVo.getStartTime(),statisVo.getEndTime());
			return statisService.findPagerList(statisVo);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "统计查询异常"+e);
		}
	}
	
	/**
	 * 普通Excel导出，获取的数据格式是List<JavaBean>
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/export.do")
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response,GlobalStatisVo statisVo) throws Exception{
        response.setContentType("application/msexcel;charset=UTF-8");
        
        Subject subject = SecurityUtils.getSubject();
        CustomerService user = null;
        String userName = null;
		if(subject.isAuthenticated()){
			user =(CustomerService)subject.getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(null != user){
				userName=user.getUserName();
			}else{
				userName="";
			}
		}
		
		List<GlobalStatisVo> list = statisService.findGlobalStatisList(statisVo);
        String title = "数据分析全局统计";
        String[] hearders = new String[] {"区域","新增商铺","活跃商铺","高频商铺","下单次数","下单SKU"
        		               ,"销量","销售金额","盈利产品销售额","盈利商品利润","费用金额（元）","费用率"};//表头数组
        String[] fields = new String[] {"areaName", "xzzd", "hyzd", "gpzd", "xdcs", "xdsku","xl"
        		, "orderPriceTotal", "ylspxse", "ylsplr", "fyje","fyl"};//People对象属性数组
        TableData td = ExcelUtils.createTableData(list, 
    							ExcelUtils.createTableHeader(hearders),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, userName, td);
	}
	
	
	/**
	 * 导出全局统计数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value="/export2.do")
    public void exportGlobalStatis(HttpServletRequest request, HttpServletResponse response,GlobalStatisVo statisVo) {
		 OutputStream out = null;
		try {
			 String fileName = DateUtils.getDate("yyyyMMddHHmmss");
			 response.setHeader("content-disposition", "attachment;filename=" + fileName + ".zip");  
             out = response.getOutputStream();									
	         String[] headers = { "区域", "新增商铺", "活跃商铺", "高频商铺", "下单次数"   , "下单SKU", "销量", "销售金额", "盈利产品销售额", "盈利商品利润", "费用金额（元）"};
	         List<String>  list = new ArrayList<String>();
	         String startTime = statisVo.getStartTime();
	         String endTime = statisVo.getEndTime();
	         list.add(startTime);
	         list.add(endTime);
	         list.add(startTime);
	         list.add(endTime);
	         list.add(startTime);
	         list.add(endTime);
	         list.add(startTime);
	         list.add(endTime);
	         list.add(startTime);
	         list.add(endTime);
	         list.add(startTime);
	         list.add(endTime);
	         list.add(startTime);
	         list.add(endTime);
	         list.add(startTime);
	         list.add(endTime);
	         list.add(startTime);
	         list.add(endTime);
	         list.add(startTime);
	         list.add(endTime);
	         
	         //将list转化为数组格式
	         final int size = list.size();
	         Object[] sqlParams = (Object[])list.toArray(new Object[size]);
	         //, concat(left (SUM(C.fyje) / SUM(A.xsje) *100,2),'%') as flratio 
	         StringBuffer sbSql = new StringBuffer();
	         sbSql.append(" SELECT xx.areaName,xx.xzzd,xx.hyzd,xx.gpzd,xx.xdcs,xx.xdsku, ")
	        	  .append(" xx.xl,xx.xsje,xx.ylspxse,xx.ylsplr,xx.fyje,concat(LEFT (xx.fyje/xx.xsje*100,4),'%') AS flratio FROM( ")
	              .append(" SELECT A.areaName,D.xzzd,A.hyzd, B.gpzd, A.xdcs, C.xdsku, C.xl, A.xsje, C.ylspxse, C.ylsplr, C.fyje ")
	        	  .append(" FROM  (SELECT s.areaId,r.name areaName,count(soi.id) xdcs,count(DISTINCT 	soi.storeId) hyzd, ")
	        	  .append(" sum(soi.orderPrice) xsje,sum(soi.zmaoli) sumMaoli,sum(soi.zfee) sumFee FROM SpOrderInfo 	soi LEFT JOIN Store s ON soi.storeId = s.id  ")
				  .append(" LEFT JOIN Region r on s.areaId = r.id ")
				  .append(" WHERE soi.addTime >= DATE_FORMAT(?, '%Y-%m-%d') AND soi.addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d')  ")
				  .append(" AND soi.status !=6 AND soi.status != 1 AND soi.level = 1 GROUP BY s.areaId) AS A LEFT JOIN  ")
				  .append(" (SELECT s.areaId,count(DISTINCT soi.storeId) gpzd  FROM SpOrderInfo soi LEFT JOIN Store s  ")
				  .append(" ON soi.storeId = s.id WHERE soi.addTime >= DATE_FORMAT(?, '%Y-%m-%d') AND soi.addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') AND soi.storeId ") 	
				  .append(" IN (SELECT  soi1.storeId FROM SpOrderInfo soi1 WHERE soi1.addTime >= DATE_FORMAT(?, '%Y-%m-%d') AND soi1.addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d')  ")
				  .append(" AND soi1.status !=6 AND soi1.status != 1 AND soi1.level = 1 GROUP BY soi1.storeId HAVING count(soi1.storeId)> 3) GROUP BY s.areaId) AS B ON A.areaId = B.areaId LEFT JOIN  ")
				  .append(" (SELECT p.areaId,count(sod.orderId) xdsku,sum(sod.quantity) xl,sum(if(sod.fee<0,sod.totalPrice,0)) ylspxse,sum(if(sod.fee>0,sod.fee,0)) fyje, ")
				  .append(" sum(if(sod.fee<0,sod.fee,0)) ylsplr  FROM SpOrderDetail sod  ")
				  .append(" LEFT JOIN PlantItem p ON p.id = sod.itemId WHERE sod.addTime >= DATE_FORMAT(?, '%Y-%m-%d') ")
				  .append(" AND sod.addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') GROUP BY p.areaId) AS C  ")
				  .append(" ON A.areaId = C.areaId LEFT JOIN (SELECT r.name,s.areaId,count(s.id) xzzd FROM Store s  ")
				  .append(" LEFT JOIN Region r ON r.id=s.areaId WHERE s.addTime >= DATE_FORMAT(?, '%Y-%m-%d') AND s.addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') GROUP BY s.areaId) AS D  ")
				  .append(" ON A.areaId = D.areaId  LEFT JOIN Region r ON r.id = D.areaId  ")
	              .append(" ) xx");
	         sbSql.append(" union all ");
			 sbSql.append(" SELECT'累计' AS 区域,sum(D.xzzd) 新增商铺,sum(A.hyzd) 活跃商铺, ");
			 sbSql.append(" sum(B.gpzd) 高频商铺,sum(A.xdcs) 下单次数,sum(C.xdsku) 下单SKU, ");
			 sbSql.append(" sum(C.xl) 销量,sum(A.xsje) 销售金额,sum(C.ylspxse) 盈利销售额度, ");
			 sbSql.append(" sum(C.ylsplr) 盈利商品利润,sum(C.fyje) 费用金额, ");
			 sbSql.append(" concat(left (SUM(C.fyje) / SUM(A.xsje) *100,4),'%') as flratio ");
			 sbSql.append(" FROM( SELECT s.areaId,s. NAME,count(soi.id) xdcs, ");
			 sbSql.append(" count(DISTINCT soi.storeId) hyzd,sum(soi.orderPrice) xsje,sum(soi.zmaoli) sumMaoli,sum(soi.zfee) sumFee ");
			 sbSql.append(" FROM SpOrderInfo soi LEFT JOIN Store s ON soi.storeId = s.id ");
			 sbSql.append(" WHERE soi.addTime >= DATE_FORMAT(?, '%Y-%m-%d') AND soi.addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') ");
			 sbSql.append(" AND soi. STATUS <> 6 AND soi. STATUS <> 1 AND soi. LEVEL = 1 GROUP BY s.areaId ) AS A ");
			 sbSql.append(" LEFT JOIN (SELECT s.areaId, count(DISTINCT soi.storeId) gpzd ");
			 sbSql.append(" FROM SpOrderInfo soi LEFT JOIN Store s ON soi.storeId = s.id ");
			 sbSql.append(" WHERE soi.addTime >= DATE_FORMAT(?, '%Y-%m-%d') AND soi.addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') ");
			 sbSql.append(" AND soi.storeId IN ( SELECT soi1.storeId FROM SpOrderInfo soi1 ");
			 sbSql.append(" WHERE soi1.addTime >= DATE_FORMAT(?, '%Y-%m-%d') AND soi1.addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') ");
			 sbSql.append(" AND soi1. STATUS <> 6 AND soi1. STATUS <> 1 AND soi1. LEVEL = 1 ");
			 sbSql.append(" GROUP BY soi1.storeId HAVING count(soi1.storeId) > 3 ) GROUP BY ");
			 sbSql.append(" s.areaId ) AS B ON A.areaId = B.areaId ");
			 sbSql.append(" LEFT JOIN ( SELECT p.areaId,count(sod.orderId) xdsku,sum(sod.quantity) xl, ");
			 sbSql.append(" sum(IF (sod.fee < 0, sod.totalPrice, 0)) ylspxse,sum(IF(sod.fee > 0, sod.fee, 0)) fyje, ");
			 sbSql.append(" sum(IF(sod.fee < 0, sod.fee, 0)) ylsplr FROM SpOrderDetail sod ");
			 sbSql.append(" LEFT JOIN PlantItem p ON p.id = sod.itemId ");
			 sbSql.append(" WHERE sod.addTime >= DATE_FORMAT(?, '%Y-%m-%d') AND sod.addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') ");
			 sbSql.append(" GROUP BY p.areaId ) AS C ON A.areaId = C.areaId ");
			 sbSql.append(" LEFT JOIN ( SELECT r. NAME, s.areaId, count(s.id) xzzd FROM Store s LEFT JOIN Region r ON r.id = s.areaId ");
			 sbSql.append(" WHERE s.addTime >= DATE_FORMAT(?, '%Y-%m-%d') AND s.addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') ");
			 sbSql.append(" GROUP BY s.areaId ) AS D ON A.areaId = D.areaId LEFT JOIN Region r ON r.id = D.areaId ");
	         
	         //System.err.println(sbSql.toString());
	         exportBigData.exportToZip(headers, out, 50000, sbSql.toString(), sqlParams);
		} catch (Exception e) {
			logger.error("全局统计 控制器中的getStatisList方法异常：{}",e.getMessage());
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
}
