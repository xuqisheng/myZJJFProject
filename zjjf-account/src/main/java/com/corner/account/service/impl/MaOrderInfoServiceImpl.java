package com.corner.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.account.beans.ro.MaOrderMgCondition;
import com.corner.account.beans.vo.MaOrderInfoMgVo;
import com.corner.account.dao.MaOrderInfoMgMapper;
import com.corner.account.service.MaOrderInfoService;
import com.corner.account.utils.BeanUtil;
import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.PlantWalletLog;
import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.WhWalletLog;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.MaOrderInfoMapper;

@Service
public class MaOrderInfoServiceImpl extends BaseServiceImpl implements MaOrderInfoService {
	@Autowired
	MaOrderInfoMgMapper maOrderInfoMgMapper;
	@Autowired
	MaOrderInfoMapper maOrderInfoMapper;
	@Override
	public Pager<MaOrderInfoMgVo> getMaOrderInfoList(MaOrderMgCondition command) {
		List<MaOrderInfoMgVo> list = maOrderInfoMgMapper.getMaOrderInfoPageList(command);
		int result = maOrderInfoMgMapper.getMaOrderInfoPageListSize(command);
		return new Pager<MaOrderInfoMgVo>(result , list);
	}
	
	/**
	 * 根据id获取经销商订单详情
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年1月25日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public MaOrderInfoMgVo getScOrderInfoById(String id) {
		MaOrderInfoMgVo maOrderInfoMgVo = BeanUtil.toObject(MaOrderInfoMgVo.class ,this.selectMaOrderInfoById(id));
		if (maOrderInfoMgVo != null) {
			maOrderInfoMgVo.setScOrderDetails(this.getOrderDetailList(false, null, null, null, maOrderInfoMgVo.getId(), "1"));
		}
		return maOrderInfoMgVo;
	}
	
	@Override
	public MaOrderInfo selectMaOrderInfoById(String id) {
		return maOrderInfoMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<ScOrderDetail> getOrderDetailList(boolean isDelete,String id,String orderId , String orderId2 ,String maOrderInfoId,String total){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orderId", orderId);
		map.put("orderId2", orderId2);
		map.put("maOrderInfoId", maOrderInfoId);
		map.put("isDelete", isDelete);
		map.put("total", total);
		return maOrderInfoMgMapper.findOrderDetailList(map);
	}

	@Override
	public ModelMsg submitMaOrderInfo(MaOrderInfo son) {
		Date actionTime = new Date();
		//		仓库入账流水--对公转账充值
		WhWalletLog whWalletLog = new WhWalletLog();
		whWalletLog.setWarehouseId(son.getWarehouseId());
		whWalletLog.setPayer((byte)10);//付款方 平台
		whWalletLog.setGeter((byte)34);//收款方 仓库
		whWalletLog.setActionType((byte)1);//actionType 收入
		whWalletLog.setOptType((byte)11);//optType 订单金额
		whWalletLog.setActionTime(actionTime);//操作时间
		whWalletLog.setTradeWay((byte)5);//支付平台
		whWalletLog.setOrderId(son.getId());//订单主键
		whWalletLog.setMoney(son.getOrderPrice());//操作金额
		
		long whQbGetId = maOrderInfoMgMapper.updateWhWalletByLog(whWalletLog);
		
		//		仓库出账流水--货款
		whWalletLog.setWarehouseId(son.getWarehouseId());
		whWalletLog.setPayer((byte)34);//付款方 仓库
		whWalletLog.setGeter((byte)10);//收款方 平台
		whWalletLog.setActionType((byte)2);
		whWalletLog.setOptType((byte)2);
		whWalletLog.setActionTime(actionTime);//操作时间
		whWalletLog.setTradeWay((byte)5);//支付平台
		whWalletLog.setOrderId(son.getId());//订单主键
		whWalletLog.setMoney(son.getOrderPrice().multiply(new BigDecimal("-1")));//操作金额
		long whQbPayId = maOrderInfoMgMapper.updateWhWalletByLog(whWalletLog);
		
//		平台出账--货款
		PlantWalletLog plantWalletLog = new PlantWalletLog();
		plantWalletLog.setPayer((byte)34);//付款方 平台
		plantWalletLog.setGeter((byte)10);//收款方 仓库
		plantWalletLog.setPlantWalletId("000000");//经销商主键
		plantWalletLog.setActionType((byte)1);//支出
		plantWalletLog.setOptType((byte)2);//订单金额
		plantWalletLog.setActionTime(actionTime);//操作时间
		plantWalletLog.setTradeWay((byte)5);//支付平台
		plantWalletLog.setOrderId(son.getId());//订单主键
		plantWalletLog.setSheetId(son.getId());//汇单主键
		plantWalletLog.setMoney(son.getOrderPrice());//操作金额
		maOrderInfoMgMapper.updatePlantWalletByLog(plantWalletLog);
		
		son.setWhPayStatus(true);
		son.setWhPayMetho(Byte.valueOf("6"));
		son.setWhPayTime(actionTime);
		son.setWhQbPayId(whQbPayId);
		son.setWhQbPayNum(son.getOrderPrice());
		son.setWhQbGetId(whQbGetId);
		son.setWhQbGetNum(son.getOrderPrice());
		int result = maOrderInfoMapper.updateByPrimaryKeySelective(son);
		if(result == 0)
			return new ModelMsg(false, "订单确认收款失败");
		return new ModelMsg(true, "订单确认收款成功");
	}
}
