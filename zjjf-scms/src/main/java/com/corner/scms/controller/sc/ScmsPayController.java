/**   
toStatus.do* @Title: ScmsPayController.java 
* @Package com.corner.scms.controller.sc 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月22日 上午10:34:49 
* @version V1.0   
*/

package com.corner.scms.controller.sc;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.pay.wx.WXTYZFBACKBean;
import com.corner.core.pay.util.DateUtil;
import com.corner.core.pay.wx.client.TenpayHttpClient;
import com.corner.core.pay.wx.config.WXConfig;
import com.corner.core.pay.wx.util.MD5Util;
import com.corner.core.pay.wx.util.Sha1Util;
import com.corner.core.pay.wx.util.XMLUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.config.SCMSConstants;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sc.ScOrderInfoMgService;
import com.corner.scms.service.sc.ScmsPayService;
import com.corner.scms.service.sc.ScmsShoppingCartService;
import com.corner.scms.service.sp.ScmsSpWalletLogMgService;
import com.corner.scms.service.sp.ScmsSupplierMgService;
import com.corner.scms.utils.RandomUtils;
import com.corner.scms.utils.mail.MailSenderInfo;
import com.corner.scms.utils.mail.SimpleMailSender;
import com.corner.scms.utils.pay.wx.WXSignUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @ClassName: ScmsPayController
 * @Description:支付
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月22日 上午10:34:49
 * 
 */
