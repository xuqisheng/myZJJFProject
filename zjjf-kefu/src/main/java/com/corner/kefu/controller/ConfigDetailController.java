package com.corner.kefu.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.ConfigPayRo;
import com.corner.kefu.beans.ro.ConfigShareRo;
import com.corner.kefu.beans.ro.sp.SpVoucherTempRo;
import com.corner.kefu.beans.vo.ConfigPayVo;
import com.corner.kefu.beans.vo.ConfigShareVo;
import com.corner.kefu.beans.vo.sp.SpVoucherTempVo;
import com.corner.kefu.service.ConfigDetailService;

@Controller
@RequestMapping("/keFu/systemConfigDetail")
public class ConfigDetailController extends KefuBaseWebController {

	private static final Logger logger = LoggerFactory.getLogger(ConfigDetailController.class);
	
	@Autowired
	ConfigDetailService ConfigDetailService;
	
	/**
	 * 根据id获取支付方式
	* @Title
	* @Description: TODO 
	* @param @param systemConfigDetailRo
	* @param @param request
	* @param @param model
	* @param @return
	* @2016年4月14日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getSystemConfigDetailById.do")
	public String getSystemConfigDetailById(ConfigPayRo configPayRo,HttpServletRequest request,Model model){
		String str = request.getParameter("str");
		try {
			model.addAttribute("str", str);
			model.addAttribute("systemConfigDetailRo", configPayRo);
			ConfigPayVo configPayVo = ConfigDetailService.getSystemConfigDetailById(configPayRo);
			if(configPayVo != null ){
				model.addAttribute("systemConfigDetailVo", configPayVo);
			}
			return "/system/parm-config-edit";
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
	}
	
	@RequestMapping("updateSystemConfigDetailByPayType.do")
	@ResponseBody
	public Object updateSystemConfigDetailByPayType(ConfigPayRo configPayRo,HttpServletRequest request,Model model){
		String[] spGroupIds = null;
		String spGroupId = "";
		if(configPayRo.getSelectType()==1){
			spGroupIds = request.getParameterValues("spGroupId[]");
//			if(spGroupIds == null || spGroupIds.length==0){
//				return ResponseUtils.sendMsg(false, "请增加定格区域！");
//			}
			if(spGroupIds != null && spGroupIds.length>0){
				for (int i = 0; i < spGroupIds.length; i++) {
					spGroupId += spGroupIds[i]+",";
				}
				spGroupId = spGroupId.substring(0, spGroupId.length()-1);
			}else{
				spGroupId = "0";
			}
			configPayRo.setSpGroupIds(spGroupId);
		}else{
			configPayRo.setSpGroupIds("0");
		}
		if(configPayRo.getPayType()==null){
			configPayRo.setPayType(Byte.parseByte("0"));
		}
		try {
			ConfigDetailService.updateSystemConfigDetailByPayType(configPayRo);
			return ResponseUtils.sendMsg(true, "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "操作失败！");
		}
	}
	
	/**
	 *  获取邀请配置
	* @Title
	* @Description: TODO 
	* @param @return
	* @2016年4月26日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getConfigShareById.do")
	public String getConfigShareById(ConfigShareRo configShareRo,HttpServletRequest request,Model model) {
		String str = request.getParameter("str");
		try {
			ConfigShareVo configShareVo = ConfigDetailService.getConfigShareById(configShareRo);
			if(configShareVo != null){
				model.addAttribute("configShare", configShareVo);
			}
			model.addAttribute("configId", configShareRo.getConfigId());
			model.addAttribute("str", str);
			return "/system/invite";
		} catch (Exception e) {
			e.printStackTrace();
			return error("出错了！", model, request);
		}
	}
	
	/**
	 *  更新邀请配置
	* @Title
	* @Description: TODO 
	* @param @return
	* @2016年4月26日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("updateConfigShare.do")
	@ResponseBody
	public Object updateConfigShare(ConfigShareRo configShareRo,HttpServletRequest request,Model model){
		try {
			configShareRo.setUpdateTime(new Date());
			ConfigDetailService.updateConfigShare(configShareRo);
			return ResponseUtils.sendMsg(true, "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(true, "操作失败！");
		}
		
	}
	
	
	/**
	 * 获取所有优惠券 
	* @Title
	* @Description: TODO 
	* @param @param spVoucherTemp
	* @param @return
	* @2016年4月26日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getAllSpVoucherTemp.do")
	@ResponseBody
	public Object getAllSpVoucherTemp(SpVoucherTempRo spVoucherTempRo,HttpServletRequest request,Model model){
		if(!StringUtil.stringIsNullOrEmpty(spVoucherTempRo.getRuleName())){
			spVoucherTempRo.setRuleName(spVoucherTempRo.getRuleName().trim());
		}
		try {
			Pager<SpVoucherTempVo> pager = ConfigDetailService.getAllSpVoucherTemp(spVoucherTempRo);
			return pager;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping("returnExitStock.do")
	public String returnExitStock(){
		return "/system/systemStock";
	}
	
	
	@RequestMapping("updateAllGoodsStock.do")
	public @ResponseBody Object updateAllGoodsStock(Integer stockNum){
		try {
			if(stockNum==null){
				stockNum=0;
			}
			int num = ConfigDetailService.updateAllGoodsStock(stockNum);
			if(num>0){
				return ResponseUtils.sendMsg(true, "修改成功");
			}else{
				return ResponseUtils.sendMsg(true, "修改失败");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtils.sendMsg(true, "修改失败");
		}
		
	}
	
	
	
}
