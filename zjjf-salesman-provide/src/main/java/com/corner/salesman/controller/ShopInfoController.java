package com.corner.salesman.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.corner.rpc.salesman.api.service.DictService;
import com.corner.rpc.salesman.api.service.LinePlansService;
import com.corner.rpc.salesman.api.service.ShopService;
import com.corner.rpc.salesman.model.LinePlans;
import com.corner.rpc.salesman.model.Shop;
import com.corner.rpc.shop.api.service.RegionService;
import com.corner.rpc.shop.api.service.StoreService;
import com.corner.rpc.shop.model.Store;
import com.corner.salesman.common.persistence.AppPage;
import com.corner.salesman.common.utils.AppJson;
import com.corner.salesman.common.utils.EmojiFilter;
import com.corner.salesman.common.utils.Encodes;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.commons.utils.GenerateSequence;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping("/mobile/shop")
public class ShopInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopInfoController.class);

//	@Autowired
//	private ShopInfoService shopInfoService;	
	@Autowired
	private RegionService regionService;
	@Autowired
	private DictService dictService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private LinePlansService lineServce;
	
	/**
	 * 获取店铺类型信息
	 * @param dailyVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = {"getShopType"})
	public Object getShopType(HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 AppJson appJson = new AppJson();
			 List list = dictService.getDictListByType("shop_type");
			 appJson.setList(list);
			 json.setData(appJson);
			 json.setMsg("获取店铺类型列表成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 logger.error("获取店铺类型列表异常:{}",e.getMessage());
			 json.setMsg("获取店铺类型列表异常！");
			 json.setSuccess(false);
			 json.setCode("500");
		}
		return json;
	}
	        
	/**
	 * 添加商铺信息
	 * @param shopInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addShopInfo"})
	public Object addShopInfoInfo(Shop shopInfo, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 logger.info("提交商铺信息为：{}", JSON.toJSON(shopInfo));
			 String shopName = shopInfo.getShopName();
			 String shopAddress = shopInfo.getShopAddress();
			 String remarks = shopInfo.getRemarks();
			 String shopArea = shopInfo.getShopArea();//商铺面积
			 String sku = shopInfo.getSku();//库存
			 String staffNum = shopInfo.getStaffNum();//人员数量
			 String distributionNum = shopInfo.getDistributionNum();//配送人员数量
			 String turnover = shopInfo.getTurnover();//日均营业额
			 
			 Integer provinceId = shopInfo.getProvinceId();
			 Integer cityId = shopInfo.getCityId();
			 Integer areaId = shopInfo.getAreaId();
			 
			 if(null == provinceId || provinceId ==0){
				json.setMsg("省份ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 if(null == cityId || cityId ==0){
				json.setMsg("城市ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 if(null == areaId || areaId ==0){
				json.setMsg("区域ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 
			 if(StringUtils.isNotBlank(shopArea)&&shopArea.length()>9){
				json.setMsg("商铺面积不能超过9位数字长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }else if(StringUtils.isBlank(shopArea)){
				 //防止app端传空字符串，insert到整数的字段时候会报错
				 shopInfo.setShopArea(null);//商铺面积
			 }
			 if(StringUtils.isNotBlank(sku)&&sku.length()>9){
				json.setMsg("库存不能超过9位数字长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }else if(StringUtils.isBlank(sku)){
				 //防止app端传空字符串，insert到整数的字段时候会报错
				 shopInfo.setSku(null);//商铺面积
			 }
			 if(StringUtils.isNotBlank(staffNum)&&staffNum.length()>9){
				json.setMsg("人员数量不能超过9位数字长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }else if(StringUtils.isBlank(staffNum)){
				 //防止app端传空字符串，insert到整数的字段时候会报错
				 shopInfo.setStaffNum(null);//商铺面积
			 }
			 if(StringUtils.isNotBlank(distributionNum)&&distributionNum.length()>9){
				json.setMsg("配送人员数量不能超过9位数字长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }else if(StringUtils.isBlank(staffNum)){
				 //防止app端传空字符串，insert到整数的字段时候会报错
				 shopInfo.setStaffNum(null);//商铺面积
			 }	 
			 if(StringUtils.isNotBlank(turnover)&&turnover.length()>9){
				json.setMsg("日均营业额不能超过9位数字长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }else if(StringUtils.isBlank(turnover)){
				 //防止app端传空字符串，insert到整数的字段时候会报错
				 shopInfo.setTurnover(null);//商铺面积
			 }
			 
			 
			 if(StringUtils.isNotBlank(shopName)){
				if(EmojiFilter.containsEmoji(shopName)){
					json.setMsg("商铺名称不能包含特殊字符！");
					json.setSuccess(false);
					json.setCode("500");
					return json;
				}
			 }
			 if(StringUtils.isNotBlank(shopAddress)){
				if(EmojiFilter.containsEmoji(shopAddress)){
					json.setMsg("商铺地址不能包含特殊字符！");
					json.setSuccess(false);
					json.setCode("500");
					return json;
				}
			 }
			 if(StringUtils.isNotBlank(remarks)){
				if(EmojiFilter.containsEmoji(remarks)){
					json.setMsg("备注信息不能包含特殊字符！");
					json.setSuccess(false);
					json.setCode("500");
					return json;
				}
			 }
			 //对shopInfo对象中包含中文字段且不为空的字段进行解码
			 decodeObject(shopInfo);
			 shopService.addShopInfo(shopInfo);
			 json.setMsg("添加商铺信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 json.setMsg("添加商铺信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
			logger.info("添加商铺信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	public void generateShopNo(Shop shopInfo) throws Exception{
			//区域编号
			String regionStr = regionService.getRegionStr(shopInfo.getProvinceId(),shopInfo.getCityId(),shopInfo.getAreaId());
			regionStr = regionStr==null || "".equals(regionStr)?"":regionStr;
			//根据区域查出生成的最大值
			Store store = new Store();
			store.setAreaId(shopInfo.getAreaId());
			Integer num = storeService.getMaxSequenceNum(store);
			if(num==null){
				num=0;
			}
			//获取生成的数字
			String sequenceNum = "";
			do {
				sequenceNum = GenerateSequence.getSequence(num, 4);
				if(sequenceNum.contains("4")){
					num++;
					//不包含4的
					continue;
				}else{
					break;
				}
			} while (true);
			//拼接的店铺编号
			String supplierCode = (regionStr+sequenceNum).toString();
			// 查询该店铺编号是否存在
			// 如果是已经审批过的店铺,不进行判断
			/*int total =storeService.checkShopNoIsExist(supplierCode);
			if (total>0) {
				this.generateShopNo(shopInfo);
			}*/
			shopInfo.setShopNo(supplierCode);
	}
	
	/**
	 * 修改商铺信息
	 * @param shopInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"updateShopInfo"})
	public Object updateShopInfo(Shop shopInfo, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 //logger.info("修改商铺信息为：{}", JSON.toJSON(shopInfo));
			 String shopId = shopInfo.getShopId();
			 String shopArea = shopInfo.getShopArea();//商铺面积
			 String sku = shopInfo.getSku();//库存
			 String staffNum = shopInfo.getStaffNum();//人员数量
			 String distributionNum = shopInfo.getDistributionNum();//配送人员数量
			 String turnover = shopInfo.getTurnover();//日均营业额
			 
			 if(StringUtils.isBlank(shopId)){
				json.setMsg("店铺ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }else if(StringUtils.isNotBlank(shopArea)&&shopArea.length()>9){
				json.setMsg("商铺面积不能超过9位数字长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }else if(StringUtils.isBlank(shopArea)){
				 //防止app端传空字符串，insert到整数的字段时候会报错
				 shopInfo.setShopArea(null);//商铺面积
			 }
			 if(StringUtils.isNotBlank(sku)&&sku.length()>9){
				json.setMsg("库存不能超过9位数字长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }else if(StringUtils.isBlank(sku)){
				 //防止app端传空字符串，insert到整数的字段时候会报错
				 shopInfo.setSku(null);//商铺面积
			 }
			 if(StringUtils.isNotBlank(staffNum)&&staffNum.length()>9){
				json.setMsg("人员数量不能超过9位数字长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }else if(StringUtils.isBlank(staffNum)){
				 //防止app端传空字符串，insert到整数的字段时候会报错
				 shopInfo.setStaffNum(null);//商铺面积
			 }
			 if(StringUtils.isNotBlank(distributionNum)&&distributionNum.length()>9){
				json.setMsg("配送人员数量不能超过9位数字长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }else if(StringUtils.isBlank(staffNum)){
				 //防止app端传空字符串，insert到整数的字段时候会报错
				 shopInfo.setStaffNum(null);//商铺面积
			 }	 
			 if(StringUtils.isNotBlank(turnover)&&turnover.length()>9){
				json.setMsg("日均营业额不能超过9位数字长度！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }else if(StringUtils.isBlank(turnover)){
				 //防止app端传空字符串，insert到整数的字段时候会报错
				 shopInfo.setTurnover(null);//商铺面积
			 } 
			 //对shopInfo对象中包含中文字段且不为空的字段进行解码
			 decodeObject(shopInfo);
			 shopService.updateShopInfo(shopInfo);
			 json.setMsg("修改商铺信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 json.setMsg("修改商铺信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
			logger.info("修改商铺信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 对shopInfo对象中包含中文字段且不为空的字段进行解码
	 * @param shopInfo
	 */
	public void decodeObject(Shop shopInfo){
		 String shopName = shopInfo.getShopName();
		 String shopAddress = shopInfo.getShopAddress();
		 String shopType = shopInfo.getShopType();
		 String mainProduct = shopInfo.getMainProduct();
		 String dcShop = shopInfo.getDcShop();//配送商
		 String ipay = shopInfo.getIpay();
		 String remarks = shopInfo.getRemarks();
		 String province = shopInfo.getProvince();
		 String city = shopInfo.getCity();
		 String area = shopInfo.getArea();
		 String bossName = shopInfo.getBossName();
		 
		 
		 //对app端转码的字段进行解码
		 shopName = Encodes.urlDecode(shopName);
		 shopInfo.setShopName(shopName);
		 shopAddress = Encodes.urlDecode(shopAddress);
		 shopInfo.setShopAddress(shopAddress);
		 shopType = Encodes.urlDecode(shopType);
		 shopInfo.setShopType(shopType);
		 mainProduct = Encodes.urlDecode(mainProduct);
		 shopInfo.setMainProduct(mainProduct);
		 dcShop = Encodes.urlDecode(dcShop);
		 shopInfo.setDcShop(dcShop);
		 ipay = Encodes.urlDecode(ipay);
		 shopInfo.setIpay(ipay);
		 remarks = Encodes.urlDecode(remarks);
		 shopInfo.setRemarks(remarks);
		 province = Encodes.urlDecode(province);
		 shopInfo.setProvince(province);
		 city = Encodes.urlDecode(city);
		 shopInfo.setCity(city);
		 area = Encodes.urlDecode(area);
		 shopInfo.setArea(area);
		 bossName = Encodes.urlDecode(bossName);
		 shopInfo.setBossName(bossName);
	}
	
	
	
	
	/**
	 * 根据门店ID查询门店信息
	 * @param shopId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getShopInfoById"})
	public Object getShopInfoById(Shop shop, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			
			 //查询店铺ID查询店铺信息
			 String shopId = shop.getShopId();
			 String lineId = shop.getLineId();
			 logger.info("根据门店ID：{},线路ID：{}", shopId,lineId);
			 Map<String,Object> paramMap = new HashMap<String,Object>();
			 paramMap.put("shopId", shopId);
			 paramMap.put("lineId", lineId);
			 Shop retShop = shopService.findShopById(paramMap);
			 AppJson resultObj = new  AppJson();
			 resultObj.setResultObj(retShop);
			 resultObj.setList(null);
			 json.setData(resultObj);
			 json.setMsg("查询查询门店信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 json.setMsg("查询查询门店信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
			logger.info("查询查询门店信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 根据业务员ID获取我的商铺列表信息
	 * @param shopInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyShopList"})
	public Object getMyShopList(Shop shop, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 //logger.info("获取商铺列表条件为：{}", JSON.toJSONString(shopInfo));
			 //Page<ShopInfo> page = shopInfoService.queryShopList(new Page<ShopInfo>(request, response), shopInfo);
			 //Page<Shop> page = shopService.queryShopList(new Page<Shop>(request, response), shop);
			String userType = shop.getUserType();
			String userId = shop.getUserId();
			String deptId = shop.getDeptId();
			//如果部门是总裁办、运营部级销售经理，则可以本公司所有
			 if("C_1".equals(deptId) || "XSB001".equals(deptId)|| "YY001".equals(deptId)|| "TEST001".equals(deptId)){
					//如果是上部门直接查询全部（不需要任何条件）
				 shop.setDeptId(null);
			 }
			 //如果是普通用户则需要将用户id设置到业务员属性作为查询条件查询
			 if("0".equals(userType)){
				 shop.setSalesmanId(userId);
			 }
			 
			 Page<Shop> page = shopService.getMyShopList(new Page<Shop>(request, response), shop);
			 AppPage<Shop> target = new AppPage<Shop>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取个人商铺列表信息成功！");
			 json.setSuccess(true);
			//logger.info("获取商铺列表信息为：{}", JSON.toJSONString(json));
		} catch (Exception e) {
			 json.setMsg("获取个人商铺列表信息异常！");
			 json.setSuccess(false);
			 json.setCode("500");
			logger.info("获取个人商铺列表信息异常：{}",e.getMessage());
		}
		return json;
	}
	
	/**
	 * 根据客户ID获取客户线路列表
	 * @param shop
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyShopLineList"})
	public Object getMyShopLineList(Shop shop,HttpServletRequest request){
		Json json = new Json();
			try {
				 AppJson appJson = new AppJson();	
				 //声明一个list
				 List<HashMap<String,Object>> shoplinelist =shopService.getMyShopLineList(shop);
				 //接收这个list的数据
			   	 appJson.setList(shoplinelist);
			   	 //显示数据
			   	 json.setData(appJson);
				 json.setMsg("查询客户线路列表成功！");
				 //返回为true还是false
				 json.setSuccess(true);
			} catch (Exception e) {				 
				 json.setMsg("查询客户线路列表异常！");
				 json.setSuccess(false);
				 json.setCode("500");
			}
		return json;
		
	}
	
	
	/**
	 * 我的拜访线路方法
	 * @param record
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getMyVisitLineList"})
	public Object  getMyVisitLineList(LinePlans record,HttpServletRequest request){
		//声明一个json用来接受数据
		Json json=new Json();
		try {
			
			 AppJson appJson = new AppJson();	
			 List<HashMap<String,Object>> linelist =lineServce.getMyVisitLineList(record);
			 //保存list
			 appJson.setList(linelist);
			 //显示查询到的结果
			 json.setData(appJson);
			 json.setMsg("查询拜访路线列表成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			 json.setMsg("查询拜路线列表异常！");
			 json.setSuccess(false);
			 //返回状态
			 json.setCode("500");
		}
		return json;	
	}
	
	/**
	 * 根据条件获取业务员管理商铺的坐标列表
	 * @param lineVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getShopSiteList"})
	public Object getShopSiteList(Shop shop, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			
			String userType = shop.getUserType();
			String userId = shop.getUserId();
			//String salesmanId = shop.getSalesmanId();
			//String deptId = shop.getDeptId();
			 if(StringUtils.isBlank(userType)){
				 json.setMsg("用户类型不能为空！");
				 json.setSuccess(false);
				 json.setCode("500");
				 return json;
			 }
			 
			 if("0".equals(userType)){
				 shop.setSalesmanId(userId);
			 }
			 
			AppJson appJson = new AppJson();
			List<HashMap<String,Object>> list = shopService.getShopSiteList(shop);
			appJson.setList(list);
			json.setData(appJson);
			json.setSuccess(true);
			json.setMsg("获取商铺的坐标列表成功！");
		} catch (Exception e) {
			json.setCode("500");
			json.setSuccess(false);
			json.setMsg("获取商铺的坐标列表异常！");
			logger.error("获取商铺的坐标列表异常{}",e.getMessage());
		}
		return json;
	}
}
