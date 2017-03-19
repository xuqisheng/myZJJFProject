/**   
* @Title: SpMobileStoreController.java 
* @Package com.corner.kefu.controller.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年6月22日 上午11:25:07 
* @version V1.0   
*/

package com.corner.kefu.controller.sp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ShareDisseminateLog;
import com.corner.core.beans.Store;
import com.corner.core.beans.SystemInfo;
import com.corner.core.beans.msg.AppModelMsg;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.DateStringUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.msg.PhoneMsgService;
import com.corner.kefu.beans.ro.ConfigShareRo;
import com.corner.kefu.beans.ro.StoreMgRo;
import com.corner.kefu.beans.vo.ConfigShareVo;
import com.corner.kefu.beans.vo.mobile.StoreMoblieVo;
import com.corner.kefu.config.CommonPageConfig;
import com.corner.kefu.config.SpVoucherKey;
import com.corner.kefu.service.ConfigDetailService;
import com.corner.kefu.service.ShareDisseminateLogService;
import com.corner.kefu.service.SystemInfoService;
import com.corner.kefu.service.sp.SpStoreService;
import com.corner.kefu.service.sp.SpVoucherService;
import com.corner.kefu.utils.CreateNumberUtil;

/** 
* @ClassName: SpMobileStoreController 
* @Description:阿街足记使用店铺相关接口
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年6月22日 上午11:25:07 
*  
*/
@Controller
@RequestMapping("/mobile/store")
public class SpMobileStoreController {
	private static final Logger logger = LoggerFactory.getLogger(SpMobileStoreController.class);
	
	@Autowired
	SpStoreService spStoreService;

	@Autowired
	SystemInfoService systemInfoService;
	
	@Autowired
	ConfigDetailService configDetailService;
	
	@Autowired
	SpVoucherService spVoucherService;
	
	@Autowired
	PhoneMsgService phoneMsgService;
	
	@Autowired
	ShareDisseminateLogService shareDisseminateLogService; 

