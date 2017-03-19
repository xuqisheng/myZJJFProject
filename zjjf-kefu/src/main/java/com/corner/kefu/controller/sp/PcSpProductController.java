/**
 * 
 */
package com.corner.kefu.controller.sp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.corner.core.beans.ERPWarehouse;
import com.corner.core.beans.ItemBase;
import com.corner.core.beans.PlantItem;
import com.corner.core.beans.PlantItemPre;
import com.corner.core.beans.SpGroup;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.SystemInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.sp.PlantItemRo;
import com.corner.kefu.beans.ro.sp.SupplierMgRo;
import com.corner.kefu.beans.vo.sp.RegionVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.ItemBaseService;
import com.corner.kefu.service.RegionService;
import com.corner.kefu.service.SystemInfoService;
import com.corner.kefu.service.sp.ERPWarehouseService;
import com.corner.kefu.service.sp.SpGroupService;
import com.corner.kefu.service.sp.SpPlantItemService;
import com.corner.kefu.service.sp.SpSupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @ClassName: PcSpProductController
 * 
 * @Description: 定格商品管理
 * 
 * @author: 杨开泰
 * 
 * @date: 2015年10月19日 上午11:24:24
 */
@RequestMapping("/Customer/SpProduct")
@Controller
public class PcSpProductController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(PcSpProductController.class);

	@Autowired
	SpSupplierService spSupplierService;

	@Autowired
	SpGroupService spGroupService;

