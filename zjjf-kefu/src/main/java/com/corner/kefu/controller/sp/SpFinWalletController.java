/**   
* @Title: SpFinWalletController.java 
* @Package com.corner.kefu.controller.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年7月18日 下午5:57:01 
* @version V1.0   
*/

package com.corner.kefu.controller.sp;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.RechargeGrade;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.beans.ro.sp.FinWalletMgRo;
import com.corner.kefu.beans.ro.sp.FinWalletRechargeInfoRo;
import com.corner.kefu.beans.ro.sp.FinWalletRo;
import com.corner.kefu.beans.vo.sp.FinWalletLogVo;
import com.corner.kefu.beans.vo.sp.FinWalletRechargeInfoVo;
import com.corner.kefu.beans.vo.sp.FinWalletVo;
import com.corner.kefu.beans.vo.sp.RechargeGradeVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.sp.SpFinWalletService;

/** 
* @ClassName: SpFinWalletController 
* @Description:统一钱包管理控制器
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年7月18日 下午5:57:01 
*  
*/
@Controller
@RequestMapping("/wallet")
public class SpFinWalletController extends KefuBaseWebController {
	private final static String PATH = "wallet-shop/";

	@Autowired
	private SpFinWalletService spFinWalletService;
	
	
	
	
	
	/**
	 * 
	* @Title: deleteRechargeGrade 
	* @Description:删除梯度
	* @param @param rechargeGrade
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/deleteRechargeGrade.do")
	@ResponseBody
	private Object deleteRechargeGrade(RechargeGrade rechargeGrade){
		try {
			spFinWalletService.deleteRechargeGrade(rechargeGrade);
			return ResponseUtils.sendMsg(true, "删除成功!");
		} catch (Exception e) {
			logger.error("删除充值送劵出错:",e);
			return ResponseUtils.sendMsg(false, e.toString());
		}
	}
	
	
	
	/**
	 * 
	* @Title: addRechargeGrade 
	* @Description:
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/addRechargeGrade.do")
	@ResponseBody
	private Object addRechargeGrade(RechargeGrade rechargeGrade){
		if(rechargeGrade.getGradeMoney()==null){
			return ResponseUtils.sendMsg(false, "缺少必要参数!");
		}
		spFinWalletService.addRechargeGrade(rechargeGrade);
		return ResponseUtils.sendMsg(true, rechargeGrade);
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	* @Title: updateStoreFinWalletStatus 
	* @Description:修改钱包状态
	* @param @param finWalletRo
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/updateStoreFinWalletStatus.do")
	@ResponseBody
	private Object updateStoreFinWalletStatus(FinWalletRo finWalletRo){
		if(finWalletRo.getStatus().intValue()==1){
			//表示当前钱包是正常状态，操作是要冻结钱包
			finWalletRo.setStatus((byte)0);//冻结
		}else {
			finWalletRo.setPwdErrorCount((byte)0);
			finWalletRo.setStatus((byte)1);//解冻
		}
		try {
			spFinWalletService.updateStoreFinWallet(finWalletRo);
			return ResponseUtils.sendMsg(true, "更新成功!");
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, e.toString());
		}
	}
	
	
	/**
	 * 
	* @Title: getStoreWalletList 
	* @Description:获取钱包明细列表
	* @param @param finWalletRo
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getStoreWalletList.do")
	@ResponseBody
	private Object	getStoreWalletList(FinWalletRo finWalletRo){
		Pager<FinWalletVo> pager = spFinWalletService.getStoreWalletList(finWalletRo);
		pager.setFlag(true);
		return pager;
	}
	
	
	/**
	 * 
	* @Title: getRechargeGradeList 
	* @Description:获取充值梯度列表 暂时不用
	* @param @return 
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getRechargeGradeList.do")
	@ResponseBody
	private Object getRechargeGradeList(){
		List<RechargeGradeVo> list = spFinWalletService.getRechargeGradeList();
		return ResponseUtils.sendMsg(false, list);
	}
	
	
	/**
	 * 
	* @Title: toRechargeInfo 
	* @Description:跳转到充值记录页面
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toRechargeInfo.do")
	private String toRechargeInfo(FinWalletRechargeInfoRo rechargeInfo,Model model,HttpServletRequest request) {
		model.addAttribute("rechargeInfo", rechargeInfo);
		Date beginTime = rechargeInfo.getBeginTime();
		Date endTime = rechargeInfo.getEndTime();
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("mobile", rechargeInfo.getMobile());
		try {
			Pager<FinWalletRechargeInfoVo> page = spFinWalletService.getAllWalletRechargeInfo(rechargeInfo);
			if(page != null){
				pageUtil(rechargeInfo.getPageIndex(), page.getTotalSize(), rechargeInfo.getPageSize(), request, model);
			}
			model.addAttribute("rechargeInfoList", page.getList());
			return PATH + "recharge-record";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("程序异常");
			return error("程序异常", model, request);
		}
		
	}
	
	
	/**
	 * 
	* @Title: getStoreWalletLog 
	* @Description:
	* @param @param finWalletRo
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toStoreWalletLog.do")
	private String toStoreWalletLog(FinWalletMgRo finWalletMgRo,Model model,HttpServletRequest request){
		try {
			String pageIndexStr = request.getParameter("pageIndex");
			model.addAttribute("id", finWalletMgRo.getId());
			if(!StringUtils.isEmpty(pageIndexStr)){
				finWalletMgRo.setPageIndex(Integer.parseInt(pageIndexStr));
			}else {
				pageIndexStr = "1";
				finWalletMgRo.setPageIndex(1);
			}
			if(finWalletMgRo.getStartTime()!=null){
				Date date = finWalletMgRo.getStartTime();
                DateUtils.setHours(date, 0);
                DateUtils.setMinutes(date, 0);
                DateUtils.setMilliseconds(date, 0);
                finWalletMgRo.setStartTime(date);
                model.addAttribute("startTime",  DateUtil.date2String(date));
			}
			if(finWalletMgRo.getEndTime()!=null){
				Date date = finWalletMgRo.getEndTime();
				model.addAttribute("endTime", DateUtil.date2String(date));
				DateUtils.setHours(date, 0);
                DateUtils.setMinutes(date, 0);
                DateUtils.setMilliseconds(date, 0);
                date = DateUtils.addDays(date, 1);
                finWalletMgRo.setEndTime(date);
			}
			
			Pager<FinWalletLogVo> pager = spFinWalletService.getWalletLog(finWalletMgRo);
			if(pager.getList()!=null&&pager.getList().size()!=0){
				model.addAttribute("list", pager.getList());
				pageUtil(finWalletMgRo.getPageIndex(), pager.getTotalSize(), finWalletMgRo.getPageSize(), request, model);
			}else {
				model.addAttribute("list", null);
				pageUtil(1, pager.getTotalSize(), 10, request, model);
			}
			return PATH+"wallet-deal-detail";
		} catch (Exception e) {
			logger.error("查看交易明细出错:", e);
			return PATH+"wallet-deal-detail";
		}
	}
	
	
	
	/**
	 * 
	* @Title: toWithDraw 
	* @Description:跳转到提现记录页面
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toWithDraw.do")
	private String toWithDraw(){
		return null;
	}
	
	
	/**
	 * 
	* @Title: toRechargeSpVoucher 
	* @Description:跳转到充值送劵页面
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toRechargeSpVoucher.do")
	private String toRechargeSpVoucher(Model model){
		List<RechargeGradeVo> list = spFinWalletService.getRechargeGradeList();
		model.addAttribute("list", list);
		return PATH+"recharge-send-coupon";
	}
	
	
	/**
	 * 
	* @Title: toStoreWallet 
	* @Description:跳转到小店钱包明细页面
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toStoreWallet.do")
	private String toStoreWallet(){
		return PATH+"wallet";
	}
}
