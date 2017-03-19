package com.corner.kefu.service.scms;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.kefu.service.BaseService;

@Service
public interface ScOrderDetailMgService extends BaseService{
	/**
	 * 
	* @Title: updateOrderDetail 
	* @Description: 修改订单内容 
	* @param @param scOrderDetail
	* @param @return    设定文件 
	* @return ModelMsg    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg updateOrderDetail(ScOrderDetail scOrderDetail);
	/**
	 * 
	* @Title: findOrderDetailList 
	* @Description: 查询订单详情表 
	* @param @param map
	* @param @return    设定文件 
	* @return List<ScOrderDetail>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScOrderDetail> findOrderDetailList(Map<String, Object> map);
}
