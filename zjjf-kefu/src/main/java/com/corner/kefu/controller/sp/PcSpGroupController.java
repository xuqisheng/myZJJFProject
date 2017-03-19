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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.Region;
import com.corner.core.beans.SpGroup;
import com.corner.core.beans.Store;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.sp.SpGroupMgRo;
import com.corner.kefu.beans.ro.sp.SpGroupRo;
import com.corner.kefu.beans.vo.RegionVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;
import com.corner.kefu.config.SCMSConstants;
import com.corner.kefu.config.SystemKeys;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.PublicService;
import com.corner.kefu.service.sp.SpGroupService;
import com.corner.kefu.service.sp.SpRegionService;
import com.corner.kefu.service.sp.SpShoppingCartService;
import com.corner.kefu.service.sp.SpStoreService;
import com.corner.kefu.service.sp.SpSupplierService;
import com.corner.kefu.service.sp.SpUserService;

/**
 * 
 * @ClassName: PcSpGroupController
 * 
 * @Description: 批发商组管理
 * 
 * @author: 杨开泰
 * 
 * @date: 2015年10月10日 上午10:48:45
 */
@RequestMapping(value = "/Customer/SpGroup")
@Controller
public class PcSpGroupController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(PcSpGroupController.class);
	@Autowired
	SpGroupService spGroupService;

	@Autowired
	SpSupplierService supplierService;

	@Autowired
	SpStoreService storeService;

	@Autowired
	SpRegionService regionService;

	@Autowired
	SpUserService userService;

	@Autowired
	SpShoppingCartService spShoppingCartService;
	@Autowired
	PublicService publicService;

	/**
	 * 
	* @Title: getAcTiveSpGroupList 
	* @Description:获取活动管理页面定格集合
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getAcTiveSpGroupList.do")
	@ResponseBody
	public Object getAcTiveSpGroupList(HttpServletRequest request, RegionVo regionVo) {
		try {
			List<RegionVo> list = spGroupService.getAcTiveSpGroupList(regionVo);
			return ResponseUtils.sendMsg(true, list);
		} catch (Exception e) {
			logger.error("{}", e);
			return ResponseUtils.sendMsg(false, "查询失败");
		}
	}

	/**
	 * 
	* @Title: updateSpGroup 
	* @Description:新建/修改定格
	* @param @param request
	* @param @param model
	* @param @param spGroup
	* @param @return
	* @param @throws Exception
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/updateSpGroup.do")
	@ResponseBody
	public Object updateSpGroup(HttpServletRequest request, Model model, SpGroup spGroup) throws Exception {

		if (spGroup.getId() != null) {
			// 查询定格
			SpGroup sp = spGroupService.getSpGroupById(spGroup.getId());
			// 如果是一样的对象 则不作修改
			if (sp.getName().equals(spGroup.getName()) && sp.getAreaId().equals(spGroup.getAreaId())
				&&sp.getAppCfgId().intValue()==spGroup.getAppCfgId().intValue()) {
				return ResponseUtils.sendMsg(true, "修改定格成功");
			}
		}
		if (spGroup.getCityId() == null || spGroup.getAreaId() == null || spGroup.getProvinceId() == null) {
			return ResponseUtils.sendMsg(false, "请选择区域信息!");
		} /*else {
			// 判断是否同名
			spGroup.setStatus((byte) 1);
			Map<String, Object> map = new HashMap<>();
			map.put("pageIndex", 0);
			map.put("pageSize", 0);// 不分页
			map.put("spGroup", spGroup);
			List<SpGroup> resultList = spGroupService.isExist(map);
			if (resultList.size() > 0) {
				return ResponseUtils.sendMsg(false, "已经存在相同定格分组");
			}
		}*/
		if(spGroup.getAppCfgId() == null || spGroup.getAppCfgId() == -1){
			spGroup.setAppCfgId(1);
		}
		if (spGroup.getId() != null) {
			spGroupService.updateSpGroup(spGroup);
		} else {
			spGroupService.insertSelective(spGroup);
		}
		return ResponseUtils.sendMsg(true, "修改定格成功");
	}

	/**
	 * 
	* @Title: toSupplierList 
	* @Description:查询定格下有哪些批发商 
	* @param @param request
	* @param @param model
	* @param @param spGroupRo
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toSupplierList.do")
	public String toSupplierList(HttpServletRequest request, Model model, SpGroupRo spGroupRo) throws Exception {
		model.addAttribute("status", 1);// 用于前台页面样式显示
		if (!StringUtil.stringIsNullOrEmpty(spGroupRo.getName())) {
			model.addAttribute("searchName", spGroupRo.getName());
		}
		spGroupRo.setIsIn((byte) 0);
		SpGroupVo spGroupVo = spGroupService.getSpGoupVoById(spGroupRo);
		Pager<Supplier> pager = spGroupService.getSpGroupAndSupplierList(spGroupRo);
		spGroupVo.setSupplierList(pager.getList());
		model.addAttribute("spGroupVo", spGroupVo);
		model.addAttribute("spGroupList", pager.getList());
		// 分装分页
		this.pageUtil(spGroupRo.getPageIndex(), pager.getTotalSize(), spGroupRo.getPageSize(), request, model);
		return SCMSConstants.SUPPLIER_GROUP_DETAIL;
	}

	/**
	 * 
	* @Title: getSupplierOutList 
	* @Description:查询不属于某个定格的批发商列表 
	* @param @param request
	* @param @param model
	* @param @param spGroupRo
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getSupplierOutList.do")
	public String getSupplierOutList(HttpServletRequest request, Model model, SpGroupRo spGroupRo) throws Exception {
		model.addAttribute("status", 2);// 用于前台页面样式显示
		if (!StringUtil.stringIsNullOrEmpty(spGroupRo.getName())) {
			model.addAttribute("searchName", spGroupRo.getName());
		}
		spGroupRo.setIsIn((byte) 1);
		SpGroupVo spGroupVo = spGroupService.getSpGoupVoById(spGroupRo);
		Pager<Supplier> pager = spGroupService.getSpGroupAndSupplierList(spGroupRo);
		spGroupVo.setSupplierList(pager.getList());
		model.addAttribute("spGroupVo", spGroupVo);
		model.addAttribute("spGroupList", pager.getList());
		// 分装分页
		this.pageUtil(spGroupRo.getPageIndex(), pager.getTotalSize(), spGroupRo.getPageSize(), request, model);
		return SCMSConstants.SUPPLIER_GROUP_DETAIL;
	}

	/**
	 * 
	* @Title: getSpGropuWithList 
	* @Description:获取定格列表,同时查询定格下包含的批发商数和店铺数
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getSpGropuWithList.do")
	@ResponseBody
	public Object getSpGropuWithList(SpGroupMgRo spGroupMgRo) {
		try {
			Pager<SpGroupVo> pager = spGroupService.getSpGropuWithList(spGroupMgRo);
			pager.setFlag(true);
			return pager;
		} catch (Exception e) {
			logger.error("{}", e);
			return null;
		}
	}

	/**
	 * 
	* @Title: toDG 
	* @Description:跳转到定格列表首页
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toDG.do")
	public String toDG() {
		return SCMSConstants.SUPPLIER_GROUP_INDEX;
	}

	/**
	 * 上架定格
	 * 
	 * @return
	 */
	@RequestMapping("/upSpGroup.do")
	@ResponseBody
	public Object upSpGroup(HttpServletRequest request, Model model, SpGroup spGroup) {
		try {
			// 上架定格,和定格关联的商品也全部上架
			spGroup.setStatus(Byte.valueOf("1"));
			spGroup.setIsDelete(false);
			spGroupService.updateSpGroup(spGroup);
			return ResponseUtils.sendMsg(true);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, e.toString());
		}
	}

	@RequestMapping("/getRegion.do")
	@ResponseBody
	public Object getRegion(HttpServletRequest request) {
		try {
			String pid = request.getParameter("pid");
			if (StringUtil.stringIsNullOrEmpty(pid)) {
				return ResponseUtils.sendMsg(false, "参数有误!");
			}
			List<Region> list = publicService.findRegionByPId(pid);
			return ResponseUtils.sendMsg(true, list);
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseUtils.sendMsg(false, "查询区域出错!");
		}
	}

	/**
	 * 
	* @Title: toStoreList 
	* @Description:查询定格下的店铺集合 
	* @param @param request
	* @param @param model
	* @param @param spGroup
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toStoreList.do")
	public String toStoreList(HttpServletRequest request, Model model, SpGroupRo spGroupRo) throws Exception {
		model.addAttribute("status", 1);// 用于前台页面样式显示
		if (!StringUtil.stringIsNullOrEmpty(spGroupRo.getName())) {
			model.addAttribute("searchName", spGroupRo.getName());
		}
		spGroupRo.setIsIn((byte) 0);
		SpGroupVo spGroupVo = spGroupService.getSpGoupVoById(spGroupRo);
		Pager<Store> pager = spGroupService.getSpGroupAndStoreList(spGroupRo);
		spGroupVo.setStoreList(pager.getList());
		model.addAttribute("spGroupVo", spGroupVo);
		// 分装分页
		this.pageUtil(spGroupRo.getPageIndex(), pager.getTotalSize(), spGroupRo.getPageSize(), request, model);
		return SCMSConstants.SUPPLIER_GROUP_MANAGE_STORE;
	}

	/**
	 * 
	* @Title: getStoreOutList 
	* @Description: 查询不在此定格下的批发商列表
	* @param @param request
	* @param @param model
	* @param @param spGroup
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getStoreOutList.do")
	public String getStoreOutList(HttpServletRequest request, Model model, SpGroupRo spGroupRo) throws Exception {
		model.addAttribute("status", 2);// 用于前台页面样式显示
		if (!StringUtil.stringIsNullOrEmpty(spGroupRo.getName())) {
			model.addAttribute("searchName", spGroupRo.getName());
		}
		spGroupRo.setIsIn((byte) 1);
		SpGroupVo spGroupVo = spGroupService.getSpGoupVoById(spGroupRo);
		Pager<Store> pager = spGroupService.getSpGroupAndStoreList(spGroupRo);
		spGroupVo.setStoreList(pager.getList());
		model.addAttribute("spGroupVo", spGroupVo);
		// 分装分页
		this.pageUtil(spGroupRo.getPageIndex(), pager.getTotalSize(), spGroupRo.getPageSize(), request, model);
		return SCMSConstants.SUPPLIER_GROUP_MANAGE_STORE;
	}

	/**
	 * 定格中批量添加批发商
	 * 
	 * @param request
	 * @param model
	 * @param spGroup
	 * @return
	 */
	@RequestMapping("/batchAddSupplier.do")
	@ResponseBody
	public Object batchAddSupplier(HttpServletRequest request, Model model, SpGroup spGroup) {
		try {
			String chkList = request.getParameter("chkList");
			if (!StringUtil.stringIsNullOrEmpty(chkList)) {
				chkList = chkList.substring(0, chkList.lastIndexOf(","));
				Map<String, Object> map = new HashMap<>();
				map.put("spGroupId", spGroup.getId());
				map.put("chkList", chkList.split(","));
				map.put("remove", false);
				spGroupService.batchAddSupplier(map);
			}
			return ResponseUtils.sendMsg(true, spGroup.getId());
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseUtils.sendMsg(false, "批量添加供应商出错");
		}
	}

	/**
	 * 定格中添加批发商
	 * 
	 * @return
	 */
	@RequestMapping("/addSupplier.do")
	public String addSupplier(HttpServletRequest request, Model model, SpGroup spGroup) {
		try {
			String supplierId = request.getParameter("suId");
			Map<String, Object> map = new HashMap<>();
			map.put("spGroupId", spGroup.getId());
			map.put("supplierId", supplierId);
			map.put("reomve", false);
			spGroupService.addSupplier(map);
			return "redirect:/Customer/SpGroup/getSupplierOutList.do?id=" + spGroup.getId();
		} catch (Exception e) {
			logger.error(e.toString());
			return error("添加批发商出错", model, request);
		}
	}

	/**
	 * 批量删除定格中的供应商
	 * 
	 * @return
	 */
	@RequestMapping("/batchRemoveSupplier.do")
	@ResponseBody
	public Object batchRemoveSupplier(HttpServletRequest request, Model model, SpGroup spGroup) {
		try {
			String chkList = request.getParameter("chkList");
			if (!StringUtil.stringIsNullOrEmpty(chkList)) {
				chkList = chkList.substring(0, chkList.lastIndexOf(","));
				Map<String, Object> map = new HashMap<>();
				map.put("chkList", chkList.split(","));
				map.put("spGroupId", spGroup.getId());
				map.put("remove", true);// 用于标识是否将数据假删除
				spGroupService.batchRemoveSupplier(map);
			}
			return ResponseUtils.sendMsg(true, spGroup.getId());
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseUtils.sendMsg(false, "删除批发商失败");
		}
	}

	/**
	 * 删除定格中的某个供应商
	 * 
	 * @return
	 */
	@RequestMapping("/removeSupplier.do")
	public String removeSupplier(HttpServletRequest request, Model model, SpGroup spGroup) {
		String supplierId = request.getParameter("suId");
		Map<String, Object> map = new HashMap<>();
		map.put("supplierId", supplierId);
		map.put("spGroupId", spGroup.getId());
		map.put("reomve", true);// 用于标识商品是否上下架
		spGroupService.removeSupplier(map);
		return "redirect:/Customer/SpGroup/toSupplierList.do?id=" + spGroup.getId();
	}

	/**
	 * ajax方式 查询定格
	 * 
	 * @param request
	 * @param model
	 * @param spGroup
	 * @return
	 */
	@RequestMapping("/getSpGroupAjax.do")
	@ResponseBody
	public Object getSpGroupAjax(HttpServletRequest request, Model model, Integer id) throws Exception {
		SpGroup spGroup = spGroupService.selectByPrimaryKey(id);
		Map<String, Object> map = new HashMap<>();
		map.put("spGroup", spGroup);
		map.put("regions", publicService.findRegionByPId("1"));
		if (spGroup.getProvinceId() != null && spGroup.getProvinceId() != 0)
			map.put("citys", publicService.findRegionByPId("" + spGroup.getProvinceId()));
		else
			map.put("citys", null);
		if (spGroup.getCityId() != null && spGroup.getCityId() != 0)
			map.put("countys", publicService.findRegionByPId("" + spGroup.getCityId()));
		else
			map.put("countys", null);
		return ResponseUtils.sendMsg(true, map);
	}

	/**
	 * 下架定格
	 * 
	 * @return
	 */
	@RequestMapping("/deleteSpGroup.do")
	@ResponseBody
	public Object deleteSpGroup(HttpServletRequest request, Model model, Integer id) throws Exception {
		try {
			SpGroup spGroup = new SpGroup();
			spGroup.setId(id);
			spGroup.setIsDelete(true);
			spGroup.setStatus(Byte.valueOf("0"));
			spGroupService.updateSpGroup(spGroup);
			return ResponseUtils.sendMsg(true);			
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false,e.toString());
		}
		//return "redirect:/Customer/SpGroup/toDG.do";
	}

	/**
	 * 
	* @Title: removeStore 
	* @Description:删除定格张某个店铺 
	* @param @param request
	* @param @param model
	* @param @param spGroup
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/removeStore.do")
	@ResponseBody
	public Object removeStore(HttpServletRequest request, Model model, SpGroup spGroup) throws Exception {
		Integer stId = Integer.parseInt(request.getParameter("stId"));
		Store store = new Store();
		store.setId(stId);
		ModelMsg modelMsg = storeService.updetaStore(store);
		if(!modelMsg.isSuccess()){
			return ResponseUtils.sendMsg(false, modelMsg.getData(),"1");
		}
		return ResponseUtils.sendMsg(true);
	}

	/**
	 * 定格中添加商铺
	 * 
	 * @param request
	 * @param model
	 * @param spGroup
	 * @return
	 */
	@RequestMapping("/addStore.do")
	@ResponseBody
	public Object addStore(HttpServletRequest request, Model model, Integer stId, Integer id) throws Exception {
		Store store = new Store();
		store.setSpGroupId(id);
		store.setId(stId);
		ModelMsg modelMsg = storeService.updetaStore(store);
		if(!modelMsg.isSuccess()){
			return ResponseUtils.sendMsg(false, modelMsg.getData(),"1");
		}
		return ResponseUtils.sendMsg(true);
	}

	
	/**
	 * 
	* @Title: batchAddStore 
	* @Description:批量添加定格中的商铺
	* @param @param request
	* @param @param model
	* @param @param spGroup
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/batchAddStore.do")
	@ResponseBody
	public Object batchAddStore(HttpServletRequest request, Model model, SpGroup spGroup) {
		try {
			String chkList = request.getParameter("chkList");
			if (!StringUtil.stringIsNullOrEmpty(chkList)) {
				chkList = chkList.substring(0, chkList.lastIndexOf(","));
				Map<String, Object> map = new HashMap<>();
				String[] storeIdArr = chkList.split(",");
				map.put("chkList", storeIdArr);
				map.put("spGroup", spGroup);
				ModelMsg modelMsg = spGroupService.batchAddStore(map);
				if(!modelMsg.isSuccess()){
					return ResponseUtils.sendMsg(false, modelMsg.getData(),"1");
				}
			}
			return ResponseUtils.sendMsg(true, spGroup.getId());
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, "批量添加商铺失败");
		}
	}

	/**
	 * 
	* @Title: batchRemoveStore 
	* @Description:批量删除定格中的店铺 
	* @param @param request
	* @param @param model
	* @param @param spGroup
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/batchRemoveStore.do")
	@ResponseBody
	public Object batchRemoveStore(HttpServletRequest request, Model model, SpGroup spGroup) {
		try {
			String chkList = request.getParameter("chkList");
			if (!StringUtil.stringIsNullOrEmpty(chkList)) {
				chkList = chkList.substring(0, chkList.lastIndexOf(","));
				Map<String, Object> map = new HashMap<>();
				String[] storeIdArr = chkList.split(",");
				map.put("chkList", storeIdArr);
				ModelMsg modelMsg = spGroupService.removeBatchStore(map);
				if(!modelMsg.isSuccess()){
					return ResponseUtils.sendMsg(false, modelMsg.getData(),"1");
				}
			}
			return ResponseUtils.sendMsg(true, spGroup.getId());
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, "批量删除商铺失败");
		}
	}

	/**
	 * 
	* @Title: getVoucherSpGroupList 
	* @Description:优惠劵获取定格列表
	* @param @param flag
	* @param @return
	* @return Object
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getVoucherSpGroupList.do")
	@ResponseBody
	public Object getVoucherSpGroupList(Integer spGroupSelect, SpGroupRo spGroupRo, HttpServletRequest request) {
		try {
			Integer pageIndex = SystemKeys.asynchronousPageIndex;
			Integer pageSize = SystemKeys.asynchronousPageSize;
			String pageIndexStr = request.getParameter("pageIndex");
			if (!StringUtil.stringIsNullOrEmpty(pageIndexStr)) {
				pageIndex = Integer.parseInt(pageIndexStr);
			}
			Map<String, Object> map = new HashMap<>();
			map.put("pageIndex", pageIndex * pageSize);
			map.put("pageSize", pageSize);
			String search = request.getParameter("search");// 为1 表示是在搜索
			if ("1".equals(search)) {
				map.put("flag", request.getParameter("spGroupSelect"));// flag为空:查找所有定格;flag为0:按定格名查找;flag为1:按分类查找定格
			} else {
				map.put("flag", null);
			}
			map.put("spGroup", spGroupRo);
			Pager<SpGroupVo> pager = spGroupService.getVoucherSpGroupList(map);
			pager.setFlag(true);
			return pager;
		} catch (Exception e) {
			logger.error("", e);
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}

	@RequestMapping("getAllSpGroup1.do")
	@ResponseBody
	public Object getAllSpGroup1() {
		try {
			List<SpGroup> spGroupList = spGroupService.getAllSpGroup1();
			if (spGroupList != null && spGroupList.size() > 0) {
				return ResponseUtils.sendMsg(true, spGroupList);
			} else {
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	@RequestMapping("getSupplierIdByGroupId.do")
	@ResponseBody
	public Object getSupplierIdByGroupId(Integer groupId) {
		try {
			List<Supplier> supplierList = spGroupService.getSupplierIdByGroupId(groupId);
			if (supplierList != null && supplierList.size() > 0) {
				return ResponseUtils.sendMsg(true, supplierList);
			} else {
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
	
	/**
	 * 根据区域id获取对应定格
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年4月26日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("getSpGroupByAreaId.do")
	public Object getSpGroupByAreaId(Integer areaId) {
		Map<String, Object> map = new HashMap<>();
		map.put("areaId", areaId);
		try {
			List<SpGroup> spGroupList = spGroupService.getSpGroupByAreaId(map);
			if(spGroupList!=null && spGroupList.size()>0){
				return ResponseUtils.sendMsg(true, spGroupList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseUtils.sendMsg(false);
		}
		
		
	}
}
