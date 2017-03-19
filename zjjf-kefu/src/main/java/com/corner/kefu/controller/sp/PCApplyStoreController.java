package com.corner.kefu.controller.sp;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.config.UploadKeys;
import com.corner.core.utils.DateStringUtil;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.msg.PhoneMsgService;
import com.corner.kefu.beans.ro.ConfigShareRo;
import com.corner.kefu.beans.ro.sp.StoreRo;
import com.corner.kefu.beans.vo.ConfigShareVo;
import com.corner.kefu.beans.vo.sp.ApplyStoreVo;
import com.corner.kefu.beans.vo.sp.StoreVo;
import com.corner.kefu.config.SCMSConstants;
import com.corner.kefu.config.SpVoucherKey;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.ConfigDetailService;
import com.corner.kefu.service.PublicService;
import com.corner.kefu.service.ShareDisseminateLogService;
import com.corner.kefu.service.sp.*;
import com.corner.kefu.utils.CreateNumberUtil;
import com.corner.kefu.utils.FileUpload;
import com.corner.kefu.utils.PathUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import com.corner.rpc.salesman.api.service.ShopService;
import com.corner.rpc.shop.api.service.StoreService;*/

/**
 * 商铺管理控制器
 *
 * @author aimee at 2015年6月5日上午9:54:35
 * @email 1297579898@qq.com
 */