	/**
	 * 
	* @Title: getStoreList 
	* @Description:获取待审核店铺列表或者已拒绝的店铺列表
	* @param 
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getStoreList.do")
	@ResponseBody
	public Object getStoreList(HttpServletRequest request) {
		AppModelMsg<StoreMoblieVo> appModelMsg = new AppModelMsg<>();
		/*String securityKey = request.getParameter("securityKey");
		if (StringUtils.isEmpty(securityKey)) {
			return ResponseUtils.sendMobileMsg(false, "缺少参数securityKey!", null);
		}
		// 验证securityKey是否正确;
		SystemInfo systemInfo = systemInfoService.getSystemInfo("mobileSecurityKey");
		if (!securityKey.equals(systemInfo.getContent())) {
			return ResponseUtils.sendMobileMsg(false, "验证不通过！", null);
		}*/
		String status = request.getParameter("status");
		if (StringUtils.isEmpty(status)) {
			return ResponseUtils.sendMobileMsg(false, "缺少参数status!", null);
		}
		if (!status.equals("2") && !status.equals("3")) {
			return ResponseUtils.sendMobileMsg(false, "参数status值不正确!", null);
		}
		/*String areaId = request.getParameter("areaId");
		if (StringUtils.isEmpty(areaId)) {
			return ResponseUtils.sendMobileMsg(false, "缺少参数areaId!", null);
		}*/
		String saleManMobile = request.getParameter("saleManMobile");
		if(StringUtils.isEmpty(saleManMobile)){
			return ResponseUtils.sendMobileMsg(false, "缺少参数saleManMobile!", null);
		}
		StoreMgRo storeMgRo = new StoreMgRo();
		storeMgRo.setStatus(Byte.parseByte(status));
		storeMgRo.setSaleManMobile(saleManMobile);
		//storeMgRo.setAreaId(Integer.parseInt(areaId));
		String pageIndexStr = request.getParameter("pageIndex");
		if (StringUtils.isNotEmpty(pageIndexStr)) {
			storeMgRo.setPageIndex(Integer.parseInt(pageIndexStr));
			appModelMsg.setPageNo(Integer.parseInt(pageIndexStr));
		} else {
			appModelMsg.setPageNo(1);
		}
		Pager<StoreMoblieVo> pager = spStoreService.getStoreList(storeMgRo);
		appModelMsg.setCount(pager.getTotalSize());
		appModelMsg.setList(pager.getList());
		appModelMsg.setPageSize(CommonPageConfig.commonPageSize);
		return ResponseUtils.sendMobileMsg(true, null, appModelMsg);
	}

	/**
	 * 
	* @Title: getStoreDetailInfo 
	* @Description:获取待审核店铺的详细信息以及定格信息
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getStoreDetailInfo.do")
	@ResponseBody
	public Object getStoreDetailInfo(HttpServletRequest request) {
		String securityKey = request.getParameter("securityKey");
		if (StringUtils.isEmpty(securityKey)) {
			return ResponseUtils.sendMobileMsg(false, "缺少参数securityKey!", null);
		}
		// 验证securityKey是否正确;
		SystemInfo systemInfo = systemInfoService.getSystemInfo("mobileSecurityKey");
		if (!securityKey.equals(systemInfo.getContent())) {
			return ResponseUtils.sendMobileMsg(false, "验证不通过！", null);
		}
		String storeId = request.getParameter("storeId");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String areaId = request.getParameter("areaId");
		String numRex = "^\\d+$";
		Pattern pattern = Pattern.compile(numRex);
		Matcher matcher = null;
		if (StringUtils.isEmpty(storeId)) {
			return ResponseUtils.sendMobileMsg(false, "缺少参数storeId", null);
		} else {
			matcher = pattern.matcher(storeId);
			if (!matcher.matches()) {
				return ResponseUtils.sendMobileMsg(false, "参数storeId格式不正确!", null);
			}
		}
		if (StringUtils.isEmpty(provinceId)) {
			return ResponseUtils.sendMobileMsg(false, "缺少参数provinceId", null);
		} else {
			matcher = pattern.matcher(provinceId);
			if (!matcher.matches()) {
				return ResponseUtils.sendMobileMsg(false, "参数provinceId格式不正确!", null);
			}
		}
		if (StringUtils.isEmpty(cityId)) {
			return ResponseUtils.sendMobileMsg(false, "缺少参数cityId", null);
		} else {
			matcher = pattern.matcher(cityId);
			if (!matcher.matches()) {
				return ResponseUtils.sendMobileMsg(false, "参数cityId格式不正确!", null);
			}
		}
		if (StringUtils.isEmpty(areaId)) {
			return ResponseUtils.sendMobileMsg(false, "缺少参数areaId", null);
		} else {
			matcher = pattern.matcher(areaId);
			if (!matcher.matches()) {
				return ResponseUtils.sendMobileMsg(false, "参数areaId格式不正确!", null);
			}
		}
		StoreMgRo storeMgRo = new StoreMgRo();
		storeMgRo.setId(Integer.parseInt(storeId));
		storeMgRo.setProvinceId(Integer.parseInt(provinceId));
		storeMgRo.setCityId(Integer.parseInt(cityId));
		storeMgRo.setAreaId(Integer.parseInt(areaId));
		ModelMsg msg = spStoreService.getStoreDetail(storeMgRo);
		if (!msg.isSuccess()) {
			return ResponseUtils.sendMobileMsg(false, msg.getMessage(), null);
		} else {
			return ResponseUtils.sendMobileMsg(true, null, msg.getData());
		}
	}

	/**
	 * 
	* @Title: approvalStore 
	* @Description:
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/approvalStore.do")
	@ResponseBody
	public Object approvalStore(HttpServletRequest request) {
		StoreMgRo storeMgRo = new StoreMgRo();
		String securityKey = request.getParameter("securityKey");
		if (StringUtils.isEmpty(securityKey)) {
			return ResponseUtils.sendMobileMsg(false, "缺少参数securityKey!", null);
		}
		// 验证securityKey是否正确;
		SystemInfo systemInfo = systemInfoService.getSystemInfo("mobileSecurityKey");
		if (!securityKey.equals(systemInfo.getContent())) {
			return ResponseUtils.sendMobileMsg(false, "验证不通过！", null);
		}
		String storeId = request.getParameter("storeId");
		if (StringUtils.isEmpty(storeId)) {
			return ResponseUtils.sendMobileMsg(false, "缺少参数storeId!", null);
		}
		String status = request.getParameter("status");
		if (StringUtils.isEmpty(status)) {
			return ResponseUtils.sendMobileMsg(false, "缺少参数status!", null);
		}
		if (!status.equals("1") && !status.equals("3")) {
			return ResponseUtils.sendMobileMsg(false, "参数status值错误!", null);
		}
		String spGroupId = request.getParameter("spGroupId");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		if(StringUtils.isEmpty(userId)){
			return ResponseUtils.sendMobileMsg(false, "缺少userId参数!", null);
		}
		if(StringUtils.isEmpty(userName)){
			return ResponseUtils.sendMobileMsg(false, "缺少userName参数!", null);
		}
        storeMgRo.setId(Integer.parseInt(storeId));
        storeMgRo.setStatus(Byte.parseByte(status));
        storeMgRo.setUserId(userId);
        storeMgRo.setUserName(userName);
		if (status.equals("1")) {// 审核通过
			if(StringUtils.isEmpty(spGroupId)){
				return ResponseUtils.sendMobileMsg(false, "缺少参数spGroupId", null);
			}
			storeMgRo.setSpGroupId(Integer.parseInt(spGroupId));
			// 通过店铺id查询店铺,判断是否有重新分配定格或区域
			Store newStore = spStoreService.getStoreById(storeMgRo.getId());
			// 初始化送货时间与起送价，评价数
			Date start = DateStringUtil.stringToDate3("08:00:00");
			Date end = DateStringUtil.stringToDate3("22:00:00");
			storeMgRo.setSendTimeBegin(start);
			storeMgRo.setSendTimeEnd(end);
			storeMgRo.setSendess(new BigDecimal(20));
			BigDecimal avgComment = new BigDecimal(5);
			storeMgRo.setAvgComment(avgComment);
			if (StringUtils.isEmpty(newStore.getSupplierCode())) {// 表示是第一次审核
				Map<String, String> map = CreateNumberUtil.createNumber(newStore);
				String supplierCode = "";
				if (map != null) {
					supplierCode = map.get("regionStr") + map.get("sequenceNum").toString();
				}
				storeMgRo.setSupplierCode(supplierCode);
				if (map.get("sequenceNum") != null && !"".equals(map.get("sequenceNum"))) {
					storeMgRo.setSequenceNum(Integer.parseInt(map.get("sequenceNum")));
				} else {
					storeMgRo.setSequenceNum(null);
				}
				try {
					spStoreService.updateApprovalStore(storeMgRo);
					//发送优惠劵
					//查出邀请记录
					ShareDisseminateLog log = new ShareDisseminateLog();
					logger.info("审核通过后给邀请人发送优惠券");
					if(newStore.getFromWho()==2 && (null != newStore.getInviteId() && !"".equals(newStore.getInviteId()))){
						//根据邀请人id获取邀请人的店铺
						Map<String, Object> paramMap = new HashMap<>();
						paramMap.put("id", newStore.getInviteId());
						Store store1 = spStoreService.getStoreByInviteId(paramMap);
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
					spVoucherService.sendSpVoucherByActive(SpVoucherKey.registerSendType, null,null, null, null, storeMgRo.getSpGroupId(),newStore.getAcGroupId() ,newStore.getId(), newStore.getName(), newStore.getMobile());
					log.setAcceptUserId(storeMgRo.getId());
					log.setConfirmTime(new Date());
					//修改邀请记录
					shareDisseminateLogService.updateLogByStoreId(log);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {//已经审核通过一次了
				try {
					spStoreService.updateApprovalStore(storeMgRo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {// 拒绝
			try {
				spStoreService.updateApprovalStore(storeMgRo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		/*
		 * if(status.equals("1")){//审核通过 if(StringUtils.isEmpty(spGroupId)){
		 * return ResponseUtils.sendMobileMsg(false, "缺少参数spGroupId!", null); }
		 * storeMgRo.setSpGroupId(Integer.parseInt(spGroupId));//定格id } String
		 * userId = request.getParameter("userId");
		 * if(StringUtils.isEmpty(userId)){ return
		 * ResponseUtils.sendMobileMsg(false, "缺少参数userId!", null); } String
		 * userName = request.getParameter("userName");
		 * if(StringUtils.isEmpty(userName)){ return
		 * ResponseUtils.sendMobileMsg(false, "缺少参数userName!", null); }
		 * 
		 * storeMgRo.setId(Integer.parseInt(storeId));//店铺id
		 * storeMgRo.setStatus(Byte.parseByte(status));//状态
		 * storeMgRo.setUserId(userId);//审批人id
		 * storeMgRo.setUserName(userName);//审批人name try { ModelMsg modelMsg =
		 * spStoreService.updateApprovalStore(storeMgRo); } catch (Exception e)
		 * { return ResponseUtils.sendMobileMsg(false, "提交数据失败，服务器异常!", null); }
		 */
		return ResponseUtils.sendMobileMsg(true, "提交数据成功!", null);
	}
}
