package com.corner.scms.controller.sp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ScmsStore;
import com.corner.core.beans.Store;
import com.corner.core.beans.StoreGroup;
import com.corner.core.beans.StoreGroupMember;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.ScmsStoreCondition;
import com.corner.scms.beans.vo.ScmsStoreVo;
import com.corner.scms.beans.vo.StoreGroupVo;
import com.corner.scms.beans.vo.StoreVo;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sp.ScStoreService;
import com.corner.scms.service.sp.StoreGroupMemberService;
import com.corner.scms.service.sp.StoreGruopService;

/**
 * ClassName: ScStoreController
 * 
 * @Description: 客户管理 控制器
 * @author 海灵子
 * @date 2015年12月6日
 */

@Controller
@RequestMapping(value = "/scms/store")
public class ScStoreController extends ScmsBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(ScStoreController.class);

	@Autowired
	private ScStoreService scStoreService;

	@Autowired
	private StoreGroupMemberService storeGroupMemberService;

	@Autowired
	private StoreGruopService storeGruopService;

	// 客户分组更新
	@RequestMapping(value = "/updategroupdown.do")
	@ResponseBody
	public Object updateGroupDown(HttpServletRequest request, Model model, StoreGroupMember condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		String id = user.getId();
		condition.setSupplierId(id);
		condition.setType(2);
		StoreGroupMember mem = this.storeGroupMemberService.findMember(condition);// 原来的分组信息
		if (mem == null && condition.getStoreGroupID().trim().equals("")) {// 原来未分组
																			// 现在也未分组
			return ResponseUtils.sendMsg(true, "修改成功");
		} else if (mem == null && !condition.getStoreGroupID().trim().equals("")) {// 原来未分组
																					// 现在需要分组
			this.storeGroupMemberService.insertObject(condition);// 维护storeGroupMember关系
			this.storeGruopService.updateNumber(condition.getStoreGroupID());// 更改组的number
																				// +1
			return ResponseUtils.sendMsg(true, "修改成功");
		} else if (mem != null && condition.getStoreGroupID().trim().equals("")) {// 原来
																					// 分组
																					// 现在不分组
			this.storeGroupMemberService.deleteObject(mem.getId());
			this.storeGruopService.updateNumberMis(mem.getStoreGroupID());// 更改组的number
																			// -1
			return ResponseUtils.sendMsg(true, "修改成功");
		} else { // 原来分组 现在 分组
			if (mem != null && mem.getStoreGroupID().equals(condition.getStoreGroupID().trim())) {
				return ResponseUtils.sendMsg(true, "修改成功");
			} else {
				this.storeGruopService.updateNumber(condition.getStoreGroupID());
				this.storeGruopService.updateNumberMis(mem.getStoreGroupID());
				mem.setStoreGroupID(condition.getStoreGroupID());
				this.storeGroupMemberService.updateObject(mem);
				return ResponseUtils.sendMsg(true, "修改成功");
			}

		}

	}

	@RequestMapping(value = "/scmshome.do")
	// 线上客户首页
	public String scmshome(HttpServletRequest request, Model model, StoreGroupVo condition) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		String id = user.getId();
		condition.setSupplierId(id);
		List<StoreGroup> gruops = this.storeGruopService.findAllGroups(condition);
		model.addAttribute("gruops", gruops);
		return "customer/index";
	}

	@RequestMapping(value = "/scmshomedown.do")
	// 线下客户首页
	public String scmshomedown(HttpServletRequest request, Model model, ScmsStoreCondition condition) {
		// Pager<ScmsStoreVo>
		// stores=this.scStoreService.getAllStoreByCondition(condition);
		// model.addAttribute("result", stores);
		// model.addAttribute("nameOrTelphone", condition.getNameOrTelphone());
		return "customer/index-xianxia";
	}

	@RequestMapping(value = "/delete.do")
	@ResponseBody
	public Object deleteById(Integer id) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		String supplierId = user.getId();
		int result = this.scStoreService.deleteById(id, supplierId);
		if (result == 1) {

			return ResponseUtils.sendMsg(true, "修改成功");

		} else {
			return ResponseUtils.sendMsg(false, "修改失败");
		}
	}

	@RequestMapping(value = "/list.do")
	// 线下客户查询
	@ResponseBody
	public Object list(HttpServletRequest request, Model model, ScmsStoreCondition condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		String id = user.getId();
		condition.setSpId(id);
		condition.setPageIndex(pageIndex + 1);
		condition.setSortName("addTime");
		condition.setSortOrder("desc");
		Pager<ScmsStoreVo> stores = this.scStoreService.getAllStoreByCondition(condition);
		return ResponseUtils.sendPagination(stores);
	}

	@RequestMapping(value = "/listdown.do")
	// 线上客户查询
	@ResponseBody
	public Object listdown(HttpServletRequest request, Model model, ScmsStoreCondition condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		String id = user.getId();
		condition.setSpId(id);
		condition.setPageIndex(pageIndex + 1);
		condition.setSortName("addTime");
		condition.setSortOrder("desc");
		Pager<StoreVo> stores = this.scStoreService.getAllDownStoreByCondition(condition);
		return ResponseUtils.sendPagination(stores);
	}

	@RequestMapping(value = "/find.do")
	@ResponseBody
	public Object findById(HttpServletRequest request, String id) {
		if (id == null || id.trim().equals("")) {
			return ResponseUtils.sendMsg(false, "缺少必要参数");
		} else {
			ScmsStoreVo scmsStore = this.scStoreService.findById(id);
			if (scmsStore == null) {
				return ResponseUtils.sendMsg(false, "参数错误");
			} else {
				return ResponseUtils.sendMsg(true, scmsStore);
			}
		}
	}

	@RequestMapping(value = "/update.do")
	@ResponseBody
	public Object updateScmsStore(HttpServletRequest request, ScmsStore scmsStore) {

		int result = this.scStoreService.updateScmsStore(scmsStore);
		if (result == 1) {
			logger.info("跟新客户id 为 {}的信息", scmsStore.getId());
			return ResponseUtils.sendMsg(true, "修改成功");

		} else {
			return ResponseUtils.sendMsg(false, "修改失败");
		}
	}

	@RequestMapping(value = "/add.do")
	@ResponseBody
	public Object addScmsStore(HttpServletRequest request, ScmsStore scmsStore) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		String id = user.getId();
		StoreGroupVo g = new StoreGroupVo();
		g.setSupplierId(id);
		this.storeGruopService.updateXianXiagroup(g);
		if (scmsStore == null) {
			return ResponseUtils.sendMsg(false, "缺少必要参数");
		} else {
			scmsStore.setSpId(id);
			int result = this.scStoreService.addScmsStore(scmsStore);
			if (result == 1) {
				return ResponseUtils.sendMsg(true, "添加成功");
			} else {
				return ResponseUtils.sendMsg(false, "添加失败");
			}
		}

	}

}