@Controller
@RequestMapping(value = "/customer/appStore")
public class PCApplyStoreController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(PCApplyStoreController.class);
	@Autowired
	SpStoreService storeService;
	@Autowired
	SpRegionService spRegionService;
	@Autowired
	PublicService publicService;
	/*@Autowired
	SpSalesmanService salesmanService;*/
	@Autowired
	SpGroupService spGroupService;
	/*@Autowired
	SpUserService userService;*/
	@Autowired
	SpShoppingCartService spShoppingCartService;
	@Autowired
	SpStoreInfoService storeInfoService;
	@Autowired
	PhoneMsgService phoneMsgService;
	@Autowired
	SpCustomerServiceService customerServiceService;
	@Autowired
	ConfigDetailService configDetailService;
	@Autowired
	SpVoucherService spVoucherService;
	@Autowired
	ShareDisseminateLogService shareDisseminateLogService;
	/*@Autowired
	private ShopService shopService;
	@Autowired
	private StoreService ajStoreService;*/


	/**
	 * 删除待审核记录表里面的商铺
	 *
	 * @author aimee at 2015年6月2日下午4:58:20
	 * @email 1297579898@qq.com
	 * @param model
	 * @param request
	 * @param store
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteAppStoreById.do")
	public String deleteAppStoreById(Model model, HttpServletRequest request, Store store, HttpSession session) throws Exception{
		try {
			// 根据商铺id逻辑删除
			storeService.deleteApplyStoreById(store.getId());
			// 从session中获取客服信息
			CustomerService service = getCurrentUser(CustomerService.class, request);
			// 插入审核记录表
			//userService.saveToRecord(String.valueOf(service.getId()), service.getMobile(), store.getSupplierCode(), store.getId(), "0");
			return "redirect:appStores.do";
		} catch (Exception e) {
			logger.error("PCApplyStoreController's method deleteAppStoreById has an error:{}", e);
		}
		return error("出错了", model, request);
	}

	/**
	 * 拒绝审批
	 *
	 * @author aimee at 2015年6月2日下午5:11:00
	 * @email 1297579898@qq.com
	 * @param model
	 * @param store
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/rejectApply.do")
	public String rejectApply(Model model, Store store, HttpServletRequest request) {
		try {
			// 权限
			String content = "感谢您关注转角店宝。抱歉您的申请没有通过资料审核，请到应用重新提交。详情可联系当区业务员。";
			// 发信息
			// TODO 短信发送
			//phoneMsgService.sendMessage(store.getMobile(), content);

			// 获取权限，获取session中客服信息
			CustomerService service = getCurrentUser(CustomerService.class, request);
			store.setStatus((byte) 3);// 拒绝
//			store.setSupplierCode("");// 用于那些已经通过审核,但又要拒绝的店铺,编码就置为空
			store.setSpGroupId(null);
			// 更新店铺
			storeService.updetaStoreStatus(store);
			if (service != null) {
				// 插入审核记录
				//userService.saveToRecord(String.valueOf(service.getId()), service.getUserName(), store.getSupplierCode(), store.getId(), "2");
			}
			return "redirect:appStores.do";
		} catch (Exception e) {
			logger.error("PCApplyStoreController's method rejectApply has an error:{}", e);
		}
		return error("出错了", model, request);
	}
	/**
	 * ajax 审批成功更新信息
	 *
	 * @param
	 * @param store
	 * @return
	 */
	@RequestMapping(value = "/updateAppStore.do")
	@ResponseBody
	public Object updateAppStore(Store store,String licenseNum,HttpServletRequest request, Model model) {
		try {
			if (StringUtil.stringIsNullOrEmpty(String.valueOf(store.getAreaId()), store.getAddress())) {
				return ResponseUtils.sendMsg(false, "请完善资料");
			}
			// 获取权限，获取session中客服信息
			CustomerService service = getCurrentUser(CustomerService.class, request);
			if (service != null) {
				store.setStatus((byte) 1);// 通过
				// 地址不为空，解码
				if (!StringUtil.stringIsNullOrEmpty(store.getAddress())) {
					store.setAddress(decode(store.getAddress()));
				}
				// 店名不为空，解码
				if (!StringUtil.stringIsNullOrEmpty(store.getName())) {
					store.setName(decode(store.getName()));
				}
				if(!StringUtil.stringIsNullOrEmpty(store.getContact())){
					store.setContact(decode(store.getContact()));
				}
				// 通过店铺id查询店铺,判断是否有重新分配定格或区域
				Store newStore = storeService.getStoreById(store.getId());
				// 初始化送货时间与起送价，评价数
				Date start = DateStringUtil.stringToDate3("08:00:00");
				Date end = DateStringUtil.stringToDate3("22:00:00");
				store.setSendTimeBegin(start);
				store.setSendTimeEnd(end);
				store.setSendess(new BigDecimal(20));
				BigDecimal avgComment = new BigDecimal(5);
				store.setAvgComment(avgComment);
				if(StringUtil.stringIsNullOrEmpty(store.getSupplierCode()) || store.getAreaId().intValue() != newStore.getAreaId().intValue()){

					Map<String, String> map = CreateNumberUtil.createNumber(store);
					String supplierCode = "";
					if(map != null){
						supplierCode = map.get("regionStr")+map.get("sequenceNum").toString();
					}
					// 查询该店铺编号是否存在,如果是已经审批过的店铺,不进行判断
					List<StoreVo> storeVos =storeService.getStoresIdByCode(supplierCode);
					if (storeVos!= null&&storeVos.size()!=0) {
						return ResponseUtils.sendMsg(false, "该店铺编号已存在，请重新输入");
					}
					store.setSupplierCode(supplierCode);
					if(map.get("sequenceNum") != null && !"".equals(map.get("sequenceNum"))){
						store.setSequenceNum(Integer.parseInt(map.get("sequenceNum")));
					}else{
						store.setSequenceNum(null);
					}

				}
				ModelMsg result = null;
				if(store.getSpGroupId() != null && newStore.getSpGroupId() != null){
					if (store.getSpGroupId().intValue()!=newStore.getSpGroupId().intValue()) {
						// 更新店铺信息(切换定格)
						result = storeService.updetaStoreStatusAll(store);
					}else{
						// 更新店铺信息(不切换定格)
						result = storeService.updetaStoreStatus(store);
					}
				}else{
					// 更新店铺信息(不切换定格)
					result = storeService.updetaStoreStatus(store);
				}

				if(!StringUtils.isEmpty(licenseNum)){
					StoreInfo storeInfo = storeInfoService.getStoreById(store.getId());
					if(storeInfo == null){
						StoreInfo storeInfoNew = new StoreInfo();
						storeInfoNew.setId(store.getId());
						storeInfoNew.setLicenseNum(licenseNum);
						storeInfoService.save(storeInfoNew);
					}else{
						storeInfo.setLicenseNum(licenseNum);
						storeInfoService.updateStore(storeInfo);
					}
				}
				storeService.updateRegionHasStore(store.getProvinceId(), store.getCityId(), store.getAreaId());
				if (result.isSuccess()) {
					//审核成功后 查看是否是第一次审核
					if(newStore.getSupplierCode() == null || "".equals(newStore.getSupplierCode())){
						//查出邀请记录
						ShareDisseminateLog log = new ShareDisseminateLog();
						logger.info("审核通过后给邀请人发送优惠券");
						if(newStore.getFromWho()==2 && (null != newStore.getInviteId() && !"".equals(newStore.getInviteId()))){
							//根据邀请人id获取邀请人的店铺
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", newStore.getInviteId());
							Store store1 = storeService.getStoreByInviteId(map);
							//获取邀请配置信息
							ConfigShareRo ro = new ConfigShareRo();
							ro.setConfigId(2);
							ConfigShareVo configShare = configDetailService.getConfigShareById(ro);
							if(store1 != null && configShare != null){
								log.setEncourageType(configShare.getAwardType());
								log.setEncourageVoucher(configShare.getRuleId().toString());
								//向推邀请人发送优惠券
								boolean flag = spVoucherService.saveSpVoucher(store1,configShare);
								if(flag){
									String content1 = "恭喜您，您推荐商店已审核通过，优惠券已发送到"+store1.getMobile()+"的账户";
									phoneMsgService.sendMessage(store1.getMobile(), content1);
								}
							}
						}
						//给新用户发优惠券
						spVoucherService.sendSpVoucherByActive(SpVoucherKey.registerSendType, null,null, null, null, store.getSpGroupId(),newStore.getAcGroupId() ,newStore.getId(), newStore.getName(), newStore.getMobile());
						log.setAcceptUserId(store.getId());
						log.setConfirmTime(new Date());
						//修改邀请记录
						shareDisseminateLogService.updateLogByStoreId(log);
					}
					// 插入审核记录
					// userService.saveToRecord(String.valueOf(service.getId()), service.getUserNae(), store.getSupplierCode(), store.getId(), "1");
					//TODO 通知部分
					String content = "欢迎加入转角街坊，您的账号已经审核通过。登录账号是注册手机号。您可以登录街坊店宝修改密码后订购进货。";
					phoneMsgService.sendMessage(store.getMobile(), content);
					//同步阿街接口
					try{
						//new Thread(new SyncShop(store.getId(), shopService, ajStoreService)).start();
					}catch(Exception e){
						logger.info(e.toString());
					}
					return ResponseUtils.sendMsg(true, result.getMessage());
				}else{
					return ResponseUtils.sendMsg(false, result.getMessage());
				}
			}
			return ResponseUtils.sendMsg(false, "登录已过期，请重新登陆后操作");
		} catch (Exception e) {
			logger.error("PCApplyStoreController's method updateAppStore has an error:{}", e);
		}
		return ResponseUtils.sendMsg(false, "程序出错!");
	}
	/**
	 * 审核列表
	 *
	 * @author aimee at 2015年6月3日上午9:09:51
	 * @email 1297579898@qq.com
	 * @param model
	 * @param storeRo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/appStores.do")
	public String AppStores(Model model, StoreRo storeRo, HttpServletRequest request) throws Exception{
		Date endtime = storeRo.getEndTime();
		// 关键字不为空，解码
		if (!StringUtil.stringIsNullOrEmpty(storeRo.getKeywords())) {
			storeRo.setKeywords(storeRo.getKeywords().trim());
		}

		if(storeRo.getEndTime()!=null){
			Date endTime = storeRo.getEndTime();
			endTime = DateUtils.addDays(endTime, 1);
			storeRo.setEndTime(endTime);
		}
		//拉取所有的客服人员
		List<CustomerService> customerServiceList = customerServiceService.getAllCustomerService();
		// 查询满足条件的集合
		Pager<ApplyStoreVo> pager = storeService.getApplyStores(storeRo);
		storeRo.setEndTime(endtime);
		model.addAttribute("storeRo", storeRo);
		model.addAttribute("applyStores", pager.getList());
		model.addAttribute("size", pager.getTotalSize());

		model.addAttribute("customerServiceList", customerServiceList);
		pageUtil(storeRo.getPageIndex(), pager.getTotalSize(), storeRo.getPageSize(), request, model);
		// 分页封装
		return SCMSConstants.SHOP;
	}
	/**
	 * 审核页面
	 *
	 * @author aimee at 2015年6月3日上午9:10:04
	 * @email 1297579898@qq.com
	 * @param model
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getStoreById.do")
	public String getStoreById(Model model, Integer id, HttpServletRequest request) throws Exception{
		// 根据商铺id查询
		ApplyStoreVo applyStore = storeService.getApplyStoreById(id);
		if (applyStore == null) {
			return error("店铺不存在", model, request);
		}
		model.addAttribute("applyStore", applyStore);
		//默认拉取所有省
		request.setAttribute("regions", publicService.findRegionByPId("1"));	//获取省信息
		if(applyStore.getProvinceId() != null){
			request.setAttribute("citys", publicService.findRegionByPId(applyStore.getProvinceId().toString()));	//获取市信息
		}
		if(applyStore.getCityId() != null){
			request.setAttribute("countys", publicService.findRegionByPId(applyStore.getCityId().toString()));	//获取区信息
		}
		if(applyStore.getAreaId() != null){
			request.setAttribute("streets", publicService.findRegionByPId(applyStore.getAreaId().toString()));	//获取街道信息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaId", applyStore.getAreaId());
			request.setAttribute("areas", spGroupService.getSpGroupByAreaId(map));//获取定格信息
		}
		return SCMSConstants.SHOP_DETAIL;
	}

	//	/***
//	 * 审核记录表
//	 *
//	 * @author aimee at 2015年6月3日上午9:10:17
//	 * @email 1297579898@qq.com
//	 * @param request
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/findRecordList.do")
//	public String findRecordList(HttpServletRequest request, Model model, SpAdminVerifyRecordRo spadmin) throws Exception{
//		// 根据关键字查询，解码
//		if (!StringUtil.stringIsNullOrEmpty(spadmin.getKeywords())) {
//			model.addAttribute("keywords", spadmin.getKeywords());
//		}
//		Pager<SpAdminVerifyRecordVo> pager = storeService.findRecordList(spadmin);
//		// 共同分页封装
//		model.addAttribute("recordList", pager.getList());
//		model.addAttribute("size", pager.getTotalSize());
//		model.addAttribute("spadmin", spadmin);
//		pageUtil(spadmin.getPageIndex(), pager.getTotalSize(), spadmin.getPageSize(), request, model);
//		return SCMSConstants.SHOP;
//	}
//
	@RequestMapping("/getSpGroupList.do")
	@ResponseBody
	public Object getSpGroupList() {
		try {
			List<SpGroup> list = spGroupService.selectSpGroupALL();
			return ResponseUtils.sendMsg(true, list);
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseUtils.sendMsg(true, "查询定格列表出错");
		}
	}

	@RequestMapping("/TZPage.do")
	public String TZPage(){
		return "/map/TZ";
	}


	@RequestMapping("/szMapPage.do")
	public String szMapPage(){
		return "/map/map";
	}
	@RequestMapping("/gzMapPage.do")
	public String gzMapPage(){
		return "/map/GZmap";
	}
	@RequestMapping("/dhMapPage.do")
	public String dhMapPage(String mobile,Model model){
		model.addAttribute("mobile", mobile);
		return "/map/DHmap";
	}

	@RequestMapping("/getAllStoreCoordinates.do")
	@ResponseBody
	public Object getAllStoreCoordinates(Integer regionId,Integer cityId,String mobile,Model model){
		Map<String, Object> map = new HashMap<String,Object>();
		if(regionId != null){
			map.put("regionId", regionId);
		}
		if(cityId != null){
			map.put("cityId", cityId);
		}
		if(mobile != null && !"".equals(mobile)){
			map.put("mobile", mobile);
		}
		try {
			List<Store> storeList = storeService.getStoreByRegion(map);
			if(storeList != null && storeList.size()>0){
				StringBuilder str = new StringBuilder("[");
				for (Store store : storeList) {
					if((store.getLat()!=null && !"".equals(store.getLat())) && (store.getLng()!=null && !"".equals(store.getLng()))){
						str.append("{\"content\":\"转角街坊标记\",\"title\":\""+store.getName()+"\",\"imageOffset\": {\"width\":\"0\",\"height\":\"3\"},\"position\":{\"lat\":\""+store.getLat()+"\",\"lng\":\""+store.getLng()+"\"}},");
					}
				}
				String strJson = str.substring(0, str.length()-1)+"]";
				System.out.println(strJson);
				return ResponseUtils.sendMsg(true, strJson);
			}else{
				return ResponseUtils.sendMsg(false, "");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
//		map = [{status=0, result={location={lng=113.90785869975336, lat=22.579738278320633}, precise=0, confidence=60, level=地产小区}}
//		{status=0, result={location={lng=113.90785869975336, lat=22.579738278320633}, precise=0, confidence=60, level=地产小区}}]
//		return  var markers = "[
//		                       {content:"我的备注",title:"我的标记",imageOffset: {width:0,height:3},position:{lat:39.912233,lng:116.300964}},
//		                       {content:"我的备注",title:"我的标记",imageOffset: {width:0,height:3},position:{lat:22.579156,lng:113.906926}}
//		                     ]";
	}

	@RequestMapping("/readExcel.do")
	public String readExcel(MultipartFile file, Model model,HttpServletRequest request){
		TempTable tempTable = null;
		String url = "http://api.map.baidu.com/geocoder/v2/";
		String ak = "GmeC8Vox1AaY65IzhYb0H6TGg1nTEXQZ";
		HttpClient httpClient = new HttpClient();
		PostMethod method = null;
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		Workbook workbook = null;
		String filePath = PathUtil.getClasspath() + UploadKeys.FILEPATHFILE; // 文件上传路径
		String fileName = FileUpload.fileUp(file, filePath,file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf(".")));

		InputStream in;
		try {
			in = new FileInputStream(fileName);
			if(fileName.toLowerCase().endsWith(".xls")){
				workbook = new HSSFWorkbook(in);
			}else if(fileName.toLowerCase().endsWith(".xlsx")){
				workbook = new XSSFWorkbook(in);
			}
			Sheet sheet = workbook.getSheetAt(0);
			if(sheet!=null){
				storeService.delTempTable();
				for (int i = 1; i < sheet.getLastRowNum(); i++) {
					sheet.getRow(i). getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					sheet.getRow(i). getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					sheet.getRow(i). getCell(4).setCellType(Cell.CELL_TYPE_STRING);
					System.out.println((sheet.getRow(i).getCell(2).getStringCellValue().trim()+sheet.getRow(i).getCell(3).getStringCellValue().trim()+sheet.getRow(i).getCell(4).getStringCellValue().trim()).toString());
					method = new PostMethod(url);
					method.addParameter("address", (sheet.getRow(i).getCell(2).getStringCellValue().trim()+sheet.getRow(i).getCell(3).getStringCellValue().trim()+sheet.getRow(i).getCell(4).getStringCellValue().trim()).toString());
					method.addParameter("city", sheet.getRow(i).getCell(2).getStringCellValue().trim());
					method.addParameter("ak", ak);
					method.addParameter("output", "json");
					method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
					int status = httpClient.executeMethod(method);
					if(status == 200){
						String resultData = method.getResponseBodyAsString();
						if(resultData != null && !"".equals(resultData)){
							Map<String, Object> map = JSONUtil.JSONToObject(resultData, Map.class);
							if(map != null && (int)map.get("status")==0){
								Map<String, Object> result = (Map<String, Object>)map.get("result");
								if(result != null){
									Map<String, Object> location = (Map<String, Object>)result.get("location");
									if(location != null){
										tempTable = new TempTable();
										tempTable.setAddress((sheet.getRow(i).getCell(2).getStringCellValue().trim()+sheet.getRow(i).getCell(3).getStringCellValue().trim()+sheet.getRow(i).getCell(4).getStringCellValue().trim()).toString());
										tempTable.setLng(location.get("lng").toString());
										tempTable.setLat(location.get("lat").toString());
										storeService.addTempTable(tempTable);
									}
									System.out.println("执行成功");
//										if(i==500){
//											ak="N5Wyb85nHkLWs0FHz9yoIkmbzs55NGva";
//										}else if(i==1000){
//											ak="GmeC8Vox1AaY65IzhYb0H6TGg1nTEXQZ";
//										}else if(i==1500){
//											ak="Qxstg6Lo1cE3LRtgBVPPKIYUOjUzmE83";
//										}else if(i==2000){
//											ak="";
//										}else{
//											ak="";
//										}
								}
							}else{
								System.out.println("店铺地址不准确");
							}
						}else{
							System.out.println("远程请求服务失败");
						}
					}else{
						System.out.println("远程请求服务失败");
					}
				}
			}
			return "/map/TZ";

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return error(e.getMessage(), model, request);

		} catch (IOException e) {
			e.printStackTrace();
			return error(e.getMessage(), model, request);
		}finally{
			method.releaseConnection();
		}
	}

	@RequestMapping("returnTempMap.do")
	public String returnTempMap(){
		return "/map/universalMap";
	}

	@ResponseBody
	@RequestMapping("getTempMap.do")
	public Object getTempMap(){
		List<TempTable> tempTableList = storeService.getTempMap();
		if(tempTableList != null && tempTableList.size()>0){
			StringBuilder str = new StringBuilder("[");
			for (TempTable tempTable : tempTableList) {
				if((tempTable.getLat()!=null && !"".equals(tempTable.getLat())) && (tempTable.getLng()!=null && !"".equals(tempTable.getLng()))){
					str.append("{\"content\":\"转角街坊标记\",\"title\":\"\",\"imageOffset\": {\"width\":\"0\",\"height\":\"3\"},\"position\":{\"lat\":\""+tempTable.getLat()+"\",\"lng\":\""+tempTable.getLng()+"\"}},");
				}
			}
			String strJson = str.substring(0, str.length()-1)+"]";
			System.out.println(strJson);
			return ResponseUtils.sendMsg(true, strJson);
		}else{
			return ResponseUtils.sendMsg(false, "地图无店铺数据");
		}
	}


}


/*class SyncShop implements Runnable { // 实现了Runnable接口，jdk就知道这个类是一个线程

	private ShopService shopService;
	private StoreService storeService;

	private Integer storeId;
	public SyncShop(){

	}
	public SyncShop(Integer storeId,ShopService shopService,StoreService storeService){

		this.storeId =  storeId;
		this.shopService =  shopService;
		this.storeService =  storeService;
	}

	public void run() {
		try {
			//1.根据店铺storeId查询客户信息
			HashMap<String, Object> storeMap = storeService.queryStoreById(storeId);

			if(null  != storeMap){
				//2.根据将店宝查询回来的客户信息insert阿街tbl_shop_t表中
				shopService.addAuditShop(storeMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}*/
