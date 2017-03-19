package com.corner.task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.task.beans.SpOrderInfo;
import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.beans.ro.OrderRo;
import com.corner.task.dao.TaskMapper;
import com.corner.task.dao.mg.SpOrderInfoMgMapper;
import com.corner.task.service.SpOrderInfoService;
import com.corner.task.util.HttpRequestUtils;
import com.corner.task.util.JSONUtil;
import com.corner.task.util.PropertiesCacheUtil;
import com.corner.task.util.SpringContextHolder;
import com.corner.task.util.StringUtil;

@Service
public class SpOrderInfoServiceImpl implements SpOrderInfoService {
	private static Logger logger = LoggerFactory.getLogger(SpOrderInfoServiceImpl.class);
	@Autowired
	SpOrderInfoMgMapper spOrderInfoMgMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ModelMsg clearOrder(String taskParams){
		Map map = new HashMap();
		String url ="";
		ModelMsg modelMsg = new ModelMsg(true, "无订单需要取消！");
		if(!StringUtil.stringIsNullOrEmpty(taskParams)){
			logger.info("有参数传入方法：{}" , taskParams);
			map = JSONUtil.JSONToObject(taskParams, Map.class);
			if(map.containsKey("url"))
				url = map.get("url").toString();
			else
				return new ModelMsg(false, "缺少请求地址");
		}else{
			url = PropertiesCacheUtil.getValue(PropertiesCacheUtil.SYSTEM_URL, PropertiesCacheUtil.SYSTEM);
			url += PropertiesCacheUtil.getValue(PropertiesCacheUtil.SYSTEM_SPORDERREVOKE, PropertiesCacheUtil.SYSTEM);
		}
		List<SpOrderInfo> list = spOrderInfoMgMapper.getAllNoPayOrderInfoList(map);
		if(list == null || list.size() == 0){
			logger.error("无订单需要取消！");
		}else{
			for (SpOrderInfo spOrderInfo : list) {
				Object object = HttpRequestUtils.httpPost(url + "?id="+spOrderInfo.getId() , null);
				Map map2 = JSONUtil.JSONToObject(JSONUtil.objectToJSONString(object), Map.class);
				if(map2.containsKey("success")){
					modelMsg.setMessage(map2.get("message").toString());
					modelMsg.setSuccess((Boolean)map2.get("success"));
				}else{
					modelMsg.setSuccess(false);
					modelMsg.setMessage("未解析到对应字段");
				}
				if(!modelMsg.isSuccess())
					break;

			}
		}
		return modelMsg;
	}

	@Override
	public List<Map<String, Object>> selectSpOrderSales(OrderRo orderRo) {
		return spOrderInfoMgMapper.selectSpOrderSales(orderRo);
	}
}
