package com.corner.kefu.controller.sp;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.Supplier;
import com.corner.core.beans.User;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.safe.MD5;
import com.corner.kefu.beans.ro.sp.SupplierRo;
import com.corner.kefu.beans.vo.RegionVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;
import com.corner.kefu.config.SCMSConstants;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.PublicService;
import com.corner.kefu.service.sp.SpFinWalletService;
import com.corner.kefu.service.sp.SpItemBaseService;
import com.corner.kefu.service.sp.SpRegionService;
import com.corner.kefu.service.sp.SpSupplierService;
import com.corner.kefu.service.sp.SpUserService;

/**
 * 供应商控制器
 * @author Administrator2015年6月15日
 * @Email  823882651@qq.com
 * @Desc
 */
@RequestMapping(value = "/customer/supplier")
@Controller
public class PcSupplierController extends KefuBaseWebController {
	@Autowired
	SpSupplierService supplierService;
	@Autowired
	SpItemBaseService itemBaseService;
	@Autowired
	SpRegionService regionService;
	@Autowired
	PublicService publicService;
	@Autowired
	SpFinWalletService spFinWalletService;
	
	@Autowired
	SpUserService spUserService;
	
	
	
	
	@RequestMapping("/getTreeSupplierList.do")
	@ResponseBody
	public Object getTreeSupplierList(HttpServletRequest request) {
		List<RegionVo> regionVos = supplierService.getTreeSupplierList();
		return ResponseUtils.sendMsg(true,regionVos);
	}
	
	
	/**
	 * 供货商页面
	 * 
	 * @author Dick 2015年6月4日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @return
	 */
	@RequestMapping(value = "/supplierPage.do")
	public String supplierPage(HttpServletRequest request, Model model, SupplierRo supplierRo) {
		//供应商列表
		if(!StringUtil.stringIsNullOrEmpty(supplierRo.getMobile())){
			supplierRo.setMobile(supplierRo.getMobile().trim());
		}
		Pager<SupplierVo> list = supplierService.getSuppliers(supplierRo);
		model.addAttribute("suppliers", list.getList());
		model.addAttribute("supplierRo", supplierRo);
		//列表数
		this.pageUtil(supplierRo.getPageIndex(), list.getTotalSize(), supplierRo.getPageSize(), request, model);
		return SCMSConstants.SUPPLIER;
	}

	/**
	 * 更新供货商信息页面	TODO
	 * 
	 * @author Dick 2015年6月4日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param supplier
	 * @return
	 */
	@RequestMapping(value = "/updateSupplierPage.do")
	public String updatePage(SupplierRo supplier, HttpServletRequest request, Model model) {
		if (StringUtil.stringIsNullOrEmpty(supplier.getId())) {
			return error("缺少必要参数", model, request);
		}
		//供应商集合
		List<SupplierVo> suppliers = supplierService.getSuppliers(supplier).getList();
		if (suppliers != null && suppliers.size() == 1) {
			/**新增部分	by	孟星魂	2016年3月25日18:11:14**/
			request.setAttribute("regions", publicService.findRegionByPId("1"));//获取省信息
			if(suppliers.get(0).getProvince() != null){
				request.setAttribute("citys", publicService.findRegionByPId(suppliers.get(0).getProvince().toString()));	//获取省信息
				request.setAttribute("countys", publicService.findRegionByPId(suppliers.get(0).getCity().toString()));	//获取省信息
			}
			model.addAttribute("action", 2);
			model.addAttribute("supplierDetail", suppliers.get(0));
			return SCMSConstants.SUPPLIER_EDIT;
		}
		return "PcKdianbao/error";
	}

