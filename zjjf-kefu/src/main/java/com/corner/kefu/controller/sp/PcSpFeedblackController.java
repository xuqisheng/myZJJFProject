package com.corner.kefu.controller.sp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.SpFeedback;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.beans.ro.sp.SpFeedbackRo;
import com.corner.kefu.beans.vo.sp.SpFeedbackVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.sp.SpFeedbackService;



@Service
@RequestMapping("/CornerV2/SpFeedblack")
public class PcSpFeedblackController extends KefuBaseWebController {

	private static final Logger logger = LoggerFactory.getLogger(PcSpFeedblackController.class);
	@Autowired
	SpFeedbackService spFeedbackService;
	
	
	/**
	 * 根据条件查出所有的反馈消息
	 * @Title
	 * @Description: TODO 
	 * @param @param spFeedbackRo
	 * @param @return
	 * @2016年1月31日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	@RequestMapping("/getAllSpFeedbackByParam.do")
	public String getAllSpFeedbackByParam(HttpServletRequest request,Model model,SpFeedbackRo spFeedbackRo){
		Date endtime = spFeedbackRo.getEndTime();
		if(spFeedbackRo.getAppVersion() != null && !"".equals(spFeedbackRo.getAppVersion())){
			spFeedbackRo.setAppVersion(spFeedbackRo.getAppVersion().trim());
		}
		if(spFeedbackRo.getEndTime()!= null){
			Date endTime = spFeedbackRo.getEndTime();
			endTime = DateUtils.addDays(endTime, 1);
			spFeedbackRo.setEndTime(endTime);
		}
		
		try {
			Pager<SpFeedbackVo> SpFeedbackList = spFeedbackService.getAllSpFeedbackByParam(spFeedbackRo);
			if(SpFeedbackList != null){
				spFeedbackRo.setEndTime(endtime);
				model.addAttribute("spFeedbackRo", spFeedbackRo);
				model.addAttribute("SpFeedbackList", SpFeedbackList.getList());
				pageUtil(spFeedbackRo.getPageIndex(), SpFeedbackList.getTotalSize(), spFeedbackRo.getPageSize(), request, model);
			}
			return "feedback/app";
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
			
		}
	}
	
	//返回回复页面
	@RequestMapping("returnHuiFuPage.do")
	public String returnHuiFuPage(HttpServletRequest request,Model model){
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		if(id != null && !"".equals(id)){
			map.put("id", Integer.parseInt(id));
		}
		SpFeedbackVo spFeedbackVo = spFeedbackService.getFeedbackById(map);
		if(spFeedbackVo.getPicPaths() != null && !"".equals(spFeedbackVo.getPicPaths())){
			if(spFeedbackVo.getPicPaths().contains("|")){
				String []  pic = spFeedbackVo.getPicPaths().split("\\|");
				for (int i = 0; i < pic.length; i++) {
					if(pic[i]!=null && !"".equals(pic[i]) && i==0){
						spFeedbackVo.setPic1(pic[i]);
					}else if(pic[i]!=null && !"".equals(pic[i]) && i==1){
						spFeedbackVo.setPic2(pic[i]);
					}else if(pic[i]!=null && !"".equals(pic[i]) && i==2){
						spFeedbackVo.setPic3(pic[i]);
					}
				}
			}else{
				spFeedbackVo.setPic1(spFeedbackVo.getPicPaths());
			}
		}
		model.addAttribute("spFeedbackVo", spFeedbackVo);
		return "/feedback/app-reply";
	}
	
//	
//	/**
//	 * 根据批发商id查出所有的反馈内容和客服回复内容
//	* @Title
//	* @Description: TODO 
//	* @param @param storeId
//	* @param @return
//	* @2016年2月25日     
//	* @author 龙五  longwu@izjjf.cn
//	* @return
//	* @throws
//	 */
//	@RequestMapping("getAllFeedbackByStoreId.do")
//	public String getAllFeedbackByStoreId(HttpServletRequest request,Model model,SpFeedbackRo spFeedbackRo){
//		
//		try {
//			Pager<SpFeedbackVo> spFeedbackVoList =  spFeedbackService.getAllFeedbackByStoreId(spFeedbackRo);
//			if(spFeedbackVoList != null){
//				model.addAttribute("spFeedbackVoList", spFeedbackVoList.getList());
//				model.addAttribute("spFeedbackRo", spFeedbackRo);
//				pageUtil(spFeedbackRo.getPageIndex(), spFeedbackVoList.getTotalSize(), spFeedbackRo.getPageSize(), request, model);
//				return "order/sp-feedback-detail";
//			}else{
//				return error("没有相关的反馈！", model, request);
//			}
//		} catch (Exception e) {
//			logger.error("根据批发商id查出所有的反馈内容和客服回复内容时出错了！",e);
//			return error("出错了！", model, request);
//		}
//	}
	
	
	/**
	 * 保存客服处理内容
	* @Title
	* @Description: TODO 
	* @param @param spFeedbackRo
	* @2016年1月31日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("saveCustomerServiceHuiFu.do")
	@ResponseBody
	public Object saveCustomerServiceHuiFu(HttpServletRequest request,SpFeedback spFeedback){
		try {
			CustomerService service = getCurrentUser(CustomerService.class, request);
			spFeedback.setConfirmTime(new Date());
			spFeedback.setCheckerId(service.getId());
			spFeedback.setCheckerNm(service.getUserName());
			spFeedbackService.updateFeedback(spFeedback);
			return ResponseUtils.sendMsg(true, "处理成功！");
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(true, "处理失败！");
		}
	}
}
