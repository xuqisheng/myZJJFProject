package com.corner.account.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.account.beans.ro.BillPayCondition;
import com.corner.account.beans.ro.BillSheetCondition;
import com.corner.account.config.AuthorityConfig;
import com.corner.account.service.AcSheetService;
import com.corner.account.service.PublicService;
import com.corner.core.beans.AcSheet;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;


@Controller
@RequestMapping(value="/account/billsheetctl")
public class BillSheetController extends AccountBaseWebController {
	
	private static Logger logger = LoggerFactory.getLogger(BillSheetController.class);
	
	@Autowired
	PublicService publicService;
	
	@Autowired
	AcSheetService acSheetService;
	
	@RequestMapping(value = "/BillSheetManagerPage.do")
	public String index(HttpServletRequest request, Model model) {
		logger.debug("用户进入核心权限管理界面BillSheetManagerPage");
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole(AuthorityConfig.SPUER_ADMIN)){
			return "BillSheet/BillSheetManage";
		}else{
			model.addAttribute("message","您无权访问该界面");
			return "Alert";
		}
	}
	
	@RequestMapping(value = "/List.do")
	@ResponseBody
	public Object list(HttpServletRequest request, BillSheetCondition command) {
		Pager<AcSheet> pager = acSheetService.getAcSheetList(command);
		return ResponseUtils.sendEasyUIPagination(pager);
	}
	
	/**
	 * 生成结算单据，保存在服务器目录里面
	 * @param request
	 * @param command
	 * @throws Exception 
	 */
	@RequestMapping(value = "/CreatAcSheet.do")
	@ResponseBody
	public Object creatAcSheet(HttpServletRequest request,HttpServletResponse response,BillSheetCondition command) throws Exception {
		if(command != null && !StringUtils.isEmpty(command.getSpOrderIds())){
			ModelMsg msg =  acSheetService.addAcSheet(command,request,response);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,msg.getData());				
			}else{
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}else{
			return ResponseUtils.sendMsg(false,"参数错误");	
		}
	}
	/**
	 * 录入反馈，银行流水
	 * @param request
	 * @param command
	 * @throws Exception 
	 */
	@RequestMapping(value = "/FillBack.do")
	@ResponseBody
	public Object fillBack(HttpServletRequest request,HttpServletResponse response,BillSheetCondition command) throws Exception {
		if(command != null 
				&& !StringUtils.isEmpty(command.getPaybanknum())
				&& !StringUtils.isEmpty(command.getId())){
			ModelMsg msg =  acSheetService.updateAcsheetByCondition(command);
			if(msg != null && msg.isSuccess()){
				return ResponseUtils.sendMsg(true,msg.getMessage());				
			}else{
				return ResponseUtils.sendMsg(false,msg.getMessage());				
			}
		}else{
			return ResponseUtils.sendMsg(false,"参数错误");	
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
	public void output(HttpServletRequest request,HttpServletResponse response,BillPayCondition command) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
//		Pager<BillPayVo> pager = billPayService.getBillPayOverViewList(command);
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