//	@Autowired
//	PlantItemService plantItemService;

	@Autowired
	SpPlantItemService spPlantItemService;

	@Autowired
	SystemInfoService systemInfoService;

	@Autowired
	ItemBaseService itemBaseService;

	@Autowired
	RegionService regionService;
	
	@Autowired
	ERPWarehouseService eRPWarehouseService;
	
    private static final String PATH = "supplier-goods/";

    @RequestMapping("/clearRestrict.do")
    @ResponseBody
    public Object clearRestrict(HttpServletRequest request) {
    	String id  = request.getParameter("id");
    	if(StringUtils.isEmpty(id)){
    		return ResponseUtils.sendMsg(false, "缺少必要参数id!");
    	}
    	spPlantItemService.updatePlantItemRestrict(id);
		return ResponseUtils.sendMsg(true, "清除限制成功!");
	}
    
    
    /**
     * 
    * @Title: updateOrder 
    * @Description:
    * @param @param request
    * @param @return
    * @return Object    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	@RequestMapping("/updateOrder.do")
	@ResponseBody
	public Object updateOrder(HttpServletRequest request) {
		try {
			String mdseId = request.getParameter("mdseId");
			String spid = request.getParameter("spid");
			String spGroupId = request.getParameter("spGroupId");
			String ordid = request.getParameter("ordid");
			if (StringUtil.stringIsNullOrEmpty(mdseId) || StringUtil.stringIsNullOrEmpty(spid)
					|| StringUtil.stringIsNullOrEmpty(spGroupId) || StringUtil.stringIsNullOrEmpty(ordid)) {
				return ResponseUtils.sendMsg(false, "参数有误!");
			}
			ItemBase itemBase = itemBaseService.selectItemByBrand(mdseId);
			if (itemBase == null) {
				return ResponseUtils.sendMsg(false, "程序出错!");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("itemBaseId", itemBase.getId());
			map.put("id", spid);
			map.put("spGroupId", spGroupId);
			List<PlantItem> list = spPlantItemService.getPlantItemListBySpIdAndSpGroupIdAndItemBaseId(map);
			if (list.size() == 0) {
				return ResponseUtils.sendMsg(false, "程序出错!");
			}
			PlantItem plantItem = list.get(0);
			plantItem.setOrdId(Integer.parseInt(ordid));
			spPlantItemService.upPlantItemOderId(plantItem);

			PlantItemPre plantItempre = spPlantItemService.getPlantItemPreById(plantItem.getId());
			if (plantItempre != null) {
				plantItempre.setOrdId(Integer.parseInt(ordid));
				spPlantItemService.upPlantItemPreOderId(plantItempre);
			}
			return ResponseUtils.sendMsg(true, "修改排序成功!");
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}

	/**
	 * 上下架
	 * 
	 * @return
	 */
	@RequestMapping("/upordown.do")
	@ResponseBody
	public Object upordown(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");// plantItem id
			String cup = request.getParameter("cup");// cup为1表示上架 为0表示下架
			// 参数校验
			if (StringUtil.stringIsNullOrEmpty(id) || StringUtil.stringIsNullOrEmpty(cup)) {
				return ResponseUtils.sendMsg(false, "缺少PlantItem id!");
			}
			PlantItem plantItem = spPlantItemService.selectByPrimaryKey(id);
			// 查询定格
			SpGroup spGroup = spGroupService.selectByPrimaryKey(plantItem.getSpGroupId());
			if (cup.equals("1")) {
				if (spGroup.getStatus() == 0) {
					return ResponseUtils.sendMsg(false, "该商品所属定格已经下架,商品不能上架!");
				}
				plantItem.setStatus(new Byte("1"));
				spPlantItemService.upPlantItem(plantItem);
			} else {
				plantItem.setStatus(new Byte("0"));
				spPlantItemService.upPlantItem(plantItem);
			}
			return ResponseUtils.sendMsg(true);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, e.toString());
		}
	}

	/**
	 * 判断条形码是否唯一
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/mdseIdIsExit.do")
	@ResponseBody
	public Object mdseIdIsExit(HttpServletRequest request, Model model) {
		try {
			String mdseId = request.getParameter("mdseId");
			ItemBase itemBase = itemBaseService.selectItemByBrand(mdseId);
			if (itemBase == null) {
				return ResponseUtils.sendMsg(false);// 条形码不存在
			}
			return ResponseUtils.sendMsg(true);// 条形码存在
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseUtils.sendMsg(false, "判断条形码是否已经存在失败!");
		}
	}
	

	/**
	 * 添加或修改商品到预设表
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping("/addItem.do")
	@ResponseBody
	public Object addItem(HttpServletRequest request, ItemBase itemBase, PlantItemRo plantItemro) {
		try {
			String supplierId = request.getParameter("supplierId");
			String itemBaseId = request.getParameter("itemBaseId");// 根据itemBaseId是否为空,判断新增商品还是修改商品
			String jsonStr = request.getParameter("jsonStr");
			if(StringUtil.stringIsNullOrEmpty(jsonStr)){
				return ResponseUtils.sendMsg(false, "参数不正确!");
			}
			
			if (StringUtil.stringIsNullOrEmpty(supplierId)) {
				return ResponseUtils.sendMsg(false, "参数有误!");
			}
			Supplier supplier = spSupplierService.getSupplierById(supplierId);
			Integer areaId = null;
			if(supplier!=null && supplier.getAreaId()!=null){
				areaId = supplier.getAreaId();
			}
			
			/**
			 * 1.如果是预录入的话,根据id修改数据
			 */
			List<PlantItemPre> plantItemPreList = new ArrayList<PlantItemPre>();
			List<Map<String, Object>> jsonList = ((List<Map<String, Object>>)JSONUtil.JSONToObject(jsonStr, List.class));
			for (Map<String, Object> jsonMap : jsonList) {
			  	PlantItemPre plantItemPre = new PlantItemPre();
			  	Set<String> keySet = jsonMap.keySet();
			  	String spGroupId = "";
			  	for (String key : keySet) {
					spGroupId = key;
					break;
				}
			  	spGroupId = StringUtils.substringBefore(spGroupId, "_");//定格id
			  	String areaprice = (String) jsonMap.get(spGroupId + "_areaprice");
			  	if(StringUtil.stringIsNullOrEmpty(areaprice)||StringUtils.equalsIgnoreCase(areaprice, "null")){
			  		continue;
			  	}
			    plantItemPre.setAreaPrice(new BigDecimal(areaprice));//出货价
			    String plantItemId = (String) jsonMap.get(spGroupId+"_plantItemId");
			    String isNew = (String) jsonMap.get(spGroupId+"_isNew");
			    Date time = new Date();
			    if(isNew.equals("0")){//表示在PlantItem表中有该plantItemId的数据存在
			    	plantItemPre.setId(plantItemId);
			    	plantItemPre.setUpdateTime(time);
			    }else {
			    	plantItemPre.setAddTime(time);
					plantItemPre.setUpdateTime(time);
				}
//			    if(isEdit.equals("0")){
//			    	//如果是从预录入进入本方法,那么页面会传递真实存在的PlantItem id
//			  	}else {
//				}
			    String costPrice = ((String) jsonMap.get(spGroupId+"_costPrice")).trim();
			    plantItemPre.setFee(new BigDecimal(costPrice));//费用
			    String grossProfit = ((String) jsonMap.get(spGroupId+"_grossProfit")).trim();
			    plantItemPre.setMaoli(new BigDecimal(grossProfit));//毛利
			    String marketprice = ((String) jsonMap.get(spGroupId+"_marketprice")).trim();
			    plantItemPre.setPlantMemPrice(new BigDecimal(marketprice));//市场价
			    String plantDisPrice = ((String) jsonMap.get(spGroupId+"_plantDisPrice")).trim();
			    plantItemPre.setPlantDisPrice(new BigDecimal(plantDisPrice));//进货价
			    String remark = ((String) jsonMap.get(spGroupId+"_remark")).trim();
			    if(!StringUtil.stringIsNullOrEmpty(remark)&&!StringUtils.equalsIgnoreCase(remark, "null")){
			    	plantItemPre.setRemark(remark);//商品备注
			    }else {
			    	plantItemPre.setRemark("");//商品备注
				}
			    String restrict = ((String) jsonMap.get(spGroupId+"_restrict")).trim();
			    //页面控制一定要输入数量限制字段
			    plantItemPre.setRestrict(Integer.parseInt(restrict));//数量限制
				plantItemPre.setSKUProLimitNum(plantItemPre.getRestrict());
				plantItemPre.setSKUProTotalLimitNum(plantItemPre.getRestrict());

			    String youHui = ((String) jsonMap.get(spGroupId+"_youHui")).trim();
			    if(!StringUtil.stringIsNullOrEmpty(youHui)&&!StringUtils.equalsIgnoreCase(youHui, "null")){
			    	plantItemPre.setYouHui(youHui);//优惠信息
			    }else {
			    	plantItemPre.setYouHui("");//优惠信息
				}
			    //设置仓库Id
			    String warehouseIdStr = (String) jsonMap.get(spGroupId+"_warehouse");
			    plantItemPre.setWarehouseId(warehouseIdStr);
			    //设置库存
			    String logicStockTypeMgStr = (String) jsonMap.get(spGroupId+"_logicStockTypeMg");
			    plantItemPre.setLogicStockTypeMg(Byte.parseByte(logicStockTypeMgStr));
			    
			    plantItemPre.setSpGroupId(Integer.parseInt(spGroupId));//定格id
			    plantItemPre.setSpId(supplierId);//批发商id
			    plantItemPre.setItemBaseId(Integer.parseInt(itemBaseId));//itemBaseId
			    plantItemPre.setAreaId(areaId);//区域id
			    String status = (String) jsonMap.get(spGroupId+"_status");
			    plantItemPre.setStatus(new Byte(status));
			    plantItemPreList.add(plantItemPre);
			}
			Map<String, Object>paramMap = new HashMap<String,Object>();
			paramMap.put("plantItemPreList", plantItemPreList);
			ModelMsg modelMsg = spPlantItemService.updateOrInserPlantItemPre(paramMap);
            return ResponseUtils.sendMsg(true);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}

	
	/**
	 * 
	* @Title: isExist 
	* @Description:根据商品条形码mdseId,查询批发商该条码商品在批发商所在的
	* 所有定格下的在线数据价格(查询PlantItem)
	* @param @param brand 条形码mdseId
	* @param @param id  //批发商id
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	 */
	@RequestMapping(value = "/isExist.do")
	@ResponseBody
	public Object isExist(String brand, String id) {
		try {
			if (StringUtil.stringIsNullOrEmpty(brand)) {
				return ResponseUtils.sendMsg(false, "缺少参数mdseId");
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mdseId", brand);//条形码
			paramMap.put("id", id);//批发商id
			
			ModelMsg modelMsg = spPlantItemService.getPlantItemPrice(paramMap);
			if(!modelMsg.isSuccess()){
				return ResponseUtils.sendMsg(false,modelMsg.getMessage());
			}
			return ResponseUtils.sendMsg(true, modelMsg.getData());
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, "程序出错");
		}
	}

	/**
	 * 
	* @Title: isPreExist 
	* @Description:查询PlantItemPre表
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/isPreExist.do")
	@ResponseBody
	public Object isPreExist(HttpServletRequest request) {
		try {
			String mdseId = request.getParameter("brand");
			if (StringUtil.stringIsNullOrEmpty(mdseId)) {
				return ResponseUtils.sendMsg(false, "缺少参数mdeseId");
			}
			String id = request.getParameter("id");//批发商id
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mdseId", mdseId);//条形码
			paramMap.put("id", id);//批发商id
			
			ModelMsg modelMsg = spPlantItemService.getPlantItemPrePrice(paramMap);
			if(!modelMsg.isSuccess()){
				return ResponseUtils.sendMsg(false,modelMsg.getMessage());
			}
			return ResponseUtils.sendMsg(true, modelMsg.getData());
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
	}
	
	
	/**
	 * 
	* @Title: toPreEdit 
	* @Description:跳转到预录入页面
	* @param @param request
	* @param @param model
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toPreEdit.do")
	public String toPreEdit(HttpServletRequest request, Model model) throws Exception{
		String mdseId = request.getParameter("mdseId");// 条形码
		if(StringUtil.stringIsNullOrEmpty(mdseId)){
			model.addAttribute("mdseId", "");
		}else {
			model.addAttribute("mdseId", mdseId);
		}
		String id = request.getParameter("id");// 供应商id
		String pageIndexStr = request.getParameter("pageIndex");
		if(!StringUtil.stringIsNullOrEmpty(pageIndexStr)){
			model.addAttribute("pageIndex", pageIndexStr);
		}else {
			model.addAttribute("pageIndex",1);
		}
		//查询设定的系统时间
		SystemInfo sysinfo = systemInfoService.getKDB_Price_Task("KDB_Price_Task");
		if (sysinfo != null && !StringUtil.stringIsNullOrEmpty(sysinfo.getContent())) {
			model.addAttribute("taskTime", sysinfo.getContent());
		}
		model.addAttribute("supplierId", id);//批发商id

		//根据批发商id查询仓库信息.
		//TODO 如果没有仓库不让录入
		List<ERPWarehouse> list = eRPWarehouseService.getSupplierAllERPWarehouseById(id);
		if(list==null||list.size()==0){
			//TODO 错误提示
		}
		model.addAttribute("warehouseList", JSON.toJSONString(list));
		return PATH+"pre-edit";
	}

	/**
	 * 
	* @Title: getSpGroupAndProduct 
	* @Description:查询对应供应商所属定格和商品列表
	* @param @param request
	* @param @param model
	* @param @param supplier
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getSpGroupAndProduct.do")
	public String getSpGroupAndProduct(HttpServletRequest request, Model model, SupplierMgRo supplierMgRo)
			throws Exception {
		Integer pageIndex = supplierMgRo.getPageIndex();
		String pageIndexStr = request.getParameter("pageIndex");
		String errorMessage = request.getParameter("errorMessage");
		model.addAttribute("errorMessage", errorMessage);
		if (!StringUtil.stringIsNullOrEmpty(pageIndexStr)) {
			supplierMgRo.setPageIndex(Integer.parseInt(pageIndexStr));
			model.addAttribute("pageIndex", pageIndexStr);
		}else {
			pageIndex = 1;
			model.addAttribute("pageIndex", 1);
		}
		if (!StringUtil.stringIsNullOrEmpty(supplierMgRo.getSpGroupId())) {
			model.addAttribute("spGroupId", supplierMgRo.getSpGroupId());
		}

		if (!StringUtil.stringIsNullOrEmpty(supplierMgRo.getSearchKey())) {
			supplierMgRo.setSearchKey(supplierMgRo.getSearchKey().trim());
			model.addAttribute("searchKey", supplierMgRo.getSearchKey().trim());
		}

		Map<String, Object> map = spSupplierService.getSupplierProductList(supplierMgRo);
		model.addAttribute("plList", map.get("plList"));
		Integer count = (Integer) map.get("count");
		model.addAttribute("count", count);
		model.addAttribute("spList", map.get("spList"));
		model.addAttribute("supplier", map.get("supplier"));
		// 分装分页
		this.pageUtil(pageIndex, count, supplierMgRo.getPageSize(), request, model);
		return PATH+"index";
	}

    /**
     * 
    * @Title: toProductList 
    * @Description:跳转到定格商品管理页面
    * @param @param model
    * @param @return
    * @param @throws Exception
    * @return String    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
    @RequestMapping("/toProductList.do")
	public String toProductList(Model model) throws Exception {
		// 查询批发商所在的所有区域列表
		List<RegionVo> resultList = regionService.getAllSupplierRegionList();
		model.addAttribute("list", resultList);
		return PATH+"index";
	}
    
    /**
     * 
    * @Title: getSupplierList 
    * @Description:
    * @param @param request
    * @param @param model
    * @param @param supplierVo
    * @param @return
    * @return Object    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
    @RequestMapping("/getSupplierList.do")
	@ResponseBody
	public Object getSupplierList(HttpServletRequest request, Model model, SupplierVo supplierVo) {
		try {
			List<SupplierVo> list = spSupplierService.selectSupplierList(supplierVo);
			Map<String, String> map = new HashMap<String, String>();
			for (Iterator<SupplierVo> iterator = list.iterator(); iterator.hasNext();) {
				SupplierVo supplierVo2 = (SupplierVo) iterator.next();
				map.put(supplierVo2.getSupplierName(), supplierVo2.getId());
			}
			ObjectMapper mapper = new ObjectMapper();
			return ResponseUtils.sendMsg(true, list);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, "查询批发商列表出错");
		}
	}
}
