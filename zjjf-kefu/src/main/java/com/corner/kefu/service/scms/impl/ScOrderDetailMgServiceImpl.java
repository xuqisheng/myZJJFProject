package com.corner.kefu.service.scms.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.ScOrderDetailMapper;
import com.corner.kefu.dao.ScOrderDetailMgMapper;
import com.corner.kefu.service.scms.ScOrderDetailMgService;
/**
 * 
 * @ClassName: ScOrderDetailMgServiceImpl 
 * @Description: 订单信息处理
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2015年12月4日 下午6:08:25 
 *
 */
@Service
public class ScOrderDetailMgServiceImpl implements ScOrderDetailMgService {

	private static Logger logger = LoggerFactory.getLogger(ScOrderDetailMgServiceImpl.class);
	@Autowired
	ScOrderDetailMgMapper scOrderDetailMgMapper;
	@Autowired
	ScOrderDetailMapper scOrderDetailMapper;
	
	@Override
	public ModelMsg updateOrderDetail(ScOrderDetail scOrderDetail) {
		int result = scOrderDetailMapper.updateByPrimaryKeySelective(scOrderDetail);
		if(result == 0)
			return new ModelMsg(false, "订单修改失败");
		return new ModelMsg(true, "订单修改成功");
		
	}
	@Override
	public List<ScOrderDetail> findOrderDetailList(Map<String, Object> map) {
		return scOrderDetailMgMapper.findOrderDetailList(map);
	}
}
