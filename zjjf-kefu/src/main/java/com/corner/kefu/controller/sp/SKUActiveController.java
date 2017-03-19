package com.corner.kefu.controller.sp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.AppItemLabel;
import com.corner.core.beans.CustomerService;
import com.corner.core.beans.ItemBase;
import com.corner.core.beans.SKUActive;
import com.corner.core.beans.SKUActiveGoodInfo;
import com.corner.core.beans.SystemInfo;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.ItemBaseRo;
import com.corner.kefu.beans.ro.sp.SKUActiveRo;
import com.corner.kefu.beans.vo.BrandVo;
import com.corner.kefu.beans.vo.ItemBaseVo;
import com.corner.kefu.beans.vo.SKUActiveTagVo;
import com.corner.kefu.beans.vo.sp.PlantItemVo;
import com.corner.kefu.beans.vo.sp.RegionVo;
import com.corner.kefu.beans.vo.sp.SKUActiveGoodInfoVo;
import com.corner.kefu.beans.vo.sp.SKUActiveSessionObject;
import com.corner.kefu.beans.vo.sp.SKUActiveVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;
import com.corner.kefu.config.BusinessCodeConts;
import com.corner.kefu.config.SessionConfig;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.AppItemLabelService;
import com.corner.kefu.service.ItemBaseService;
import com.corner.kefu.service.SystemInfoService;
import com.corner.kefu.service.sp.SKUActiveService;
import com.corner.kefu.service.sp.SpBrandService;
import com.corner.kefu.service.sp.SpItemBaseService;
import com.corner.kefu.service.sp.SpItemCatelogService;
import com.corner.kefu.service.sp.SpPlantItemService;
import com.corner.kefu.service.sp.SpRegionService;
import com.corner.kefu.service.sp.SpSupplierService;

/**
 * 单品促销控制类
 * @ClassName: SKUActiveController
 * @Description: 单品促销控制类，处理所有的请求入口
 * @author 小武
 * @version 飓风
 * @date 2016年9月1日 上午10:45:35
 *
 */
@Controller
@RequestMapping("/keFu/SKUActive")
public class SKUActiveController extends KefuBaseWebController {
	
	private static final Logger logger = LoggerFactory.getLogger(SKUActiveController.class);
	static final String key_SKUActiveTaskAuth="SKUActiveTaskAuth";
	@Autowired
	SKUActiveService sKUActiveService;
	@Autowired
	SpRegionService regionService;
	@Autowired
	SpSupplierService supplierService;
	@Autowired
	SpPlantItemService plantItemService;
	@Autowired
	AppItemLabelService tagService;
	@Autowired
	SpItemCatelogService itemCatelogService;
	@Autowired
	SpItemBaseService itemBaseService;
	
	@Autowired
	ItemBaseService itemBaseS;
	
	@Autowired
	SpBrandService brandService;
	
	@Autowired
	SystemInfoService systemInfoService;
	

	private final String key_sessionObject="sessionObject";
	
	private final String key_sessionObject4edit="sessionObject4edit";
	
	private final String key_skuActiveLabel="skuActiveLabel";
	private final String key_skuActivePublishTag="skuActivePublishTag";
	
	//获取session
	private Session getSession(){
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}
	