@Controller
@RequestMapping("/scms/pay")
public class ScmsPayController extends ScmsBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(ScmsPayController.class);

	@Autowired
	ScOrderInfoMgService scOrderInfoMgService;
	@Autowired
	ScmsSupplierMgService scmsSupplierMgService;
	
	@Autowired
	ScmsShoppingCartService scmsShoppingCartService;
	
	@Autowired
	ScmsPayService scmsPayService;
	
	@Autowired
	ScmsSpWalletLogMgService scmsSpWalletLogMgService;
	

	/**
	 * 
	* @Title: getOrderInfoStatus 
	* @Description:获取获取订单支付状态
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getOrderStatus.do")
	@ResponseBody
	public Object getOrderInfoStatus(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		if (StringUtil.stringIsNullOrEmpty(orderId)) {
			return ResponseUtils.sendMsg(false, "缺少必要参数!");
		}
		ScOrderInfo scOrderInfo = scOrderInfoMgService.selectByPrimaryKey(orderId);
		if (scOrderInfo.getIsDelete()) {
			return ResponseUtils.sendMsg(false, "订单被删除!");
		}
		if (scOrderInfo.getStatus() == 2) {
			return ResponseUtils.sendMsg(true);
		} else {
			return ResponseUtils.sendMsg(false);
		}
	}

	/**
	 * 
	* @Title: WXPayBackNotify 
	* @Description:微信支付通知函数
	* @param @param request
	* @param @param response
	* @param @param wxNoticeBean
	* @param @return
	* @param @throws Exception
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/WXPayBackNotify.do", produces = "application/xml;charset=utf-8")
	@ResponseBody
	public Object WXPayBackNotify(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String wxNoticeBean) {
		logger.info("【联合采购微信支付】开始===================");
		logger.info("【联合采购微信支付】 微信返回报文 {}", wxNoticeBean);
		logger.info("request head：{}，{}", request.getContentType(), request.getHeader("Content-Type"));

		// mail
		MailSenderInfo mailSenderInfo = new MailSenderInfo();
		mailSenderInfo.setSubject("【联合采购微信支付异常--解析微信通知报文异常】");
		String content = "";
		SimpleMailSender sender = new SimpleMailSender();
		// xml字符转为map对象
		SortedMap<String, String> wxNotifyMap = new TreeMap<String, String>();
		try {
			wxNotifyMap = XMLUtil.doXMLParseToSortMap(wxNoticeBean);
		} catch (Exception e) {
			logger.error("【联合采购微信支付】收到微信通知报文解析出错!", e);
			content += "【联合采购微信支付】收到微信通知报文解析出错,异常原因:\n" + e.toString();
			sender.sendTextMail(mailSenderInfo);
			return new WXTYZFBACKBean("FAIL", "报文解析出错");
		}
		// 签名验证
		try {
			String wxSign = wxNotifyMap.get("sign");// 原生扫码支付
			String sign = WXSignUtil.signForTYZF(wxNotifyMap, WXConfig.scmsKey);
			if (StringUtils.isEmpty(sign) || StringUtils.isEmpty(wxSign) || !sign.equals(wxSign)) {
				logger.error("【联合采购微信支付】收到微信通知报文sign比对错误");
				return new WXTYZFBACKBean("FAIL", "sign比对错误");
			}
		} catch (Exception e) {
			mailSenderInfo.setSubject("【联合采购微信支付异常--解析签名异常】");
			content += "【联合采购微信支付】解析签名异常,异常原因:\n" + e.toString();
			sender.sendTextMail(mailSenderInfo);
			return new WXTYZFBACKBean("FAIL", "报文解析错");
		}
		// 数据处理
		String return_code = wxNotifyMap.get("return_code");
		String result_code = wxNotifyMap.get("result_code");
		String out_trade_no = wxNotifyMap.get("out_trade_no");// 提交给微信的标示符,forWxTradeNo
		String total_fee = wxNotifyMap.get("total_fee");// 金额,以分为单位
		String transaction_id = wxNotifyMap.get("transaction_id");// 微信支付订单号
		String time_end = wxNotifyMap.get("time_end");
		Date time_end_date = DateUtil.StringToDateDtLong(time_end);
		if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)) {
			//保存微信返回报文
			logger.info("【联合采购微信支付】开始保存报文==============");
			try {
				Pay pay = new Pay();
				pay.setOrderNo(out_trade_no);
				pay.setPayNo(transaction_id);
				pay.setPayAmount(getYuanFromFeng(total_fee));
				pay.setPayWay(new Byte("3"));//微信支付
				pay.setDealState(result_code);
				pay.setDealPayTime(time_end_date);
				pay.setState((byte)1);
				pay.setIsDelete(false);
				pay.setCreateTime(new Date());
				scmsPayService.savePayRecord(pay);	
			} catch (Exception e) {
				logger.error("【联合采购微信支付】结束保存微信通知参数！失败！订单号：{},原因：{}",out_trade_no,e);
			}
			
			
			
			
			// ------------------------------
			// 即时到账处理业务开始
			// ------------------------------
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", out_trade_no.substring(0, 23));
			ScOrderInfo scOrderInfo = scOrderInfoMgService.getScOrderInfo(map);
			if (scOrderInfo == null) {
				logger.info("【联合采购微信支付】 scOrderInfo 为null");
				return new WXTYZFBACKBean("ERROR", "NOT FOUND");
			}
			Supplier supplier = scmsSupplierMgService.selectByPrimaryKey(scOrderInfo.getSupplierId());
			//	TODO	提前入账未写
			// 如果处理过就不用处理
			if (scOrderInfo.getStatus() >= 2) {
//				try {
//					Date actionTime = new Date();
//					SpWalletLog spWalletLog = new SpWalletLog();
//					spWalletLog.setActionTime(actionTime);//操作时间
//					spWalletLog.setPayer((byte)32);//付款方 批发商
//					spWalletLog.setGeter((byte)20);//收款方 微信
//					spWalletLog.setSpId(supplier.getId());//批发商Id
//					spWalletLog.setActionType((byte)1);//收入
//					spWalletLog.setOptType((byte)11);//微信充值
//					spWalletLog.setTradeWay((byte)4);//微信支付
//					spWalletLog.setTradeNo(scOrderInfo.getTradeNo());//第三方支付流水
//					spWalletLog.setOrderId(scOrderInfo.getId());//订单主键
//					spWalletLog.setMoney(scOrderInfo.getThirdPayRealNum());//第三方实际支付金额
//					spWalletLog.setBalance(supplier.getWallet());
//					
//					scmsSpWalletLogMgService.insertSelective(spWalletLog);
//					
//					spWalletLog.setGeter((byte)10);//收款方 平台
//					spWalletLog.setActionType((byte)2);//支出
//					spWalletLog.setOptType((byte)5);//进货支出
//					spWalletLog.setTradeWay((byte)5);//钱包支付
//					BigDecimal thirdPaymentNum = scOrderInfo.getThirdPaymentNum().multiply(new BigDecimal("-1"));
//					spWalletLog.setMoney(thirdPaymentNum);//微信应付金额
//					BigDecimal balance = supplier.getWallet().add(scOrderInfo.getThirdPayRealNum());
//					spWalletLog.setBalance(balance);
//					
//					scmsSpWalletLogMgService.insertSelective(spWalletLog);//生成支付交易流水
//					
//					
//					logger.info("【联合采购微信支付】 scOrderInfo status>=2");
					return new WXTYZFBACKBean("SUCCESS", "ok");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
			

			// 验证金额是否一致
			//还要判断该笔订单总价是否发生变化
			map = new HashMap<String,Object>();
			map.put("scOrderInfo", scOrderInfo);
			map.put("supplier", supplier);
			
			BigDecimal thirdRealPayNum = scOrderInfo.getThirdPayRealNum();
			thirdRealPayNum = thirdRealPayNum.multiply(new BigDecimal(100));
			BigDecimal zhifuOrderAmout = new BigDecimal(total_fee);
			if (thirdRealPayNum.compareTo(zhifuOrderAmout) != 0) {// 价格不一致
				logger.error("【微信APP支付】收到价格与订单价格不一致，收到价格：" + zhifuOrderAmout + "  订单价格：" + thirdRealPayNum);
				new WXTYZFBACKBean("FAIL", "收到价格与订单价格不一致");
			}

			// 更新订单支付信息
			scOrderInfo.setSupportStatus(new Byte("1"));// 支付完成
			scOrderInfo.setSupportTime(time_end_date);// 在线支付时间
			scOrderInfo.setStatus(new Byte("2"));// 更新订单转态为已付款
			scOrderInfo.setTradeNo(transaction_id);//第三方支付流水号
			if(scOrderInfo.getIsUsedBalance()!=null&&scOrderInfo.getIsUsedBalance()==true){
				scOrderInfo.setSupportmetho(new Byte("54"));// 更新第三方支付方式
			}else {
				scOrderInfo.setSupportmetho((byte)4);
			}
			
			BigDecimal outOfPrice = new BigDecimal("0");
			if(scOrderInfo.getIsUsedBalance()){
				outOfPrice = outOfPrice.add(scOrderInfo.getBalanceUsedNum());
			}
			outOfPrice = outOfPrice.add(scOrderInfo.getThirdPayRealNum());
			scOrderInfo.setOutOfPrice(outOfPrice);
			
			logger.info("【联合采购微信支付】 开始更新订单支付信息===========");
			try {
				int res = scOrderInfoMgService.updateByPrimaryKeySelective(scOrderInfo);
				if (res != 1) {
					logger.error("【联合采购微信支付】异常更新订单状态记录数{}条", res);
					mailSenderInfo.setSubject("【联合采购微信支付--更新订单记录数异常】");
					content += "批发商:" + scOrderInfo.getSupplierName() + "   " + "批发商id:" + scOrderInfo.getSupplierId()
							+ "\n";
					content += "订单id:" + scOrderInfo.getId() + "   " + "订单号:" + scOrderInfo.getOrderId();
					sender.sendTextMail(mailSenderInfo);
					new WXTYZFBACKBean("FAIL", "异常更新订单状态");
				}
			} catch (Exception e) {
				logger.error("【联合采购微信支付】更新订单状态失败:{}", e);
				mailSenderInfo.setSubject("【联合采购微信支付--更新订单状态失败】");
				content += "批发商:" + scOrderInfo.getSupplierName() + "   " + "批发商id:" + scOrderInfo.getSupplierId()
						+ "\n";
				content += "订单id:" + scOrderInfo.getId() + "   " + "订单号:" + scOrderInfo.getOrderId() + "\n";
				content += "异常原因:" + e.toString();
				sender.sendTextMail(mailSenderInfo);
				return new WXTYZFBACKBean("FAIL", "更新订单状态失败");
			}
			logger.info("【联合采购微信支付】订单号：{} 即时到账支付成功", out_trade_no);

			logger.info("【联合采购微信支付】订单号：{} 微信支付入批发商钱包流水开始===", out_trade_no);
			try {
				Date actionTime = new Date();
				SpWalletLog spWalletLog = new SpWalletLog();
				spWalletLog.setActionTime(actionTime);//操作时间
				spWalletLog.setPayer((byte)32);//付款方 批发商
				spWalletLog.setGeter((byte)20);//收款方 微信
				spWalletLog.setSpId(supplier.getId());//批发商Id
				spWalletLog.setActionType((byte)1);//收入
				spWalletLog.setOptType((byte)11);//微信充值
				spWalletLog.setTradeWay((byte)4);//微信支付
				spWalletLog.setTradeNo(scOrderInfo.getTradeNo());//第三方支付流水
				spWalletLog.setOrderId(scOrderInfo.getId());//订单主键
				spWalletLog.setMoney(scOrderInfo.getThirdPayRealNum());//第三方实际支付金额
				spWalletLog.setBalance(supplier.getWallet());
				
				scmsSpWalletLogMgService.insertSelective(spWalletLog);//生成微信充值记录
				
				spWalletLog.setGeter((byte)10);//收款方 平台
				spWalletLog.setActionType((byte)2);//支出
				spWalletLog.setOptType((byte)5);//进货支出
				spWalletLog.setTradeWay((byte)5);//钱包支付
				BigDecimal thirdPaymentNum = scOrderInfo.getThirdPaymentNum().multiply(new BigDecimal("-1"));
				spWalletLog.setMoney(thirdPaymentNum);//微信应付金额
				BigDecimal balance = supplier.getWallet().add(scOrderInfo.getThirdPayRealNum());
				spWalletLog.setBalance(balance);
				
				scmsSpWalletLogMgService.insertSelective(spWalletLog);//生成支付交易流水
				
				if(scOrderInfo.getThirdPayFee()!=null&&scOrderInfo.getThirdPayFee().compareTo(new BigDecimal("0"))>0){
					spWalletLog.setGeter((byte)20);//收款方 微信
					spWalletLog.setOptType((byte)8);//微信手续费
					BigDecimal thirdPayFee = scOrderInfo.getThirdPayFee().multiply(new BigDecimal("-1"));
					spWalletLog.setMoney(thirdPayFee);//手续费
					balance = balance.subtract(scOrderInfo.getThirdPaymentNum());
					spWalletLog.setBalance(balance);
					scmsSpWalletLogMgService.insertSelective(spWalletLog);
				}
				
				/**平台钱包入账**/
				PlantWalletLog plantWalletLog = new PlantWalletLog();
				plantWalletLog.setPlantWalletId(SCMSConstants.PLANTWALLET_ID_TRADE);
				plantWalletLog.setActionTime(new Date());// 操作时间
				plantWalletLog.setActionType(new Byte("1"));// 收入
				plantWalletLog.setPayer(new Byte("32"));//付款方 批发商
				plantWalletLog.setGeter(new Byte("10"));//收款方
				plantWalletLog.setOrderId(scOrderInfo.getpId());// 订单PId
				plantWalletLog.setOrderPId(scOrderInfo.getpId());
				plantWalletLog.setOptType(new Byte("1"));// 进货
				plantWalletLog.setIsDelete(false);
				
				/**平台钱包入账**/
				if(thirdPaymentNum.compareTo(new BigDecimal(0)) < 0)
					plantWalletLog.setMoney(thirdPaymentNum.multiply(new BigDecimal("-1")));
				else
					plantWalletLog.setMoney(thirdPaymentNum);
				//	ModelMsg modelMsg = plantWalletService.updatePlantWalletAndLog(plantWalletLog);
				logger.error("平台流水入账失败:{}");

			} catch (Exception e) {
				logger.error("{}",e);
			}
			

			// 开始合单
			logger.info("【联合采购微信支付】 =======开始合单");
			
			try {
				scOrderInfoMgService.updateAddScOrderInfo(map);
			} catch (Exception e) {
				logger.info("【联合采购微信支付---合单异常】 合单异常", e);
				mailSenderInfo.setSubject("【联合采购微信支付---合单异常】");
				content += "批发商:" + scOrderInfo.getSupplierName() + "   " + "批发商id:" + scOrderInfo.getSupplierId()
						+ "\n";
				content += "订单id:" + scOrderInfo.getId() + "   " + "订单号:" + scOrderInfo.getOrderId() + "\n";
				content += "异常原因:" + e.toString();
				sender.sendTextMail(mailSenderInfo);
			}
			// 其他情况返回成功
			return new WXTYZFBACKBean("SUCCESS", "即时到账支付成功");
			// ------------------------------
			// 即时到账处理业务完毕
			// ------------------------------
		} else {
			logger.info("【联合采购微信支付 ---失败】");
			mailSenderInfo.setSubject("【联合采购微信支付--微信通知支付失败】");
			content += "【联合采购微信支付】微信通知支付失败,返回报文\n";
			content += wxNoticeBean;
			sender.sendTextMail(mailSenderInfo);
			return new WXTYZFBACKBean("FAIL", "通知支付失败");
		}
	}
	
	
	

	/**
	 * 
	* @Title: toStatus 
	* @Description:跳转到支付状态页面
	* @param @param request
	* @param @param model
	* @param @return
	* @param @throws Exception
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toStatus.do")
	public String toStatus(HttpServletRequest request, Model model) throws Exception {
		String scOrderInfoId = request.getParameter("scOrderInfoId");
		ScOrderInfo scOrderInfo = scOrderInfoMgService.selectByPrimaryKey(scOrderInfoId);
		if (scOrderInfo == null || scOrderInfo.getIsDelete() == true) {
			return "redirect:/scms/procurementcenter/listPage.do";
		}
		if (scOrderInfo.getStatus() == 2 || scOrderInfo.getStatus() == 3 || scOrderInfo.getStatus() == 4) {
			model.addAttribute("status", true);
			//model.addAttribute("orderPrice", scOrderInfo.getOrderPrice());
			model.addAttribute("outOfPrice", scOrderInfo.getOutOfPrice());
			model.addAttribute("orderId", scOrderInfo.getOrderId());
		} else {
			model.addAttribute("status", false);
		}
		return "purchase/shopcart-pay-status";
	}


	/**
	 * 	作废
	* @Title: verifyPaymentPassword 
	* @Description:使用钱包支付的时候，需要验证密码，并且钱包有余额的时候,先扣除钱包部分
	* @param @param request
	* @param @return
	* @param @throws Exception
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/verfifypp.do")
	@ResponseBody
	public synchronized Object verifyPaymentPassword(HttpServletRequest request) {
		try {
			Supplier supplier = this.getCurrentUser(Supplier.class, request);
			if(supplier==null){
				return ResponseUtils.sendMsg(false, "e4");//用户没有登入
			}
			
			supplier = scmsSupplierMgService.selectByPrimaryKey(supplier.getId());
			String scOrderInfoId = request.getParameter("scOrderInfoId");// 订单主键
			if (StringUtil.stringIsNullOrEmpty(scOrderInfoId)) {
				return ResponseUtils.sendMsg(false, "e2");// 没有订单主键,页面跳转到采购中心
			}
			String money = request.getParameter("money");// 页面上展示的用户当时可用的钱包余额
			if (StringUtil.stringIsNullOrEmpty(money)) {
				// 验证支付密码进入本方法,表示当时用户钱包应该是有余额的,但是收到的参数为空,程序异常
				return ResponseUtils.sendMsg(false, "e1");
			}

			// 验证密码
			String password = request.getParameter("password");

			if (supplier.getPayPassword() == null) {// 用户没有设置支付密码
				return ResponseUtils.sendMsg(false, "e5");// 密码不对
			}

			if (StringUtil.stringIsNullOrEmpty(password)) {// 如果参数中没有密码
				// 判断用户钱包是否有余额,有余额则回到结算页并刷新
				// 需要验证密码,表示要使用钱包支付
				return ResponseUtils.sendMsg(false, "e3");// 密码不对

			} else {
				password = MD5Util.MD5Encode(password, "UTF-8");
				if (!supplier.getPayPassword().equals(password)) {
					return ResponseUtils.sendMsg(false, "e3");// 密码不对
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", scOrderInfoId);
			map.put("supplier", supplier);
			ScOrderInfo scOrderInfo = scOrderInfoMgService.getScOrderInfo(map);
			if (scOrderInfo == null || scOrderInfo.getStatus() >= 2) {
				return ResponseUtils.sendMsg(false, "e2");// 订单为空或已经支付完成,跳转到采购中心
			}
			map.put("scOrderInfo", scOrderInfo);
			// 判断订单是否已经支付完成
			if (scOrderInfo.getStatus() == 1) {// 订单支付未完成
				BigDecimal bigDecimalMoney = new BigDecimal(money);

				if (supplier.getWallet() == null || supplier.getWallet().compareTo(new BigDecimal("0")) == 0) {
					return ResponseUtils.sendMsg(false, "e1");
				}

				if (bigDecimalMoney.compareTo(supplier.getWallet()) != 0) {
					// 页面上传过来的钱包可用余额和现在的不同
					return ResponseUtils.sendMsg(false, "e1");
				}

				//map = scmsShoppingCartService.updateScOrderInfoPayStatus(map);
				Boolean priceChange = (Boolean)map.get("priceChange");
				if(priceChange!=null&&priceChange==true){
					return ResponseUtils.sendMsg(false, "e6");//订单金额变动
				}
				// 只有在订单未完成的前提下,才进行以下操作
				scOrderInfo = (ScOrderInfo) map.get("scOrderInfo");
				if (scOrderInfo.getStatus() == 2) {// 订单已经支付完成
					// 开始合单
					try {
						scOrderInfoMgService.updateAddScOrderInfo(map);
					} catch (Exception e) {
						// 要是此处产生异常,应该是线程池异常不是合单接口的异常,合单接口异常已经在线程中try-catch处理
						MailSenderInfo mailSenderInfo = new MailSenderInfo();
						mailSenderInfo.setSubject("【联合采购线程池异常】");
						String content = "";
						content += "批发商:" + supplier.getSupplierName() + "  " + "批发商id:" + supplier.getId() + "\n";
						content += "订单id:" + scOrderInfo.getId() + "   " + "订单号:" + scOrderInfo.getOrderId() + "\n";
						content += "异常原因:" + e.toString();
						mailSenderInfo.setContent(content);
						SimpleMailSender simpleMailSender = new SimpleMailSender();
						simpleMailSender.sendTextMail(mailSenderInfo);
					}
					return ResponseUtils.sendMsg(true, "s1");// 表示订单已经支付完成
				} else {
					if (scOrderInfo.getIsUsedBalance() == true) {// 表示该笔订单已经使用钱包支付
						supplier = (Supplier) map.get("supplier");
						map.put("wallet", supplier.getWallet());
						map.remove("supplier");
						map.remove("scOrderInfo");
						map.remove("scOrderInfoId");
						return ResponseUtils.sendMsg(true, map);
					}
				}
			} else {// 订单支付完成
				return ResponseUtils.sendMsg(false, "e2");
			}
		} catch (Exception e) {
			logger.error("【联合采购结算模块异常】", e);
			return ResponseUtils.sendMsg(false, e.toString());
		}
		return request;
	}

	/**
	 * 
	* @Title: creatQrImage 
	* @Description:需要用微信扫码支付,生成二维码
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws Exception
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/creatQrImage.do")
	@ResponseBody
	public Object creatQrImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String scOrderInfoId = request.getParameter("scOrderInfoId");// 订单主键
		if (StringUtil.stringIsNullOrEmpty(scOrderInfoId)) {
			return ResponseUtils.sendMsg(false, "参数有误!没有订单主键");
		}
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		supplier = scmsSupplierMgService.selectByPrimaryKey(supplier.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", scOrderInfoId);
		ScOrderInfo scorderInfo = scOrderInfoMgService.getScOrderInfo(map);
		if (scorderInfo == null || scorderInfo.getStatus() >= 2) {
			return ResponseUtils.sendMsg(false, "没有该笔订单!");
		}
		// 微信要支付的金额为订单金额减去使用钱包的金额
		/*if (scorderInfo.getIsUsedBalance() != null && scorderInfo.getIsUsedBalance() == true) {
			BigDecimal fee = scorderInfo.getOrderPrice().subtract(scorderInfo.getBalanceUsedNum());
			scorderInfo.setOrderPrice(fee);
		}*/
		String resContent = getWXResContent(scorderInfo, request);// 调用微信统一支付接口,获取二维码返回值
		encodeQrcode(resContent, response);
		return "";
	}

	/**
	 * 
	* @Title: getWXResContent 
	* @Description:调用微信统一支付接口,获取二维码url
	* @param @param scorderInfo
	* @param @param request
	* @param @return
	* @param @throws Exception
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@SuppressWarnings("unchecked")
	private String getWXResContent(ScOrderInfo scorderInfo, HttpServletRequest request) throws Exception {
		// 设置价格格式
		DecimalFormat df = new DecimalFormat("##################");
		//BigDecimal bigDecimal = scorderInfo.getOrderPrice();
		BigDecimal bigDecimal = scorderInfo.getThirdPayRealNum();
		String total_fee = df.format(bigDecimal.multiply(new BigDecimal(100)));
		String nonce_str = Sha1Util.getNonceStr();// 随机字符串

		String forWxTradeNo = scorderInfo.getOrderId()+"_"+RandomUtils.getRandNum(3);
		scorderInfo.setForWxTradeNo(forWxTradeNo);
		// 更新需要提交给微信的交易号
		scOrderInfoMgService.updateByPrimaryKeySelective(scorderInfo);

		// 设置参数map
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", WXConfig.scmsAppID);
		packageParams.put("mch_id", WXConfig.scmsMch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", "转角街坊商品");
		packageParams.put("out_trade_no", scorderInfo.getForWxTradeNo());
		// packageParams.put("attach", scorderInfo.getOrderId());
		packageParams.put("total_fee", total_fee);
		packageParams.put("spbill_create_ip", getLocalIp(request));
		//packageParams.put("spbill_create_ip", request.getRemoteAddr());
		//设置回调接口
		packageParams.put("notify_url", WXConfig.scms_notify_url);
		packageParams.put("trade_type", WXConfig.TradeTypeNATIVE);
		packageParams.put("product_id", scorderInfo.getId());

		// 生成签名
		String sign = WXSignUtil.signForTYZF(packageParams, WXConfig.scmsKey);

		// 拼接xml报文
		StringBuilder sbxml = new StringBuilder();
		sbxml.append("<xml>");
		sbxml.append("<appid>");
		sbxml.append(packageParams.get("appid"));
		sbxml.append("</appid>");
		sbxml.append("<mch_id>");
		sbxml.append(packageParams.get("mch_id"));
		sbxml.append("</mch_id>");
		sbxml.append("<nonce_str>");
		sbxml.append(packageParams.get("nonce_str"));
		sbxml.append("</nonce_str>");
		sbxml.append("<body>");
		sbxml.append(packageParams.get("body"));
		sbxml.append("</body>");
		sbxml.append("<out_trade_no>");
		sbxml.append(packageParams.get("out_trade_no"));
		sbxml.append("</out_trade_no>");
		sbxml.append("<total_fee>");
		sbxml.append(packageParams.get("total_fee"));
		sbxml.append("</total_fee>");
		sbxml.append("<spbill_create_ip>");
		sbxml.append(packageParams.get("spbill_create_ip"));
		sbxml.append("</spbill_create_ip>");
		sbxml.append("<notify_url>");
		sbxml.append(packageParams.get("notify_url"));
		sbxml.append("</notify_url>");
		sbxml.append("<trade_type>");
		sbxml.append(packageParams.get("trade_type"));
		sbxml.append("</trade_type>");
		sbxml.append("<product_id>");
		sbxml.append(packageParams.get("product_id"));
		sbxml.append("</product_id>");
		sbxml.append("<sign>");
		sbxml.append(sign);
		sbxml.append("</sign>");
		sbxml.append("</xml>");

		// 记录日志
		logger.info("【微信支付】订单号：{} 准备发送统一支付xml报文：{} \n {}", scorderInfo.getId(), scorderInfo.getOrderId(),
				sbxml.toString());
		Map<String, String> prepayResultMap;
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(WXConfig.TY_ZF_Url);
		httpClient.callHttpPost(WXConfig.TY_ZF_Url, sbxml.toString());
		String resContent = httpClient.getResContent();
		logger.info("【微信支付】订单号：{} 接收到统一支付xml报文：{} \n {}", scorderInfo.getId(), scorderInfo.getOrderId(), resContent);
		try {
			prepayResultMap = XMLUtil.doXMLParse(resContent);
		} catch (Exception e) {
			logger.error("【微信支付】订单号：{} 返回报文解析出错：{} \n {}", scorderInfo.getId(), scorderInfo.getOrderId(), e);
			throw new Exception(e);
		}
		if ("SUCCESS".equals(prepayResultMap.get("return_code"))
				&& "SUCCESS".equals(prepayResultMap.get("result_code"))) {
			System.out.println(prepayResultMap.toString());
			System.out.println(prepayResultMap.get("code_url"));
		}
		return prepayResultMap.get("code_url");
	}

	/**
	 * 
	* @Title: encodeQrcode 
	* @Description:生成二维码图片 不存储 直接以流的形式输出到页面
	* @param @param content
	* @param @param response
	* @param @throws IOException
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
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

	/**
	 * 
	* @Title: getLocalIp 
	* @Description:获取本机ip
	* @param @param request
	* @param @return
	* @param @throws Exception
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getLocalIp.do")
	public String getLocalIp(HttpServletRequest request) throws Exception {
		String ip = null;
		Enumeration allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
				for (InterfaceAddress add : InterfaceAddress) {
					InetAddress Ip = add.getAddress();
					if (Ip != null && Ip instanceof Inet4Address) {
						ip = Ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			logger.warn("获取本机Ip失败:异常信息:" + e.getMessage());
		}
		return "";
	}

	/**
	 * 
	* @Title: signForTYZF 
	* @Description:生成签名 
	* @param @param params
	* @param @param secretKey
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	public static String signForTYZF(SortedMap<String, String> params, String secretKey) {
		StringBuilder sb = new StringBuilder();
		Set es = params.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + secretKey);
		return MD5Util.MD5Encode(sb.toString(), WXConfig.input_charset).toUpperCase();
	}
	
	public BigDecimal getYuanFromFeng(String feng){
		if(feng == null || "".equals(feng)){
			return null;
		}else{
			try {
				BigDecimal result =	new BigDecimal(feng);
				result = result.divide(new BigDecimal(100));
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("金额转化错误，原因：{}", e.getMessage() );
				return null;
			}

		}
	}
}
