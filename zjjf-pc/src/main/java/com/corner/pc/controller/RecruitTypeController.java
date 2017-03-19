package com.corner.pc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.pc.beans.vo.RecruitTypeVo;
import com.corner.pc.service.RecruitTypeService;
import com.corner.pc.utils.ResponseUtils;
@Controller
@RequestMapping("/pc/recruitType")
public class RecruitTypeController {

	@Autowired RecruitTypeService recruitTypeService;
	
	@RequestMapping("getRecruitInfo.do")
	@ResponseBody
	public Object getRecruitInfo(){
		try {
			List<RecruitTypeVo> recruitInfoList = recruitTypeService.getRecruitInfo();
			return ResponseUtils.sendMsg(true, recruitInfoList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtils.sendMsg(false);
		}
		
		
	}
}