	/**
	 * 促销活动列表
	* @Title
	* @Description:  
	* @param @param SKUActive
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年8月1日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getAllSKUActive.do")
	public String getAllSKUActive(SKUActiveRo SKUActive,HttpServletRequest request,Model model){
		SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)this.getSession().getAttribute(key_sessionObject);
		if(sessionObject != null){
			this.getSession().removeAttribute(key_sessionObject);
		}
		Date beginTime = SKUActive.getBeginTime();
		Date endTime = SKUActive.getEndTime();
		String activeName = SKUActive.getActiveName();
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("activeName", activeName);
		try {
			Pager<SKUActiveVo> page = sKUActiveService.getAllSKUActive(SKUActive);
			if(page != null){
				model.addAttribute("activeList", page.getList());
				pageUtil(SKUActive.getPageIndex(), page.getTotalSize(), SKUActive.getPageSize(), request, model);
			}
			return "/promotions/goods-promotion/goods-promotion";
		} catch (Exception e) {
			
			e.printStackTrace();
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	
	private void initSKUActiveLable(HttpServletRequest request,Model model){
		Object obj = request.getSession().getAttribute(key_skuActiveLabel);
		if(obj == null){
			List<AppItemLabel> tagList = tagService.getAllTag();
			if(tagList != null && tagList.size()>0){
				model.addAttribute(key_skuActiveLabel, JSONUtil.objectToJSONString(tagList));
				request.getSession().setAttribute(key_skuActiveLabel, JSONUtil.objectToJSONString(tagList));
			}
		}
	}
	
	private void initSKUActivePublishTag(HttpServletRequest request,Model model){
		Object obj = request.getSession().getAttribute(key_skuActivePublishTag);
		if(obj == null){
			List<SKUActiveTagVo> publishTagList = sKUActiveService.selectSKUActivePublishTag();
			if(publishTagList != null && publishTagList.size()>0){
				model.addAttribute(key_skuActivePublishTag, publishTagList);
				request.getSession().setAttribute(key_skuActivePublishTag, publishTagList);
			}
		}
	}
	/**
	 * 根据action的值，填充值到下一个页面。
	 * @Title: returnAddOrEditPage
	 * @date 2016年9月13日  上午10:12:53
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("returnAddOrEditPage.do")
	public String returnAddOrEditPage(HttpServletRequest request,Model model){
		String action = request.getParameter("action");
		SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)this.getSession().getAttribute(key_sessionObject);
		if(action.equals("1")){
			if(sessionObject != null){
				this.getSession().removeAttribute(key_sessionObject);
			}
			model.addAttribute("action", "1");
			request.getSession().removeAttribute(key_skuActiveLabel);
			request.getSession().removeAttribute(key_skuActivePublishTag);
			initSKUActiveLable(request, model);
			initSKUActivePublishTag(request, model);
		}else if(action.equals("2")){
			model.addAttribute("action", "2");
			model.addAttribute("itemBases", JSONUtil.objectToJSONString(sessionObject.getItemBases()));
		}
//		request.getSession().removeAttribute(key_skuActiveLabel);
//		request.getSession().removeAttribute(key_skuActivePublishTag);
//		
//		initSKUActiveLable(request, model);
//		initSKUActivePublishTag(request, model);
		if(action != null && !"".equals(action)){			
//			if(action.equals("2")){
//				String id = request.getParameter("id");
//				if(id != null && !"".equals(id)){
//					//通过id查询
//				}else{
//					logger.info("id为空");
//					return error("请求有误，请重试", model, request);
//				}
//			}
			return "/promotions/goods-promotion/add-goods-promotion";
		}else{
			logger.info("action为空");
			return error("请求有误，请重试", model, request);
		}
		
	}
	
	/**
	 * 活动页面的下一步
	* @Title
	* @Description:  
	* @param @param object
	* @param @param request
	* @param @return
	* @2016年8月4日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("skuActiveNext.do")
	public String skuActiveNext(SKUActiveSessionObject object,HttpServletRequest request,Model model){
		String[] itemBaseIds = request.getParameterValues("itemBaseIds");
		String[] proPrices = request.getParameterValues("proPrices");
		String[] proLimitNums = request.getParameterValues("proLimitNums");
		String[] tagIds = request.getParameterValues("tagIds");
		String[] mdseIds = request.getParameterValues("mdseIds");
		String[] names = request.getParameterValues("names");
		String[] specs = request.getParameterValues("specs");
		String[] pfPrices = request.getParameterValues("pfPrices");
		String[] brandIds = request.getParameterValues("brandIds");
		String[] publishTags = request.getParameterValues("publishTag");
		ItemBaseVo vo = null;
		Object obj = request.getSession().getAttribute(key_skuActivePublishTag);
		if(null != obj && null != publishTags && publishTags.length > 0){
			List<SKUActiveTagVo> publishTagList = (List<SKUActiveTagVo>)obj;
			for(int i=0;i<publishTagList.size();i++){
				SKUActiveTagVo one = publishTagList.get(i);
				for(String tagId:publishTags){
					if(one.getId().equals(tagId)){
						one.setChecked(1);
						publishTagList.set(i, one);
						break;
					}
				}
			}
			request.getSession().setAttribute(key_skuActivePublishTag,publishTagList);
			object.setPublishTagList(publishTagList);
		}
		try {
			if(itemBaseIds!= null && itemBaseIds.length>0){
				for (int i = 0; i < itemBaseIds.length; i++) {
					vo = new ItemBaseVo();
					vo.setId(Integer.parseInt(itemBaseIds[i]));
					if(proPrices != null && proPrices.length>0){
						if(proPrices[i] != null && !"".equals(proPrices[i])){
							vo.setProPrice(new BigDecimal(proPrices[i]));
						}
					}
					if(proLimitNums != null && proLimitNums.length>0){
						if(proLimitNums[i] != null && !"".equals(proLimitNums[i])){
							vo.setProLimitNum(proLimitNums[i] != null && !"".equals(proLimitNums[i])?Integer.parseInt(proLimitNums[i]):0);
						}
					}
					if(tagIds != null && tagIds.length>0){
						vo.setTagId(tagIds[i]);
					}
					
					if(mdseIds != null && mdseIds.length>0){
						vo.setMdseId(mdseIds[i]);
					}
					if(names != null && names.length>0){
						vo.setName(names[i]);
					}
					if(specs != null && specs.length>0){
						vo.setSpec(specs[i]);
					}
					if(pfPrices != null && pfPrices.length>0){
						if(pfPrices[i] != null && !"".equals(pfPrices[i])){
							vo.setPfPrice(new BigDecimal(pfPrices[i]));
						}
					}
					
					if(brandIds != null && brandIds.length>0){
						if(brandIds[i] != null && !"".equals(brandIds[i]) && !brandIds[i].equals("null") && !brandIds[i].equals("undefined")){
							vo.setBrandId(new Integer(brandIds[i]));
						}else{
							vo.setBrandId(null);
						}
					}
					object.getItemBases().add(vo);
				}
				//把对象放入session
				Object old = this.getSession().getAttribute(key_sessionObject);
				SKUActiveSessionObject oldObj = (old == null ? null : (SKUActiveSessionObject)old);
				if(oldObj != null && oldObj.getSuppliers() != null && oldObj.getSuppliers().size() > 0){
					model.addAttribute("suppliers", JSONUtil.objectToJSONString(cleanSuppliers(oldObj.getSuppliers())));
				}
				object.setStartTime(DateUtil.StringToDateSimple(object.getActiveStartTime()));
				object.setEndTime(DateUtil.StringToDateSimple(object.getActiveEndTime()));
				
				this.getSession().setAttribute(key_sessionObject, object);
				//获取省，市，区，定格的集合
				List<RegionVo> regionList = regionService.getAllAreaAndSpGroup();
				regionList = initRegionVoListIsNocheck(regionList);
				if(regionList != null && regionList.size()>0){
					model.addAttribute("regionList", JSONUtil.objectToJSONString(regionList));
				}
				
				//返回下一个页面
				return "/promotions/goods-promotion/suppliers";
			}else{
				return error("请求有误，请重试", model, request);
			}
		} catch (InvalidSessionException e) {
			
			e.printStackTrace();
			logger.info("InvalidSessionException");
			return error("程序异常，请联系技术人员", model, request);
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			logger.info("NumberFormatException");
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	/** 最后一级进行复选操作 */
	private static List<RegionVo> initRegionVoListIsNocheck(List<RegionVo> regionList){
		for(int i=0;i<regionList.size();i++){
			RegionVo one = regionList.get(i);
			if(one.getRegionList() != null && one.getRegionList().size() > 0){
				List<RegionVo> child = initRegionVoListIsNocheck(one.getRegionList());
				one.setRegionList(child);
			}else{
				one.setNocheck(false);
			}
			regionList.set(i, one);
		}
		return regionList;
	}
	
