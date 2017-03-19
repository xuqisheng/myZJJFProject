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
import com.corner.data.analysis.beans.vo.OrderStatisVo;
import com.corner.data.analysis.common.export.bigdata.ExportBigData;
import com.corner.data.analysis.controller.ScmsBaseWebController;
import com.corner.data.analysis.service.bus.OrderStatisService;
import com.corner.data.analysis.utils.DateUtil;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.utils.DateUtils;

/**
 * ClassName: OrderStatisController
 * 
 * @Description: 订单统计 控制器
 * @author 元宝
 * @date 2016年01月8日
 */
@Controller
@RequestMapping(value = "/analysis/order")
public class OrderStatisController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderStatisController.class);
	
	private final String LOGIN_URL="/login/index";
	
	@Autowired
	private ExportBigData exportBigData;
	
	@Autowired
	private OrderStatisService orderService;
	
	/**
	 * 
	 * @Title: toStatisPage 
	 * @Description: 跳转到订单统计页面
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/toOrderPage.do")
	public String toStatisPage(Model model,OrderStatisVo orderVo){
		
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
			orderVo.setStartTime(currentDate);
			orderVo.setEndTime(currentDate);
			
			//================开始================================
	        //String currentDate = "2016-01-29";
	        //小于查询时间
	        Date date = DateUtil.fmtStrToDate(currentDate, "yyy-MM-dd");//查询时间
	        String tempDate = DateUtil.fmtDateToYM(date)+"-28";
	        Date compareDate = DateUtil.fmtStrToDate(tempDate,"yyy-MM-dd");
	        
	        //小于本月28
	        int i = date.compareTo(compareDate);
	        if(i>0){
	        	//如果i大于本月28号则取，本月29号开始到下一个月的28号
	        	System.err.println("如果i大于本月28号则取，本月29号开始到下一个月的28号");
	            String firstDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,0,29), "yyy-MM-dd");//查询时间
	            String lastDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,1,28), "yyy-MM-dd");//查询时间
	            orderVo.setFirstDay(firstDay);
				orderVo.setLastDay(lastDay);
	        	
	        }else if(i<=0){
	        	//如果i小于等于28号，则取上月29号 到本月28号；
	        	System.err.println("如果i小于等于28号，则取上月29号 到本月28号");
	            String firstDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,-1,29), "yyy-MM-dd");//查询时间
	            String lastDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,0,28), "yyy-MM-dd");//查询时间
	            orderVo.setFirstDay(firstDay);
				orderVo.setLastDay(lastDay);
	        }
			//================结束==============================
			
			
//			orderVo.setFirstDay(DateUtil.getFirstDay());
//			orderVo.setLastDay(DateUtil.getLastDay());
			//2、根据查询时间段获取相关统计列表信息
			List<OrderStatisVo> list = orderService.findOrderStatisList(orderVo);
			//3、根据年份统计全年的全年累计终端  、累计销量  、累计销售额、累计盈利商品销售额 、费用 等统计数
			 model.addAttribute("list", list);
			 model.addAttribute("orderVo", orderVo);
			 
		} catch (Exception e) {
			logger.error("订单统计 控制器中的toStatisPage方法异常：{}",e.getMessage());
		}
		
		return "analysis/orderStatisList";
	}
	
	/**
	 * @Title: getStatisList 
	 * @Description: 查询订单列表（分页查询） 
	 * @return Object    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/getOrderList.do")
	public String getStatisList(HttpServletRequest request, Model model,OrderStatisVo orderVo){
		
		try {
			logger.info("统计列表查询开始，起始时间：{},结束时间：{}",orderVo.getStartTime(),orderVo.getEndTime());
			
			//1、准备当前查询起始时间的年份，如果为空则取当前时间的年份
//			orderVo.setFirstDay(DateUtil.getFirstDay());
//			orderVo.setLastDay(DateUtil.getLastDay());
			
			
			//================开始================================
	        //String currentDate = "2016-01-29";
	        //小于查询时间
	        Date date = DateUtil.fmtStrToDate(orderVo.getStartTime(), "yyy-MM-dd");//查询时间
	        String tempDate = DateUtil.fmtDateToYM(date)+"-28";
	        Date compareDate = DateUtil.fmtStrToDate(tempDate,"yyy-MM-dd");
	        
	        //小于本月28
	        int i = date.compareTo(compareDate);
	        if(i>0){
	        	//如果i大于本月28号则取，本月29号开始到下一个月的28号
	        	System.err.println("如果i大于本月28号则取，本月29号开始到下一个月的28号");
	            String firstDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,0,29), "yyy-MM-dd");//查询时间
	            String lastDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,1,28), "yyy-MM-dd");//查询时间
	            orderVo.setFirstDay(firstDay);
				orderVo.setLastDay(lastDay);
	        	
	        }else if(i<=0){
	        	//如果i小于等于28号，则取上月29号 到本月28号；
	        	System.err.println("如果i小于等于28号，则取上月29号 到本月28号");
	            String firstDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,-1,29), "yyy-MM-dd");//查询时间
	            String lastDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,0,28), "yyy-MM-dd");//查询时间
	            orderVo.setFirstDay(firstDay);
				orderVo.setLastDay(lastDay);
	        }
			//================结束==============================
			
			//1、根据查询时间段获取相关统计列表信息
			List<OrderStatisVo> list = orderService.findOrderStatisList(orderVo);
			//2、根据年份统计全年的全年累计终端  、累计销量  、累计销售额、累计盈利商品销售额 、费用 等统计数
			 model.addAttribute("list", list);
			 model.addAttribute("orderVo", orderVo);
		} catch (Exception e) {
			logger.error("订单统计 控制器中的getStatisList方法异常：{}",e.getMessage());
		}
		
		return "analysis/orderStatisList";
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
	public Object getPagerList(HttpServletRequest request, Model model,OrderStatisVo orderVo){
		
		try {
			logger.info("订单统计列表查询开始，起始时间：{},结束时间：{}",orderVo.getStartTime(),orderVo.getEndTime());
			return orderService.findPagerList(orderVo);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "统计查询异常"+e);
		}
	}
	
	
	/**
	 * 导出订单统计数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value="/export.do")
    public void exportOrderStatis(HttpServletRequest request, HttpServletResponse response,OrderStatisVo orderVo) {
		 OutputStream out = null;
		try {
			 String fileName = DateUtils.getDate("yyyyMMddHHmmss");
			 response.setHeader("content-disposition", "attachment;filename=" + fileName + ".zip");  
             out = response.getOutputStream();						
             
	         String[] headers = { "区域", "定格名称", "商品名称", "商品规格", "本日金额", "本月金额"};
	         List<String>  list = new ArrayList<String>();
	         String startTime = orderVo.getStartTime();
	         String endTime = orderVo.getEndTime();
//	         String firstDay = DateUtil.getFirstDay();
//	         String lastDay = DateUtil.getLastDay();
				//================开始================================
		        //String currentDate = "2016-01-29";
		        //小于查询时间
		        Date date = DateUtil.fmtStrToDate(orderVo.getStartTime(), "yyy-MM-dd");//查询时间
		        String tempDate = DateUtil.fmtDateToYM(date)+"-28";
		        Date compareDate = DateUtil.fmtStrToDate(tempDate,"yyy-MM-dd");
		        
		        //小于本月28
		        int i = date.compareTo(compareDate);
		        if(i>0){
		        	//如果i大于本月28号则取，本月29号开始到下一个月的28号
		        	System.err.println("如果i大于本月28号则取，本月29号开始到下一个月的28号");
		            String firstDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,0,29), "yyy-MM-dd");//查询时间
		            String lastDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,1,28), "yyy-MM-dd");//查询时间
				     list.add(firstDay);
				     list.add(lastDay);
		        	
		        }else if(i<=0){
		        	//如果i小于等于28号，则取上月29号 到本月28号；
		        	System.err.println("如果i小于等于28号，则取上月29号 到本月28号");
		            String firstDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,-1,29), "yyy-MM-dd");//查询时间
		            String lastDay = DateUtil.fmtDateToStr(DateUtil.getLastMonthStart(date,0,28), "yyy-MM-dd");//查询时间
				     list.add(firstDay);
				     list.add(lastDay);
		        }
				//================结束==============================
	         
//		     list.add(firstDay);
//		     list.add(lastDay);
	         list.add(startTime);
	         list.add(endTime);
	         
	         //将list转化为数组格式
	         final int size = list.size();
	         Object[] sqlParams = (Object[])list.toArray(new Object[size]);
	         //, concat(left (SUM(C.fyje) / SUM(A.xsje) *100,2),'%') as flratio 
	         StringBuffer sbSql = new StringBuffer();
				 sbSql.append(" select  cc.areaName,cc.shopRatedName,cc.productName,cc.productSpec,cc.dateTotalMoney,cc.monthTotalMoney from ( ");
				 sbSql.append(" select  aa.areaName,aa.areaId,aa.productPrice,aa.orderPrice,aa.productCode,aa.productName,");
				 sbSql.append(" aa.productSpec,aa.productUnitPrice,bb.dateTotalQuantity,bb.dateTotalMoney,aa.monthTotalQuantity,");
				 sbSql.append(" aa.monthTotalMoney,aa.wholesalerId,aa.shopRatedName,aa.shopRatedId,aa.category,aa.categoryId,");
				 sbSql.append(" aa.status from(SELECT rr.`name` as areaName,rr.id as areaId,si.goodsPrice productPrice,");
				 sbSql.append(" si.orderPrice orderPrice,sd.barCode productCode,sd.name productName,sd.spec productSpec,");
				 sbSql.append(" sd.price productUnitPrice,sum(sd.quantity) monthTotalQuantity,sum(sd.totalPrice) monthTotalMoney,");
				 sbSql.append(" sp.id wholesalerId,sg.name shopRatedName,sg.id shopRatedId,itc2.name as  category,");
				 sbSql.append(" itc2.id as  categoryId,si.status status FROM SpOrderDetail AS sd");
				 sbSql.append(" JOIN SpOrderInfo AS si ON sd.orderId2 = si.orderId");
				 sbSql.append(" JOIN ItemBase AS ib ON ib.mdseId = sd.barCode");
				 sbSql.append(" JOIN Supplier as sp on sp.id = si.supplierId");
				 sbSql.append(" JOIN SpGroupMap as sgm on sp.id = sgm.spId");
				 sbSql.append(" JOIN SpGroup sg on sgm.groupId = sg.id");
				 sbSql.append(" JOIN ItemCatelog itc ON  ib.cateId = itc.id");
				 sbSql.append(" JOIN ItemCatelog itc2 ON itc.pid = itc2.id");
				 sbSql.append(" LEFT JOIN Store SS on SS.ID= si.storeId");
				 sbSql.append(" left join Region rr on rr.id=SS.areaId");
				
				 sbSql.append("  where sd.addTime BETWEEN DATE_FORMAT(?, '%Y-%m-%d') ");
				 sbSql.append("  and DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d')");
				 sbSql.append(" and si.`status` !=1 and si.`status`!=6  and itc2.name = '促销专区' ");
				 sbSql.append(" GROUP BY sg.id,sd.barCode ) aa ");
				 sbSql.append(" left JOIN (SELECT rr.`name` as areaName,rr.id as areaId, si.goodsPrice productPrice, si.orderPrice orderPrice, ");
				 sbSql.append(" sd.barCode productCode, sd.name productName, sd.spec productSpec, sd.price productUnitPrice,");
				 sbSql.append(" sum(sd.quantity) dateTotalQuantity, sum(sd.totalPrice) dateTotalMoney, sp.id wholesalerId,");
				 sbSql.append(" sg.name shopRatedName, sg.id shopRatedId, itc2.name as  category, itc2.id as  categoryId,");
				 sbSql.append(" si.status status FROM SpOrderDetail AS sd ");
				 sbSql.append(" JOIN SpOrderInfo AS si ON sd.orderId2 = si.orderId ");
				 sbSql.append(" JOIN ItemBase AS ib ON ib.mdseId = sd.barCode ");
				 sbSql.append(" JOIN Supplier as sp on sp.id = si.supplierId ");
				 sbSql.append(" JOIN SpGroupMap as sgm on sp.id = sgm.spId ");
				 sbSql.append(" JOIN SpGroup sg on sgm.groupId = sg.id ");
				 sbSql.append(" JOIN ItemCatelog itc ON  ib.cateId = itc.id ");
				 sbSql.append(" JOIN ItemCatelog itc2 ON itc.pid = itc2.id ");
				 sbSql.append(" LEFT JOIN Store SS on SS.ID= si.storeId ");
				 sbSql.append(" left join Region rr on rr.id=SS.areaId ");
				
				 sbSql.append(" where sd.addTime BETWEEN DATE_FORMAT(?, '%Y-%m-%d') ");
				 sbSql.append(" and DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') ");
				 sbSql.append(" and si.`status` !=1 and si.`status`!=6  and itc2.name = '促销专区' "); 
				 sbSql.append(" GROUP BY sg.id,sd.barCode ) bb on aa.productCode =bb.productCode and aa.shopRatedId=bb.shopRatedId ");
				 sbSql.append(" ) cc where cc.dateTotalMoney is not null ");
	         //System.err.println(sbSql.toString());
	         exportBigData.exportToZip(headers, out, 50000, sbSql.toString(), sqlParams);
		} catch (Exception e) {
			logger.error("订单统计 控制器中的exportOrderStatis方法异常：{}",e.getMessage());
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
