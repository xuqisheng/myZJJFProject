package com.corner.kefu.controller.sp;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.AcActionRecord;
import com.corner.core.beans.CustomerService;
import com.corner.core.beans.Region;
import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.Store;
import com.corner.core.beans.Supplier;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.SpOrderInfoRo;
import com.corner.kefu.beans.ro.sp.PlantItemRo;
import com.corner.kefu.beans.ro.sp.SupplierRo;
import com.corner.kefu.beans.vo.sp.SpOrderInfoVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.SpOrderInfoService;
import com.corner.kefu.service.sp.SpItemBaseService;
import com.corner.kefu.service.sp.SpOrderDetailService;
import com.corner.kefu.service.sp.SpRegionService;
import com.corner.kefu.service.sp.SpStoreService;
import com.corner.kefu.service.sp.SpSupplierService;
import com.corner.kefu.utils.ExportExcel;



@Controller
@RequestMapping(value = "Customer/Finance")
public class PcFinanceController extends KefuBaseWebController {

	private static final Logger logger = LoggerFactory.getLogger(PcFinanceController.class);
	
	
	@Autowired
	SpSupplierService supplierService;

	@Autowired
	SpOrderInfoService spOrderInfoService;

	@Autowired
	SpOrderDetailService orderDetailService;

	@Autowired
	SpItemBaseService itemBaseService;

	@Resource
	ExportExcel ex;

	@Autowired
	SpStoreService storeService;

	@Autowired
	SpRegionService regionService;
	
	/**
	 * 显示客服财务信息
	 * 
	 * @author xgh 2015年7月10日下午5:01:54
	 * @Desc
	 * @return
	 */
	@RequestMapping(value = "/FinanceList.do")
	public String finnanceList(SpOrderInfoRo spOrderInfoRo, PlantItemRo plantItem, HttpServletRequest request, Model model) {
		Map<String, Object> map = null;
		try {
			CustomerService service = getCurrentUser(CustomerService.class, request);
			// 分页参数
			int pageIndex = 1;
			int pageSize = 50;
			String pageIndexStr = request.getParameter("pageIndex");
			if (!StringUtil.stringIsNullOrEmpty(pageIndexStr)) {
				pageIndex = Integer.parseInt(pageIndexStr);
			}
			spOrderInfoRo.setPageIndex(pageIndex);
			spOrderInfoRo.setPageSize(pageSize);
			// 区域信息
			//通用省得集合
			map =  new HashMap<String, Object>();
			map.put("pId", 1);
			List<Region> shengList = regionService.getRegionByPidOrRegionLevel(map);
			model.addAttribute("shengList", shengList);
			
			if(spOrderInfoRo.getProvince() != null){
				//市的集合
				map = new HashMap<String, Object>();
				map.put("pId", spOrderInfoRo.getProvince());
				List<Region> shiList = regionService.getRegionByPidOrRegionLevel(map);
				model.addAttribute("shiList", shiList);
			}
			if(spOrderInfoRo.getCity() != null){
				//区的集合
				map =  new HashMap<String, Object>();
				map.put("pId", spOrderInfoRo.getCity());
				List<Region> areaList = regionService.getRegionByPidOrRegionLevel(map);
				model.addAttribute("areaList", areaList);
			}
			
			// 财务分页查询
			spOrderInfoRo.setWhoStatus("c.kfStatus");// 客服状态
			spOrderInfoRo.setAreaId(spOrderInfoRo.getAreaid());
			List<SpOrderInfoVo> spOrderInfos = spOrderInfoService.selectSpOrderInfoFinace(spOrderInfoRo);
			Integer count = spOrderInfoService.selectSpOrderCountOfFinace(spOrderInfoRo);
			// 存入页面信息
			model.addAttribute("spOrderInfos", spOrderInfos);
			model.addAttribute("spOrderInfoRo", spOrderInfoRo);
			this.pageUtil(pageIndex, count, pageSize, request, model);
			return "order/finance-info";
		} catch (Exception e) {
			logger.error("PcSpOrderInfosController's method getSpOrderInfos has an error:{}", e);
		}
		return error("出错了", model, request);
	}

	@RequestMapping(value = "/getFinanceSpOrderInfo.do")
	public String getFinanceSpOrderInfo(SpOrderInfoRo spOrderInfoRo, PlantItemRo plantItem, HttpServletRequest request, Model model) {
		try {
			
			spOrderInfoRo.setPageIndex(spOrderInfoRo.getPageIndex());
			spOrderInfoRo.setPageSize(spOrderInfoRo.getPageSize());
			//财务分页查询
			spOrderInfoRo.setWhoStatus("c.kfStatus");//客服状态
			spOrderInfoRo.setAreaId(spOrderInfoRo.getAreaid());
			List<SpOrderInfoVo> spOrderInfos = spOrderInfoService.selectFinaceSpOrderInfo(spOrderInfoRo);
			BigDecimal allorderPirce =new BigDecimal(0);
			BigDecimal allorderFee =new BigDecimal(0);
			for (Iterator<SpOrderInfoVo> iterator = spOrderInfos.iterator(); iterator.hasNext();) {
				SpOrderInfoVo spOrderInfoVo=iterator.next();
				if(spOrderInfoVo.getSpOrderPrice()!=null){
					allorderPirce=allorderPirce.add(spOrderInfoVo.getSpOrderPrice());
				}
				if(spOrderInfoVo.getSpZfee()!=null){
					allorderFee=allorderFee.add(spOrderInfoVo.getSpZfee());					
				}
			}
			Integer count = spOrderInfoService.selectCountFinaceSpOrderInfo(spOrderInfoRo);
			//存入页面信息
			model.addAttribute("spOrderInfos", spOrderInfos);
			model.addAttribute("spOrderInfoRo", spOrderInfoRo);
			model.addAttribute("allPrice", allorderPirce);
			model.addAttribute("allFei", allorderFee);
			this.pageUtil(spOrderInfoRo.getPageIndex(), count, spOrderInfoRo.getPageSize(), request, model);
			return "order/finance-sp-order-info";
		} catch (Exception e) {
			logger.error("PcSpOrderInfosController's method getFinanceSpOrderInfo has an error:{}", e);
		}
		return error("出错了", model, request);
	}

