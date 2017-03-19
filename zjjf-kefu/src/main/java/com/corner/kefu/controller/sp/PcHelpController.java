package com.corner.kefu.controller.sp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.SpGroup;
import com.corner.core.beans.SpHelp;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.beans.vo.sp.SpHelpVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.sp.SpSpHelpService;

@Controller
@RequestMapping("/customer/spHelp")
public class PcHelpController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(PcHelpController.class);
	@Autowired
	SpSpHelpService spHelpService;
	
	
	@RequestMapping("/getAllHelp.do")
	public String getAllHelp(HttpServletRequest request, Model model){
		SpHelp spHelp = new SpHelp();
		String title = request.getParameter("title");
		try {
			if(title != null && !"".equals(title)){
				spHelp.setTitle(title);
			}
			List<SpHelpVo> helps= spHelpService.getAllHelpByParameter(spHelp);
			if(helps!=null&&helps.size()>0){
				model.addAttribute("title", title);
				model.addAttribute("helps", helps);
				return "/help/faq";
			}else{
				return "/help/faq";
			}

		} catch (Exception e) {
			logger.error("",e);
			return this.error("出错了！", model, request);
		}
	} 

	@RequestMapping("/addHelp.do")
	@ResponseBody
	public Object addHelp(HttpServletRequest request, Model model){
		SpHelp spHelp = new SpHelp();
		String title = request.getParameter("title");
		String solution = request.getParameter("solution");
		if(title==null || "".equals(title)){
			return ResponseUtils.sendMsg(false, "标题不能为空！");
		}
		spHelp.setTitle(title);
		if(solution == null || "".equals(solution)){
			return ResponseUtils.sendMsg(false, "答案不能为空！");
		}
		spHelp.setSolution(solution);
		spHelp.setTypeId(Byte.parseByte("1"));
		spHelp.setAddTime(new Date());
		
		spHelp.setUpdateTime(new Date());
		spHelp.setOrdId(225);
		spHelp.setIsDelete(false);
		try {
			spHelpService.addHelp(spHelp);
			return ResponseUtils.sendMsg(true, "添加成功！");
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, "添加失败！");
		}
	}
	
	@RequestMapping("/deleteHelp.do")
	@ResponseBody
	public Object deleteHelp(HttpServletRequest request, Model model,SpHelp spHelp){
		try {
			spHelpService.updateHelp(spHelp);
			return ResponseUtils.sendMsg(true, "成功！");
		} catch (Exception e) {
			logger.error("失败",e);
			return ResponseUtils.sendMsg(false, "失败！");
		}
	}
	
	@RequestMapping("/updateHelp.do")
	@ResponseBody
	public Object updateHelp(HttpServletRequest request, Model model){
		try {
			SpHelp spHelp = new SpHelp();
			String id1 = request.getParameter("id");
			int id = 0;
			if(id1 != null){
				id = Integer.parseInt(id1);
				spHelp.setId(id);
			}
			spHelp.setUpdateTime(new Date());
			String title = request.getParameter("title");
			spHelp.setTitle(title);
			String solution = request.getParameter("solution");
			spHelp.setSolution(solution);
			spHelpService.updateHelp(spHelp);
			return ResponseUtils.sendMsg(true, "更新成功！");
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, "更新失败！");
		}
	}
	
	@RequestMapping("/pageContrul.do")
	public String pageContrul(HttpServletRequest request, Model model, SpGroup spGroup){
		String pageStr = request.getParameter("pageStr");
		if(pageStr !=null && pageStr.equals("add")){
			return "/help/faq-add";
		}else if(pageStr !=null && pageStr.equals("edit")){
			String id1 = request.getParameter("id");
			if(id1 != null){
				int id = Integer.parseInt(id1);
				SpHelp spHelp = spHelpService.getHelpById(id);
				if(spHelp.getTitle() != null && spHelp.getSolution() != null){
					String title = spHelp.getTitle();
					String solution = spHelp.getSolution();
					model.addAttribute("id",id);
					model.addAttribute("title", title);
					model.addAttribute("solution", solution);
					return "/help/faq-edit";
				}
			}else{
				return error("出错了！", model, request);
			}
			return error("出错了！", model, request);
		}else{
			return error("出错了！", model, request);
		}
	}
	
	@RequestMapping("updateOrderId.do")
	@ResponseBody
	public Object updateOrderId(HttpServletRequest request, Model model){
		String ordId1 = request.getParameter("ordId");
		int ordId = 0;
		if(ordId1!=null&&!"".equals(ordId1)){
			ordId = Integer.parseInt(ordId1);
			if(ordId<0){
				return ResponseUtils.sendMsg(false, "数字不能小于0！");
			}
		}
		String id1 = request.getParameter("id");
		int id = 1;
		if(id1 != null && !"".equals(id1)){
			id = Integer.parseInt(id1);
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ordId", ordId);
		param.put("id", id);
		try {
			spHelpService.orderHelpList(param);
			return ResponseUtils.sendMsg(true, "设置成功!");
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, "设置失败！");
		}
	}
	
}
