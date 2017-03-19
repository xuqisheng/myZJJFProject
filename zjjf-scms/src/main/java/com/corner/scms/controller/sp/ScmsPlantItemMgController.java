package com.corner.scms.controller.sp;

import com.corner.core.beans.ItemCatelog;
import com.corner.core.beans.PlantItem;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.PlantItemRo;
import com.corner.scms.beans.ro.StockManagerParamRo;
import com.corner.scms.beans.vo.PlantItemVo;
import com.corner.scms.beans.vo.ScmsUserTypeVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.callable.CallAbleService;
import com.corner.scms.service.erp.ERPWarehouseService;
import com.corner.scms.service.sp.ScmsItemCatelogMgService;
import com.corner.scms.service.sp.ScmsPlantItemMgService;
import com.corner.scms.service.sp.ScmsStockLogMgService;
import com.corner.scms.service.sp.ScmsSupplierMgService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/scms/plantItem")
public class ScmsPlantItemMgController extends ScmsBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(ScmsPlantItemMgController.class);
	@Autowired
	private ScmsPlantItemMgService plantItemMgService;
	//core工程里面的
	@Autowired
	private ScmsItemCatelogMgService scmsItemCatelogMgService;

	@Autowired
	private ScmsStockLogMgService scmsStockLogMgService;
    
	@Autowired
    ScmsSupplierMgService scmsSupplierMgService;
	
	@Autowired
	CallAbleService callAbleService;
	@Autowired
	ERPWarehouseService erpWarehouseService;
	
	//跳转到批发商库存页面
	@RequestMapping(value="/plantItemPage.do")
	public String plantItemPage(HttpServletRequest request,Model model){
		String commodityIdAndName = request.getParameter("commodityIdAndName");
		model.addAttribute("commodityIdAndName", commodityIdAndName);
		return "/product/stock";
	}
	//批发商工作台跳转到批发商库存预警页面
	@RequestMapping(value="/returnStockCautionPage.do")
	public String returnStockCautionPage(HttpServletRequest request,Model model){
		return "/index/stock-caution";
	}
	
//	/**
//	 * 获得所有的库存
//	 * 
//	 * @author longwu at 2015年12月6日上午11:03:33
//	 * @email tiezhongtang@izjjf.cn
//	 * @param String
//	 * @return
//	 */
//	@RequestMapping(value = "/getAllPlantItem.do")
//	@ResponseBody
//	public Object getAllPlantItem(HttpServletRequest request, Model model, StockManagerParamRo stockManagerParam,Integer pageIndex) {
//		try {
//			//得到供应商对象
//			Supplier supplier = this.getCurrentUser(Supplier.class, request);
//			stockManagerParam.setSpId(supplier.getId());
//			stockManagerParam.setPageIndex(pageIndex+1);
//			stockManagerParam.setCommodityIdAndName(stockManagerParam.getCommodityIdAndName().trim());
//			Pager<PlantItemVo>  plantItemVoList = plantItemMgService.getAllPlantItem(stockManagerParam);
//			if(plantItemVoList !=null){
//				return new Pager<PlantItemVo>(plantItemVoList.getTotalSize(), plantItemVoList.getList());
//			} else {
//				return new Pager<PlantItemVo>(plantItemVoList.getTotalSize(), plantItemVoList.getList());
//			}
//		} catch (Exception e) {
//			logger.error("", e);
//
//		}
//		return null;
//	}

//	/**
//	 * 根据批发商id和商品名查询所有的商品信息
//	 * 
//	 * @author 龙五 at 2015年12月6日上午11:36:46
//	 * @email longwu@izjjf.cn
//	 * @param Object
//	 * @return
//	 */
//	@RequestMapping("/getCommodityName.do")
//	@ResponseBody
//	public Object getCommodityName(HttpServletRequest request, Model model, StockManagerParamRo stockManagerParam) {
//		try {
//
//			//得到供应商对象
//			Supplier supplier = this.getCurrentUser(Supplier.class, request);
//			if(stockManagerParam.getCommodityName() == null){
//				stockManagerParam.setCommodityName("");
//			}
//			stockManagerParam.setSpId(supplier.getId());
//			List<PlantItemVo> CommodityNameList = plantItemMgService.getCommodityName(stockManagerParam);
//			if(CommodityNameList!=null && CommodityNameList.size()>0){
//				return ResponseUtils.sendMsg(true,CommodityNameList);
//			}
//		} catch (Exception e) {
//			logger.error("", e);
//			return ResponseUtils.sendMsg(false);
//		}
//		return ResponseUtils.sendMsg(false);
//	}

