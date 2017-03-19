package com.corner.pc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.pc.beans.PcAdvertisement;
import com.corner.pc.beans.ro.PcAdvertisementRo;
import com.corner.pc.beans.vo.Pager;
import com.corner.pc.service.PcAdvertisementService;
import com.corner.pc.utils.ResponseUtils;

@Controller
@RequestMapping("/pc/advertisement")
public class PcAdvertisementController extends APCBaseWebController {
	private static Logger logger = LoggerFactory.getLogger(PcAdvertisementController.class);
	
	@Autowired
	PcAdvertisementService pcAdvertisementService;
	
	/**
	 * 广告列表
	* @Title
	* @Description: TODO 
	* @param @param advertisementRo
	* @param @param model
	* @param @return
	* @2016年5月27日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("gatAllAdvertisement.do")
	public String gatAllAdvertisement(PcAdvertisementRo advertisementRo,Model model){
		model.addAttribute("advertisementRo", advertisementRo);
		try {
			Pager<PcAdvertisement> pager = pcAdvertisementService.getAllAdvertisement(advertisementRo);
			if(pager != null){
				model.addAttribute("adList", pager.getList());
			}
			return "/ad";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 返回编辑页面
	* @Title
	* @Description: TODO 
	* @param @param model
	* @param @param id
	* @param @return
	* @2016年5月27日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("returnEditPage.do")
	public String returnEditPage(Model model,Integer id){
		PcAdvertisement advertisement = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if(id != null){
			try {
				map.put("id", id);
				advertisement = pcAdvertisementService.getAdvertisementById(map);
				if(advertisement != null){
					model.addAttribute("advertisement", advertisement);
				}
				return "/editAd";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "/editAd";
	}
	
	/**
	 * 添加广告
	* @Title
	* @Description: TODO 
	* @param @param advertisementRo
	* @param @return
	* @2016年5月27日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("addAdvertisement.do")
	@ResponseBody
	public Object addAdvertisement(PcAdvertisementRo advertisementRo){
		advertisementRo.setAddTime(new Date());
		advertisementRo.setUpTime(new Date());
		advertisementRo.setNextTime(new Date());
		try {
			pcAdvertisementService.addAdvertisement(advertisementRo);
			return ResponseUtils.sendMsg(true, "添加成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "添加失败");
		}
	}
	
	/**
	 * 修改广告
	* @Title
	* @Description: TODO 
	* @param @param advertisementRo
	* @param @return
	* @2016年5月27日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("updateAdvertisement.do")
	@ResponseBody
	public Object updateAdvertisement(PcAdvertisementRo advertisementRo){
		try {
			pcAdvertisementService.updateAdvertisement(advertisementRo);
			return ResponseUtils.sendMsg(true, "修改成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtils.sendMsg(false, "修改失败");
		}
	}
	
	/**
	 * 上下架
	* @Title
	* @Description: TODO 
	* @param @param advertisementRo
	* @param @return
	* @2016年5月27日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("updateStatus.do")
	@ResponseBody
	public Object updateStatus(PcAdvertisementRo advertisementRo){
		if(advertisementRo.getId() == null || advertisementRo.getStatus()==null){
			return ResponseUtils.sendMsg(false, "数据有误");
		}
		if(advertisementRo.getStatus()==1){
			advertisementRo.setUpTime(new Date());
		}else{
			advertisementRo.setNextTime(new Date());
		}
		try {
			pcAdvertisementService.updateAdvertisement(advertisementRo);
			return ResponseUtils.sendMsg(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"数据异常");
		}
		
	}
	
	/**
	 * 删除
	* @Title
	* @Description: TODO 
	* @param @param advertisementRo
	* @param @return
	* @2016年5月27日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("updateIsDelete.do")
	@ResponseBody
	public Object updateIsDelete(PcAdvertisementRo advertisementRo){
		if(advertisementRo.getId() == null || advertisementRo.getIsDelete()==null){
			return ResponseUtils.sendMsg(false, "数据有误");
		}
		try {
			pcAdvertisementService.updateAdvertisement(advertisementRo);
			return ResponseUtils.sendMsg(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtils.sendMsg(false,"数据异常");
		}
	}
	
	@RequestMapping("getAdvertisementByPositionId.do")
	@ResponseBody
	public Object getAdvertisementByPositionId(PcAdvertisementRo advertisementRo){
		if(advertisementRo.getPositionId()==null){
			return ResponseUtils.sendMsg(false);
		}
		List<PcAdvertisement> list;
		try {
			list = pcAdvertisementService.getAdvertisementByPositionId(advertisementRo);
			if(list != null && list.size()>0){
				return ResponseUtils.sendMsg(true,list);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtils.sendMsg(false);
		}
	}
	
	@RequestMapping("getPcAdvertisementById.do")
	@ResponseBody
	public Object getPcAdvertisementById(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return ResponseUtils.sendMsg(true , pcAdvertisementService.getAdvertisementById(map));
	}
	
	
}
