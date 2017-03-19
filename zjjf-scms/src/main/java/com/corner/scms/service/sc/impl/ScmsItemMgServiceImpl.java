package com.corner.scms.service.sc.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.ScmsItemMapper;
import com.corner.scms.dao.ScmsItemMgMapper;
import com.corner.scms.service.sc.ScmsItemMgService;
/**
 * 
* @ClassName: ScmsManagerMgServiceImpl 
* @Description: 商品信息处理 
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2015年12月28日 上午11:45:29 
*
 */
@Service
public class ScmsItemMgServiceImpl implements ScmsItemMgService {

	private static Logger logger = LoggerFactory.getLogger(ScmsItemMgServiceImpl.class);

	@Autowired
	ScmsItemMapper scmsItemMapper;
	
	@Autowired
	ScmsItemMgMapper scmsItemMgMapper;
	@Override
	public ModelMsg deleteObjects(String tableName, String[] array) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ScmsItem selectScmsItemById(String scmsItemId) {
		return scmsItemMapper.selectByPrimaryKey(scmsItemId);
	}
	@Override
	public ModelMsg updateScmsItemGoodsStockByOrderId(Map<String, Object> map) throws Exception{
		if(!map.containsKey("orderId"))
			return new ModelMsg(false, "未传入OrderId");
		else if(!map.containsKey("acType"))
			return new ModelMsg(false, "未传入acType操作类型,0-下单，1-取消订单，2-确认收货");
		logger.info("参数：订单号-{} ，操作类型-{}" , map.get("orderId") , map.get("acType"));
		int result = scmsItemMgMapper.updateScmsItemGoodsStockByOrderId(map);
		if(result == 0)
			return new ModelMsg(false, "修改失败");
		return new ModelMsg(true, "库存修改成功");
	}
}
