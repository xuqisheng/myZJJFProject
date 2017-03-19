package com.corner.scms.service.sc;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.scms.service.BaseService;
/**
 * 
 * @ClassName: ScmsOrderInfoMgServiceImpl 
 * @Description: 商品信息处理
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2015年12月28日 上午11:45:29 
 *
 */
@Service
public interface ScmsItemMgService extends BaseService{

	ScmsItem selectScmsItemById(String scmsItemId);
	
	/**
	* @Title: updateScmsItemGoodsStockByOrderId 
	* @Description: 修改scmsItem销量通过orderId
	* @param map:
	* @param:orderId订单号
	* @param:actype（0-下单，1-取消订单，2-确认收货）类型
	* @return	设定文件 
	* @return int    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	ModelMsg updateScmsItemGoodsStockByOrderId(Map<String, Object> map)  throws Exception;
}