	/**
	 * 对账订单详情
	 * 
	 * @author xgh 2015年7月14日
	 * @param spOrderInfoRo
	 * @return
	 */
	@RequestMapping(value = "/getSpOrderInfoFinance.do")
	public String getSpOrderInfoFinance(SpOrderInfoRo spOrderInfoRo, Model model, HttpServletRequest request) {
		try {
			if (StringUtil.stringIsNullOrEmpty(spOrderInfoRo.getOrderid())) {
				return error("请求的参数有误", model, request);
			}
			SpOrderInfo so = spOrderInfoService.getSpOrderInfo(spOrderInfoRo);
			if (so == null) {
				return error("该订单不存在", model, request);
			}
			// 重新发送
			String send_agin = request.getParameter("send_agin");
			if (send_agin != null) {
				model.addAttribute("send_agin", send_agin);
			}
			// 订单详细
			List<SpOrderDetail> orderDetails = orderDetailService.getOrderDetail(spOrderInfoRo.getOrderid());
			BigDecimal bigDecimal = new BigDecimal(0.00);
			int ordernum = 0;

			for (SpOrderDetail orderDetail : orderDetails) {
				bigDecimal = bigDecimal.add(orderDetail.getTotalPrice());
				ordernum += orderDetail.getQuantity();
			}

			SupplierRo s = new SupplierRo();
			Store as = storeService.getStoreById(so.getStoreId());
			if (as == null) {
				return error("店铺不存在", model, request);
			}
			s.setAreaid(as.getAreaId());
			// 供应商信息
			List<SupplierVo> su = supplierService.getSuppliers(s).getList();
			model.addAttribute("supplierList", su);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderInfo", so);
			map.put("ordernum", ordernum);
			map.put("total", bigDecimal);
			map.put("orderDetail", orderDetails);
			model.addAttribute("map", map);
			return "order/finance-order-detail";
		} catch (Exception e) {
			logger.error("PcSpOrderInfosController's method getSpOrderInfo has an error:{}", e);
		}
		return error("出错了", model, request);
	}


	/**
	 * 对账审核
	 * 
	 * @author xgh 2015年7月13日下午5:56:09
	 * @Desc
	 * @return
	 */
	@RequestMapping(value = "/auditSpOrderInfo.do")
	@ResponseBody
	public Object auditSpOrderInfo(
			String strIds, 
			int status,
			String type,
			String note, 
			Model model, HttpServletRequest request) {
		try {
			CustomerService service = getCurrentUser(CustomerService.class, request);
			if (!StringUtils.isEmpty(strIds) && status>0 && !StringUtils.isEmpty(type)) {// 批量审核
				String orderIds[] = strIds.split(",");
				for (int i = 0; i < orderIds.length; i++) {
					SpOrderInfo spOrderInfo = new SpOrderInfo();
					spOrderInfo.setId(orderIds[i]);
					if("kf".equals(type)){
						spOrderInfo.setKfId(service.getId());
						spOrderInfo.setKfName(service.getUserName());
						spOrderInfo.setKfStatus((byte)status);
						spOrderInfo.setKfCheckTime(new Date());
						spOrderInfo.setKfnote(note);
					}else if("sp".equals(type)){
						spOrderInfo.setSpStatus((byte) status);
						spOrderInfo.setSpCheckTime(new Date());
						spOrderInfo.setSpRemark(note);
					}else{
						return ResponseUtils.sendMsg(false, "参数错误");
					}
					spOrderInfoService.updateGetOrderAcStatus(spOrderInfo);
				}
				//记录操作日志
				AcActionRecord acrecord =new  AcActionRecord();
				if(service != null && service.getId()!= null){
					acrecord.setUserId(service.getId()+"");
				}
				acrecord.setObjectId("SporderInfo");
				acrecord.setActionDate(strIds+"|"+note);
				acrecord.setActionTime(new Date());
				acrecord.setActionType(status);
				spOrderInfoService.addAcSystemAction(acrecord);
				return ResponseUtils.sendMsg(true, "审核成功");
			}else{
				return ResponseUtils.sendMsg(false, "参数错误");
			}
		} catch (Exception e) {
			logger.error("PcSpOrderInfosController's method auditSpOrderInfo has an error:{}", e);
		}
		return ResponseUtils.sendMsg(false, "审核失败");
	}

}
