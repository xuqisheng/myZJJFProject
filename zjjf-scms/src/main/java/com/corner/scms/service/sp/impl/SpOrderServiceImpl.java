package com.corner.scms.service.sp.impl;

import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderDetailExample;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.SpOrderInfoExample;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.SpOrderDetailMapper;
import com.corner.core.dao.SpOrderInfoMapper;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.erp.SpOrderInfoMgRo;
import com.corner.scms.service.sp.SpOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @ClassName: SpOrderServiceImpl
 * @Description: 订单信息处理
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2016年9月19日14:11:52
 *
 */
@Service
public class SpOrderServiceImpl implements SpOrderService {

	private static Logger logger = LoggerFactory.getLogger(SpOrderServiceImpl.class);
	@Autowired
	private SpOrderInfoMapper spOrderInfoMapper;
	@Autowired
	private SpOrderDetailMapper spOrderDetailMapper;

	@Override
	public ModelMsg deleteObjects(String tableName, String[] array) {
		return null;
	}

	@Override
	public SpOrderInfo getInfoById(String id) {
		return spOrderInfoMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<SpOrderInfo> getInfoListByPId(String pId) {
		SpOrderInfoExample example = new SpOrderInfoExample();
		example.createCriteria().andPIdEqualTo(pId).andIsDeleteEqualTo(false);
		return spOrderInfoMapper.selectByExample(example);
	}

	@Override
	public SpOrderDetail getDetailById(String id) {
		return spOrderDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateDetail(SpOrderDetail detail) throws Exception {
		if(spOrderDetailMapper.updateByPrimaryKeySelective(detail) != 1){
			throw new Exception("修改失败");
		}
	}

	@Override
	public void updateInfo(SpOrderInfo info) throws Exception{
		if(spOrderInfoMapper.updateByPrimaryKeySelective(info) != 1){
			throw new Exception("修改失败");
		}
	}

	@Override
	public List<SpOrderDetail> getDetailByOrderId(String orderId) {
		SpOrderDetailExample example = new SpOrderDetailExample();
		example.createCriteria().andOrderIdEqualTo(orderId);
		return spOrderDetailMapper.selectByExample(example);
	}

	@Override
	public List<SpOrderDetail> getDetailByOrderId2(String orderId2) {
		SpOrderDetailExample example = new SpOrderDetailExample();
		example.createCriteria().andOrderId2EqualTo(orderId2);
		return spOrderDetailMapper.selectByExample(example);
	}

	@Override
	public Pager<SpOrderInfo> getSpOrderInfoList(SpOrderInfoMgRo ro){
		SpOrderInfoExample example = new SpOrderInfoExample();
		example.setOrderByClause("addTime desc");
		SpOrderInfoExample.Criteria criteria= example.createCriteria();
		SpOrderInfoExample.Criteria criteria2= example.or();

		criteria.andLevelEqualTo(Byte.valueOf("2"));
		criteria.andStatusBetween(Byte.valueOf("2") , Byte.valueOf("3"));
		criteria.andIsOutStockEqualTo(false);
		criteria.andSupplierIdEqualTo(ro.getSupplierId());
		criteria.andIsDeleteEqualTo(false);

		criteria2.andLevelEqualTo(Byte.valueOf("2"));
		criteria2.andStatusBetween(Byte.valueOf("2") , Byte.valueOf("3"));
		criteria2.andIsOutStockEqualTo(false);
		criteria2.andSupplierIdEqualTo(ro.getSupplierId());
		criteria.andIsDeleteEqualTo(false);

		if(!StringUtil.stringIsNullOrEmpty(ro.getKeyStr())) {
			criteria2.andMobileLike("%" + ro.getKeyStr() + "%");
			criteria.andOrderIdLike("%" + ro.getKeyStr() + "%");
		}
		Page page = PageHelper.startPage(ro.getPageIndex() == 0 ? 1 : (ro.getPageIndex()/ro.getPageSize())+1 , ro.getPageSize());
		List<SpOrderInfo> list = spOrderInfoMapper.selectByExample(example);
		return new Pager<SpOrderInfo>((int)page.getTotal(),list);
	}

	@Override
	public List<SpOrderDetail> getOtherDetail(String orderId, String orderId2, List<String> ids) {
		SpOrderDetailExample example = new SpOrderDetailExample();
		for (String id : ids) {
			SpOrderDetailExample.Criteria criteria= example.createCriteria();
			if(!StringUtil.stringIsNullOrEmpty(orderId)){
				criteria.andOrderIdEqualTo(orderId);
			}
			if(ids != null && ids.size() >0){
				criteria.andIdNotIn(ids);
			}
			if(!StringUtil.stringIsNullOrEmpty(orderId2)){
				criteria.andOrderId2EqualTo(orderId2);
			}
			example.or(criteria);
		}
		return spOrderDetailMapper.selectByExample(example);
	}
}
