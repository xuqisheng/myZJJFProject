/**
 * 
 */
package com.corner.kefu.controller.sp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.Adboard;
import com.corner.core.beans.Advertisement;
import com.corner.core.beans.ItemCatelog;
import com.corner.core.beans.SpGroup;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.AdvertisementRo;
import com.corner.kefu.beans.vo.AdvertisementVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.sp.SpAdbordService;
import com.corner.kefu.service.sp.SpAdvertisementService;
import com.corner.kefu.service.sp.SpGroupService;
import com.corner.kefu.service.sp.SpItemCatelogService;



/**
 * 
 * @ClassName: PcAdManageController
 * 
 * @Description: 广告管理
 * 
 * @author: 杨开泰
 * 
 * @date: 2015年11月10日 下午4:27:52
 */
@Controller
@RequestMapping("/Customer/AdManage")
public class PcAdManageController extends KefuBaseWebController {

	private static final Logger logger = LoggerFactory.getLogger(PcAdManageController.class);

	@Autowired
	SpAdbordService adbordService;
	@Autowired
	SpAdvertisementService advertisementService;
	@Autowired
	SpGroupService spGroupService;
	@Autowired
	SpItemCatelogService itemCatelogService;
	
	
	/**
	 * 删除广告
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteAd.do")
	@ResponseBody
	public Object deleteAd(Integer id) {
		try {
			if (StringUtil.stringIsNullOrEmpty(id + "")) {
				return ResponseUtils.sendMsg(false, "参数出错!");
			}
			advertisementService.deleteAd(id);
			return ResponseUtils.sendMsg(true, "删除成功!");
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseUtils.sendMsg(false, "删除广告程序出错!");
		}
	}
	
	 /**
	   * 批量删除广告
	  * @Title
	  * @Description: TODO 
	  * @param @param name
	  * @2016年1月11日     
	  * @author 龙五  longwu@izjjf.cn
	  * @return
	  * @throws
	   */
	 @RequestMapping("batchDeleteAd.do")
	 @ResponseBody
	  public Object deleteAdByName(Integer id){
		 try {
			if(id!=null){
				 advertisementService.deleteAdByName(id);
				 return ResponseUtils.sendMsg(true, "删除成功！");
			 }else{
				 return ResponseUtils.sendMsg(false, "删除失败");
			 }
		} catch (Exception e) {
			logger.error("批量删除广告时出错了",e);
			return ResponseUtils.sendMsg(false, "删除失败！");
		}
	 }

	 
	 
	  /**
	   * 批量上下架广告
	  * @Title
	  * @Description: TODO 
	  * @param @param name
	  * @2016年1月11日     
	  * @author 龙五  longwu@izjjf.cn
	  * @return
	  * @throws
	   */
	 @RequestMapping("batchUpdateAdstatus.do")
	 @ResponseBody
	  public Object updateAdstatusByName(Integer id,Byte status){
		 try {
			if(id!=null && status != null){
				String successStr = "";
				 advertisementService.updateAdstatusByName(id,status);
				 if(status==0){
					 successStr = "下架成功！";
				 }else if(status==1){
					 successStr = "上架成功！";
				 }
				 return ResponseUtils.sendMsg(true, successStr);
			 }else{
				 String errorStr = "";
				 if(status==0){
					 errorStr = "下架失败！";
				 }else if(status==1){
					 errorStr = "上架失败！";
				 }
				 return ResponseUtils.sendMsg(false, errorStr);
			 }
		} catch (Exception e) {
			logger.error("批量下架广告时出错了",e);
			 String exceStr = "";
			 if(status==0){
				 exceStr = "下架失败！";
			 }else if(status==1){
				 exceStr = "上架失败！";
			 }
			 return ResponseUtils.sendMsg(false, exceStr);
		}
		 
	  }
	 
	 
	 
