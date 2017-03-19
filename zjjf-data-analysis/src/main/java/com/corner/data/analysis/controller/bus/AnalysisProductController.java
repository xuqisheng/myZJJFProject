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
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.data.analysis.beans.vo.AnalysisProductVo;
import com.corner.data.analysis.common.export.bigdata.ExportBigData;
import com.corner.data.analysis.controller.ScmsBaseWebController;
import com.corner.data.analysis.service.bus.AnalysisProductService;
import com.corner.data.analysis.utils.DateUtil;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.utils.DateUtils;
/**
 * ClassName: GlobalStatisController
 * 
 * @Description: 商品数据分析 控制器
 * @author 元宝
 * @date 2016年01月8日
 */
@Controller
@RequestMapping(value = "/analysis/product")
public class AnalysisProductController extends ScmsBaseWebController {
	private static Logger logger = LoggerFactory.getLogger(AnalysisProductController.class);
	
	private final String LOGIN_URL="/login/index";
	@Autowired
	private ExportBigData exportBigData;
	@Autowired
	private AnalysisProductService productService;
	
	/**
	 * 
	 * @Title: toStatisPage 
	 * @Description: 跳转到商品数据分析页面
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/toProductPage.do")
	public String toProductPage(Model model,AnalysisProductVo productVo){
		
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
			
			Date monthStartDate =DateUtil.getMonthStart(new Date());
			productVo.setStartTime((DateUtil.fmtDateToStr(monthStartDate, "yyyy-MM-dd")));
			productVo.setEndTime(DateUtil.fmtDateToStr(new Date(), "yyyy-MM-dd"));
		} catch (Exception e) {
			logger.error("商品数据分析 控制器中的toStatisPage方法异常：{}",e.getMessage());
		}
		
		 model.addAttribute("productVo", productVo);
		return "analysis/analysisProductList";
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
	public Object getPagerList(HttpServletRequest request, Model model,AnalysisProductVo productVo){
		
		try {
			logger.info("统计列表查询开始，起始时间：{},结束时间：{}",productVo.getStartTime(),productVo.getEndTime());
			//return productService.findPagerList(productVo);
			Pager<AnalysisProductVo> pager = productService.findPagerList(productVo);
			pager.getMap().put("productVo", productVo);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "统计查询异常"+e);
		}
	}
	
	/**
	 * 
	 * @Title: toStatisPage 
	 * @Description: 跳转到商品数据分析页面
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/getProductDetailById.do")
	public String getProductDetailById(Model model,AnalysisProductVo productVo){
		List<AnalysisProductVo> proList = null;
		try {
			//查询列表信息
			proList = productService.getProductDetailById(productVo);
			//2、如果查询列表有值，则提取一条对象取公共的区域+商品名称
			if(null != proList && proList.size()>0){
				model.addAttribute("info", proList.get(0));
			}
		} catch (Exception e) {
			logger.error("商品数据分析 控制器中的getProductDetailById方法异常：{}",e.getMessage());
		}
		model.addAttribute("proList", proList);
		return "analysis/analysisProductDetail";
	}
	
	/**
	 * 导出商品数据分析数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value="/export.do")
    public void exportProductList(HttpServletRequest request, HttpServletResponse response,AnalysisProductVo productVo) {
		 OutputStream out = null;
		try {
			 String fileName = DateUtils.getDate("yyyyMMddHHmmss");
			 response.setHeader("content-disposition", "attachment;filename=" + fileName + ".zip");  
             out = response.getOutputStream();		
             
	         String[] headers = { "区域", "商品条码", "一级分类", "二级分类", "商品名称" , "规格", "市场价", 
            					"合作商进货价", "合作商出货价", "合作商毛利", "下单次数","销售数量","销售金额","商品费用","费用率"};
	         List<String>  list = new ArrayList<String>();
	         String startTime = productVo.getStartTime();
	         String endTime = productVo.getEndTime();
	         String areaId = productVo.getAreaId();
	         String spmc = productVo.getSpmc();
	         list.add(startTime);
	         list.add(endTime);
	         
	         if(StringUtils.isNotBlank(areaId)){
	        	 list.add(areaId);
	         }
	         if(StringUtils.isNotBlank(spmc)){
	        	 list.add(spmc);
	        	 list.add(spmc);
	         }
	         //将list转化为数组格式
	         final int size = list.size();
	         Object[] sqlParams = (Object[])list.toArray(new Object[size]);
	         
	         //, concat(left (SUM(C.fyje) / SUM(A.xsje) *100,2),'%') as flratio 
	         StringBuffer sbSql = new StringBuffer();
	         sbSql.append(" SELECT F. NAME AS areaName, B.sptm, E.yjfl, D.ejfl, ")
				  .append(" B.spmc, B.spec, A.scj, A.hzsjhj, A.hzschj, A.maoli, ")
				  .append(" C.xdcs, C.xssl, C.xsje xsje, C.fyje, concat(left (C.fyje/ C.xsje*100,4),'%') as flratio ")
				  .append(" FROM ( ")
				  .append(" SELECT id, itemBaseId, areaId, areaPrice hzschj, plantDisPrice hzsjhj, plantMemPrice scj, maoli FROM PlantItem ) A ")
				  .append(" LEFT JOIN ( SELECT id, itemId, count(itemId) xdcs, SUM(quantity) xssl, SUM(totalPrice) xsje, SUM(fee) fyje ")
				  .append(" FROM SpOrderDetail WHERE addTime >= DATE_FORMAT(?, '%Y-%m-%d') AND addTime <= DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') ")
				  .append(" GROUP BY itemId ) C ON A.id = C.itemId ")
				  .append(" LEFT JOIN ( SELECT id, mdseId sptm, cateId, NAME spmc, spec FROM ItemBase ) B ON A.itemBaseId = B.id ")
				  .append(" LEFT JOIN ( SELECT id, NAME ejfl, pid FROM ItemCatelog ")
				  .append(" ) D ON B.cateId = D.id LEFT JOIN ( SELECT id, NAME yjfl FROM ItemCatelog ")
				  .append(" ) E ON E.id = D.pid LEFT JOIN (SELECT id, NAME FROM Region) F ON F.id = A.areaId ")
				  .append(" WHERE 1 = 1 ");
				  if(StringUtils.isNotBlank(areaId)){
					  sbSql.append(" AND A.areaId = ? ");
		          }
		          if(StringUtils.isNotBlank(spmc)){
		        	 sbSql.append(" AND B.spmc LIKE CONCAT('%' ,?, '%') OR B.sptm LIKE CONCAT('%' ,?, '%') ");
		          }
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
