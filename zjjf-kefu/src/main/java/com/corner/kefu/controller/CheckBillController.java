package com.corner.kefu.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.Region;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.beans.ro.CheckBillCondition;
import com.corner.kefu.beans.vo.CheckBillVo;
import com.corner.kefu.service.CheckBillService;
import com.corner.kefu.service.PublicService;
import com.corner.kefu.service.SpOrderInfoService;

@Controller
@RequestMapping(value="/account/checkbillctl")
public class CheckBillController extends KefuBaseWebController {

	private static Logger logger = LoggerFactory.getLogger(CheckBillController.class);
	
	
	@Autowired
	CheckBillService checkBillService;
	
	
	@Autowired
	PublicService publicService;
	
	
	@Autowired
	SpOrderInfoService spOrderInfoService;
	
	
	@RequestMapping(value = "/CheckBillManagerPage.do")
	public String index(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心权限管理界面");
		//Subject subject = SecurityUtils.getSubject();
		//if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			List<Region> list = publicService.getRegions();
			model.addAttribute("areaList", list);
			return "CheckBill/CheckBillManage";
		//}else{
			//model.addAttribute("message","您无权访问该界面");
			//return "Alert";
		//}
	}
	
	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, CheckBillCondition command) {
		Pager<CheckBillVo> pager = checkBillService.getCheckBillList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}

	
	@RequestMapping(value = "/CheckBillDetailManagerPage.do")
	public String checkBillDetailManagerPage(HttpServletRequest request,String supplierId, Model model) {
		logger.debug("用户进入核心权限管理界面");
		model.addAttribute("supplierId", supplierId);
		return "CheckBill/CheckBillOrderListManage";
	}
	
	@RequestMapping(value = "/SpList.do")
	@ResponseBody
	public Object spList(HttpServletRequest request, CheckBillCondition command) {
		Pager<CheckBillVo> pager = checkBillService.getCheckBillDetailList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	
	@RequestMapping(value = "/CheckBillOrderInfoManagerPage.do")
	public String checkBillOrderInfoManagerPage(HttpServletRequest request,String spOrderId, Model model) {
		logger.debug("用户进入核心权限管理界面");
		if(StringUtils.isEmpty(spOrderId)){
			model.addAttribute("message", "订单号不能为空");
			return "Alert";
		}else{
			spOrderInfoService.getSpOrderAllForPage(spOrderId,model);
			return "CheckBill/CheckBillOrderInfoManage";
		}
	}
	
	@RequestMapping(value = "/UpdateAcStatusPass.do")
	@ResponseBody
	public Object updateAcStatusPass(HttpServletRequest request, CheckBillCondition command) {
		if(command ==null || StringUtils.isEmpty(command.getSpOrderIds())){
			return ResponseUtils.sendMsg(false,"参数错误");
		}else{			
			ModelMsg msg = spOrderInfoService.updateAcStatus(command);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,"修改成功");				
			}else{				
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @param sporder
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IntrospectionException
	 */
	@RequestMapping(value = "/output.do")
	@ResponseBody
	public void output(HttpServletRequest request,HttpServletResponse response, CheckBillCondition command) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
//		command.setSupplier(request.getSession().getAttribute("supplierTel").toString());
//		
//		
//		Pager<CheckBillVo> pager=checkBillService.getSpOrderInfoList(command);
//		LinkedHashMap<String, String> attrs=new LinkedHashMap<String, String>();
//		CheckBillVo s=checkBillService.getCount(command);
//		attrs.put("orderid", "订单号");
//		attrs.put("storename", "便利店全称");
//		attrs.put("storeid", "便利店编码");
//		attrs.put("acktime", "送达时间");
//		attrs.put("orderprice", "交易金额");
//		attrs.put("zfee", "费用金额");
//		attrs.put("acremark", "备注");
//		List<String> titles=new ArrayList<String>();
//		titles.add(s.getSupplierNam()+"未审核明细");
//
//		response.setHeader("content-disposition","attachment;filename=" +s.getSupplierNam()  + "未审核明细.xls");
//		String begin=null;
//		String end=null;
////		if(!isEnpty(command.getBeginTime())){
////			begin=command.getBeginTime();
////		}
////		if(!isEnpty(command.getEndTime())){
////			end=command.getEndTime();
////		}
////		
//		List<String> conclusions=new ArrayList<String>();
//		conclusions.add("制表：     财务复核：       客服复核：         渠道总监审核：       总经理审批：      ");
//		HSSFWorkbook workbook=ExcelToll.getWorkBook(pager.getList(), attrs,  titles, conclusions);
//		ServletOutputStream st=null;
//		try {
//			 st=response.getOutputStream();
//			workbook.write(st);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			try {
//				st.flush();
//				st.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}