	/**
	 * 选定个，添加定个批发商。
	 * @Title: spGroupOk
	 * @date 2016年9月13日  下午3:11:49
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param spGroupIds
	 * @return
	 */
	@RequestMapping("spGroupOk.do")
	@ResponseBody
	public Object spGroupOk(HttpServletRequest request,@RequestParam("spGroupIds[]")Integer[] spGroupIds){
		try {
			SKUActiveSessionObject object = (SKUActiveSessionObject)this.getSession().getAttribute(key_sessionObject);
			if(spGroupIds != null && spGroupIds.length>0){
				List<SupplierVo> supplierList = supplierService.getSupplierBySpGroupId(spGroupIds);
				if(supplierList != null && supplierList.size()>0){
					//合并数据
					List<SupplierVo> newAddlist = new ArrayList<SupplierVo>();
					List<SupplierVo> newSlist = new ArrayList<SupplierVo>();
					List<SupplierVo> objList = object.getSuppliers();
					newSlist.addAll(objList);
					for(int i=0;i < supplierList.size();i++){
						boolean eflag = false;
						for(int j=0;j<objList.size();j++){
							if(supplierList.get(i).getId().equals(objList.get(j).getId())
								&& supplierList.get(i).getSpGroupId().equals(objList.get(j).getSpGroupId())){
								eflag = true;
								break;
							}
						}
						
						if(!eflag){
							newSlist.add(supplierList.get(i));
							newAddlist.add(supplierList.get(i));
						}
					}
					
					object.setSuppliers(newSlist);
					this.getSession().setAttribute(key_sessionObject,object);
					return ResponseUtils.sendMsg(true,newSlist);
				}
			}
			return ResponseUtils.sendMsg(true,object.getSuppliers());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false);
		}
	}
	/**
	 * 删除批发商
	 * @Title: delSupplier
	 * @date 2016年9月13日  下午3:29:04
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param supplierId
	 * @return
	 */
	@RequestMapping("delSupplier.do")
	@ResponseBody
	public Object delSupplier(HttpServletRequest request,String supplierId){
		try {
			if(supplierId != null && supplierId.length() > 0){
				SKUActiveSessionObject object = (SKUActiveSessionObject)this.getSession().getAttribute(key_sessionObject);
				List<SupplierVo> objList = object.getSuppliers();
				List<SupplierVo> newSlist = new ArrayList<SupplierVo>();
				for(SupplierVo vo:objList){
					if(!vo.getId().equals(supplierId)){
						newSlist.add(vo);
					}
				}
				object.setSuppliers(newSlist);
				this.getSession().setAttribute(key_sessionObject,object);
				return ResponseUtils.sendMsg(true,newSlist);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false);
		}
	}
	
	//选取定格页面的下一步
	@RequestMapping("spGroupNext.do")
	public String spGroupNext(Integer[] spGroupIds,String[] supplierIds,Model model,HttpServletRequest request){
		try {
			SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)this.getSession().getAttribute(key_sessionObject);
			if(sessionObject != null){
				SupplierVo vo = null;
				sessionObject.getSuppliers().clear();
				if(spGroupIds != null && spGroupIds.length>0 && supplierIds != null && supplierIds.length>0){
					String[] provinceNames = request.getParameterValues("provinceNames");
					String[] cityNames = request.getParameterValues("cityNames");
					String[] areaNames = request.getParameterValues("areaNames");
					String[] groupNames = request.getParameterValues("groupNames");
					String[] supplierCodes = request.getParameterValues("supplierCodes");
					String[] supplierNames = request.getParameterValues("supplierNames");
					String[] mobiles = request.getParameterValues("mobiles");
					for (int i = 0; i < spGroupIds.length; i++) {
						vo = new SupplierVo();
						vo.setSpGroupId(spGroupIds[i]);
						vo.setId(supplierIds[i]);
						if(provinceNames != null && provinceNames.length>0){
							vo.setProvinceName(provinceNames[i]);
						}
						if(cityNames != null && cityNames.length>0){
							vo.setCityName(cityNames[i]);
						}
						if(areaNames != null && areaNames.length>0){
							vo.setAreaName(areaNames[i]);
						}
						if(groupNames != null && groupNames.length>0){
							vo.setGroupName(groupNames[i]);
						}
						if(supplierCodes != null && supplierCodes.length>0){
							vo.setSupplierCode(supplierCodes[i]);
						}
						if(supplierNames != null && provinceNames.length>0){
							vo.setSupplierName(supplierNames[i]);
						}
						if(mobiles != null && mobiles.length>0){
							vo.setMobile(mobiles[i]);
						}
						sessionObject.getSuppliers().add(vo);
					}
					sessionObject.setSuppliers(cleanSuppliers(sessionObject.getSuppliers()));
					
					//再次把数据放到session
					this.getSession().setAttribute(key_sessionObject, sessionObject);
					//拿定格和批发商id来排除商品
					List<PlantItemVo> plantItemList = plantItemService.getPlantItemBySpGroupIdAndSupplierIdAndItemBaseId(spGroupIds,supplierIds,sessionObject);
//					List<PlantItemVo> plantItemList2 = sKUActiveService.queryPlatItemList(sessionObject);
					model.addAttribute("plantItemList", plantItemList);
				}
				return "/promotions/goods-promotion/supplier-goods";
			}else{
				return error("请求有误，请重试", model, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	
	//选取定格页面的上一步
	@RequestMapping("spGroupBack.do")
	public String spGroupBack(HttpServletRequest request,Model model){
		try {
			SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)this.getSession().getAttribute(key_sessionObject);
			if(sessionObject != null){
				initSKUActiveLable(request, model);
				//把商品信息补全
				model.addAttribute(key_sessionObject, sessionObject);
				model.addAttribute("itemBases", JSONUtil.objectToJSONString(sessionObject.getItemBases()));
				//返回活动添页面
				return "/promotions/goods-promotion/add-goods-promotion";
			}else{
				return error("session过期了", model, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	/**
	 * 清除重叠的批发信息
	 * @Title: cleanSuppliers
	 * @date 2016年9月1日  上午11:18:52
	 * @author 小武
	 * @version  飓风
	 * @param suppliers
	 * @return
	 */
	private static List<SupplierVo> cleanSuppliers(List<SupplierVo> suppliers){
		Map<String,SupplierVo> mapId = new HashMap<String,SupplierVo>();
		List<SupplierVo> suppliers_new = new ArrayList<SupplierVo>();
		for(int i=0;i<suppliers.size();i++){
			SupplierVo one = suppliers.get(i);
			if(!mapId.containsKey(one.getSpGroupId() + "_" + one.getId())){
				suppliers_new.add(one);
				mapId.put(one.getSpGroupId() + "_" + one.getId(), one);
			}
		}
		return suppliers_new;
	}
	
	//批发商商品页面的上一步
	@RequestMapping("supplierGoodsBack.do")
	public String supplierGoodsBack(HttpServletRequest request,Model model){
		try {
			SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)this.getSession().getAttribute(key_sessionObject);
			if(sessionObject != null){
				//获取省，市，区，定格的集合
				List<RegionVo> regionList = regionService.getAllAreaAndSpGroup();
				if(regionList != null && regionList.size()>0){
					model.addAttribute("regionList", JSONUtil.objectToJSONString(regionList));
				}
				List<SupplierVo> sOldList = cleanSuppliers(sessionObject.getSuppliers());
//				List<SupplierVo> sNewList = supplierService.getSupplierBySupplierVoList(sOldList);
				model.addAttribute("suppliers", JSONUtil.objectToJSONString(sOldList));
				//返回活动添页面
				return "/promotions/goods-promotion/suppliers";
			}else{
				return error("session过期了", model, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error("程序异常，请联系技术人员", model, request);
		}
	}
	
	@ResponseBody
	@RequestMapping("addSKUActive.do")
	public Object addSKUActive(HttpServletRequest request){
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		String[] plantItemIds = request.getParameterValues("plantItemIds");
		String[] spGroupIds = request.getParameterValues("spGroupIds");
		String[] supplierIds = request.getParameterValues("supplierIds");
		String[] buyLimitNums = request.getParameterValues("buyLimitNums");
		String[] activePrices = request.getParameterValues("activePrices");
		String[] tagIds = request.getParameterValues("tagIds");
		String[] brandIds = request.getParameterValues("brandIds");
		String[] totalLimitNums = request.getParameterValues("totalLimitNums");
		try{
			
			for(String one:brandIds){
				Integer.parseInt(one);
			}
			for(String one:totalLimitNums){
				Integer.parseInt(one);
			}
			for(String one:buyLimitNums){
				Integer.parseInt(one);
			}

			for(String one:spGroupIds){
				Integer.parseInt(one);
			}
			for(String one:activePrices){
				new BigDecimal(one);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "参数格式异常");
		}

		try {
			SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)this.getSession().getAttribute(key_sessionObject);
			if(plantItemIds != null && !"".equals(plantItemIds)
					&& sessionObject != null
					&& spGroupIds != null && spGroupIds.length >0 
					&& supplierIds != null && supplierIds.length >0 ){
				boolean flag = sKUActiveService.addSKUActive(plantItemIds,spGroupIds,supplierIds,sessionObject,user,buyLimitNums,activePrices,tagIds,brandIds,totalLimitNums);
				if(flag){
					this.getSession().removeAttribute(key_sessionObject);
					return ResponseUtils.sendMsg(true, "添加成功");
				}else{
					return ResponseUtils.sendMsg(false, "添加失败");
				}
				
			}else{
				return ResponseUtils.sendMsg(false, "请求有误，请重试");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员");
		}
		
	}
	/**
	 * 查询基础商品库列表
	 * @Title: getGoodsList
	 * @date 2016年9月1日  上午11:18:27
	 * @author 小武
	 * @version  飓风
	 * @param itemBaseRo
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("getGoodsList.do")
	@ResponseBody
	public Object getGoodsList(ItemBaseRo itemBaseRo,Integer pageIndex){
		if(pageIndex!=null){
			itemBaseRo.setPageIndex(pageIndex+1);
		}
		Pager<ItemBaseVo> itemBaseList;
		try {
			itemBaseList = itemBaseService.getAllItemBaseByPatam(itemBaseRo);
			return itemBaseList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据名称查询基础商品库的商品名称
	 * @Title: getItemBaseListByName
	 * @date 2016年8月29日  下午1:05:53
	 * @author 小武
	 * @version  七彩虹
	 * @param request
	 * @param itemBaseName
	 * @return
	 */
	@RequestMapping("getItemBaseByName.do")
	@ResponseBody
	public Object getItemBaseListByName(HttpServletRequest request,String itemBaseName){
		try {
			ItemBase itemBase = new ItemBase();
			itemBase.setName(itemBaseName);
			List<ItemBase> list = itemBaseS.getItemBaseList(itemBase);
			if(list != null && list.size()>0){
				return ResponseUtils.sendMsg(true,list);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
	/**
	 * 查询品牌列表
	 * @Title: getBrandByName
	 * @date 2016年9月1日  下午2:11:40
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param brandName
	 * @return
	 */
	@RequestMapping("getBrandByName.do")
	@ResponseBody
	public Object getBrandByName(HttpServletRequest request,String brandName){
			
		Map<String, Object> map = new HashMap<String, Object>();
		String str=request.getParameter("str");
		map.put("str", str);
		map.put("brandName", brandName);
		try {
			List<BrandVo> brandVoList = brandService.getBrandByName(map);
			if(brandVoList != null && brandVoList.size()>0){
				return ResponseUtils.sendMsg(true,brandVoList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	/**
	 * 停止单品活动
	 * @Title: stopSKUActive
	 * @date 2016年8月29日  下午2:16:05
	 * @author 小武
	 * @version  七彩虹
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("stopSKUActive.do")
	@ResponseBody
	public Object stopSKUActive(HttpServletRequest request,String id){
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		try {
			return sKUActiveService.stopSKUActiveByUser(id, user.getUserName());
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	

	/**
	 * 审批单品活动
	 * @Title: confirmSKUActive
	 * @date 2016年8月29日  下午2:15:50
	 * @author 小武
	 * @version  七彩虹
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("confirmSKUActive.do")
	@ResponseBody
	public Object confirmSKUActive(HttpServletRequest request,String id){
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}

		try {
			return sKUActiveService.confirmSKUActiveByUser(id, user.getUserName());
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
	
	
	/**
	 * 生效单品促销数据
	 * @Title: effecSKUActive
	 * @date 2016年8月29日  下午2:18:07
	 * @author 小武
	 * @version  七彩虹
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("effecSKUActive.do")
	@ResponseBody
	public Object effecSKUActive(HttpServletRequest request,String id){
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}

		try {
			return sKUActiveService.effecSKUActive(id, user.getUserName());
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
	/**
	 * 失效单品促销数据
	 * @Title: invalidSKUActive
	 * @date 2016年8月30日  下午4:50:40
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("invalidSKUActive.do")
	@ResponseBody
	public Object invalidSKUActive(HttpServletRequest request,String id){
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}

		try {
			return sKUActiveService.invalidSKUActive(id, user.getUserName());
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	/**
	 * 
	 * @Title: querySKUActive
	 * @date 2016年8月30日  下午7:06:11
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("querySKUActive.do")
	public String querySKUActive(HttpServletRequest request,Model model,String id){
		try{
			SKUActive skuA = sKUActiveService.getSKUActiveById(id);
			if(skuA.getStatus() == 2 && skuA.getDownTime().getTime() < new Date().getTime())
				skuA.setStatus(Byte.valueOf("3"));
			List<SKUActiveGoodInfo> glist = sKUActiveService.queryListById(id);
			List<PlantItemVo> plantItemList = sKUActiveService.queryPlatItemList(glist);
			List<SKUActiveGoodInfoVo> listDetial = sKUActiveService.querySKUActiveInfoVoList(id);
			
			List<SKUActiveTagVo> list = sKUActiveService.querySKUActivePublishTag(id);
			request.getSession().setAttribute(key_skuActivePublishTag, list);
			
			model.addAttribute("skuActive", skuA);
			model.addAttribute("plantItemList", plantItemList);
			model.addAttribute("listDetial", listDetial);
			model.addAttribute("opType", request.getParameter("opType"));
			List<AppItemLabel> tagList = tagService.getAllTag();
			if(tagList != null && tagList.size()>0){
				model.addAttribute("tagList", tagList);
			}
		}catch(Exception e){
			e.printStackTrace();
			return error("请求有误，请重试", model, request);
		}
		return "/promotions/goods-promotion/goods-promotion-detail";
		
	}
	
	/**
	 * 编辑单品促销活动
	 * @Title: editSKUActive
	 * @date 2016年9月1日  上午10:49:03
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("editSKUActive.do")
	public String editSKUActive(HttpServletRequest request,Model model,String id,String step){
		try{
			//第一次
			if(step.equals("1")){
				SKUActive skuA = sKUActiveService.getSKUActiveById(id);
				List<SKUActiveGoodInfo> glist = sKUActiveService.queryListById(id);
				List<PlantItemVo> plantItemList = sKUActiveService.queryPlatItemList(glist);
				List<SKUActiveGoodInfoVo> listDetial = sKUActiveService.querySKUActiveInfoVoList(id);
				SKUActiveSessionObject sessionObject = new SKUActiveSessionObject();
				PropertyUtils.copyProperties(sessionObject, skuA);
				List<SKUActiveGoodInfo> listNew = new ArrayList<SKUActiveGoodInfo>(glist.size());
				for(SKUActiveGoodInfo one:glist)  {
					listNew.add(one);
				}
				sessionObject.setGoodsList4Old(glist);
				sessionObject.setGoodsList4New(listNew);
				sessionObject.setListDetial(listDetial);
				this.getSession().setAttribute(key_sessionObject4edit + id, sessionObject);
				
				List<SKUActiveTagVo> list = sKUActiveService.querySKUActivePublishTag(id);
				request.getSession().setAttribute(key_skuActivePublishTag, list);
				
				model.addAttribute("skuActive", skuA);
				model.addAttribute("plantItemList", plantItemList);
				model.addAttribute("listDetial", listDetial);
			}else if(step.equals("2")){
				//新增限购商品
				Object obj = this.getSession().getAttribute(key_sessionObject4edit + id);
				SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)obj;
				List<SKUActiveGoodInfo> glist = sessionObject.getGoodsList4New();
				List<PlantItemVo> plantItemList = sKUActiveService.queryPlatItemList(glist);
				model.addAttribute("skuActive", sessionObject);
				model.addAttribute("plantItemList", plantItemList);
				List<SKUActiveGoodInfoVo> listDetialOld = sessionObject.getListDetial();
				List<SKUActiveGoodInfoVo> listDetial = new ArrayList<SKUActiveGoodInfoVo>();
				//处理已有的数据
				for(SKUActiveGoodInfoVo one:listDetialOld){
					for(SKUActiveGoodInfo info:glist){
						if(one.getId().equals(info.getId())){
							listDetial.add(one);
						}
					}
				}
				//处理新增的数据
				List<SKUActiveGoodInfoVo> switchList = sKUActiveService.switchSKUActiveGoodInfoVoList(sessionObject, plantItemList);
				boolean eflag = false;
				for(SKUActiveGoodInfoVo one:switchList){
					for(SKUActiveGoodInfo info:glist){
						if(one.getId().equals(info.getId())){
							eflag = true;
						}
					}
					if(!eflag){
						listDetial.add(one);
					}
				}
				
				model.addAttribute("listDetial", listDetial);
			}else if(step.equals("3")){
				//修改价格、限购数量、标贴，刷新界面
				Object obj = this.getSession().getAttribute(key_sessionObject4edit + id);
				SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)obj;
//				List<SKUActiveGoodInfo> glist = sessionObject.getGoodsList4New();
				model.addAttribute("skuActive", sessionObject);
//				List<SKUActiveGoodInfoVo> listDetialOld = sessionObject.getListDetial();
//				List<SKUActiveGoodInfoVo> listDetial = new ArrayList<SKUActiveGoodInfoVo>();
//				//处理已有的数据
//				for(SKUActiveGoodInfoVo one:listDetialOld){
//					for(SKUActiveGoodInfo info:glist){
//						if(one.getId().equals(info.getId())){
//							listDetial.add(one);
//						}
//					}
//				}
				model.addAttribute("listDetial", sessionObject.getListDetial());
			}else if(step.equals("4")){
				//删除，刷新界面
				Object obj = this.getSession().getAttribute(key_sessionObject4edit + id);
				SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)obj;
				List<SKUActiveGoodInfo> glist = sessionObject.getGoodsList4New();
				model.addAttribute("skuActive", sessionObject);
				List<SKUActiveGoodInfoVo> listDetialOld = sessionObject.getListDetial();
				List<SKUActiveGoodInfoVo> listDetial = new ArrayList<SKUActiveGoodInfoVo>();
				//处理已有的数据
				for(SKUActiveGoodInfoVo one:listDetialOld){
					for(SKUActiveGoodInfo info:glist){
						if(one.getId().equals(info.getId())){
							listDetial.add(one);
						}
					}
				}
				model.addAttribute("listDetial", listDetial);
			}
			
			List<AppItemLabel> tagList = tagService.getAllTag();
			if(tagList != null && tagList.size()>0){
				model.addAttribute("tagList", tagList);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return error("请求有误，请重试", model, request);
		}
		return "/promotions/goods-promotion/goods-promotion-edit";
	}
	
	/**
	 * 编辑单品促销活动，添加促销商品
	 * @Title: editSKUActive4addPlantItem
	 * @date 2016年9月1日  下午7:00:00
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param id
	 * @param spGroupIds 定格信息
	 * @param itemBaseIds 基础商品库id
	 * @return
	 */
	@RequestMapping("editSKUActive4addPlantItem.do")
	@ResponseBody
	public Object editSKUActive4addPlantItem(HttpServletRequest request,String id,Integer[] spGroupIds,Integer[] itemBaseIds ){
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		try{
			Object obj = this.getSession().getAttribute(key_sessionObject4edit + id);
			SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)obj;
			List<SKUActiveGoodInfo> addList = sKUActiveService.querySKUActiveGoodInfoList4AddGoodInfoList(sessionObject, spGroupIds, itemBaseIds);
			for(int i=0;i<addList.size();i++){
				SKUActiveGoodInfo one=addList.get(i);
				if(one.getBuyLimitNum() == null){
					one.setBuyLimitNum(0);
				}
				if(one.getTotalLimitNum() == null){
					one.setTotalLimitNum(0);
				}
				if(one.getTotalBuyNum() == null){
					one.setTotalBuyNum(0);
				}
				addList.set(i, one);
			}
			sessionObject.setGoodsList4New(addList);
			this.getSession().setAttribute(key_sessionObject4edit + id,sessionObject);
			sKUActiveService.updateSKUActiveGoodInfo(id, addList);
			return ResponseUtils.sendMsg(true,"处理成功",null,BusinessCodeConts.allbusiness_ok,null);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"处理失败",null,"",null);
		}		
	}
	
	/**
	 * 编辑商品，修改单品促销商品的tag
	 * @Title: editSKUActive4updateTag
	 * @date 2016年9月1日  下午7:00:53
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param id
	 * @param spGroupIds
	 * @param itemBaseIds
	 * @return
	 */
	@RequestMapping("editSKUActive4updateTag.do")
	@ResponseBody
	public Object editSKUActive4updateTag(HttpServletRequest request,String id,String plantItemId,String tagId){
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		try{
			return sKUActiveService.updateSKUActiveGoodInfoTagId(id, plantItemId, tagId);
//			Object obj = this.getSession().getAttribute(key_sessionObject4edit + id);
//			SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)obj;
//			List<SKUActiveGoodInfo> listNew = sessionObject.getGoodsList4New();
//			for(int i=0;i<listNew.size();i++){
//				SKUActiveGoodInfo one = listNew.get(i);
//				if(one.getPlantItemId().equals(plantItemId.trim())){
//					one.setTagId(tagId);
//					listNew.set(i, one);
//				}
//			}
//			sessionObject.setGoodsList4New(listNew);
//			
//			List<SKUActiveGoodInfoVo> listVo = sessionObject.getListDetial();
//			for(int i=0;i<listVo.size();i++){
//				SKUActiveGoodInfoVo one = listVo.get(i);
//				if(one.getPlantItemId().equals(plantItemId.trim())){
//					one.setTagId(tagId);
//					listVo.set(i, one);
//				}
//			}
//			sessionObject.setListDetial(listVo);
//			this.getSession().setAttribute(key_sessionObject4edit + id,sessionObject);
//			return ResponseUtils.sendMsg(true,"处理成功",null,BusinessCodeConts.allbusiness_ok,null);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"处理失败",null,"",null);
		}		
	}
	
	/**
	 * 修改价格
	 * @Title: editSKUActive4updatePrice
	 * @date 2016年9月2日  上午11:20:23
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param id
	 * @param plantItemId
	 * @param price
	 * @return
	 */
	@RequestMapping("editSKUActive4updatePrice.do")
	@ResponseBody
	public Object editSKUActive4updatePrice(HttpServletRequest request,String id,String plantItemId,String price){
		CustomerService user = null;
		
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		try{
			return sKUActiveService.updateSKUActiveGoodInfoPrice(id, plantItemId, new BigDecimal(price));
//			Object obj = this.getSession().getAttribute(key_sessionObject4edit + id);
//			SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)obj;
//			List<SKUActiveGoodInfo> addList = sessionObject.getGoodsList4New();
//			for(int i=0;i<addList.size();i++){
//				SKUActiveGoodInfo one = addList.get(i);
//				if(one.getPlantItemId().equals(plantItemId.trim())){
//					one.setActivePrice(new BigDecimal(price));
//					addList.set(i, one);
//				}
//			}
//			sessionObject.setGoodsList4New(addList);
//			
//			List<SKUActiveGoodInfoVo> listVo = sessionObject.getListDetial();
//			for(int i=0;i<listVo.size();i++){
//				SKUActiveGoodInfoVo one = listVo.get(i);
//				if(one.getPlantItemId().equals(plantItemId.trim())){
//					one.setActivePrice(new BigDecimal(price));
//					listVo.set(i, one);
//				}
//			}
//			sessionObject.setListDetial(listVo);
//			
//			this.getSession().setAttribute(key_sessionObject4edit + id,sessionObject);
//			return ResponseUtils.sendMsg(true,"处理成功",null,BusinessCodeConts.allbusiness_ok,null);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"处理失败",null,"",null);
		}		
	}
	
	/**
	 * 修改限购数量
	 * @Title: editSKUActive4updateNum
	 * @date 2016年9月2日  上午11:20:11
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param id
	 * @param plantItemId
	 * @param num
	 * @return
	 */
	@RequestMapping("editSKUActive4updateNum.do")
	@ResponseBody
	public Object editSKUActive4updateNum(HttpServletRequest request,String id,String plantItemId,String num){
		CustomerService user = null;
		
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		try{
			return sKUActiveService.updateSKUActiveGoodInfoLimitNum(id, plantItemId, new Integer(num));
//			Object obj = this.getSession().getAttribute(key_sessionObject4edit + id);
//			SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)obj;
//			List<SKUActiveGoodInfo> addList = sessionObject.getGoodsList4New();
//			for(int i=0;i<addList.size();i++){
//				SKUActiveGoodInfo one = addList.get(i);
//				if(one.getPlantItemId().equals(plantItemId.trim())){
//					one.setBuyLimitNum(new Integer(num));
//					addList.set(i, one);
//				}
//			}
//			sessionObject.setGoodsList4New(addList);
//			
//			List<SKUActiveGoodInfoVo> listVo = sessionObject.getListDetial();
//			for(int i=0;i<listVo.size();i++){
//				SKUActiveGoodInfoVo one = listVo.get(i);
//				if(one.getPlantItemId().equals(plantItemId.trim())){
//					one.setBuyLimitNum(new Integer(num));
//					listVo.set(i, one);
//				}
//			}
//			sessionObject.setListDetial(listVo);
//			
//			this.getSession().setAttribute(key_sessionObject4edit + id,sessionObject);
//			return ResponseUtils.sendMsg(true,"处理成功",null,BusinessCodeConts.allbusiness_ok,null);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"处理失败",null,"",null);
		}		
	}
	
	@RequestMapping("editSKUActive4updateTotalNum.do")
	@ResponseBody
	public Object editSKUActive4updateTotalNum(HttpServletRequest request,String id,String plantItemId,String totalNum){
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		try{
			return sKUActiveService.updateSKUActiveGoodInfototalLimitNum(id, plantItemId, new Integer(totalNum));
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"处理失败",null,"",null);
		}		
	}
	
	/**
	 * 删除一个单品促销商品。
	 * @Title: delSKUActiveGoodsInfo
	 * @date 2016年9月1日  下午7:04:22
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param id
	 * @param plantItemId
	 * @return
	 */
	@RequestMapping("delSKUActiveGoodsInfo.do")
	@ResponseBody
	public Object delSKUActiveGoodsInfo(HttpServletRequest request,String id,String plantItemId){
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		try{
//			Object obj = this.getSession().getAttribute(key_sessionObject4edit + id);
//			SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)obj;
//			List<SKUActiveGoodInfo> listOne = sessionObject.getGoodsList4New();
//			List<SKUActiveGoodInfo> listNew = new ArrayList<SKUActiveGoodInfo>();
//			for(SKUActiveGoodInfo one : listOne){
//				if(!one.getPlantItemId().equals(plantItemId)){
//					listNew.add(one);
//				}
//			}
//			sessionObject.setGoodsList4New(listNew);
//			
//			List<SKUActiveGoodInfoVo> listVo = sessionObject.getListDetial();
//			List<SKUActiveGoodInfoVo> listVoNew = new ArrayList<SKUActiveGoodInfoVo>();
//			for(int i=0;i<listVo.size();i++){
//				if(!listVo.get(i).getPlantItemId().equals(plantItemId)){
//					listNew.add(listVo.get(i));
//				}
//			}
//			sessionObject.setListDetial(listVoNew);
//			
//			this.getSession().setAttribute(key_sessionObject4edit + id,sessionObject);
//			return ResponseUtils.sendMsg(true,"处理成功",null,BusinessCodeConts.allbusiness_ok,null);
			
			return sKUActiveService.delSKUActiveGoodInfo(id, plantItemId);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"处理失败",null,"",null);
		}		
	}
	
	/**
	 * 查询所有的定格信息
	 * @Title: queryRegionList
	 * @date 2016年9月1日  下午2:23:38
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("queryRegionList.do")
	@ResponseBody
	public Object queryRegionList(HttpServletRequest request , Model model)
	{
		try{
			List<RegionVo> regionList = regionService.getAllAreaAndSpGroup();
			regionList = initRegionVoListIsNocheck(regionList);
			if(regionList != null && regionList.size()>0){
				model.addAttribute("regionList", JSONUtil.objectToJSONString(regionList));
			}
			return ResponseUtils.sendMsg(true,regionList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseUtils.sendMsg(false,"处理失败");
	}
	
	/**
	 * 根据定格id，查询批发商列表
	 * @Title: querySupplierList
	 * @date 2016年9月1日  下午3:04:54
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param model
	 * @param spGroupIds
	 * @return
	 */
	@RequestMapping("querySupplierList.do")
	@ResponseBody
	public Object querySupplierList(HttpServletRequest request , Model model,Integer[] spGroupIds)
	{
		try{
			List supplierList = supplierService.getSupplierBySpGroupId(spGroupIds);
			if(supplierList != null && supplierList.size()>0){
				model.addAttribute("suppliers", JSONUtil.objectToJSONString(supplierList));
			}
			return ResponseUtils.sendMsg(true,JSONUtil.objectToJSONString(supplierList));
		}catch(Exception e){
			e.printStackTrace();
		}
		return ResponseUtils.sendMsg(false,"处理失败");
	}
	
	/**
	 * 保存数据到数据库
	 * @Title: saveEditSKUActive
	 * @date 2016年9月1日  下午7:05:58
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("saveEditSKUActive.do")
	@ResponseBody
	public Object saveEditSKUActive(HttpServletRequest request,String id){
		CustomerService user = null;
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		try{
			Object obj = this.getSession().getAttribute(key_sessionObject4edit + id);
			SKUActiveSessionObject sessionObject = (SKUActiveSessionObject)obj;
			return sKUActiveService.saveEditSKUActive(user.getUserName(),sessionObject);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"处理失败",null,"",null);
		}
	}
	
	/**
	 * 处理所有的单品促销活动任务
	 * @Title: taskSKUActive
	 * @date 2016年9月12日  下午5:36:32
	 * @author 小武
	 * @version  飓风
	 * @param request
	 * @param auth
	 * @return
	 */
	@RequestMapping("taskSKUActive.do")
	@ResponseBody
	public Object taskSKUActive(HttpServletRequest request,String auth){
		try{
			if(StringUtil.stringIsNullOrEmpty(auth)){
				return ResponseUtils.sendMsg(false,"口令为空或口令错误");
			}
			
			SystemInfo info = systemInfoService.getSystemInfo(key_SKUActiveTaskAuth);
			if(null == info || !info.getContent().equals(auth)){
				return ResponseUtils.sendMsg(false,"口令为空或口令错误");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			Map<String, Object> maps = sKUActiveService.taskSKUActive();
			return ResponseUtils.sendMsg(true,maps);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
	@RequestMapping("taskEffecSKUActive.do")
	@ResponseBody
	public Object taskEffecSKUActive(HttpServletRequest request,String auth,String id){
		try{
			if(StringUtil.stringIsNullOrEmpty(auth)){
				return ResponseUtils.sendMsg(false,"口令为空或口令错误");
			}
			
			SystemInfo info = systemInfoService.getSystemInfo(key_SKUActiveTaskAuth);
			if(null == info || !info.getContent().equals(auth)){
				return ResponseUtils.sendMsg(false,"口令为空或口令错误");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			return  sKUActiveService.effecSKUActive(id, "job");
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
	@RequestMapping("taskInvalidSKUActive.do")
	@ResponseBody
	public Object taskInvalidSKUActive(HttpServletRequest request,String auth,String id){
		try{
			if(StringUtil.stringIsNullOrEmpty(auth)){
				return ResponseUtils.sendMsg(false,"口令为空或口令错误");
			}
			
			SystemInfo info = systemInfoService.getSystemInfo(key_SKUActiveTaskAuth);
			if(null == info || !info.getContent().equals(auth)){
				return ResponseUtils.sendMsg(false,"口令为空或口令错误");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			return  sKUActiveService.invalidSKUActive(id, "job");
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
	/**
	 * 编辑生效活动的价格
	 * @Title: editEffecSKUActive4updatePrice
	 * @date 2016年10月14日  上午10:22:05
	 * @author 小武
	 * @version  七彩虹
	 * @param request
	 * @param id
	 * @param plantItemId
	 * @param price
	 * @return
	 */
	@RequestMapping("editEffecSKUActive4updatePrice.do")
	@ResponseBody
	public Object editEffecSKUActive4updatePrice(HttpServletRequest request,String id,String plantItemId,String price){
		CustomerService user = null;
		
		try{
			user =(CustomerService)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(StringUtil.stringIsNullOrEmpty(user.getUserName())){
				return ResponseUtils.sendMsg(false,"用户名称为空");
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"请登陆系统后再执行此操作");
		}
		
		try{
			return sKUActiveService.updateEffecSKUActiveGoodInfoPrice(id, plantItemId, new BigDecimal(price));
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"处理失败",null,"",null);
		}		
	}
	
}
