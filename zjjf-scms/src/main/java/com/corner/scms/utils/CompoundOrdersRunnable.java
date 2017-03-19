/**   
* @Title: CompoundOrdersRunnable.java 
* @Package com.corner.scms.utils 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月28日 下午6:08:57 
* @version V1.0   
*/

package com.corner.scms.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.Supplier;
import com.corner.scms.utils.mail.MailSenderInfo;
import com.corner.scms.utils.mail.SimpleMailSender;

/** 
* @ClassName: CompoundOrdersRunnable 
* @Description:合单线程
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年1月28日 下午6:08:57 
*  
*/

public class CompoundOrdersRunnable implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(CompoundOrdersRunnable.class);
	private CompoundOrdersService compoundOrdersService;
	private Map<String, Object>map=null;
	public CompoundOrdersRunnable() {
		super();
	}

	public CompoundOrdersRunnable(Map<String, Object> map) {
		this.map = map;
	}


	public CompoundOrdersRunnable(Map<String, Object> map, CompoundOrdersService compoundOrdersService) {
		this.map = map;
		this.compoundOrdersService=compoundOrdersService;
	}

	@Override
	public void run() {
		try {
			compoundOrdersService.compoundOrders(map);
		} catch (Exception e) {
			logger.error(e.toString());
			//发送邮件
			Supplier supplier = (Supplier) map.get("supplier");
			ScOrderInfo scOrderInfo = (ScOrderInfo) map.get("scOrderInfo");
			MailSenderInfo mailSenderInfo = new MailSenderInfo();
			mailSenderInfo.setSubject("【联合采购合单异常】");
			String content = "";
			content+="批发商:"+supplier.getSupplierName()+"  "+"批发商id:"+supplier.getId()+"\n";
			content+="订单id:"+scOrderInfo.getId()+"   "+"订单号:"+scOrderInfo.getOrderId()+"\n";
			content+="异常原因:"+e.toString();
			mailSenderInfo.setContent(content);
			SimpleMailSender simpleMailSender = new SimpleMailSender();
			simpleMailSender.sendTextMail(mailSenderInfo);
		}
	}

}