//	/**
//	 * 修正库存
//	 * @author 龙五 at  2015年12月6日上午11:36:46
//	 * @email longwu@izjjf.cn
//	 * @param Object
//	 * @return
//	 */
//	@RequestMapping("/updatePlantItem.do")
//	@ResponseBody
//	public Object updatePlantItem(HttpServletRequest request, Model model, StockManagerParamRo stockManagerParam) {
//		Supplier supplier = this.getCurrentUser(Supplier.class, request);
//		try {
//			stockManagerParam.setSpId(supplier.getId());
//			if (stockManagerParam.getNum() == null ||stockManagerParam.getxType() == null ||stockManagerParam.getItemBaseId()==null) {
//				return ResponseUtils.sendMsg(false,"参数错误");
//			}
//			plantItemMgService.updatePlantItemA(stockManagerParam);
//			
//			
//			//获取页面数据
//			String num = stockManagerParam.getNum().toString();
//			PlantItemVo plantItem = new PlantItemVo();
//			Map<String, Object>param = new HashMap<String, Object>();
//			param.put("spId", supplier.getId());
//			param.put("itemBaseId", stockManagerParam.getItemBaseId());
//			//根据商品编号和spid查出来的储存数量
//			plantItem = plantItemMgService.getGoodsStockByitemBaseIdSpId(param);
//			ScmsStockLog scmsStockLog = new ScmsStockLog();
//			scmsStockLog.setSpId(supplier.getId());
//			scmsStockLog.setXtype(Byte.parseByte("3"));
//			scmsStockLog.setAddTime(new Date());
//			scmsStockLog.setPlantItemId(plantItem.getId());
//			scmsStockLog.setStatus(Byte.parseByte("1"));
//			scmsStockLog.setIsDelete(false);
//			scmsStockLog.setRemark(stockManagerParam.getRemark());
//			scmsStockLog.setCurStock(plantItem.getGoodsStock());
//			if(stockManagerParam.getxType()==1){
//				num = "-"+num;
//			}else if(stockManagerParam.getxType()==2){
//				num = "+"+num;
//			}
//			scmsStockLog.setQuantity(Integer.parseInt(num));
//			scmsStockLogMgService.addStockLog(scmsStockLog);
//			return ResponseUtils.sendMsg(true,"修正成功");
//		} catch (Exception e) {
//			logger.error("", e);
//			return ResponseUtils.sendMsg(false,"修正异常");
//		}
//	}

