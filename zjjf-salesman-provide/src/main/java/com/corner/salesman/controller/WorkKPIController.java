package com.corner.salesman.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.corner.rpc.salesman.api.service.ShopService;
import com.corner.rpc.salesman.api.service.SpGroupLineService;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.commons.utils.DateUtils;
import com.corner.salesman.commons.utils.ObjectUtils;
import com.corner.salesman.service.DepartmentService;
import com.corner.salesman.service.UserService;
import com.google.common.collect.Lists;
import com.zjjf.analysis.producer.ajie.IStoreTurnoverService;


/**  
 * 工作绩效控制类
 * 创建时间：2016-7-6 下午1:17:27  
 * @author yuanbao  
 * @version v1.5.0  
 */
@Controller
@RequestMapping("/mobile/kpi")
public class WorkKPIController {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkKPIController.class);

	@Autowired
	private ShopService shopService;	
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private IStoreTurnoverService storeTurnoverService;
	@Autowired
	private SpGroupLineService spGroupLineService;
	@Autowired
	private UserService userService;
	@Autowired
	private DepartmentService deptService;

	/**
	 * 获取用户关注的区域销售汇总金额
	 * @param dayTime
	 * @param storeId
	 * @param timeType
	 * @param pageNo
	 * @param offset
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = {"getKPIBaseInfo"})
	public Object getKPIBaseInfo(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "createTime", required = false, defaultValue = "") String createTime,
			@RequestParam(value = "deptId", required = false, defaultValue = "") String deptId,
			@RequestParam(value = "areaId", required = false, defaultValue = "") Integer areaId) {
			
		Json json = new Json();
		
		try {

			logger.info("===================方法 名称为：getKPIBaseInfo的方法，查询请求参数分别为：userId={},deptId={},areaId={},createTime={} ===============",userId,deptId,areaId,createTime);
			//如果时间为空，则默认查询当天时间
			String dayTime = null;
			//如果时间为空，则默认查询当天时间
			if(StringUtils.isBlank(createTime)){
				dayTime = DateUtils.dateToCompactString(new Date());//yyyyMMdd
			}else{
				dayTime = createTime.replaceAll("-", "");
			}
			
			String saleMoney = null,buyMoney = null,monthMoney = null;
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			//如果是总裁办、销售部 和 运营部门 则直接按照最高级别的权限查看
			if("C_1".equals(deptId) || "XSB001".equals(deptId)|| "YY001".equals(deptId)|| "TEST001".equals(deptId)){
				HashMap<String, Object> allMap = new HashMap<String, Object>();
				//查询当天全部销售额
				//allMap.put("isUnion", "1");
				allMap.put("dayTime", dayTime);
				logger.info("===================公司领导今日战绩getKPIBaseInfo方法 查询【当天全部销售额】全部的入参为：{}",JSONObject.toJSONString(allMap));
				List<HashMap<String,Object>> saleList = storeTurnoverService.getData(allMap);
				HashMap<String, Object> retSaleMap = saleList.get(0);
				saleMoney = retSaleMap.get("turnover")+"";
				saleMoney = ObjectUtils.formatMoney(saleMoney);//去除小数点
				
				//查询当天全部采购金额
				allMap.put("isUnion", "2");
				logger.info("===================公司领导今日战绩getKPIBaseInfo方法 查询【当天全部采购销售额】全部的入参为：{}",JSONObject.toJSONString(allMap));
				List<HashMap<String,Object>> buyList = storeTurnoverService.getData(allMap);
				HashMap<String, Object> retBuyMap = buyList.get(0);
				buyMoney = retBuyMap.get("turnover")+"";
				buyMoney = ObjectUtils.formatMoney(buyMoney);//去除小数点
				
				//查询当月全部销售金额
				allMap.put("timeType", "month");
				List<HashMap<String,Object>> saleAllList = storeTurnoverService.getData(allMap);
				HashMap<String, Object> retMonthMap = saleAllList.get(0);
				monthMoney = retMonthMap.get("turnover")+"";
				monthMoney = ObjectUtils.formatMoney(monthMoney);//去除小数点
				
			}else{
				//获取当前部门及下属子部门绑定的定格
				List<String> spGroupIdList = null;
				List<String> storeCodeList = null;
				List<HashMap<String, String>> regionList = userService.getKPIDeptLevelList(deptId);
				
				//如果没有下属部门，则按照本部门下所有业务员所维护的客户来统计
				if(null != regionList && regionList.size()==1){
					storeCodeList = shopService.getShopNoListByDeptId(deptId);//获取本部门下方所有业务员的客户编码
				}else{
					//获取当前部门及下属子部门绑定的定格
					List<String> deptIdList = deptService.findAllChildDeptIdList(deptId);
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("deptIdList", deptIdList);
					spGroupIdList = spGroupLineService.getDeptBindSpGroupList(paramMap);
				}
				
				//必须保证定格编码列表或 客户编码列表不为空才可以查询接口
				if((null != storeCodeList && !storeCodeList.isEmpty()) || (null != spGroupIdList && !spGroupIdList.isEmpty())){
					//List<String> areaIdList = deptService.findDeptBindAreaList(deptId);
					//如果是其他部门的领导，在销售部门的层级范围内且部门层级为2时，则按照区域统计
					HashMap<String, Object> allMap = new HashMap<String, Object>();
					//查询当天全部销售额
					//allMap.put("areaIdList", areaIdList);
					allMap.put("spGroupIdList", spGroupIdList);
					allMap.put("storeCodeList", storeCodeList);
					allMap.put("dayTime", dayTime);
					//allMap.put("isUnion", "1");
					logger.info("===================部门领导今日战绩getKPIBaseInfo方法 查询【当天全部销售额】全部的入参为：{}",JSONObject.toJSONString(allMap));
					List<HashMap<String,Object>> saleList = storeTurnoverService.getData(allMap);
					HashMap<String, Object> retSaleMap = saleList.get(0);
					saleMoney = retSaleMap.get("turnover")+"";
					saleMoney = ObjectUtils.formatMoney(saleMoney);//去除小数点
					
					//查询当天全部采购金额
					allMap.put("isUnion", "2");
					logger.info("===================部门领导今日战绩getKPIBaseInfo方法 查询【当天全部采购销售额】全部的入参为：{}",JSONObject.toJSONString(allMap));
					List<HashMap<String,Object>> buyList = storeTurnoverService.getData(allMap);
					HashMap<String, Object> retBuyMap = buyList.get(0);
					buyMoney = retBuyMap.get("turnover")+"";
					buyMoney = ObjectUtils.formatMoney(buyMoney);//去除小数点
					
					//查询当天全部采购金额
					allMap.put("isUnion", "");
					allMap.put("timeType", "month");
					List<HashMap<String,Object>> saleAllList = storeTurnoverService.getData(allMap);
					HashMap<String, Object> retMonthMap = saleAllList.get(0);
					monthMoney = retMonthMap.get("turnover")+"";
					monthMoney = ObjectUtils.formatMoney(monthMoney);//去除小数点
				}
			}
			
			String deptName = null;
			List<HashMap<String, String>> levelList = userService.getDeptLevelList(deptId);
			if(null != levelList && !levelList.isEmpty()){
				HashMap<String, String> levelMap = levelList.get(0);
				deptName = levelMap.get("deptName")+"";
			}
			
			resultMap.put("deptName", deptName);
			resultMap.put("saleMoney", saleMoney==null?"0":saleMoney);
			resultMap.put("buyMoney", buyMoney==null?"0":buyMoney);
			resultMap.put("monthMoney", monthMoney==null?"0":monthMoney);
			json.setData(resultMap);
			json.setSuccess(true);
			json.setCode("200");
			json.setMsg("加载工作首页基础数据成功！");
			logger.info("===================方法 名称为：getKPIBaseInfo的方法，查询请求参数分别为：userId={},deptId={},areaId={},createTime={},返回结果为：{}",
					userId,deptId,areaId,createTime,JSON.toJSONString(json));
			
		} catch (Exception e) {
			logger.error("========方法 名称为：getKPIBaseInfo的方法,加载工作首页基础数据异常：{}",e);
			json.setSuccess(false);
			json.setCode("500");
			json.setMsg("加载工作首页基础数据异常！");
		}
		return json;
	}
	
	
	/**
	 * 获取关注区域战绩销售数据
	 * @param userId 用户ID
	 * @param deptId 部门ID
	 * @param areaId 区域ID
	 * @param type(1:表示普通用户；2：表示管理者；)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = {"getKPIDetailInfo"})
	public Object getKPIDetailInfo(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "createTime", required = false, defaultValue = "") String createTime,
			@RequestParam(value = "deptId", required = false, defaultValue = "") String deptId,
			@RequestParam(value = "areaId", required = false, defaultValue = "") Integer areaId,
			@RequestParam(value = "salesmanId", required = false, defaultValue = "") String salesmanId,
			@RequestParam(value = "userType", required = false, defaultValue = "0") String userType) {
		
		String visitSaleMoney = null,saleMoneyAll = null,visitBuyMoney = null,buyMoneyAll = null,dbUser = null;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Json json = new Json();
		
		try {
			logger.info("===================方法 名称为：getKPIDetailInfo的方法，查询请求参数分别为：userId={},deptId={},areaId={},createTime={},salesmanId={},userType={}",
					userId,deptId,areaId,createTime,salesmanId,userType);
			
			String dayTime = null;
			//如果时间为空，则默认查询当天时间
			if(StringUtils.isBlank(createTime)){
				dayTime = DateUtils.dateToCompactString(new Date());//yyyyMMdd
			}else{
				dayTime = createTime.replaceAll("-", "");
			}
			
			//如果userId不等于salesmanId的时候表示要查询当前选择业务员所管理的客户，故需要将类型设置为0（走普通用户查询方法查询）
			//if(StringUtils.isNotBlank(salesmanId) && !userId.equals(salesmanId)){
			if(StringUtils.isNotBlank(salesmanId)){
				userType = "0";
			}
			
			if("1".equals(userType)){
				List<String> spGroupIdList = null;
				List<String> storeCodeList = null;
				HashMap<String, Object> saleParam = new HashMap<String, Object>();
				HashMap<String, Object> buyParam = new HashMap<String, Object>();
				
				//如果部门是总裁办、运营部级销售经理，则可以看到自己直属区域的销售数据TEST001
				if("C_1".equals(deptId) || "XSB001".equals(deptId)|| "YY001".equals(deptId)|| "TEST001".equals(deptId)){
					//如果是上部门直接查询全部（不需要任何条件）
				}else{
					
					List<HashMap<String, String>> regionList = userService.getKPIDeptLevelList(deptId);
					//如果没有下属部门，则按照本部门下所有业务员所维护的客户来统计
					if(null != regionList && regionList.size()==1){
						storeCodeList = shopService.getShopNoListByDeptId(deptId);//获取本部门下方所有业务员的客户编码
					}else{
						//获取当前部门及下属子部门绑定的定格
						List<String> deptIdList = deptService.findAllChildDeptIdList(deptId);
						Map<String,Object> paramMap = new HashMap<String,Object>();
						paramMap.put("deptIdList", deptIdList);
						spGroupIdList = spGroupLineService.getDeptBindSpGroupList(paramMap);
					}
				}
				
				//必须保证定格编码列表或 客户编码列表不为空才可以查询接口
				if((null != storeCodeList && !storeCodeList.isEmpty()) || (null != spGroupIdList && !spGroupIdList.isEmpty())){
					saleParam.put("spGroupIdList", spGroupIdList);
					saleParam.put("storeCodeList", storeCodeList);
					saleParam.put("dayTime", dayTime);
					//saleParam.put("isUnion", "1");
					List<HashMap<String,Object>> saleAllList = storeTurnoverService.getData(saleParam);
					HashMap<String, Object> retSaleMap = saleAllList.get(0);
					saleMoneyAll = retSaleMap.get("turnover")+"";
					saleMoneyAll = ObjectUtils.formatMoney(saleMoneyAll);//去除小数点
					
					//查询当天拜访过的店铺销售金额
					saleParam.put("isVisit", 1);
					logger.info("===================公司领导今日战绩getKPIDetailInfo方法  查询【当天全部销售额】全部的入参为：{}",JSONObject.toJSONString(saleParam));
					List<HashMap<String,Object>> visitSaleList = storeTurnoverService.getData(saleParam);
					HashMap<String, Object> visitMap = visitSaleList.get(0);
					visitSaleMoney = visitMap.get("turnover")+"";
					visitSaleMoney = ObjectUtils.formatMoney(visitSaleMoney);//去除小数点
					
					buyParam.put("dayTime", dayTime);
					buyParam.put("isUnion", "2");
					buyParam.put("spGroupIdList", spGroupIdList);
					buyParam.put("storeCodeList", storeCodeList);
	
					//查询当天全部采购金额
					List<HashMap<String,Object>> buyAllList = storeTurnoverService.getData(buyParam);
					HashMap<String, Object> retBuyAll = buyAllList.get(0);
					buyMoneyAll = retBuyAll.get("turnover")+"";
					buyMoneyAll = ObjectUtils.formatMoney(buyMoneyAll);//去除小数点
					
					//查询当天拜访过的联合采购金额
					buyParam.put("isVisit", 1);
					logger.info("===================公司领导今日战绩getKPIDetailInfo方法 查询【当天全部采购销售额】全部的入参为：{}",JSONObject.toJSONString(buyParam));
					List<HashMap<String,Object>> buyList = storeTurnoverService.getData(buyParam);
					HashMap<String, Object> retBuyMap = buyList.get(0);
					visitBuyMoney = retBuyMap.get("turnover")+"";
					visitBuyMoney = ObjectUtils.formatMoney(visitBuyMoney);//去除小数点
				}
			}else{
				
				if(StringUtils.isBlank(salesmanId)){
					json.setMsg("业务员ID不能为空！");
					json.setSuccess(false);
					json.setCode("500");
					return json;
				}
				
				//根据用户ID 获取该业务员名下指定店铺的所有客户编码
				List<String> storeCodeList = shopService.getShopNoList(salesmanId);
				if(null != storeCodeList && !storeCodeList.isEmpty()){
				
					HashMap<String, Object> saleParam = new HashMap<String, Object>();
					//查询当天全部销售额
					saleParam.put("storeCodeList", storeCodeList);
					saleParam.put("dayTime", dayTime);
					List<HashMap<String,Object>> saleAllList = storeTurnoverService.getData(saleParam);
					HashMap<String, Object> retSaleMap = saleAllList.get(0);
					saleMoneyAll = retSaleMap.get("turnover")+"";
					saleMoneyAll = ObjectUtils.formatMoney(saleMoneyAll);//去除小数点
					
					//查询当天拜访过的店铺销售金额
					saleParam.put("isVisit", 1);
					logger.info("===================部门领导今日战绩getKPIDetailInfo方法 查询【当天全部销售额】全部的入参为：{}",JSONObject.toJSONString(saleParam));
					List<HashMap<String,Object>> visitSaleList = storeTurnoverService.getData(saleParam);
					HashMap<String, Object> visitMap = visitSaleList.get(0);
					visitSaleMoney = visitMap.get("turnover")+"";
					visitSaleMoney = ObjectUtils.formatMoney(visitSaleMoney);//去除小数点
					
					
					HashMap<String, Object> buyParam = new HashMap<String, Object>();
					buyParam.put("storeCodeList", storeCodeList);
					buyParam.put("dayTime", dayTime);
					buyParam.put("isUnion", "2");
					

					//查询当天全部采购金额
					List<HashMap<String,Object>> buyAllList = storeTurnoverService.getData(buyParam);
					HashMap<String, Object> retBuyAll = buyAllList.get(0);
					buyMoneyAll = retBuyAll.get("turnover")+"";
					buyMoneyAll = ObjectUtils.formatMoney(buyMoneyAll);//去除小数点
					
					//查询当天拜访过的联合采购金额
					buyParam.put("isVisit", 1);
					logger.info("===================部门领导今日战绩getKPIDetailInfo方法 查询【当天全部采购销售额】全部的入参为：{}",JSONObject.toJSONString(buyParam));
					List<HashMap<String,Object>> buyList = storeTurnoverService.getData(buyParam);
					HashMap<String, Object> retBuyMap = buyList.get(0);
					visitBuyMoney = retBuyMap.get("turnover")+"";
					visitBuyMoney = ObjectUtils.formatMoney(visitBuyMoney);//去除小数点
					
					//获取当前用户定格的所有业务代表
					//dbUser = spGroupLineService.getSpGroupDbUserSet(userId);
				}
			}
			
			resultMap.put("visitSaleMoney", visitSaleMoney==null?"0":visitSaleMoney);
			resultMap.put("buyMoney", visitBuyMoney==null?"0":visitBuyMoney);
			resultMap.put("saleMoneyAll", saleMoneyAll==null?"0":saleMoneyAll);
			resultMap.put("buyMoneyAll", buyMoneyAll==null?"0":buyMoneyAll);
			resultMap.put("dbUser", dbUser == null?"":dbUser);
			resultMap.put("createTime", createTime);
			json.setData(resultMap);
			json.setSuccess(true);
			json.setCode("200");
			json.setMsg("获取关注区域战绩销售数据成功！");
			logger.info("===================方法 名称为：getKPIDetailInfo的方法，查询请求参数分别为：userId={},deptId={},areaId={},createTime={},salesmanId={},userType={},返回结果为：{}",
					userId,deptId,areaId,createTime,salesmanId,userType,JSON.toJSONString(json));
		} catch (Exception e) {
			logger.error("===================方法 名称为：getKPIDetailInfo的方法,获取关注区域战绩销售数据异常：{}",e);
			json.setSuccess(false);
			json.setCode("500");
			json.setMsg("获取关注区域战绩销售数据异常！");
		}
		return json;
	}
	
	/**
	 * 获取定格订单列表信息
	 * @param userId 用户ID
	 * @param deptId 部门ID
	 * @param areaId 区域ID
	 * @param type(1:表示普通用户；2：表示管理者；)
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping(value = {"getSpgroupOrderList"})
	public Object getSpgroupOrderList(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "salesmanId", required = false, defaultValue = "") String salesmanId,
			@RequestParam(value = "createTime", required = false, defaultValue = "") String createTime,
			@RequestParam(value = "deptId", required = false, defaultValue = "") String deptId,
			@RequestParam(value = "areaId", required = false, defaultValue = "") Integer areaId,
			@RequestParam(value = "shopNo", required = false, defaultValue = "") String storeCode,
			@RequestParam(value = "isUnion", required = false, defaultValue = "") String isUnion,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer offset) {
		
		Json json = new Json();
		
		try {
			
			logger.info("===================方法 名称为：getSpgroupOrderList的方法，查询请求参数分别为：userId={},salesmanId={},createTime={},deptId={},areaId={},storeCode={},isUnion={}",
					userId,salesmanId,createTime,deptId,areaId,storeCode,isUnion);
			
			/*if(StringUtils.isBlank(salesmanId)){
				json.setMsg("业务员ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}*/
			if(StringUtils.isBlank(isUnion)){
				json.setMsg("店宝 或 采购的类型不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			String dayTime = null;
			//如果时间为空，则默认查询当天时间
			if(StringUtils.isBlank(createTime)){
				dayTime = DateUtils.dateToCompactString(new Date());//yyyyMMdd
			}else{
				dayTime = createTime.replaceAll("-", "");
			}
			
			//String spGroupId = null;
			List<String> storeCodeList = null;
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			
			//获取业务员所属定格ID
			if(!"3".equals(isUnion)){
				storeCode = null;//isUnion ！=3 表示该业务场景只需要按照定格取数即可；
				//spGroupId = spGroupLineService.getSpGroupByUserId(salesmanId); //此处逻辑属于v1.5.0版本逻辑（现已经废除）
				if(StringUtils.isNotBlank(salesmanId)){
					storeCodeList = shopService.getShopNoList(salesmanId);//根据用户ID 获取该业务员名下指定店铺的所有客户编码
				}
			}else if("3".equals(isUnion)){
				
				if(StringUtils.isBlank(storeCode)){
					json.setMsg("店铺编码不能为空！");
					json.setSuccess(false);
					json.setCode("500");
					return json;
				}
				//查询【isUnion=3 】三表示查询对应店铺订单列表（此处借用此字段而已）
				isUnion = null;
			}
			
			if(StringUtils.isNotBlank(isUnion) && "1".equals(isUnion)){
				//如果isUnion = 1 时，表示需要查询所有数据（因为数据分析平台接口调整isUnion为空表示查询所有，所有清空）
				isUnion = "";
			}
			
			//店铺编码不为空的时候才做条件查询
			if(null != storeCodeList && !storeCodeList.isEmpty()){
				paramMap.put("storeCodeList", storeCodeList);
			}
			paramMap.put("dayTime", dayTime);
			paramMap.put("isUnion", isUnion);
			paramMap.put("storeCode", storeCode);
			paramMap.put("pageNo", pageNo);
			paramMap.put("offset", offset);
			
			logger.info("===================今日战绩getSpgroupOrderList方法入参为：{}",JSONObject.toJSONString(paramMap));
			List list = null;
			if((null != storeCodeList&& !storeCodeList.isEmpty()) || StringUtils.isNotBlank(storeCode)){
				//logger.info("====================storeCodeList总店铺编码个数为:{},storeCode:{}",storeCodeList.size(),storeCode);
				list = storeTurnoverService.getTurnoverBySpGoupAndStore(paramMap);
			}
			
			//将分页参数带回APP端
			paramMap.remove("offset");
			paramMap.put("pageSize", offset);
			paramMap.put("list", list!=null?list:Lists.newArrayList());
			paramMap.remove("storeCode");
			paramMap.remove("storeCodeList");
			json.setData(paramMap);
			json.setSuccess(true);
			json.setCode("200");
			json.setMsg("获取定格订单列表信息成功！");
			logger.info("===================方法 名称为：getSpgroupOrderList的方法，查询请求参数分别为：userId={},salesmanId={},createTime={},deptId={},areaId={},storeCode={},isUnion={},返回结果为：{}",
					userId,salesmanId,createTime,deptId,areaId,storeCode,isUnion,JSON.toJSONString(json));
		} catch (Exception e) {
			logger.error("===================方法 名称为：getSpgroupOrderList的方法，获取定格订单列表信息异常：{}",e);
			json.setSuccess(false);
			json.setCode("500");
			json.setMsg("获取定格订单列表信息异常！");
		}
		return json;
	}
}
