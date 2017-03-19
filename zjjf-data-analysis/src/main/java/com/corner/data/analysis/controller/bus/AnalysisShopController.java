package com.corner.data.analysis.controller.bus;

import java.util.Date;
import java.util.List;

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
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.data.analysis.beans.vo.AnalysisShopSaleVo;
import com.corner.data.analysis.beans.vo.AnalysisShopVo;
import com.corner.data.analysis.controller.ScmsBaseWebController;
import com.corner.data.analysis.service.bus.AnalysisShopService;
import com.corner.data.analysis.utils.DateUtil;
import com.corner.scms.config.SessionConfig;
/**
 * ClassName: AnalysisShopController
 * 
 * @Description: 商铺数据分析 控制器
 * @author 元宝
 * @date 2016年01月8日
 */
@Controller
@RequestMapping(value = "/analysis/shop")
public class AnalysisShopController extends ScmsBaseWebController {
	private static Logger logger = LoggerFactory.getLogger(AnalysisShopController.class);
	
	private final String LOGIN_URL="/login/index";
	
	@Autowired
	private AnalysisShopService showService;
	
	/**
	 * 
	 * @Title: toShopPage 
	 * @Description: 跳转到商铺数据分析页面
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/toShopPage.do")
	public String toShopPage(Model model,AnalysisShopVo shopVo){
		
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
			shopVo.setStartTime((DateUtil.fmtDateToStr(monthStartDate, "yyyy-MM-dd")));
			shopVo.setEndTime(DateUtil.fmtDateToStr(new Date(), "yyyy-MM-dd"));
			//2、根据查询时间段获取相关统计列表信息
			List<AnalysisShopVo> shopList = showService.findShopSummaryList(shopVo);
			model.addAttribute("shopList", shopList);
			model.addAttribute("shopVo", shopVo);
		} catch (Exception e) {
			logger.error("商铺数据分析 控制器中的toShopPage方法异常：{}",e.getMessage());
		}
		
		return "analysis/analysisShopIndex";
	}
	
	/**
	 * 
	 * @Title: getShopList 
	 * @Description: 商铺核心数据汇总查询
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/getShopList.do")
	public String getShopList(Model model,AnalysisShopVo shopVo){
		
		try {
			logger.info("统计列表查询开始，起始时间：{},结束时间：{}",shopVo.getStartTime(),shopVo.getEndTime());
			//2、根据查询时间段获取相关统计列表信息
			List<AnalysisShopVo> shopList = showService.findShopSummaryList(shopVo);
			model.addAttribute("shopList", shopList);
			model.addAttribute("shopVo", shopVo);
		} catch (Exception e) {
			logger.error("商铺数据分析 控制器中的getShopList方法异常：{}",e.getMessage());
		}
		return "analysis/analysisShopIndex";
	}
	
	/**
	 * 
	 * @Title: getShopList 
	 * @Description: 商铺核心数据汇总查询
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/getShopSaleList.do")
	@ResponseBody
	public Object getShopSaleList(Model model,AnalysisShopSaleVo shopSaleVo){
		
		try {
			logger.info("统计列表查询开始，区域：{},起始时间：{},结束时间：{}",shopSaleVo.getAreaName(),shopSaleVo.getStartTime(),shopSaleVo.getEndTime());
			Pager<AnalysisShopSaleVo> pager = showService.findShopSaleList(shopSaleVo);
			pager.getMap().put("shopSaleVo", shopSaleVo);
			return pager;
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "统计查询异常"+e);
		}
	}
	
	/**
	 * 
	 * @Title: getShopSaleDetail 
	 * @Description: 店铺销售基本信息查询
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/getShopSaleDetail.do")
	public String getShopSaleDetail(Model model,AnalysisShopSaleVo shopSaleVo){
		
		try {
			logger.info("统计列表查询开始，店铺id:{},起始时间：{},结束时间：{}",shopSaleVo.getId(),shopSaleVo.getStartTime(),shopSaleVo.getEndTime());
			//1、根据店铺id查询店铺销售基本信息
			List<AnalysisShopSaleVo> shopBaseList = showService.findShopSaleBaseById(shopSaleVo);
			if(null != shopBaseList && shopBaseList.size()>0){
				model.addAttribute("shopBaseVo", shopBaseList.get(0));
			}
			//2、根据店铺id查询店铺销售明细列表信息
			List<AnalysisShopSaleVo> shopDetailList = showService.findShopSaleDetailList(shopSaleVo);
			model.addAttribute("shopSaleVo", shopSaleVo);
			model.addAttribute("shopDetailList", shopDetailList);
		} catch (Exception e) {
			logger.error("商铺数据分析 控制器中的getShopSaleDetail方法异常：{}",e.getMessage());
		}
		return "analysis/analysisShopSaleDetail";
	}
	
}