//	/**
//	 * 更新上下限
//	 * @author 龙五 at  2015年12月6日上午11:36:46
//	 * @email longwu@izjjf.cn
//	 * @param Object
//	 * @return
//	 */
//	@RequestMapping("/updateUpperOrLower.do")
//	@ResponseBody
//	public Object updateUpperOrLower(HttpServletRequest request, Model model, StockManagerParamRo stockManagerParam) {
//		try {
//			Supplier supplier = this.getCurrentUser(Supplier.class, request);
//			stockManagerParam.setSpId(supplier.getId());
//			plantItemMgService.updateUpperOrLower(stockManagerParam);
//			return ResponseUtils.sendMsg(true);
//		} catch (Exception e) {
//			logger.error("", e);
//			return ResponseUtils.sendMsg(false);
//		}
//	}

	/**
	 * 查出所有的商品名和商品基本表的id
	 * @author 龙五 at  2015年12月6日上午11:36:46
	 * @email longwu@izjjf.cn
	 * @param
	 * @return
	 */
	@RequestMapping("/getAllCommodityNameAndItemBaseId.do")
	@ResponseBody
	public Object getAllCommodityNameAndItemBaseId(HttpServletRequest request, Model model) {
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		String commodityName = request.getParameter("rcommodityName");
		if(commodityName == null){
			commodityName="";
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("commodityName", commodityName);
		param.put("spId", supplier.getId());
		try {

			List<PlantItemVo> PlantItemVoList = plantItemMgService.getAllCommodityNameAndItemBaseId(param);
			if(PlantItemVoList != null && PlantItemVoList.size()>0){
				return ResponseUtils.sendMsg(true,PlantItemVoList);
			}
		} catch (Exception e) {
			logger.error("", e);
			return ResponseUtils.sendMsg(false);
		}
		return ResponseUtils.sendMsg(false);
	}
//	/**
//	 * 商品入库
//	 * @author 龙五 at  2015年12月6日上午11:36:46
//	 * @email longwu@izjjf.cn
//	 * @param Object
//	 * @return
//	 */
//	@RequestMapping("/CommodityRuKu.do")
//	public String addCommodity(HttpServletRequest request, Model model) {
//		StockManagerParamRo stockManagerParam = null;
//		ScmsStockLog scmsStockLog = null;
//		PlantItemVo plantItem = null; 
//		Map<String, Object> param = null;
//		try {
//			//得到供应商对象
//			Supplier supplier = this.getCurrentUser(Supplier.class, request);
//			if(supplier == null)
//				return "/login/index";
//			String[] itemBaseId = request.getParameterValues("ritemBaseId");
//			//现在输入的库存
//			String[] inputGoodsStock = request.getParameterValues("inputGoodsStock");
//			//现在输入的价格
//			String[] inputPlantDisPrice = request.getParameterValues("inputPlantDisPrice");
//			for (int i = 0; i < itemBaseId.length; i++) {
//				Integer sInputGoodsStock = (inputGoodsStock[i] ==null || "".equals(inputGoodsStock[i])?0:Integer.parseInt(inputGoodsStock[i]));
//				BigDecimal sInputPlantDisPrice = inputPlantDisPrice[i] == null ||"".equals(inputPlantDisPrice[i])?new BigDecimal(0.00):new BigDecimal(inputPlantDisPrice[i]);
//				
//				
//				stockManagerParam = new StockManagerParamRo();
//				if(itemBaseId[i] == null || "".equals(itemBaseId[i])){
//					model.addAttribute("mess", "商品名称可能没输,或者此商品不存在！");
//					return "product/stock";
//				}else{
//					stockManagerParam.setItemBaseId(Integer.parseInt(itemBaseId[i]));
//				}
//				stockManagerParam.setSpId(supplier.getId());
//				stockManagerParam.setUpdateTime(new Date());
//				stockManagerParam.setxType(Byte.parseByte("2"));
//				stockManagerParam.setNum(sInputGoodsStock);
//				stockManagerParam.setScInPrice(sInputPlantDisPrice);
//				plantItemMgService.updatePlantItemA(stockManagerParam);
//				
//				param = new HashMap<String, Object>();
//				param.put("spId", supplier.getId());
//				
//				if(itemBaseId[i] == null || "".equals(itemBaseId[i])){
//					model.addAttribute("mess", "商品名称可能没输,或者此商品不存在！");
//					return "product/stock";
//				}else{
//					param.put("itemBaseId", Integer.parseInt(itemBaseId[i]));
//				}
//				
//				//根据商品编号和spid查出来的储存数量
//				plantItem = plantItemMgService.getGoodsStockByitemBaseIdSpId(param);
//				Integer ygoodsStock = plantItem.getGoodsStock()==null?0:plantItem.getGoodsStock();
//				scmsStockLog = new ScmsStockLog();
//				scmsStockLog.setAddTime(new Date());
//				scmsStockLog.setIsDelete(false);
//				scmsStockLog.setStatus(Byte.parseByte("1"));
//				scmsStockLog.setSpId(supplier.getId());
//				scmsStockLog.setXtype(Byte.parseByte("2"));
//				scmsStockLog.setPlantItemId(plantItem.getId());
//				scmsStockLog.setQuantity(sInputGoodsStock);
//				scmsStockLog.setCurStock(ygoodsStock);
//				scmsStockLog.setPrice(sInputPlantDisPrice);
//				scmsStockLogMgService.addStockLog(scmsStockLog);
//			}
//			model.addAttribute("mess", "success");
//			return "product/stock";
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//			model.addAttribute("mess", "error");
//			return "product/stock";
//		} catch(Exception e1){
//			e1.printStackTrace();
//			model.addAttribute("mess", "error");
//			return "product/stock";
//		}
//	}


	/************************************ 商品管理 begin ***********************************/
	
	/**
	 * 
	* @Title: toProductAdd 
	* @Description:跳转到商品新增页
	* @param @return
	* @return String
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping(value="/toProductAdd.do",method=RequestMethod.GET)
	public String toProductAdd(@RequestParam(value = "itemBaseId", required = true) Integer itemBaseId,Model model,PlantItemRo plantItemRo,HttpServletRequest request) {
		try {
			Boolean flag = true;//标记是否编辑入口
			if(itemBaseId==null){
				flag = false;
			}
			if(itemBaseId!=null){
			Supplier supplier = getCurrentUser(Supplier.class, request);
			if(supplier == null)
				return "/login/index";
			plantItemRo.setSpId(supplier.getId());
			List<PlantItemVo> list = plantItemMgService.selectPlantItemByCondition(plantItemRo);
			if(list==null||list.size()==0){
				return error("数据有误", model, request);
			}
			model.addAttribute("plantItemlist", list);
			model.addAttribute("itemBaseId", itemBaseId);
			model.addAttribute("plantItemVo", list.get(0));
			if(list.get(0).getUserTypes()!=null&&list.get(0).getUserTypes().size()!=0){
				for (ScmsUserTypeVo scmsUserTypeVo: list.get(0).getUserTypes()) {
					if(scmsUserTypeVo.getName().equals("便利店")){
						model.addAttribute("storePrice", scmsUserTypeVo.getSalePrice());
					}
					if(scmsUserTypeVo.getName().equals("餐饮店")){
						model.addAttribute("resPrice", scmsUserTypeVo.getSalePrice());
					}
				}
			}
			}
			
			model.addAttribute("pageIndex", request.getParameter("pageIndex"));
			model.addAttribute("flag", flag);
			return "product/product-add";
		} catch (Exception e) {
			logger.error(e.toString(),e);
			return "";
		}
	}
	
	
	
	/**
	 * 
	 * @Title: toProductIndex
	 * @Description: 跳转到商品管理首页
	 * @param @return
	 * @return String
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/toProductIndex.do", method = RequestMethod.GET)
	public String toProductIndex() {
		try {
			return "product/index";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			// TODO 跳转到错误页
			return "";
		}
	}

	/**
	 * 
	 * @Title: getItemCatlog
	 * @Description:获取商品类别
	 * @param @param model
	 * @param @return
	 * @return Object
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/getItemCatlog.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getItemCatlog(Model model) {
		try {
			// 所有商品分类
			List<ItemCatelog> list = scmsItemCatelogMgService.selectAll();
			// 只有一级分类
			List<ItemCatelog> firstList = new ArrayList<ItemCatelog>();
			// 只有二级分类
			List<ItemCatelog> secondList = new ArrayList<ItemCatelog>();
			for (ItemCatelog itemCatelog : list) {
				if (!itemCatelog.getPid().equals(0)) {
					secondList.add(itemCatelog);
				} else {
					firstList.add(itemCatelog);
				}
			}
			Map<Integer, String> map = new HashMap<Integer, String>();
			for (ItemCatelog itemCatelog : firstList) {
				if (itemCatelog.getPid().equals(0)) {
					map.put(itemCatelog.getId(), itemCatelog.getName());
				}
			}

			Map<Integer, Object> map2 = new HashMap<Integer, Object>();
			for (ItemCatelog itemCatelog : firstList) {
				Map<Integer, Object> tempMap = new HashMap<Integer, Object>();
				for (ItemCatelog itemCatelog2 : secondList) {
					if (itemCatelog2.getPid().equals(itemCatelog.getId())) {
						tempMap.put(itemCatelog2.getId(), itemCatelog2.getName());
					}
				}
				map2.put(itemCatelog.getId(), tempMap);
			}
			Map<String, Object> reutrnMap = new HashMap<String, Object>();
			ObjectMapper mapper = new ObjectMapper();
			reutrnMap.put("map", mapper.writeValueAsString(map));
			reutrnMap.put("map2", mapper.writeValueAsString(map2));
			return ResponseUtils.sendMsg(true, reutrnMap);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			// TODO 跳转到错误页
			return "";
		}
	}

	/**
	 * 
	 * @Title: getPlantItem
	 * @Description:获取供应商商品列表
	 * @param @return
	 * @return Object
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/getPlantItem.do")
	@ResponseBody
	public Object getPlantItem(HttpServletRequest request, PlantItemRo plantItemRo,Integer pageIndex) {
		if(pageIndex != null){
			plantItemRo.setPageIndex(pageIndex+1);
		}
		if(plantItemRo.getKeyStr() != null && !"".equals(plantItemRo.getKeyStr())){
			plantItemRo.setKeyStr(plantItemRo.getKeyStr().trim());
		}
		try {
			Supplier supplier = getCurrentUser(Supplier.class, request);
			plantItemRo.setSpId(supplier.getId());
			Pager<PlantItemVo> page = plantItemMgService.selectSupplierAllPlantItem(plantItemRo);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageIndex", request.getParameter("pageIndex"));
			return new Pager<PlantItemVo>(true, page.getTotalSize(), page.getList(),map);

		} catch (Exception e) {
			logger.error(e.toString(), e);
			// TODO 跳转到错误页
			return "";
		}
	}

	/**
	 * 
	 * @Title: getPlantItemName
	 * @Description:搜索商品
	 * @param @return
	 * @return Object
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/getPlantItemName.do")
	@ResponseBody
	public Object getPlantItemName(HttpServletRequest request) {
		try {
			String productName = request.getParameter("productName");
			if (StringUtil.stringIsNullOrEmpty(productName)) {
				// TODO 返回提示
				ResponseUtils.sendMsg(false,null);
			}
			List<PlantItemVo> list = plantItemMgService.selectItemBaseByName(productName);
			return ResponseUtils.sendMsg(true, list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			// TODO 跳转到错误页
			return "";
		}
	}

	/**
	 * 
	 * @Title: updatePlantItemPrice
	 * @Description:修改供应商品价格
	 * @param @return
	 * @return Object
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/updatePlantItemPrice.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePlantItemPrice(
			@RequestParam(required = true, value = "storePrice") BigDecimal storePrice, 
			@RequestParam(required = true, value = "resPrice") BigDecimal resPrice, 
			@RequestParam(required = true, value = "plantItemId") String plantItemId, 
			@RequestParam(required = true, value = "itemBaseId") Integer itemBaseId, 
			Integer spGroupIds[],
			BigDecimal areaPrices[], 
			Byte statuss[],
			/*@RequestParam(required = true, value = "resPriceId") String resPriceId,*/ 
			HttpServletRequest request) {
		try {
			//价格精度校验,参数校验
			if(storePrice==null){
				return ResponseUtils.sendMsg(false, "便利店出货价不能为空!");
			}
			if(resPrice==null){
				return ResponseUtils.sendMsg(false, "餐饮店出货价不能为空!");
			}
			if(storePrice.scale()>2){
				return ResponseUtils.sendMsg(false, "便利店出货价不能超过小数点后2位");
			}
			if(resPrice.scale()>2){
				return ResponseUtils.sendMsg(false, "餐饮店出货价不能超过小数点后2位");
			}
			if(storePrice.precision()>5){
				return ResponseUtils.sendMsg(false, "便利店出货价");
			}
			if(resPrice.precision()>5){
				return ResponseUtils.sendMsg(false, "餐饮店出货价不能超过小数点后2位");
			}
			
			
			Supplier supplier = getCurrentUser(Supplier.class, request);
			Map<String, Object> map = new HashMap<String, Object>();
			
			if(itemBaseId==null){
				return ResponseUtils.sendMsg(false,"数据异常,请联系客服!");
			}
			PlantItem plantItem = new PlantItem();
			if (StringUtil.stringIsNullOrEmpty(plantItemId)) {
				// 新增plantItem
				plantItem.setAddTime(new Date());
				plantItem.setUpdateTime(new Date());
				plantItem.setStatus(new Byte("3"));// 自有商品
				
				plantItem.setItemBaseId(itemBaseId);
				plantItem.setLogicStockId(callAbleService.checkItemIsHave(erpWarehouseService.getWarehouseByUpId("0", supplier.getId()).get(0).getId(), itemBaseId, 1));
				map.put("flag", true);
			} else {
				map.put("flag", false);
				map.put("spGroupIds", spGroupIds);
				map.put("areaPrices", areaPrices);
				map.put("statuss", statuss);
				map.put("plantItemId", plantItemId);
				plantItem.setId(plantItemId);
				
			}
			plantItem.setSpId(supplier.getId());
			map.put("plantItem", plantItem);
			map.put("storePrice", storePrice);
			map.put("resPrice", resPrice);
			/*map.put("storePriceId", storePriceId);
			map.put("resPriceId", resPriceId);*/
			/*map.put("plantItemId", plantItem.getId());*/
			map.put("spId", supplier.getId());
			map.put("itemBaseId",itemBaseId);
			plantItemMgService.addOrUpdateSpPlantItemPrice(map);
			return ResponseUtils.sendMsg(true);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			// TODO 跳转到错误页
			return "";
		}
	}

	/**
	 * 
	 * @Title: getPlantItemPrice
	 * @Description:
	 * @param @return
	 * @return Object
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/getPlantItemPrice.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getPlantItemPrice(HttpServletRequest request, PlantItemRo plantItemRo) {
		try {
			Supplier supplier = getCurrentUser(Supplier.class, request);
			plantItemRo.setSpId(supplier.getId());
			// 联表查询ScmsSpSalePrice
			PlantItemVo plantItemVo = plantItemMgService.selectPlantItemAndUserTypePrice(plantItemRo);
			return ResponseUtils.sendMsg(true, plantItemVo);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			// TODO 跳转到错误页
			return "";
		}
	}


	/**
	 * 
	 * @Title: getSpItmeBase 
	 * @Description:根据编号查找ItemBase和不同店铺类型的价格(编辑入口)
	 * @param @param itemBaseId
	 * @param @param request
	 * @param @param plantItemRo
	 * @param @return
	 * @return Object
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/getSpItmeBaseAndPrice.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getSpItmeBase(@RequestParam(value = "itemBaseId", required = true) Integer itemBaseId, HttpServletRequest request, PlantItemRo plantItemRo) {
		try {
			Supplier supplier = getCurrentUser(Supplier.class, request);
			plantItemRo.setSpId(supplier.getId());
			List<PlantItemVo> list = plantItemMgService.selectPlantItemByCondition(plantItemRo);
			if(list==null||list.size()==0){
				return ResponseUtils.sendMsg(true, null);
			}
			return ResponseUtils.sendMsg(true, list.get(0));
		} catch (Exception e) {
			logger.error(e.toString(), e);
			// TODO 跳转到错误页
			return "";
		}
	}

	/************************************商品管理  end***********************************/
