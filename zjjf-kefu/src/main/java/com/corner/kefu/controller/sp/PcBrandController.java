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

import com.corner.core.beans.Brand;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.BrandRo;
import com.corner.kefu.beans.vo.BrandVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.sp.SpBrandService;

@Controller
@RequestMapping("/customer/brand")
public class PcBrandController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(PcBrandController.class);
	@Autowired
	private SpBrandService brandService;

	
	
	@RequestMapping("getBrandByName.do")
	@ResponseBody
	public Object getBrandByName(HttpServletRequest request,String brandName){
		Map<String, Object> map = new HashMap<String, Object>();
		String str=request.getParameter("str");
		map.put("str", str);
		map.put("brandName", brandName);
		try {
			List<BrandVo> brandVoList = brandService.getBrandByName(map);
			if(brandVoList != null && brandVoList.size()>0){
				return ResponseUtils.sendMsg(true,brandVoList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
	
	}
	
	@RequestMapping("returnBrandAddOrEditPage.do")
	public String returnBrandAddOrEditPage(HttpServletRequest request,Model model){
		String pageIndex = request.getParameter("pageIndex");
		model.addAttribute("pageIndex", pageIndex);
		String action = request.getParameter("action");
		if(action != null && !"".equals(action)){
			if(action.equals("2")){
				String id = request.getParameter("id");
				//根据id查询数据
				 Map<String, Object> map = new HashMap<String, Object>();
				 map.put("id", id);
				 BrandVo brand = brandService.getBrandById(map);
				 if(brand != null){
					 model.addAttribute("brand", brand);
				 }
			}
			return "/goods/brand-edit";
		}else{
			return error("请求有误，请重试。", model, request);
		}
	}
	
	/**
	 * 获取所有的商品品牌
	 * @Title
	 * @Description: TODO 
	 * @param @param brandRo
	 * @param @return
	 * @2016年1月15日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	@RequestMapping("getAllBrandByParam.do")
	public String getAllBrandByParam(HttpServletRequest request,Model model,BrandRo brandRo){
//		String pageIndexStr = request.getParameter("pageIndex");
//		Integer pageIndex = 1;
//		if(!StringUtil.stringIsNullOrEmpty(pageIndexStr)){
//			pageIndex = Integer.parseInt(pageIndexStr);
//		}
		if(!StringUtil.stringIsNullOrEmpty(brandRo.getNoAndName())){
			brandRo.setNoAndName(brandRo.getNoAndName().trim());
		}
		try {
			Pager<BrandVo> pager = brandService.getAllBrandByParam(brandRo);
			if(pager!=null){
				model.addAttribute("brandRo", brandRo);
				model.addAttribute("brandList", pager.getList());
				pageUtil(brandRo.getPageIndex(), pager.getTotalSize(), brandRo.getPageSize(), request, model);
				return "/goods/brand";
			}else{
				return "/goods/brand";
			}
		} catch (Exception e) {
			logger.error("查询出错了！",e);
			return error("出错了！", model, request);

		}

	}

	/**
	 * 获取所有的商品品牌厂商
	 * @Title
	 * @Description: TODO 
	 * @param @param brandRo
	 * @param @return
	 * @2016年1月15日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	@RequestMapping("getAllBrandingByParam.do")
	public String getAllBrandingByParam(HttpServletRequest request,Model model,BrandRo brandRo){
//		String pageIndexStr = request.getParameter("pageIndex");
//		Integer pageIndex = 1;
//		if(!StringUtil.stringIsNullOrEmpty(pageIndexStr)){
//			pageIndex = Integer.parseInt(pageIndexStr);
//		}
		if(!StringUtil.stringIsNullOrEmpty(brandRo.getNoAndName())){
			brandRo.setNoAndName(brandRo.getNoAndName().trim());
		}
		try {
			Pager<BrandVo> pager = brandService.getAllBrandingByParam(brandRo);
			if(pager!=null){
				model.addAttribute("brandRo", brandRo);
				model.addAttribute("brandList", pager.getList());
				pageUtil(brandRo.getPageIndex(), pager.getTotalSize(), brandRo.getPageSize(), request, model);
				return "/goods/brand-manufacturer";
			}else{
				return "/goods/brand-manufacturer";
			}
		} catch (Exception e) {
			logger.error("查询出错了！",e);
			return error("出错了！", model, request);
		}

	}

	/**
	 * 添加品牌
	 * @Title
	 * @Description: TODO 
	 * @param @param Brand
	 * @2016年1月18日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	@RequestMapping("addBrand.do")
	@ResponseBody
	public Object addBrand(Brand brand){
		try {
			brand.setCreateTime(new Date());
			brand.setUpdateTime(new Date());
			brand.setxLevel(Byte.parseByte("2"));
			brandService.addBrandAndBranding(brand);
			return ResponseUtils.sendMsg(true, "添加成功！");
		} catch (Exception e) {
			logger.error("添加品牌时出错了！",e);
			return ResponseUtils.sendMsg(false, "添加失败！");
		}
	}

	/**
	 * 添加品牌商
	 * @Title
	 * @Description: TODO 
	 * @param @param Brand
	 * @2016年1月18日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	@RequestMapping("addBranding.do")
	@ResponseBody
	public Object addBranding(Brand brand){
		try {
			brand.setCreateTime(new Date());
			brand.setUpdateTime(new Date());
			brand.setUpId(0);
			brand.setxLevel(Byte.parseByte("1"));
			brandService.addBrandAndBranding(brand);
			return ResponseUtils.sendMsg(true, "添加成功！");
		} catch (Exception e) {
			logger.error("添加品牌商时出错了！",e);
			return ResponseUtils.sendMsg(false, "添加失败！");
		}
	}





	/**
	 * 更新品牌
	 * @Title
	 * @Description: TODO 
	 * @param @param brand
	 * @2016年1月18日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	@RequestMapping("updateBrand.do")
	@ResponseBody
	public Object updateBrand(Brand brand){
		try {
			brand.setUpdateTime(new Date());
			brandService.updateBrandAndBranding(brand);
			return ResponseUtils.sendMsg(true,"更新成功！");
		} catch (Exception e) {
			logger.error("更新品牌时出错了！",e);
			return ResponseUtils.sendMsg(false, "更新失败！");
		}
	}
	/**
	 * 更新品牌商
	 * @Title
	 * @Description: TODO 
	 * @param @param brand
	 * @2016年1月18日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	@RequestMapping("updateBranding.do")
	@ResponseBody
	public Object updateBranding(Brand brand){
		try {
			brand.setUpdateTime(new Date());
			brandService.updateBrandAndBranding(brand);
			return ResponseUtils.sendMsg(true,"更新成功！");
		} catch (Exception e) {
			logger.error("更新品牌商时出错了！",e);
			return ResponseUtils.sendMsg(false, "更新失败！");
		}
	}



	

	  /**
	   * 验证品牌和品牌商编号的唯一性
	  * @Title
	  * @Description: TODO 
	  * @param @param brandNo
	  * @param @return
	  * @2016年1月18日     
	  * @author 龙五  longwu@izjjf.cn
	  * @return
	  * @throws
	   */
	@RequestMapping("chickBrandNo.do")
	@ResponseBody
	  public Object chickBrandNo(String brandNo){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandNo", brandNo);
		  try {
				int num = brandService.chickBrandNo(map);
				if(num>0){
					return ResponseUtils.sendMsg(true, "编号已存在！");
				}else{
					return ResponseUtils.sendMsg(false);
				}
			} catch (Exception e) {
				logger.error("验证品牌和品牌商编号的唯一性时出错了！",e);
				return ResponseUtils.sendMsg(true,"出错了！"); 
			}
	  }
	  
//	  /**
//	   * 获取所有的品牌商
//	  * @Title
//	  * @Description: TODO 
//	  * @param @return
//	  * @2016年1月18日     
//	  * @author 龙五  longwu@izjjf.cn
//	  * @return
//	  * @throws
//	   */
//	@RequestMapping("getAllBranding.do")
//	@ResponseBody
//	  public Object getAllBranding(){
//		  try {
//			List<Brand> brands = brandService.getAllBranding();
//			if(brands != null && brands.size()>0){
//				return ResponseUtils.sendMsg(true,brands );
//			}else{
//				return ResponseUtils.sendMsg(false,null );
//			}
//			
//		} catch (Exception e) {
//			logger.error("获取所有的品牌商时出错了！",e);
//			return ResponseUtils.sendMsg(false,"出错了！" );
//		}
//	  }
//	
	/**
	   * 
	  * @Title根据 id获取品牌
	  * @Description: TODO 
	  * @param @param map
	  * @param @return
	  * @2016年1月18日     
	  * @author 龙五  longwu@izjjf.cn
	  * @return
	  * @throws
	   */
	@RequestMapping("getBrandById.do")
	@ResponseBody
	  public Object getBrandById(Integer id){
		  Map<String, Object> map = new HashMap<String, Object>();
		  map.put("id", id);
		  try {
			BrandVo brand = brandService.getBrandById(map);
			return ResponseUtils.sendMsg(true, brand);
		} catch (Exception e) {
			logger.error("根据 id获取品牌商时出错了！",e);
			return ResponseUtils.sendMsg(false, "出错了！");
		}
	  }
	
	 /**
	   * 
	  * @Title根据 id获取品牌商
	  * @Description: TODO 
	  * @param @param map
	  * @param @return
	  * @2016年1月18日     
	  * @author 龙五  longwu@izjjf.cn
	  * @return
	  * @throws
	   */
	@RequestMapping("getBrandingById.do")
	@ResponseBody
	  public Object getBrandingById(Integer id){
		  Map<String, Object> map = new HashMap<String, Object>();
		  map.put("id", id);
		  try {
			Brand brand = brandService.getBrandingById(map);
			return ResponseUtils.sendMsg(true, brand);
		} catch (Exception e) {
			logger.error("根据 id获取品牌商时出错了！",e);
			return ResponseUtils.sendMsg(false, "出错了！");
		}
	  }
	
	
	/**
	 * 删除品牌
	 * @Title
	 * @Description: TODO 
	 * @param @param map
	 * @2016年1月18日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	@RequestMapping("deleteBrand.do")
	@ResponseBody
	public Object deleteBrand(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			//区分删除品牌还是品牌商的参数
			String differentiate = "brand";
			ModelMsg modelMsg = brandService.deleteBrandAndBranding(map,differentiate);
			if(modelMsg != null){
				if(modelMsg.isSuccess()){
					return ResponseUtils.sendMsg(true, modelMsg.getMessage());
				}else{
					return ResponseUtils.sendMsg(false, modelMsg.getMessage());
				}
			}else{
				return ResponseUtils.sendMsg(false, "操作失败！");
			}
			
		} catch (Exception e) {
			logger.error("删除品牌或品牌商时出错了！",e);
			return ResponseUtils.sendMsg(false, "操作失败！");
		}
	}
	
	/**
	 * 删除品牌商
	 * @Title
	 * @Description: TODO 
	 * @param @param map
	 * @2016年1月18日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	@RequestMapping("deleteBranding.do")
	@ResponseBody
	public Object deleteBranding(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			//区分删除品牌还是品牌商的参数
			String differentiate = "branding";
			ModelMsg modelMsg = brandService.deleteBrandAndBranding(map,differentiate);
			if(modelMsg != null){
				if(modelMsg.isSuccess()){
					return ResponseUtils.sendMsg(true, modelMsg.getMessage());
				}else{
					return ResponseUtils.sendMsg(false, modelMsg.getMessage());
				}
			}else{
				return ResponseUtils.sendMsg(false, "操作失败！");
			}
		} catch (Exception e) {
			logger.error("删除品牌或品牌商时出错了！",e);
			return ResponseUtils.sendMsg(false, "操作失败！");
		}
	}
}
