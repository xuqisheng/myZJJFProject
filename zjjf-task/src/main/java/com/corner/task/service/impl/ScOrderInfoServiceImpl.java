package com.corner.task.service.impl;

import com.corner.task.beans.PlantWalletLog;
import com.corner.task.beans.ScOrderInfo;
import com.corner.task.beans.SpWalletLog;
import com.corner.task.beans.Supplier;
import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.config.Constants;
import com.corner.task.dao.ScOrderInfoMapper;
import com.corner.task.dao.SupplierMapper;
import com.corner.task.dao.mg.ScOrderInfoMgMapper;
import com.corner.task.service.PlantWalletService;
import com.corner.task.service.ScOrderInfoService;
import com.corner.task.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ScOrderInfoServiceImpl implements ScOrderInfoService {
	private static Logger logger = LoggerFactory.getLogger(ScOrderInfoServiceImpl.class);
	@Autowired
	PlantWalletService plantWalletService;
	@Autowired
	SupplierMapper supplierMapper;
	@Autowired
	ScOrderInfoMgMapper scOrderInfoMgMapper;
	@Autowired
	ScOrderInfoMapper scOrderInfoMapper;


	@Override
	public ModelMsg bakSpShoppingCart(){
		Integer num = scOrderInfoMgMapper.bakSpShoppingCart();
		return new ModelMsg(true , "备份SpShoppingCart记录"+num);
	}
	/**
	 * 
	* Title: deleteNoPayOrderInfo 
	* Description:24小时取消的订单
	* @param taskParams
	* @throws Exception
	 */
	@Override
	public ModelMsg clearOrder(String taskParams) throws Exception{
		List<ScOrderInfo> list = scOrderInfoMgMapper.getAllNoPayOrderInfoList();
		ModelMsg modelMsg = new ModelMsg(true, "无订单需要取消！");
		if(list == null || list.size() == 0){
			logger.error("无订单需要取消！");
		}else{
			for (ScOrderInfo scOrderInfo : list) {
				logger.error("需要处理的订单：订单号{}，ID{}" , scOrderInfo.getOrderId() , scOrderInfo.getId());
				if (scOrderInfo.getIsUsedBalance() != null && scOrderInfo.getIsUsedBalance() == true) {
					// 如果订单已经用钱包支付过了,则要归还用户钱包钱
					Supplier supplier = supplierMapper.selectByPrimaryKey(scOrderInfo.getSupplierId());
					BigDecimal bigDecimal = scOrderInfo.getBalanceUsedNum();// 使用钱包支付的金额

					// 生成钱包交易记录
					SpWalletLog spWalletLog = new SpWalletLog();
					spWalletLog.setSpId(supplier.getId());// 批发商id
					spWalletLog.setActionType(new Byte("1"));// 支出
					spWalletLog.setOptType(new Byte("10"));// 退款
					spWalletLog.setActionTime(new Date());// 操作时间
					spWalletLog.setOrderId(scOrderInfo.getId());// 订单主键
					spWalletLog.setMoney(bigDecimal);// 操作金额
					spWalletLog.setPayer(new Byte("10"));
					spWalletLog.setGeter(new Byte("32"));
					modelMsg = plantWalletService.updateSupplerWalletAndLog(spWalletLog);
					if(!modelMsg.isSuccess()){
						logger.error("批发商钱包入账失败:{}", modelMsg.getMessage());
						return modelMsg;
					}
					
					/**平台钱包出账**/
					PlantWalletLog plantWalletLog = new PlantWalletLog();
					plantWalletLog.setPlantWalletId(Constants.PLANTWALLET_ID_TRADE);
					plantWalletLog.setActionTime(new Date());// 操作时间
					plantWalletLog.setActionType(new Byte("2"));// 收入
					plantWalletLog.setPayer(new Byte("10"));//付款方 批发商
					plantWalletLog.setGeter(new Byte("32"));//收款方
					plantWalletLog.setOrderId(scOrderInfo.getpId());// 订单PId
					plantWalletLog.setOptType(new Byte("10"));// 进货
					plantWalletLog.setIsDelete(false);
					/**平台钱包出账**/
					plantWalletLog.setMoney(bigDecimal.multiply(new BigDecimal("-1")));
					modelMsg = plantWalletService.updatePlantWalletAndLog(plantWalletLog);
					if(!modelMsg.isSuccess()){
						logger.error("平台流水出账失败:{}", modelMsg.getMessage());
						return modelMsg;
					}
				}
				scOrderInfo.setStatus(Byte.valueOf("6"));
				scOrderInfo.setIsDelete(true);
				scOrderInfo.setClosemsg("24小时取消订单");
				int result = scOrderInfoMapper.updateByPrimaryKeySelective(scOrderInfo);
				if(result == 0)
					modelMsg = new ModelMsg(false, "修改订单状态失败");
				modelMsg = new ModelMsg(true, "无订单需要取消！");
			}
		}
		return modelMsg;
	}
}
