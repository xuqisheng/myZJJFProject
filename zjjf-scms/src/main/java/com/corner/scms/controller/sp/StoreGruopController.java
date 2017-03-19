package com.corner.scms.controller.sp;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.SpVoucherActiveMiddle;
import com.corner.core.beans.Store;
import com.corner.core.beans.StoreGroup;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.core.config.PropertieNameConts;
import com.corner.core.utils.PropertiesCacheUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.ScmsStoreCondition;
import com.corner.scms.beans.vo.ScmsStoreVo;
import com.corner.scms.beans.vo.StoreGroupVo;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sp.ScmsStoreMgService;
import com.corner.scms.service.sp.ScmsSupplierMgService;
import com.corner.scms.service.sp.StoreGroupMemberService;
import com.corner.scms.service.sp.StoreGruopService;
import com.corner.scms.service.sp.StoreService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@Controller
@RequestMapping(value = "/scms/storegruop")
public class StoreGruopController extends ScmsBaseWebController {

	@Autowired
	private StoreGruopService storeGruopService; // 分组service

	@Autowired
	private StoreService storeService; // 线上客户service

	@Autowired
	private StoreGroupMemberService storeGroupMemberService;

	private static Logger logger = LoggerFactory.getLogger(StoreGruopController.class);

	// 客户分组首页
	@RequestMapping(value = "/home.do")
	public String scmshome(HttpServletRequest request, Model model, ScmsStoreCondition condition) {

		return "/customer/group-manage";
	}

	// 客户邀请invite
	@RequestMapping(value = "/invite.do")
	public String invite(HttpServletRequest request, Model model, StoreGroupVo condition) {
		// 获取配置的服务器域名
		
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		String requestUrl = this.storeGruopService.findUrl();
		requestUrl += "&fromWho=1&suId=" + user.getId();
		model.addAttribute("condition", requestUrl);
		return "/customer/invite";
	}

	/**
	 * 
	 * @Title: creatQrImage
	 * @Description:需要用微信扫码支付,生成二维码
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception
	 * @return Object 返回类型
	 * @author hailingzi
	 * @throws
	 */
	@RequestMapping("/creatQrImage.do")
	@ResponseBody
	public Object creatQrImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		String requestUrlSql = this.storeGruopService.findUrl();
		String requestUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/scms/storegruop/share.do?picpath=http://www.izjjf.cn/group1/M00/00/FD/cEpChlc5lR2AA_22AAAhr8CzPDc356.png";
		requestUrl += "&fromWho=1&suId=" + user.getId() + "&" + requestUrlSql.split("&")[1];
		System.out.println(requestUrl);
		encodeQrcode(requestUrl, response);
		return "";
	}

	@RequestMapping("/share.do")
	public String share(HttpServletRequest request, Model model, StoreGroupVo condition) {
		/*
		 * Subject subject = SecurityUtils.getSubject(); Supplier user =
		 * (Supplier)
		 * subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		 * condition.setSupplierId(user.getId());
		 * model.addAttribute("condition", condition);
		 */

		return "/customer/share";
	}

	/**
	 * 
	 * @Title: encodeQrcode
	 * @Description:生成二维码图片 不存储 直接以流的形式输出到页面
	 * @param @param content
	 * @param @param response
	 * @param @throws IOException
	 * @return void 返回类型
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void encodeQrcode(String content, HttpServletResponse response) throws IOException {

		if (StringUtils.isBlank(content))
			return;
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
		} catch (WriterException e1) {
			e1.printStackTrace();
		} finally {
		}
	}

	// 客户分组查询
	@RequestMapping(value = "/list.do")
	@ResponseBody
	public Object list(HttpServletRequest request, Model model, StoreGroupVo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		condition.setPageIndex(pageIndex + 1);
		condition.setSortName("date");
		condition.setSortOrder("desc");
		Pager<StoreGroup> stores = this.storeGruopService.updateAndgetAllStoreGroupByCondition(condition);
		return ResponseUtils.sendPagination(stores);
	}

	// 添加分组 时查询所有未添加组的 客户 即 未分组的 store
	@RequestMapping(value = "/findAllNoGroupStore.do")
	@ResponseBody
	public Object findAllNoGroupStore(HttpServletRequest request, Model model, StoreGroupVo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		List<Store> stores = this.storeService.findAllNoGroupStore(condition);
		return ResponseUtils.sendMsg(true, stores);
	}

	// 删除分组
	@RequestMapping(value = "/deletegroup.do")
	@ResponseBody
	public Object deleteGroup(HttpServletRequest request, Model model, StoreGroupVo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		SpVoucherActiveMiddle middle = this.storeGruopService.findSpVoucherActiveMiddle(condition);
		if (middle != null) {
			return ResponseUtils.sendMsg(false, "该组正在参与活动不能删除!");
		}
		this.storeGruopService.deleteObject(condition);
		this.storeGroupMemberService.deleteByGroupId(condition.getId());
		return ResponseUtils.sendMsg(true, "操作成功!");
	}

	// 编辑分组
	@RequestMapping(value = "/editgroup.do")
	@ResponseBody
	public Object editGroup(HttpServletRequest request, Model model, StoreGroupVo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		StoreGroup group = this.storeGruopService.findById(condition);
		return ResponseUtils.sendMsg(true, group);
	}

	// 添加客户
	@RequestMapping(value = "/addcustomer.do")
	@ResponseBody
	public Object addCustomer(HttpServletRequest request, Model model, StoreGroupVo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		if (condition.getIds() == null) {
			condition.setNumber(0);
			return ResponseUtils.sendMsg(true, "操作成功!");
		} else {
			String ids[] = condition.getIds();
			for (int i = 0; i < ids.length; i++) {
				for (int j = i + 1; j < ids.length; j++) {
					if (ids[i].equals(ids[j])) {
						return ResponseUtils.sendMsg(false, "不能添加同一个客户!");
					}
				}
			}
			condition.setNumber(condition.getIds().length);
		}
		this.storeGruopService.addCustomer(condition);
		return ResponseUtils.sendMsg(true, "操作成功!");
	}

	// 增加分组
	@RequestMapping(value = "/addgroup.do")
	@ResponseBody
	public Object addGroup(HttpServletRequest request, Model model, StoreGroupVo condition, Integer pageIndex) {
		Subject subject = SecurityUtils.getSubject();
		Supplier user = (Supplier) subject.getSession().getAttribute(SessionConfig.SUPPLY_SESSION_KEY);
		condition.setSupplierId(user.getId());
		condition.setDate(new Date());
		if (condition.getIds() == null) {
			condition.setNumber(0);
		} else {
			condition.setNumber(condition.getIds().length);
			String ids[] = condition.getIds();
			for (int i = 0; i < ids.length; i++) {
				for (int j = i + 1; j < ids.length; j++) {
					if (ids[i].equals(ids[j])) {
						return ResponseUtils.sendMsg(false, "不能添加同一个客户!");
					}
				}
			}
		}
		if (condition.getId().equals("")) {
			int count = this.storeGruopService.findCountNumer(condition.getSupplierId());
			if (count >= 10) {
				return ResponseUtils.sendMsg(false, "分组不能大于10个!");
			}
		}

		this.storeGruopService.addGroup(condition);
		return ResponseUtils.sendMsg(true, "操作成功!");
	}

}