	/**
	 * 添加供货商页面	TODO
	 * 
	 * @author Dick 2015年6月5日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addSupplierPage.do")
	public String addSupplierPage(Model model, HttpServletRequest request) {
		model.addAttribute("action", 1);
		/**新增部分	by	孟星魂	2016年3月25日18:11:14**/
		request.setAttribute("regions", publicService.findRegionByPId("1"));	//获取省信息
		return SCMSConstants.SUPPLIER_EDIT;
	}

	/**
	 * 添加供货商	TODO
	 * 
	 * @author Dick 2015年6月5日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param supplier
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	@ResponseBody
	public Object addSupplier(Supplier supplier) {
		if (StringUtil.stringIsNullOrEmpty(supplier.getPassword(), supplier.getMobile())) {
			return ResponseUtils.sendMsg(false, "用户名或密码不能为空");
		}else if(supplier.getAreaId()== null || "".equals(supplier.getAreaId()) || supplier.getAreaId() == -1){
			return ResponseUtils.sendMsg(false, "请选择区域！");
		}
		SupplierRo su = new SupplierRo();
		su.setMobile(supplier.getMobile());
		//判断供应商是否存在
		Integer res = supplierService.getSuppliers(su).getTotalSize();
		if (res > 0) {
			return ResponseUtils.sendMsg(false, "此供货商已存在");
		}
		//添加供应商
		ModelMsg msg = supplierService.addSupplier(supplier);
		if(msg.isSuccess()){
			return ResponseUtils.sendMsg(true);
		}else {
			return ResponseUtils.sendMsg(false, msg.getMessage());
		}
	}

	/**
	 * 更新供货商	TODO
	 * 
	 * @author Dick 2015年6月5日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param supplier
	 * @return
	 */
	@RequestMapping(value = "/updateSupplier.do")
	@ResponseBody
	public Object updateSupplier(Supplier supplier) {
		if (StringUtil.stringIsNullOrEmpty(supplier.getId())) {
			return ResponseUtils.sendMsg(false, "id不能为空");
		}else if(supplier.getAreaId()== null || "".equals(supplier.getAreaId()) || supplier.getAreaId() == -1){
			return ResponseUtils.sendMsg(false, "请选择区域！");
		}
		if (StringUtil.stringIsNullOrEmpty(supplier.getPassword())) {
			supplier.setPassword(null);
		}else{
			supplier.setPassword(MD5.StringToMd5(supplier.getPassword()));
		}
		supplier.setUpdateTime(new Date());
		SupplierRo su = new SupplierRo();
		su.setMobile(supplier.getMobile());
		
		//根据批发商id查询User数据
		User user = spUserService.getUserBySupplierId(supplier.getId());
		if(user==null){
			return ResponseUtils.sendMsg(false, "没有对应的User数据!");
		}
		if(!supplier.getMobile().equals(user.getMobile())){
			List<User> userList = spUserService.getUserByMobile(supplier.getMobile());
			if(userList!=null&&userList.size()>1){
				return ResponseUtils.sendMsg(false, "该号码已经存在!");
			}else if (userList.size()==1) {
				if(!supplier.getId().equals(userList.get(0).getSupplierId())){
					return ResponseUtils.sendMsg(false, "该号码已经存在!");
				}
			}	
		}
        user.setMobile(supplier.getMobile());
        if(!StringUtils.isEmpty(supplier.getPassword())){
        	user.setPassword(supplier.getPassword());
        }else {
        	user.setPassword(null);
		}
        user.setUpdateTime(new Date());
        supplierService.updateUserAndSupplier(user,supplier);
		return ResponseUtils.sendMsg(true, "修改成功");
		/*//更新操作
		Boolean flag = supplierService.update(supplier);
		if (flag) {
		} else {
			return ResponseUtils.sendMsg(false, "修改失败");
		}*/
	}

	/**
	 * 判断供货商是否存在	TODO
	 * 
	 * @author Dick 2015年6月4日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param supplierRo
	 * @return
	 */
	@RequestMapping(value = "/isExit.do")
	@ResponseBody
	public Object isExit(SupplierRo supplierRo) {
		Integer res = supplierService.getSuppliers(supplierRo).getTotalSize();
		if (res > 0) {
			return ResponseUtils.sendMsg(false, "供应商已存在");
		}
		return ResponseUtils.sendMsg(true, "");
	}
}
