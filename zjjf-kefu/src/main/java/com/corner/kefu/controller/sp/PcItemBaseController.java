package com.corner.kefu.controller.sp;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.ItemBase;
import com.corner.core.beans.ItemCatelog;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.FastDFSUploadUtil;
import com.corner.core.utils.GetPinyin;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.code.MatrixUtil;
import com.corner.kefu.beans.ro.ItemBaseMgRo;
import com.corner.kefu.beans.ro.ItemBaseRo;
import com.corner.kefu.beans.vo.ItemBaseVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.sp.SpItemBaseService;
import com.corner.kefu.service.sp.SpItemCatelogService;


@Controller
@RequestMapping("/customer/itemBases")
public class PcItemBaseController extends KefuBaseWebController {

	@Autowired
	SpItemBaseService itemBaseService;
	
	@Autowired
	SpItemCatelogService itemCatelogService;
	
	
	
	
	/**
	 * 
	* @Title: getPaiChuItemBase 
	* @Description:获取排除商品
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getPaiChuItemBase.do")
	@ResponseBody
	public Object getPaiChuItemBase(HttpServletRequest request) {
		try {
			String level = request.getParameter("level");
			String cateId = request.getParameter("cateId");
			String searchKey = request.getParameter("searchKey");
			if(StringUtil.stringIsNullOrEmpty(level)||StringUtil.stringIsNullOrEmpty(cateId)){
				return ResponseUtils.sendMsg(false, "缺少参数level和cateId!");
			}
			Map<String, Object>map = new HashMap<String,Object>();
			map.put("level", level);
			map.put("cateId", cateId);
			if(!StringUtil.stringIsNullOrEmpty(searchKey)){
				map.put("searchKey", searchKey);
			}
			List<ItemBase> itemBaseList = itemBaseService.getPaiChuItemBase(map);
			return ResponseUtils.sendMsg(true, itemBaseList);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, e.toString());
		}
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @throws UnsupportedEncodingException 
	 * @Title: upload @Author luke luke@mibodoctor.com @Description:
	 *         TODO(上传图片) @param @param request @param @return @param @throws
	 *         IOException 设定文件 @return List<Map<String,String>> 返回类型 @throws
	 */
	@RequestMapping(value = "/upload.do")
	@ResponseBody
	public String upload(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			for (String iterable_element : map.keySet()) {
				MultipartFile uploadFile = map.get(iterable_element);
				String name = FastDFSUploadUtil.saveFile(uploadFile, null);
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put("filename", name);
				list.add(resultMap);
			}
		} catch (Exception e) {
			return "错误";
		}
		model.addAttribute("img", list.get(0));
		return list.get(0).get("filename");
	}
	
	

	/**
	 * 根据条件查出所有基本商品列表
	 * 
	 * @Title: getAllItemBaseByPatam
	 * @Description:
	 * @param @param
	 *            itemBaseRo
	 * @param @return
	 * @return List<ItemBaseVo>
	 * @author 龙五 at 2016年1月5日上午10:57:41
	 * @email longwu@izjjf.cn
	 */
	@RequestMapping("getAllItemBaseByPatam.do")
	public String getAllItemBaseByPatam(ItemBaseRo itemBaseRo, Model model, HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			if(itemBaseRo.getNoAndName() != null && !"".equals(itemBaseRo.getNoAndName())){
				itemBaseRo.setNoAndName(itemBaseRo.getNoAndName().trim());
			}
			model.addAttribute("itemBaseRo", itemBaseRo);
			//所有一级分类
			map =  new HashMap<String, Object>();
			map.put("pid", 0);
			List<ItemCatelog> itemCatelogYiJiList = itemCatelogService.getAllItemCateByPid(map);
			model.addAttribute("itemCatelogYiJiList", itemCatelogYiJiList);
			//根据一级分类获取所有二级分类
			if(itemBaseRo.getYiJiId() != null ){
				map =  new HashMap<String, Object>();
				map.put("pid", itemBaseRo.getYiJiId());
				List<ItemCatelog> itemCatelogErJiList = itemCatelogService.getAllItemCateByPid(map);
				model.addAttribute("itemCatelogErJiList", itemCatelogErJiList);
			}
			Pager<ItemBaseVo> itemBaseList = itemBaseService.getAllItemBaseByPatam(itemBaseRo);
			if (itemBaseList != null) {
				model.addAttribute("itemBaseList", itemBaseList.getList());
				//pageUtil(pageIndex, itemBaseList.getTotalSize(), itemBaseRo.getPageSize(), request, model);
				pageUtil(itemBaseRo.getPageIndex(), itemBaseList.getTotalSize(), itemBaseRo.getPageSize(), request, model);
			}
			return "/goods/index";
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}

	}
	
	@RequestMapping("/returnAddOrEditPage.do")
	public String returnAddOrEditPage(HttpServletRequest request,Model model){
		Map<String, Object> map = null;
		//图片服务器的地址
		model.addAttribute("fileServiceName", MatrixUtil.fastfdspreurl);
		//所有一级分类
		map =  new HashMap<String, Object>();
		map.put("pid", 0);
		List<ItemCatelog> itemCatelogYiJiList = itemCatelogService.getAllItemCateByPid(map);
		model.addAttribute("itemCatelogYiJiList", itemCatelogYiJiList);
		String requestPageStr = request.getParameter("requestPageStr");
		if(requestPageStr == null && "".equals(requestPageStr)){
			return "";
		}else if(requestPageStr.equals("add")){
			return "/goods/add";
		}else if(requestPageStr.equals("edit")){
			String pageIndex = request.getParameter("pageIndex");
			String id1 = request.getParameter("id");
			Integer id = (id1!=null && !"".equals(id1))?Integer.parseInt(id1):null;
			//回写的基本商品
			ItemBaseVo itemBase = itemBaseService.getItemBaseById(id);
			//根据一级分类获取所有二级分类
			map = new HashMap<String, Object>();
			map.put("pid", itemBase.getYiJiId());
			List<ItemCatelog> itemCatelogErJiList = itemCatelogService.getAllItemCateByPid(map);
			model.addAttribute("itemCatelogErJiList", itemCatelogErJiList);
			if(itemBase!=null){
				//回写的商品物流规格
				map = new HashMap<String, Object>();
				map.put("id", id);
				List<ItemBaseVo> itemBaseList = itemBaseService.getLogisticsSpecificationsById(map);
				model.addAttribute("itemBaseList", itemBaseList);
			}
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("id", id);
			model.addAttribute("itemBase", itemBase);
			return "/goods/edit";
		}else{
			return null;
		}
	}
	
	/**
	 * 添加基本商品
	* @Title
	* @Description: TODO 
	* @param @param itemBaseRo
	* @2016年1月6日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/addOneItemBase.do")
	public Object addOneItemBase(ItemBaseRo itemBaseRo,HttpServletRequest request,Model model){
		String tgId = StringUtil.getUUID();
		if(itemBaseRo.getMdseId()==null ||"".equals(itemBaseRo.getMdseId())
				||itemBaseRo.getName()==null||"".equals(itemBaseRo.getName())){
			return ResponseUtils.sendMsg(false, "参数有误！");
		}
		itemBaseRo.setCateid(itemBaseRo.getErJiId());
		itemBaseRo.setAddtime(new Date());
		itemBaseRo.setUpdatetime(new Date());
		itemBaseRo.setTgId(tgId);
		
		if(GetPinyin.getPinYinHeadChar(itemBaseRo.getName()).length()>=32){
			itemBaseRo.setShortNm(GetPinyin.getPinYinHeadChar(itemBaseRo.getName()).substring(0, 32));
		}else {
			itemBaseRo.setShortNm(GetPinyin.getPinYinHeadChar(itemBaseRo.getName()));
		}
		
		if(GetPinyin.getPingYin(itemBaseRo.getName()).length()>=32){
			itemBaseRo.setQuanNm(GetPinyin.getPingYin(itemBaseRo.getName()).substring(0, 32));
		}else{
			itemBaseRo.setQuanNm(GetPinyin.getPingYin(itemBaseRo.getName()));
		}
		
		try {
			itemBaseRo.setUpId(0);
			itemBaseService.addOneItemBase(itemBaseRo);
			int id = itemBaseRo.getId();
			String[] xmdseId = request.getParameterValues("xmdseId");
			String[] xpkgNum = request.getParameterValues("xpkgNum");
			String[] xpkg = request.getParameterValues("xpkg");
			String[] xguige = request.getParameterValues("xguige");
			String str = itemBaseRo.getMdseId();
			
			if(xpkgNum!=null && xpkgNum.length>0 &&(!"".equals(xpkgNum[0].trim()))){
				for (int i = 0; i < xmdseId.length; i++) {
					if(xmdseId[i]!=null&&!"".equals(xmdseId[i])){
						itemBaseRo.setMdseId(xmdseId[i]);
					}else{
						itemBaseRo.setMdseId(str+xpkgNum[i]);
					}
					itemBaseRo.setPkgNum(xpkgNum[i]!=null&&!"".equals(xpkgNum[i])?Integer.parseInt(xpkgNum[i]):null);
					itemBaseRo.setPkg(xpkg[i]);
					itemBaseRo.setSpec(xguige[i]);
					itemBaseRo.setUpId(id);
					itemBaseRo.setId(null);
					itemBaseService.addOneItemBase(itemBaseRo);
				}
			}
			return ResponseUtils.sendMsg(true, "添加成功！");
		} catch (Exception e) {
			logger.error("添加商品时出现异常",e);
			return ResponseUtils.sendMsg(false, "添加失败！");
		}
	}
	
	/**
	 * 确定商品条形码唯一
	* @Title
	* @Description: TODO 
	* @param @param mdseId
	* @param @return
	* @2016年1月6日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/okUniqueness.do")
	public Object okUniqueness(String mdseId){
		try {
			if(mdseId==null ||"".equals(mdseId)){
				return ResponseUtils.sendMsg(false);
			}
			int num =itemBaseService.okUniqueness(mdseId);
			if(num>0){
				return ResponseUtils.sendMsg(true, "此条形码已存在！");
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("检查条形码时出现异常",e);
			return ResponseUtils.sendMsg(false);		
		}
	}
	
	/**
	 * 更新商品和物流规格
	* @Title
	* @Description: TODO 
	* @param @param itemBaseRo
	* @2016年1月12日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("updateItemBaseOneById.do")
	@ResponseBody
	public Object updateItemBaseOneById(HttpServletRequest request,ItemBaseRo itemBaseRo,Integer[] xidA){
		if(itemBaseRo.getMdseId()==null ||"".equals(itemBaseRo.getMdseId())
				||itemBaseRo.getName()==null||"".equals(itemBaseRo.getName())){
			return ResponseUtils.sendMsg(false, "参数有误！");
		}
		if(GetPinyin.getPinYinHeadChar(itemBaseRo.getName()).length()>=32){
			itemBaseRo.setShortNm(GetPinyin.getPinYinHeadChar(itemBaseRo.getName()).substring(0, 32));
		}else {
			itemBaseRo.setShortNm(GetPinyin.getPinYinHeadChar(itemBaseRo.getName()));
		}
		if(GetPinyin.getPingYin(itemBaseRo.getName()).length()>=32){
			itemBaseRo.setQuanNm(GetPinyin.getPingYin(itemBaseRo.getName()).substring(0, 32));
		}else{
			itemBaseRo.setQuanNm(GetPinyin.getPingYin(itemBaseRo.getName()));
		}
		try {
			CustomerService customerService = this.getCurrentUser(CustomerService.class, request);
			if(customerService != null){
				itemBaseRo.setCheckUser(customerService.getUserName());
			}
			itemBaseRo.setCateid(itemBaseRo.getErJiId());
			itemBaseRo.setUpdatetime(new Date());
			itemBaseRo.setCheckTime(new Date());
			itemBaseService.updateItemBaseOneById(itemBaseRo);
			//添加或修改物流规格
			int id = itemBaseRo.getId();
			String[] xid = request.getParameterValues("xid");
			String[] xmdseId = request.getParameterValues("xmdseId");
			String[] xpkgNum = request.getParameterValues("xpkgNum");
			String[] xpkg = request.getParameterValues("xpkg");
			String[] xguige = request.getParameterValues("xguige");
			String str = itemBaseRo.getMdseId();
			if(xpkgNum != null && xpkgNum.length>0 &&(!"".equals(xpkgNum[0].trim()))){
				for (int i = 0; i < xmdseId.length; i++) {
					if(xid[i]!=null && !"".equals(xid[i])){
						Map<String, Object> map = new HashMap<String,Object>();
						map.put("id", xid[i]);
						int num = itemBaseService.getCountItemBaseById(map);
						if(num>0){
							itemBaseRo.setId(Integer.parseInt(xid[i]));
							itemBaseRo.setMdseId((xmdseId[i]==null||"".equals(xmdseId[i]))?itemBaseRo.getMdseId()+xpkgNum[i]:xmdseId[i]);
							itemBaseRo.setPkg(xpkg[i]);
							itemBaseRo.setPkgNum(xpkgNum[i]!=null&&!"".equals(xpkgNum[i])?Integer.parseInt(xpkgNum[i]):null);
							itemBaseRo.setSpec(xguige[i]);
							itemBaseRo.setUpdatetime(new Date());
							itemBaseRo.setCheckStatus(null);
							itemBaseRo.setCheckTime(null);
							itemBaseRo.setCheckUser(null);
							itemBaseService.updateItemBaseOneById(itemBaseRo);
						}else{
							logger.error("该物流规格在数据库不存在，这条数据作废！");
							continue;
						}
					}else{
						itemBaseRo.setId(null);
						itemBaseRo.setMdseId((xmdseId[i]==null||"".equals(xmdseId[i]))?str+xpkgNum[i]:xmdseId[i]);
						itemBaseRo.setPkg(xpkg[i]);
						itemBaseRo.setPkgNum(xpkgNum[i]!=null&&!"".equals(xpkgNum[i])?Integer.parseInt(xpkgNum[i]):null);
						itemBaseRo.setSpec(xguige[i]);
						itemBaseRo.setAddtime(new Date());
						itemBaseRo.setUpdatetime(new Date());
						itemBaseRo.setUpId(id);
						itemBaseService.addOneItemBase(itemBaseRo);
					}
				}
			}
			return ResponseUtils.sendMsg(true, "修改成功！");
		} catch (NumberFormatException e) {
			logger.error("更新商品和物流规格时出错了",e);
			return ResponseUtils.sendMsg(true, "修改失败！");
			
		}
	}
	
	@RequestMapping("deleteItem.do")
	@ResponseBody
	public Object deleteItem(Integer id){
		try {
			itemBaseService.deleteItemBase(id);
			return ResponseUtils.sendMsg(true, "删除成功！");
		} catch (Exception e) {
			logger.error("删除时出错了！");
			return ResponseUtils.sendMsg(false, "删除失败！");
		}
	}
	
	
	/**
	 * 根据di删除或恢复物流规格
	* @Title
	* @Description: TODO 
	* @param @param map
	* @2016年1月12日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("deleteLogisticsById.do")
	@ResponseBody
	public Object deleteLogisticsById(Integer id,boolean isDelete){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("isDelete", isDelete);
			itemBaseService.deleteLogisticsById(map);
			return ResponseUtils.sendMsg(true);
		} catch (Exception e) {
			logger.error("删除物流规格时出错了！",e);
			return ResponseUtils.sendMsg(false);
		}
	}
	
	
	/**
	 * 
	* @Title: getPagingItemBaseList 
	* @Description: 分页查询基本商品库所有商品
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getPagingItemBaseList.do")
	@ResponseBody
	public Object getPagingItemBaseList(HttpServletRequest request,ItemBaseMgRo itemBaseMgRo) {
		try {
			Pager<ItemBase> pager = itemBaseService.getPagingItemBaseList(itemBaseMgRo);
			pager.setFlag(true);
			return pager;
		} catch (Exception e) {
			logger.error("{}",e);
			return null;
		}
	}
	
	/**
	 * 
	* @Title: getPagingItemBaseList 
	* @Description: 分页查询基本商品库所有商品
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/updateUpperLowerRelationshop.do")
	@ResponseBody
	public Object updateUpperLowerRelationshop(HttpServletRequest request,Integer id,String mdseId) {
		try {
			itemBaseService.updateUpperLowerRelationshop(id,mdseId);
			return ResponseUtils.sendMsg(true, "设置成功！");
		} catch (Exception e) {
			logger.error("{}",e);
			if(e.getMessage().equals("条形码不存在")){
				return ResponseUtils.sendMsg(false, e.getMessage());
			}else{
				return ResponseUtils.sendMsg(true, "设置失败！");
			}
		}
	}
	
}
