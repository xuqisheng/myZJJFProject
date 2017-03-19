package com.corner.data.analysis.controller.bus;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.corner.data.analysis.beans.vo.AnalysisProductVo;
import com.corner.data.analysis.beans.vo.AnalysisSupplierVo;
import com.corner.data.analysis.common.export.bigdata.ExportBigData;
import com.corner.data.analysis.controller.ScmsBaseWebController;
import com.corner.data.analysis.service.bus.AnalysisSupplierService;
import com.corner.data.analysis.utils.DateUtil;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.utils.DateUtils;
/**
 * ClassName: AnalysisSupplierController
 * 
 * @Description: 合作商数据分析 控制器
 * @author 元宝
 * @date 2016年01月8日
 */
@Controller
@RequestMapping(value = "/analysis/supplier")
public class AnalysisSupplierController extends ScmsBaseWebController {
	private static Logger logger = LoggerFactory.getLogger(AnalysisSupplierController.class);
	
	private final String LOGIN_URL="/login/index";
	@Autowired
	private ExportBigData exportBigData;
	@Autowired
	private AnalysisSupplierService supplierService;
	
	/**
	 * 
	 * @Title: toShopPage 
	 * @Description: 跳转到合作商数据分析页面
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/toSupplierPage.do")
	public String toSupplierPage(Model model,AnalysisSupplierVo supplierVo){
		
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
			
			//1、准备当前查询起始时间的年份，如果为空则取当前时间的年份
			Date monthStartDate =DateUtil.getMonthStart(new Date());
			supplierVo.setStartTime((DateUtil.fmtDateToStr(monthStartDate, "yyyy-MM-dd")));
			supplierVo.setEndTime(DateUtil.fmtDateToStr(new Date(), "yyyy-MM-dd"));
			//2、根据查询时间段获取相关统计列表信息
			List<AnalysisSupplierVo> supplierList = supplierService.findSupplierList(supplierVo);
			model.addAttribute("supplierList", supplierList);
			model.addAttribute("supplierVo", supplierVo);
		} catch (Exception e) {
			logger.error("合作商数据分析 控制器中的toSupplierPage方法异常：{}",e.getMessage());
		}
		
		return "analysis/analysisSupplierList";
	}
	
	/**
	 * 
	 * @Title: toShopPage 
	 * @Description: 跳转到合作商数据分析页面
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/getSupplierList.do")
	public String getSupplierList(Model model,AnalysisSupplierVo supplierVo){
		
		try {
			//1、根据查询时间段获取相关统计列表信息
			List<AnalysisSupplierVo> supplierList = supplierService.findSupplierList(supplierVo);
			model.addAttribute("supplierList", supplierList);
			model.addAttribute("supplierVo", supplierVo);
		} catch (Exception e) {
			logger.error("合作商数据分析 控制器中的getSupplierList方法异常：{}",e.getMessage());
		}
		
		return "analysis/analysisSupplierList";
	}
	
	/**
	 * 
	 * @Title: getSupplierDetailById 
	 * @Description: 跳转到合作商数据分析页面
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/getSupplierDetailById.do")
	public String getSupplierDetailById(Model model,AnalysisSupplierVo supplierVo){
		
		try {
			//1、查询明细页面的基础信息列表
			List<AnalysisSupplierVo> baseList = supplierService.findSupplierBaseList(supplierVo);
			//2、查询明细页面的详情列表
			List<AnalysisSupplierVo> detailList = supplierService.findSupplierDetailList(supplierVo);
			//3、为详情页面的头部分导航标题准备数据
			if(null != baseList && baseList.size()>0){
				model.addAttribute("baseInfo", baseList.get(0));
			}
			model.addAttribute("baseList", baseList);
			model.addAttribute("detailList", detailList);
			model.addAttribute("supplierVo", supplierVo);
		} catch (Exception e) {
			logger.error("合作商数据分析 控制器中的getSupplierDetailById方法异常：{}",e.getMessage());
		}
		
		return "analysis/analysisSupplierDetail";
	}
	
	/**
	 * 导出合作商数据分析数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value="/export.do")
    public void exportSupplierList(HttpServletRequest request, HttpServletResponse response,AnalysisSupplierVo supplierVo) {
		 OutputStream out = null;
		try {
			 String fileName = DateUtils.getDate("yyyyMMddHHmmss");
			 response.setHeader("content-disposition", "attachment;filename=" + fileName + ".zip");  
             out = response.getOutputStream();		

	         String[] headers = { "所属区域", "合作商编号", "合作商名称","配送商铺数", "订单数量", "销量" , "销售金额", "盈利商品销售额","盈利商品利润", "费用","费用率"};
	         List<String>  list = new ArrayList<String>();
	         String startTime = supplierVo.getStartTime();
	         String endTime = supplierVo.getEndTime();
	         String areaId = supplierVo.getAreaId();
	         String supplierCode = supplierVo.getSupplierCode();

	         
	         if(StringUtils.isNotBlank(areaId)){
	        	 list.add(areaId);
	         }
	         if(StringUtils.isNotBlank(supplierCode)){
	        	 list.add(supplierCode);
	        	 list.add(supplierCode);
	         }
	         list.add(startTime);
	         list.add(endTime);
	         
	         //将list转化为数组格式
	         final int size = list.size();
	         Object[] sqlParams = (Object[])list.toArray(new Object[size]);
	         //, concat(left (SUM(C.fyje) / SUM(A.xsje) *100,2),'%') as flratio 
	         StringBuffer sbSql = new StringBuffer();
	         sbSql.append(" SELECT xx.areaName,xx.supplierCode,xx.supplierName,xx.pssps,xx.ddsl,xx.xssl, ");
	         sbSql.append(" xx.xsje,xx.ylspxse,xx.ylsplr,xx.fyje,concat(LEFT (xx.fyje/xx.xsje*100,4),'%') AS flratio FROM( ");
	         
	         sbSql.append(" SELECT D. NAME areaName,A.supplierCode,A.supplierName,COUNT(DISTINCT B.storeId) pssps, ");
	         sbSql.append(" count(B.orderId) ddsl,sum(B.orderPrice + B.rebate) xsje,sum(B.zfee) fyje, ");
	         sbSql.append(" sum(C.quantity) xssl,sum(C.ylsplr) ylsplr,sum(C.totalPrice) ylspxse FROM ");
	         sbSql.append(" (SELECT id, supplierCode, areaId, supplierName FROM Supplier where 1=1 ");
	         if(StringUtils.isNotBlank(areaId)){
	        	 sbSql.append(" and areaId= ? ");
	         }
	         if(StringUtils.isNotBlank(supplierCode)){
	        	 sbSql.append(" and supplierCode like CONCAT('%',?,'%') or supplierName  like CONCAT('%',?,'%') ");
	         }
			 sbSql.append(" ) AS A ");
			 sbSql.append(" LEFT JOIN ( SELECT storeId,orderId,supplierId,orderPrice,zfee,rebate,STATUS,addTime ");
			 sbSql.append(" FROM SpOrderInfo WHERE  addTime >= DATE_FORMAT(?, '%Y-%m-%d') AND addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') AND STATUS != 6 ");
			 sbSql.append(" ) AS B ON A.id = B.supplierId ");
			 sbSql.append(" LEFT JOIN ( SELECT orderId, sum(quantity) quantity, sum(IF(fee < 0, fee, 0)) ylsplr, ");
			 sbSql.append(" sum(IF(fee < 0, totalPrice, 0)) totalPrice, sum(IF(fee > 0,(fee * quantity), 0)) fy ");
			 sbSql.append(" FROM SpOrderDetail GROUP BY orderId ) AS C ON B.orderId = C.orderId ");
			 sbSql.append(" LEFT JOIN (SELECT id, NAME FROM Region) AS D ON A.areaId = D.id  GROUP BY A.id ");
			 sbSql.append(" ) xx ");
	         
	         //System.err.println(sbSql.toString());
	         exportBigData.exportToZip(headers, out, 50000, sbSql.toString(), sqlParams);
		} catch (Exception e) {
			logger.error("商品数据分析 控制器中的exportProductList方法异常：{}",e.getMessage());
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}
