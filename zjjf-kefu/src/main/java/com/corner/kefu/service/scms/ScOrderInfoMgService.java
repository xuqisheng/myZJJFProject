package com.corner.kefu.service.scms;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.scms.ScOrderInfoMgRo;
import com.corner.kefu.beans.vo.sc.ScOrderInfoMgVo;
import com.corner.kefu.service.BaseService;

@Service
public interface ScOrderInfoMgService extends BaseService{
	@SuppressWarnings("rawtypes")
	Pager<Map> getScOrderDetailPageList(ScOrderInfoMgRo command);
	Pager<ScOrderInfoMgVo> getSupplerOrderDetailByOrder2(ScOrderInfoMgRo scOrderInfoMgRo);
	/**
	 * 
	 * @Title: updateScOrderInfo 
	 * @Description: 修改订单 
	 * @param @param command
	 * @param @return    设定文件
	 * @return ModelMsg    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	ModelMsg updateByPrimaryKeySelective(ScOrderInfo command);
	
	Map<String, Object> findOrderDetailSumByItemIdAndOrderId2(ScOrderInfoMgRo infoMgRo);
	
	/**
	 * 
	* @Title: selectScOrderInfoByUpId 
	* @Description: 通过UpId获取订单列表信息
	* @param @param upId
	* @param @return    设定文件 
	* @return List<ScOrderInfo>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScOrderInfo> selectScOrderInfoList(Map<String, Object> map);
}
