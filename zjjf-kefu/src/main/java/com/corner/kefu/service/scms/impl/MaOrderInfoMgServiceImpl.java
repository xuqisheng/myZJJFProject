package com.corner.kefu.service.scms.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.MaOrderInfoMapper;
import com.corner.kefu.beans.ro.scms.MaOrderInfoMgRo;
import com.corner.kefu.beans.vo.MaOrderInfoVo;
import com.corner.kefu.dao.MaOrderInfoMgMapper;
import com.corner.kefu.service.scms.MaOrderInfoMgService;
/**
 * 
 * @ClassName: ScOrderInfoMgServiceImpl 
 * @Description: 订单信息处理
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2015年12月4日 下午6:08:25 
 *
 */
@Service
public class MaOrderInfoMgServiceImpl implements MaOrderInfoMgService {
	
	private static Logger logger = LoggerFactory.getLogger(MaOrderInfoMgServiceImpl.class);
	
	@Autowired
	MaOrderInfoMgMapper maOrderInfoMgMapper;
	@Autowired
	MaOrderInfoMapper maOrderInfoMapper;
	@Override
	public Pager<MaOrderInfoVo> getPageList(MaOrderInfoMgRo command) {
		List<MaOrderInfoVo> list = maOrderInfoMgMapper.getPageList(command);
		int size = maOrderInfoMgMapper.getPageListSize(command);
		return new Pager<MaOrderInfoVo>(size , list);
	}
	@Override
	public MaOrderInfo selectByPrimaryKey(String id) {
		return maOrderInfoMapper.selectByPrimaryKey(id);
	}
	@Override
	public ModelMsg updateByPrimaryKeySelective(MaOrderInfo info) {
		int result = maOrderInfoMapper.updateByPrimaryKeySelective(info);
		if(result == 0)
			return new ModelMsg(false, "订单修改失败");
		return new ModelMsg(true, "订单修改成功");
	}
}
