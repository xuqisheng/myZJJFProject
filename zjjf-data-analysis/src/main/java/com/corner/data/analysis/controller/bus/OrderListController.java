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
import com.corner.data.analysis.beans.vo.OrderListVo;
import com.corner.data.analysis.beans.vo.StoreVo;
import com.corner.data.analysis.common.export.bigdata.ExportBigData;
import com.corner.data.analysis.controller.ScmsBaseWebController;
import com.corner.data.analysis.service.bus.OrderListService;
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
@RequestMapping(value = "/analysis/orderList")
public class OrderListController extends ScmsBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderListController.class);
	
	private final String LOGIN_URL="/login/index";
	
	@Autowired
	private ExportBigData exportBigData;
	
	@Autowired
	private OrderListService orderListService;
	
	/**
	 * 
	 * @Title: toStatisPage 
	 * @Description: 跳转到店铺信息页面
	 * @return String    返回类型
	 * @author 元宝	yuanbao@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/toOrderListPage.do")
	public String toOrderListPage(Model model,OrderListVo orderVo){
		
		try {
			Subject subject = SecurityUtils.getSubject();
			if(subject.isAuthenticated()){
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
			model.addAttribute("orderVo", orderVo);
		} catch (Exception e) {
			logger.error("店铺信息 控制器中的toStorePage方法异常：{}",e.getMessage());
		}
		
		return "analysis/orderDetailList";
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
	public Object getPagerList(HttpServletRequest request, Model model,OrderListVo orderVo){
		
		try {
			logger.info("查询店铺信息起始时间：{},结束时间：{}",orderVo.getStartTime(),orderVo.getEndTime());
			Pager<OrderListVo> pager = orderListService.findPagerList(orderVo);
			pager.getMap().put("orderVo", orderVo);
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
             
	         String[] headers = { "区域","下单时间", "父订单号", "订单号", "商铺名称", "商铺联系人", "店铺电话", "店铺地址", "商品价格",
	        		 "订单价格", "商品编码", "品牌","商品ID","商品名称","商品规格","商品单价","商品数量","商品金额","总毛利","批发商ID","商铺定格"
	        		 ,"商铺定格ID","小分类","大分类","订单状态","支付状态","优惠券金额","满减"};
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
			 sbSql.append(" SELECT rr.`name` AS areaName, ");
			 sbSql.append(" DATE_FORMAT(si.addTime, '%Y-%m-%d') AS addTime, ");
			 sbSql.append(" sd.orderId,sd.orderId2,si.storeName,si.consignee, ");
			 sbSql.append(" si.mobile,si.address,si.goodsPrice,si.orderPrice, ");
			 sbSql.append(" sd.barCode,ib.brand,ib.id AS productId,sd. NAME AS productName, ");
			 sbSql.append(" sd.spec,sd.price,sd.quantity,sd.totalPrice, ");
			 sbSql.append(" IFNULL(si.zmaoli, 0.00) zmaoli,sp.id AS supplierId, ");
			 sbSql.append(" sg. NAME AS spGroupName,sg.id AS spGroupId, ");
			 sbSql.append(" itc. NAME AS smCategory,itc2. NAME AS bgCegory, ");
			 sbSql.append(" si. STATUS,si.supportmetho,si.coupon,si.rebate ");
			 sbSql.append(" FROM SpOrderDetail AS sd ");
			 sbSql.append(" JOIN SpOrderInfo AS si ON sd.orderId2 = si.orderId ");
			 sbSql.append(" JOIN ItemBase AS ib ON ib.mdseId = sd.barCode ");
			 /*sbSql.append(" JOIN PlantItem as pi on sd.itemId = pi.id ");
			 sbSql.append(" JOIN ItemBase AS ib ON ib.id = pi.itemBaseId ");*/
			 
			 sbSql.append(" JOIN Supplier AS sp ON sp.id = si.supplierId ");
			 sbSql.append(" JOIN SpGroupMap AS sgm ON sp.id = sgm.spId ");
			 sbSql.append(" JOIN SpGroup sg ON sgm.groupId = sg.id ");
			 sbSql.append(" JOIN ItemCatelog itc ON ib.cateId = itc.id ");
			 sbSql.append(" JOIN ItemCatelog itc2 ON itc.pid = itc2.id ");
			 sbSql.append(" LEFT JOIN Store SS ON SS.ID = si.storeId ");
			 sbSql.append(" LEFT JOIN Region rr ON rr.id = SS.areaId ");
			 sbSql.append(" WHERE si.`status` != 1 AND si.`status` != 6 ");
	         if(StringUtils.isNotBlank(startTime)){
	        	 sbSql.append(" and sd.addTime >=DATE_FORMAT(?, '%Y-%m-%d') ");
	         }
	         if(StringUtils.isNotBlank(endTime)){
	        	 sbSql.append(" AND sd.addTime <=DATE_FORMAT(DATE_ADD(?,INTERVAL 1 DAY), '%Y-%m-%d') ");
	         }
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