/***************************批发商商品管理 begin***********************************/
	/**
	  * 根据参数获得所有批发商的商品 
	 * @Title
	 * @Description: TODO 
	 * @param @param plantItem
	 * @param @return
	 * @2016年3月7日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	  */
	 public Object getSupplierGoodsByParam(HttpServletRequest request,PlantItemVo plantItem,Integer pageIndex){
		 	Supplier supplier = this.getCurrentUser(Supplier.class, request);
		 	plantItem.setSpId(supplier.getId());
		 	plantItem.setPageIndex(pageIndex+1);
		 try {
			Pager<PlantItemVo> page = plantItemMgService.getSupplierGoodsByParam(plantItem);
			 if(page != null){
				 return page;
			 }else{
				 return null;
			 }
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
		 
	 }
//	 @RequestMapping("updatePlantItemStatus.do")
//	 @ResponseBody
//	 public Object updatePlantItemStatus(Byte status,String itemBaseId,BigDecimal areaPrice,HttpServletRequest request){
//		 Supplier supplier = this.getCurrentUser(Supplier.class, request);
//		 Map<String, Object> map = new HashMap<String, Object>();
//		 try {
//			 map.put("spId", supplier.getId());
//			 map.put("itemBaseId", itemBaseId);
//			 map.put("status", status);
//			 if(status != null && status == 1){
//				 map.put("areaPrice", areaPrice);
//			 }
//			 map.put("updateTime", new Date());
//			plantItemMgService.updatePlantItemStatus(map);
//			return ResponseUtils.sendMsg(true,"操作成功！");
//		} catch (Exception e) {
//			logger.error("",e);
//			return ResponseUtils.sendMsg(false,"操作失败！");
//		}
//		 
//	 }
/***************************批发商商品管理 end***********************************/
	 
	 //
	 /**
	  * 批发商库存预警
	 * @Title
	 * @Description: TODO 
	 * @param @param plantItem
	 * @param @return
	 * @2016年3月7日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	  */
		@RequestMapping("/getSupplierStockDetailBysSpId.do")
		@ResponseBody
		public Object getSupplierStockDetailBysSpId(StockManagerParamRo plantItemRo,HttpServletRequest request,Model model,Integer pageIndex){
			try {
				Supplier supplier = this.getCurrentUser(Supplier.class, request);
				plantItemRo.setSpId(supplier.getId());
				plantItemRo.setPageIndex(pageIndex==null?1:(pageIndex+1));
				Pager<PlantItemVo>  pager = plantItemMgService.getPlantItemGoodsByGroupAndSpId(plantItemRo);
				return pager;
			} catch (Exception e) {
				logger.error("",e);
				return null; 
			}
		} 
	 
	 
}
