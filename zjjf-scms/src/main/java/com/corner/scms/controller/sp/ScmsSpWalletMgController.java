package com.corner.scms.controller.sp;

import com.corner.core.beans.FinWallet;
import com.corner.core.beans.SpWithDraw;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.core.enums.TradeWay;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.FinWalletLogRo;
import com.corner.scms.beans.ro.SpWithDrawRo;
import com.corner.scms.beans.vo.FinWalletLogVo;
import com.corner.scms.beans.vo.SpWalletLogVo;
import com.corner.scms.beans.vo.SpWithDrawVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.WalletService;
import com.corner.scms.service.sp.ScmsSpWalletMgService;
import com.corner.scms.service.sp.ScmsSupplierMgService;
import com.corner.scms.utils.enums.BusinessType;
import com.corner.scms.utils.enums.SubjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 
 * @ClassName: ScmsSpWalletMgController
 * @Description:供应商钱包控制器
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2015年12月4日 上午11:34:21
 *
 */
@Controller
@RequestMapping("/scms/sp")
public class ScmsSpWalletMgController extends ScmsBaseWebController {
	private static Logger logger = LoggerFactory.getLogger(ScmsSpWalletMgController.class);

	@Autowired
	ScmsSupplierMgService suppliermgservice;

	@Autowired
	ScmsSpWalletMgService spwalletmgservice;
	@Autowired
	WalletService walletService;
    @RequestMapping("/caculateFee.do")
	@ResponseBody
	public Object caculateFee(HttpServletRequest request) {
		Supplier supplier = getCurrentUser(Supplier.class, request);
		FinWallet finWallet = walletService.selectOne(supplier.getId());
    	String operateMoney = request.getParameter("withdrawMoney");
    	BigDecimal withDrawMoney = new BigDecimal(operateMoney);
    	BigDecimal shouxufei = withDrawMoney.multiply(new BigDecimal(finWallet.getRates()+"")).setScale(2, BigDecimal.ROUND_UP);
    	BigDecimal daozhanjine = withDrawMoney.subtract(shouxufei);
    	Map<String, Object>map = new HashMap<String,Object>();
    	map.put("daozhanjine", daozhanjine);
    	map.put("shouxufei", shouxufei);
		return ResponseUtils.sendMsg(true, map);
	}
	/**
	 * 跳转到供应商钱包页
	 * 
	 * @Title: toSpWalletIndex
	 * @Description:
	 * @param @return
	 * @return String
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping("/toSpWalletIndex.do")
	public String toSpWalletIndex(HttpServletRequest request,SpWithDrawRo spWithDrawRo,Model model) {
		try {
			Supplier supplier = this.getCurrentUser(Supplier.class, request);
			// 查询钱包供应商余额
			FinWallet finWallet = walletService.selectOne(supplier.getId());
			// 查询提现记录
			spWithDrawRo.setSupplierId(supplier.getId());
			model.addAttribute("wallet", finWallet.getBalance());
			return "wallet/index";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return error(e.getMessage() , model , request);
		}
	}
	
	/**
	 * 
	* @Title: getWithDrawList 
	* @Description:查询提现列表
	* @param @return
	* @return Object
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping(value="/getWithDrawList.do")
	@ResponseBody
	public Object getWithDrawList(HttpServletRequest request,SpWithDrawRo spWithDrawRo , Model model) {
		try {
			Supplier supplier = this.getCurrentUser(Supplier.class, request);
			if(spWithDrawRo.getEndDate()!=null){
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(spWithDrawRo.getEndDate());
				calendar.add(Calendar.DATE, 1);
				spWithDrawRo.setEndDate(calendar.getTime());
			}
			// 查询钱包供应商余额
			FinWallet finWallet = walletService.selectOne(supplier.getId());
			spWithDrawRo.setSupplierId(finWallet.getId());	//钱包ID
			Pager<SpWithDrawVo> page = spwalletmgservice.selectSpWithDrawPageList(spWithDrawRo);
			return new Pager<SpWithDrawVo>(page.getTotalSize(), page.getList());
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return error(e.getMessage() , model , request);
		}
	}
	

	/**
	 * 
	 * @Title: toSupplierWalletLog
	 * @Description:跳转到收支明细页面
	 * @param @return
	 * @return String
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value="/toSpWalletLog.do",method=RequestMethod.GET)
	public String toSupplierWalletLog(HttpServletRequest request, Model model) {
		try {
			return "wallet/income";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return error(e.getMessage() , model , request);
		}
	}
	
	
	/**
	 * 
	* @Title: getSpWalletLog 
	* @Description:查询收支明细记录
	* @param @param request
	* @param @param spWalletLogRo
	* @param @param model
	* @param @return
	* @return Object
	* @author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping(value="/getSpWalletLog.do",method=RequestMethod.GET)
	@ResponseBody
	public Object getSpWalletLog(HttpServletRequest request, FinWalletLogRo finWalletLogRo, Model model) {
		try {
			Supplier supplier = getCurrentUser(Supplier.class, request);

			// 查询收支明细集合
			FinWallet finWallet = walletService.selectOne(supplier.getId());
			finWalletLogRo.setWalletId(finWallet.getId());//钱包ID

			if(finWalletLogRo.getEndTime()!=null){
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(finWalletLogRo.getEndTime());
				calendar.add(Calendar.DATE, 1);
				finWalletLogRo.setEndTime(calendar.getTime());
			}
			Pager<FinWalletLogVo> page = walletService.selectFinWalletLogPageList(finWalletLogRo);
			Iterator<FinWalletLogVo> iterator = page.getList().iterator();
			// 计算余额
			while (iterator.hasNext()){
				FinWalletLogVo finWalletLogVo = iterator.next();
				if (finWalletLogRo.getWalletId().equals(finWalletLogVo.getPayerWalletId())){	//支出
					finWalletLogVo.setZhichu("-" + finWalletLogVo.getAmount().toString());
					finWalletLogVo.setShouru("");
					finWalletLogVo.setSubjectTypeStr(SubjectType.getName(finWalletLogVo.getSubjectType())+"支出");
					finWalletLogVo.setBalance(finWalletLogVo.getPayerLastBalance().subtract(finWalletLogVo.getAmount()));
				}else if(finWalletLogRo.getWalletId().equals(finWalletLogVo.getGeterWalletId())){//收入
					finWalletLogVo.setShouru(finWalletLogVo.getAmount().toString());
					finWalletLogVo.setZhichu("");
					finWalletLogVo.setSubjectTypeStr(SubjectType.getName(finWalletLogVo.getSubjectType())+"收入");
					finWalletLogVo.setBalance(finWalletLogVo.getGeterLastBalance().add(finWalletLogVo.getAmount()));
				}
				finWalletLogVo.setRemark(BusinessType.getName(finWalletLogVo.getBusinessType())+"编号:"+  finWalletLogVo.getVoucherSub());

			}

			return new Pager<FinWalletLogVo>(true,page.getTotalSize(), page.getList(), walletService.selectAllIncomeAndOutlay(finWallet.getId()));
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return error(e.getMessage() , model , request);
		}
	}

	//用于处理页面显示
	private void doHandleView(Iterator<SpWalletLogVo> iterator) {
		//用于页面显示
		while (iterator.hasNext()){
			SpWalletLogVo spWalletLogVo = (SpWalletLogVo) iterator.next();
			spWalletLogVo.setDingdang("订单号:"+spWalletLogVo.getOrderInfoId());
			if(spWalletLogVo.getActionType()==1){
				//收入
				spWalletLogVo.setMingcheng(SubjectType.map().get(spWalletLogVo.getOptType()).getName() + "收入");
				//设置交易名称
				switch (spWalletLogVo.getOptType()) {
				case 1:
					spWalletLogVo.setMingcheng("订单费用收入");
					break;
				case 2:
					spWalletLogVo.setMingcheng("订单金额收入");
					break;
				case 3:
					spWalletLogVo.setMingcheng("订单补贴收入");
					break;
				case 4:
					spWalletLogVo.setMingcheng("提现收入");
					spWalletLogVo.setDingdang("提现编号:"+spWalletLogVo.getSpWithDrawId());
					break;
				case 5:
					spWalletLogVo.setMingcheng("进货收入");
					spWalletLogVo.setDingdang("订单号:"+spWalletLogVo.getOrderInfoId2());
					break;
				case 6:
					spWalletLogVo.setMingcheng("运费收入");
					break;
				case 7:
					spWalletLogVo.setMingcheng("现金券收入");
					break;
				case 8:
					spWalletLogVo.setMingcheng("微信手续费收入");
					break;
				case 9:
					spWalletLogVo.setMingcheng("提现手续费收入");
					break;
				case 10:
					spWalletLogVo.setMingcheng("采购中心订单退款");
					spWalletLogVo.setDingdang("订单号:"+spWalletLogVo.getOrderInfoId2());
					break;
				case 11:
					spWalletLogVo.setMingcheng("微信充值");
					spWalletLogVo.setDingdang("订单号:"+spWalletLogVo.getOrderInfoId2());
					break;
				default:
					spWalletLogVo.setMingcheng("默认");
					break;
				}
				
				//收入/支出
				spWalletLogVo.setShouru(spWalletLogVo.getMoney().toString());
				spWalletLogVo.setZhichu("");
				
			}else if (spWalletLogVo.getActionType()==2) {//支出
				spWalletLogVo.setMingcheng(SubjectType.map().get(spWalletLogVo.getOptType()).getName() + "支出");
				//设置交易名称
				switch (spWalletLogVo.getOptType()) {
				case 1:
					spWalletLogVo.setMingcheng("订单费用支出");
					break;
				case 2:
					spWalletLogVo.setMingcheng("订单金额支出");
					break;
				case 3:
					spWalletLogVo.setMingcheng("订单补贴支出");
					break;
				case 4:
					spWalletLogVo.setMingcheng("提现支出");
					spWalletLogVo.setDingdang("提现编号:"+spWalletLogVo.getSpWithDrawId());
					break;
				case 5:
					spWalletLogVo.setMingcheng("进货支出");
					spWalletLogVo.setDingdang("订单号:"+spWalletLogVo.getOrderInfoId2());
					break;
				case 6:
					spWalletLogVo.setMingcheng("运费支出");
					break;
				case 7:
					spWalletLogVo.setMingcheng("现金券支出");
					break;
				case 8:
					spWalletLogVo.setMingcheng("微信手续费支出");
					if(spWalletLogVo.getSupportmetho()==null){
						spWalletLogVo.setDingdang("订单号:"+spWalletLogVo.getOrderInfoId2());	
					}
					break;
				case 9:
					spWalletLogVo.setMingcheng("提现手续费支出");
					spWalletLogVo.setDingdang("提现编号:"+spWalletLogVo.getSpWithDrawId());
					break;
				case 10:
					spWalletLogVo.setMingcheng("采购中心订单退款支出");
					break;
				case 11:
					spWalletLogVo.setMingcheng("微信充值支出");
					break;
				default:
					spWalletLogVo.setMingcheng("默认");
					break;
				}
				
				//收入/支出
				spWalletLogVo.setShouru("");
				spWalletLogVo.setZhichu(spWalletLogVo.getMoney().toString());
			}
			spWalletLogVo.setZhifuzhanhu(TradeWay.getName(spWalletLogVo.getSupportmetho().intValue()));
		}
	}







	/**
	 * 
	 * @Title: toSpWithDraw
	 * @Description:跳转到提现页面
	 * @param @return
	 * @return String
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping("/toSpWithDraw.do")
	public String toSpWithDraw(HttpServletRequest request, Model model) {
		try {
			Supplier supplier = getCurrentUser(Supplier.class, request);
			if(supplier == null)
				return "/login/index";
			supplier = suppliermgservice.selectByPrimaryKey(supplier.getId());
			FinWallet finWallet = walletService.selectOne(supplier.getId());
			if(finWallet.getRates() > 0){
				model.addAttribute("rates" , finWallet.getRates()*100+"%");
			}
			model.addAttribute("supplier", supplier);
			model.addAttribute("finWallet", finWallet);
			return "wallet/withdraw";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			// TODO 出错页
			return error(e.getMessage() , model , request);
		}
	}

	/**
	 * 
	 * @Title: toSpWithDrawDealLog
	 * @Description:跳转到提现详情页
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @throws
	 */
	@RequestMapping("/toSpWithDrawDealLog.do")
	public String toSpWithDrawDealLog(@RequestParam(required = true, value = "withDrawId") Long withDrawId, HttpServletRequest request, Model model) {
		try {
			
			Supplier supplier = getCurrentUser(Supplier.class, request);
			if(supplier == null)
				return "/login/index";
			supplier = suppliermgservice.selectByPrimaryKey(supplier.getId());
			SpWithDraw spWithDraw = spwalletmgservice.selectByPrimaryKey(withDrawId);
			
			model.addAttribute("supplier", supplier);
			model.addAttribute("spWithDraw", spWithDraw);


            switch (spWithDraw.getStatus()){
                case 1 : return "wallet/withdraw-ed";// 提现申请已经提交
                case 2 : return "wallet/withdraw-ing";// 提现申请正在处理
                case 3 : return "wallet/withdraw-ing";// 提现申请正在处理
                case 4 : return "wallet/withdraw-fail";// 提现失败
                case 5 : return "wallet/withdraw-su";// 提现成功
                case 6 : return "wallet/withdraw-fail";// 提现失败
                default: return error("交易异常" , model , request);
            }
//			switch (spWithDraw.getStatus()){
//				case WithDrawStatus.START.getIndex() : return "wallet/withdraw-ed";// 提现申请已经提交
//				case WithDrawStatus.AUDITING.getIndex(): return "wallet/withdraw-ing";// 提现申请正在处理
//				case WithDrawStatus.AUDITYES.getIndex() : return "wallet/withdraw-ing";// 提现申请正在处理
//				case WithDrawStatus.AUDITNO.getIndex() : return "wallet/withdraw-fail";// 提现失败
//				case WithDrawStatus.PAYYES.getIndex() : return "wallet/withdraw-su";// 提现成功
//				case WithDrawStatus.PAYNO.getIndex() : return "wallet/withdraw-fail";// 提现失败
//			}
//			if (spWithDraw.getStatus() == 1) {
//				return "wallet/withdraw-ed";// 提现申请已经提交
//			}
//
//			if (spWithDraw.getStatus() == 2 || spWithDraw.getStatus() == 4) {
//				return "wallet/withdraw-ing";// 提现申请正在处理
//			}
//
//			if (spWithDraw.getStatus() == 3) {
//				return "wallet/withdraw-fail";// 提现失败
//			}
//
//			if (spWithDraw.getStatus() == 5) {
//				return "wallet/withdraw-su";// 提现成功
//			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			// TODO 出错页
			return error(e.getMessage() , model , request);
		}
	}
	
	
	/**
	 * 
	* @Title: doWithDraw 
	* @Description:记录提现操作
	* @param @return
	* @return Object
	* @author 杨开泰  yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping(value="/doWithDraw.do",method=RequestMethod.GET)
	@ResponseBody
	public Object doWithDraw(@RequestParam(required=true,value="presentMoney")BigDecimal presentMoney,
			HttpServletRequest request , Model model) {
		try {
			Supplier supplier = getCurrentUser(Supplier.class, request);
			FinWallet finWallet = walletService.selectOne(supplier.getId());
			/**加锁**/
//			DistributedLock lock = new DistributedLock("192.168.1.12:2181" , 1000 , finWallet.getId());
//			if(!lock.tryLock()){
//				lock.createLock();
//			}
			// 判断输入金额的精度
			if (presentMoney.scale() > 2) {
				//map.put("msg", "提现失败");
				return ResponseUtils.sendMsg(false,"");
			}
            Map<String, Object> map = new HashMap<String, Object>();
			int i = presentMoney.compareTo(new BigDecimal(1));// 0相等 1大于 -1 小于
			int j = presentMoney.compareTo(finWallet.getBalance());
			
			if ((i == 0 || i == 1) && (j == 0 || j == -1)) {

				try {
					Long id = walletService.doWithDraw(finWallet.getId() , presentMoney , supplier.getId());
					/**解锁**/
//					lock.deleteLock();
					if (id != 0) {
						// 提现成功
						map.put("msg", "提现申请成功");
						map.put("status", id);
						return ResponseUtils.sendMsg(true, map);
					}
					map.put("msg", "提现失败");
					return ResponseUtils.sendMsg(false, map);
				}catch (Exception e){
					map.put("msg", e.getMessage());
					return ResponseUtils.sendMsg(false, map);
				}

			} else {
				BigDecimal b = new BigDecimal(1);
				if (presentMoney.compareTo(b) == -1) {
					map.put("msg", "提现失败,金额不能小于1");
					return ResponseUtils.sendMsg(false, map);
				}
				b = finWallet.getBalance();
				if (presentMoney.compareTo(b) == 1) {
					map.put("msg", "提现失败,金额不能大于余额");
					return ResponseUtils.sendMsg(false, map);
				}
                map.put("msg", "提现失败");
				return ResponseUtils.sendMsg(false, map);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return error(e.getMessage() , model , request);
		}
	}
	
}
