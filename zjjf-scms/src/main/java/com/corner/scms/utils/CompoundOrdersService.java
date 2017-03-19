/**   
* @Title: CompoundOrdersService.java 
* @Package com.corner.scms.utils 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月28日 下午6:48:42 
* @version V1.0   
*/

package com.corner.scms.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.dao.MaOrderInfoMapper;
import com.corner.core.dao.ScOrderInfoMapper;
import com.corner.core.dao.ScmsGroupMapper;
import com.corner.core.dao.ScmsManagerMapper;
import com.corner.core.dao.ScmsWarehouseMapper;
import com.corner.core.utils.CreateOrderIdUtil;
import com.corner.scms.dao.MaOrderInfoMgMapper;
import com.corner.scms.dao.ScOrderDetailMgMapper;
import com.corner.scms.dao.ScOrderInfoMgMapper;
import com.corner.scms.dao.ScmsSupplierMgMapper;

/**
 * @ClassName: CompoundOrdersService
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月28日 下午6:48:42
 * 
 */
@Component
public class CompoundOrdersService {
      
	@Autowired
	private ScOrderInfoMgMapper scOrderInfoMgMapper;
	@Autowired
	private ScOrderDetailMgMapper scOrderDetailMgMapper;
	@Autowired
	private ScOrderInfoMapper scOrderInfoMapper;
	@Autowired
	private ScmsManagerMapper scmsManagerMapper;
	@Autowired
	private ScmsGroupMapper scmsGroupMapper;
	@Autowired
	private ScmsWarehouseMapper scmsWarehouseMapper;
	@Autowired
	private ScmsSupplierMgMapper scmsSupplierMgMapper;
	@Autowired
	private MaOrderInfoMgMapper maOrderInfoMgMapper;
	@Autowired
	private MaOrderInfoMapper maOrderInfoMapper;
	

	public synchronized  void compoundOrders(Map<String, Object> map) throws Exception {
		
		ScOrderInfo scOrderInfoLevel1 = (ScOrderInfo) map.get("scOrderInfo");
		List<ScOrderInfo> scOrderInfoLevel2List = scOrderInfoMgMapper.getLevel2List(scOrderInfoLevel1);
		for (ScOrderInfo scOrderInfoLevel2 : scOrderInfoLevel2List) {
			MaOrderInfo maOrderInfo = maOrderInfoMgMapper.selectMaOrderInfo(scOrderInfoLevel2);
			if(maOrderInfo==null){
				maOrderInfo = new MaOrderInfo();
				maOrderInfo.setOrderId(CreateOrderIdUtil.dateToString());//orderId
				maOrderInfo.setAddTime(new Date());//addTime
				maOrderInfo.setGroupId(scOrderInfoLevel2.getGroupId());//groupId
				maOrderInfo.setGroupName(scOrderInfoLevel2.getGroupName());//groupName
				ScmsWarehouse scmsWarehouse = scmsWarehouseMapper.selectByPrimaryKey(scOrderInfoLevel2.getWarehouseId());
				maOrderInfo.setConsignee(scmsWarehouse.getBranderName());//仓库负责人
				maOrderInfo.setMobile(scmsWarehouse.getBranderTel());//仓库电话
				maOrderInfo.setAddress(scmsWarehouse.getHouseAddress());//仓库
				maOrderInfo.setWarehouseId(scmsWarehouse.getId());//仓库id
				maOrderInfo.setWarehouseName(scmsWarehouse.getName());
				maOrderInfo.setManagerId(scOrderInfoLevel2.getManagerId());//经销商id
				maOrderInfo.setManagerName(scOrderInfoLevel2.getManagerName());//经销商名字
				maOrderInfo.setManagerTel(scOrderInfoLevel2.getManagerTel());//经销商手机号
				BigDecimal orderPrice = maOrderInfo.getOrderPrice().add(scOrderInfoLevel2.getOrderPrice());
				maOrderInfo.setOrderPrice(orderPrice);
				BigDecimal goodsPrice = maOrderInfo.getGoodsPrice().add(scOrderInfoLevel2.getGoodsPrice());
				maOrderInfo.setGoodsPrice(goodsPrice);
				BigDecimal markePrice = maOrderInfo.getMarkePrice().add(scOrderInfoLevel2.getMarkePrice());
				maOrderInfo.setMarkePrice(markePrice);
				maOrderInfo.setWhFreight(scOrderInfoLevel2.getFreight());
				maOrderInfoMapper.insertSelective(maOrderInfo);
				scOrderInfoLevel2.setMaOrderInfoId(maOrderInfo.getId());
			}else {
				BigDecimal orderPrice = maOrderInfo.getOrderPrice().add(scOrderInfoLevel2.getOrderPrice());
				maOrderInfo.setOrderPrice(orderPrice);
				BigDecimal goodsPrice = maOrderInfo.getGoodsPrice().add(scOrderInfoLevel2.getGoodsPrice());
				maOrderInfo.setGoodsPrice(goodsPrice);
				BigDecimal markePrice = maOrderInfo.getMarkePrice().add(scOrderInfoLevel2.getMarkePrice());
				maOrderInfo.setMarkePrice(markePrice);
				BigDecimal freight = maOrderInfo.getWhFreight().add(scOrderInfoLevel2.getFreight());
				maOrderInfo.setWhFreight(freight);
				maOrderInfoMapper.updateByPrimaryKey(maOrderInfo);
				scOrderInfoLevel2.setMaOrderInfoId(maOrderInfo.getId());
			}
			List<ScOrderDetail>list = scOrderDetailMgMapper.getScOrderDetailListByOrderId(scOrderInfoLevel2);
			for (ScOrderDetail scOrderDetail : list) {
				scOrderDetail.setMaOrderInfoId(maOrderInfo.getId());
			}
			scOrderDetailMgMapper.batchUpdateMaOrderInfoId(list);
			scOrderInfoMapper.updateByPrimaryKeySelective(scOrderInfoLevel2);
		}
	}
}
