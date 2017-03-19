package com.corner.kefu.controller.sp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.SpComment;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.sp.SpCommentParamterRo;
import com.corner.kefu.beans.vo.sp.SpCommentVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.sp.SpCommentService;

@RequestMapping("/Customer/SpComment")
@Controller
public class PcCommentController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(PcCommentController.class);
	@Autowired
	SpCommentService spCommentService;
	
	public String getCommentFenString(SpCommentParamterRo spcRo){
		String str = "";
		if(spcRo.getUnionFenA() != null){
			str += "1,";
		}
		if(spcRo.getUnionFenB() != null){
			str += "2,";
		}
		if(spcRo.getUnionFenC() != null){
			str += "3,";
		}
		if(spcRo.getUnionFenD() != null){
			str += "4,";
		}
		if(spcRo.getUnionFenE() != null){
			str += "5,";
		}
		if(StringUtils.isEmpty(str)){
			return null;			
		}else{
			str= str.substring(0, str.length()-1);
			return str;
		}
	}
	@RequestMapping("/getAllCommentByParameter.do")
	public String getAllCommentByParameter(HttpServletRequest request, Model model, SpCommentParamterRo spcRo) {

		try {
			if(spcRo.getSpNm()!=null){
				spcRo.setSpNm(spcRo.getSpNm().trim());
			}
			if(spcRo.getInfo() != null){
				spcRo.setInfo(spcRo.getInfo().trim());
			}
			String beginTime1 = request.getParameter("beginTime1");
			String endTime1 = request.getParameter("endTime1");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date beginTime = null;
			Date endTime = null;
			if(beginTime1 != null && !"".equals(beginTime1)){
				beginTime = (Date)dateFormat.parseObject(beginTime1);
				spcRo.setBeginTime(beginTime);
			}
			if(endTime1 != null && !"".equals(endTime1)){
				Calendar cd = Calendar.getInstance();   
				cd.setTime(dateFormat.parse(endTime1));   
				cd.add(Calendar.DATE, 1);
				endTime = dateFormat.parse(dateFormat.format(cd.getTime()));
				spcRo.setEndTime(endTime);
			}
			spcRo.setUnionFen(getCommentFenString(spcRo));
			Pager<SpCommentVo> commentPager = spCommentService.getAllCommentByParameter(spcRo);
			if(commentPager != null){
				model.addAttribute("spcRo", spcRo);
				model.addAttribute("beginTime1", beginTime1);
				model.addAttribute("endTime1", endTime1);
				model.addAttribute("commentList", commentPager.getList());
			pageUtil(spcRo.getPageIndex(), commentPager.getTotalSize(), spcRo.getPageSize(), request, model);
				return "/order/comment";
			}else{
				return "/order/comment";
			}
		} catch (Exception e) {
			logger.error("",e);
			return this.error("出错了！d", model, request);
		}
	}
	
	@RequestMapping("/getCommentById.do")
	public String getCommentById(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		
		try {
 			if (id != null && !"".equals(id)) {
				SpCommentVo comment = spCommentService.getCommentById(id);
				if (comment != null) {
					model.addAttribute("id", id);
					model.addAttribute("comment", comment);
					return "/order/comment-detail";
				} else {
					return this.error("出错了！d", model, request);
				}
			} else {
				return this.error("出错了！d", model, request);
			}

		} catch (Exception e) {
			logger.error("", e);
			return this.error("出错了！d", model, request);
		}
	}
	@RequestMapping("/updateCommentById.do")
	@ResponseBody
	public Object updateCommentById(HttpServletRequest request, Model model , SpComment spComment) {
		CustomerService service = getCurrentUser(CustomerService.class, request);
		if(StringUtil.stringIsNullOrEmpty(spComment.getId()) && service == null){
			return ResponseUtils.sendMsg(false, "缺少必要参数");
		}
		spComment.setCsDealTime(new Date());
		spComment.setCsId(service.getId());
		spComment.setCsNm(service.getUserName());
		try {
			ModelMsg modelMsg = spCommentService.updateCommentById(spComment);
			return ResponseUtils.sendMsg(modelMsg.isSuccess(), modelMsg.getMessage());
		} catch (Exception e) {
			logger.error("", e);
			return ResponseUtils.sendMsg(false, "处理失败！");
		}
	}
}