	/**
	 * 跳转到广告管理页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toIdex.do")
	public String toIndex(HttpServletRequest request, Model model,AdvertisementRo advertisement) {
		try {
			model.addAttribute("name", advertisement.getName());
			model.addAttribute("startTime", advertisement.getStartTime());
			model.addAttribute("endTime", advertisement.getEndTime());
			model.addAttribute("boardId", advertisement.getBoardId());
			Pager<AdvertisementVo> page = advertisementService.getAdvertisementList(advertisement);
			model.addAttribute("list", page.getList());
			List<Adboard> adboards = advertisementService.getAllAdboards();
			model.addAttribute("adboards", adboards);
			this.pageUtil(advertisement.getPageIndex(), page.getTotalSize(), advertisement.getPageSize(), request, model);
			return "ad/index";
		} catch (Exception e) {
			logger.error(e.toString());
			model.addAttribute("message", "跳转到广告管理页出错");
			return "/common/error";
		}
	}
	
	@RequestMapping("/getSpGroupBySpGroupName.do")
	@ResponseBody
	public Object getSpGroupBySpGroupName(String spGroupName){
		Map<String, Object> map = new HashMap<>();
		map.put("name", spGroupName);
		try {
			List<SpGroup> spGroupList = spGroupService.getSpGroupBySpGroupName(map);
			if(spGroupList != null && !spGroupList.isEmpty()){
				return ResponseUtils.sendMsg(true,spGroupList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false);
		}
	}

//	/**
//	 * 跳转到添加广告页
//	 * 
//	 * @param request
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping("/toAdAdd.do")
//	public String toAd_add(HttpServletRequest request, Model model) {
//		try {
//			//获取添加广告页面的数据map
//			Map<String, Object> map = advertisementService.getEditDate(null);
//			model.addAttribute("map", map);
//			return "ad/ad-add";
//		} catch (Exception e) {
//			logger.error(e.toString());
//			return error("跳转到添加广告页出错", model, request);
//		}
//	}
	
	
	/**
	 * 编辑广告
	 * 
	 * @param advertisement
	 * @return
	 */
	@RequestMapping("/saveAdvertisement.do")
	@ResponseBody
	public Object saveAdvertisement(HttpServletRequest request, Advertisement advertisement) {
		try {

			String[] spgroupidArr = request.getParameterValues("spGroupId");
			ModelMsg msg = advertisementService.saveAdvertisement(advertisement,spgroupidArr);
			if(msg.isSuccess()){
				return ResponseUtils.sendMsg(true, msg.getMessage());
			}else{
				return ResponseUtils.sendMsg(false, msg.getMessage());
			}
			
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseUtils.sendMsg(false, "程序异常，请联系技术人员!");
		}
	}
	
	
	/**
	 * 跳转
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/toEditAdvertisement/{str}")
	public String toEditAdvertisement(@PathVariable String str,HttpServletRequest request, Model model, Integer id) {
		try {
			//获取添加广告页面的数据map
			Map<String, Object> map = advertisementService.getEditDate(id);
			map.put("str", str);
			model.addAttribute("map", map);
			return "ad/ad-add";
		} catch (Exception e) {
			logger.error(e.toString());
			return error("程序异常!", model, request);
		}
	}
	
	
	
	/**
	 * 跳转到链接页
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getLinkContent.do")
	@ResponseBody
	public Object getLinkContent(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			if (StringUtil.stringIsNullOrEmpty(id)) {
				return ResponseUtils.sendMsg(false, "参数有误");
			}
			AdvertisementVo advertisementVo = advertisementService.getAdvertisementVoById(Integer.parseInt(id));
			return ResponseUtils.sendMsg(true, advertisementVo);
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}
	
	
	/**
	 * 跳转到广告位管理页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAdPosition.do")
	public String toAdPosition(HttpServletRequest request, Model model) {
		try {
			// 查询广告位列表
			List<Adboard> list = adbordService.getAdPositionList();
			model.addAttribute("list", list);
			return "ad/ad-position";
		} catch (Exception e) {
			logger.error("跳转到广告位管理页出错",e);
			return error("跳转到广告位管理页出错", model, request);
		}
	}
	
	
	/**
	 * 新增广告位成功
	 * 
	 * @return
	 */
	@RequestMapping("/saveAd.do")
	@ResponseBody
	public Object saveAd(Adboard adboard) {
		try {
			if (StringUtil.stringIsNullOrEmpty(adboard.getName())) {
				return ResponseUtils.sendMsg(false, "参数有误!");
			}
			if (adboard.getId() != null) {
				adbordService.updateByPrimaryKeySelective(adboard);
				return ResponseUtils.sendMsg(true, "修改成功!");
			} else {
				adboard.setWidth(0);
				adboard.setHeigtht(0);
				adbordService.saveAdboard(adboard);
				return ResponseUtils.sendMsg(true, "新增成功!");
			}

		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseUtils.sendMsg(false, "新增出错!");
		}
	}
	
	/**
	 * 修改广告位
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/editAdPosition.do")
	@ResponseBody
	public Object editAdPosition(Integer id) {
		try {
			Adboard adboard = adbordService.getAdboardById(id);
			return ResponseUtils.sendMsg(true, adboard);
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}
	
	
	/**
	 * 删除广告位
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteAdPosition.do")
	@ResponseBody
	public Object deleteAdPosition(Integer id) {
		try {
			if (StringUtil.stringIsNullOrEmpty(id + "")) {
				return ResponseUtils.sendMsg(false, "参数有误!");
			}
			adbordService.removeAdPosition(id);
			return ResponseUtils.sendMsg(true, "删除成功!");
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}
	
	//获取所有二级分类
	@RequestMapping("/getSecondCateList.do")
	@ResponseBody
	public Object getSecondCateList(){
		try {
			List<ItemCatelog> catelogList = itemCatelogService.getSecondCateList();
			if(catelogList != null && catelogList.size() >0){
				return ResponseUtils.sendMsg(true, catelogList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.sendMsg(false);
		}
		
	} 
	
	

}
