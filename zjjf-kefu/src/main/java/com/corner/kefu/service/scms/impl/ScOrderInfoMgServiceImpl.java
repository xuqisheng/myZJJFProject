package com.corner.kefu.service.scms.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ScOrderInfoMapper;
import com.corner.core.dao.ScmsItemMapper;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.utils.JSONUtil;
import com.corner.kefu.beans.ro.scms.ScOrderInfoMgRo;
import com.corner.kefu.beans.vo.ScOrderDetailVo;
import com.corner.kefu.beans.vo.sc.ScOrderInfoMgVo;
import com.corner.kefu.dao.ScOrderDetailMgMapper;
import com.corner.kefu.dao.ScOrderInfoMgMapper;
import com.corner.kefu.dao.ScmsGroupMapMgMapper;
import com.corner.kefu.dao.ScmsItemMgMapper;
import com.corner.kefu.dao.ScmsManagerMgMapper;
import com.corner.kefu.service.scms.ScOrderInfoMgService;
/**
 * 
 * @ClassName: ScOrderInfoMgServiceImpl 
 * @Description: 订单信息处理
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2015年12月4日 下午6:08:25 
 *
 */
@Service
public class ScOrderInfoMgServiceImpl implements ScOrderInfoMgService {

	private static Logger logger = LoggerFactory.getLogger(ScOrderInfoMgServiceImpl.class);

	@Autowired
	ScOrderInfoMgMapper scOrderInfoMgMapper;
	
	@Autowired
	ScOrderInfoMapper scOrderInfoMapper;
	@Autowired
	ScOrderDetailMgMapper scOrderDetailMgMapper;
	
	@Autowired
	ScmsItemMgMapper scmsItemMgMapper;
	@Autowired
	ScmsItemMapper scmsItemMapper;
	@Autowired
	ScmsGroupMapMgMapper scmsGroupMapMgMapper;
	@Autowired
	ScmsManagerMgMapper scmsManagerMgMapper;
	@Autowired
	SupplierMapper supplierMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager<Map> getScOrderDetailPageList(ScOrderInfoMgRo command) {
		logger.info("订单信息查询开始:"+JSONUtil.objectToJSONString(command));
		Map map = new HashMap();
		
		map.put("itemBaseId", command.getItemBaseId());
		map.put("maOrderInfoId", command.getMaOrderInfoId());
		map.put("sortName", "soi.addTime");
		map.put("sortOrder", "asc");
		map.put("pageSize", command.getPageSize());
		map.put("pageIndex", command.getPageIndex());
		
		List<Map> list = scOrderInfoMgMapper.getDetailPageList(map);
		int size = scOrderInfoMgMapper.getDetailPageListSize(map);
		return new Pager<Map>(size,list);
	}
	/**
     * 根据采购单号查出下单的批发商详情页面
    * @Title
    * @Description: TODO 
    * @param @param scOrderInfoMgRo
    * @param @return
    * @2016年1月29日     
    * @return
    * @throws
     */
	@Override
	public Pager<ScOrderInfoMgVo> getSupplerOrderDetailByOrder2(ScOrderInfoMgRo scOrderInfoMgRo){
    	int num = scOrderInfoMgMapper.getSupplerOrderDetailCount(scOrderInfoMgRo);
		List<ScOrderInfoMgVo> ScOrderInfoMgVoList = scOrderInfoMgMapper.getSupplerOrderDetail(scOrderInfoMgRo);
		for (ScOrderInfoMgVo scOrderInfoMgVo : ScOrderInfoMgVoList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", scOrderInfoMgVo.getOrderId());
			map.put("maOrderInfoId", scOrderInfoMgRo.getMaOrderInfoId());
			List<ScOrderDetail> scOrderDetails = scOrderDetailMgMapper.findOrderDetailList(map);
			int countNumber = 0;
			BigDecimal totalPrice = new BigDecimal(0);
			for (ScOrderDetail scOrderDetail : scOrderDetails) {
				countNumber = countNumber + scOrderDetail.getQuantity();
				totalPrice = totalPrice.add(scOrderDetail.getTotalPrice());
			}
			scOrderInfoMgVo.setScOrderDetailVos(scOrderDetails);
			scOrderInfoMgVo.setGoodsPrice(totalPrice);
			scOrderInfoMgVo.setCountNumber(countNumber);
		}
		return new Pager<ScOrderInfoMgVo>(num, ScOrderInfoMgVoList);
	}
	@Override
	public Map<String, Object> findOrderDetailSumByItemIdAndOrderId2(ScOrderInfoMgRo infoMgRo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemBaseId", infoMgRo.getItemBaseId());
		map.put("maOrderInfoId", infoMgRo.getMaOrderInfoId());
		return scOrderInfoMgMapper.findOrderDetailSumByItemIdAndOrderId2(map);
	}
	@Override
	public ModelMsg updateByPrimaryKeySelective(ScOrderInfo command) {
		if(scOrderInfoMapper.updateByPrimaryKeySelective(command) == 0)
			return new ModelMsg(false, "订单修改失败");
		return  new ModelMsg(true, "订单修改成功");
	}
	@Override
	public List<ScOrderInfo> selectScOrderInfoList(Map<String, Object> map) {
		return scOrderInfoMgMapper.selectScOrderInfoList(map);
	}
}
