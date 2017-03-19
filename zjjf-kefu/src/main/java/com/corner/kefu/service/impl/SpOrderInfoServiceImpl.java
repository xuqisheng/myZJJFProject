package com.corner.kefu.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.corner.core.beans.AcActionRecord;
import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.AcActionRecordMapper;
import com.corner.core.dao.SpOrderInfoMapper;
import com.corner.kefu.beans.ro.CheckBillCondition;
import com.corner.kefu.beans.ro.SpOrderInfoRo;
import com.corner.kefu.beans.ro.SpOrderMgCondition;
import com.corner.kefu.beans.vo.sp.SpOrderInfoVo;
import com.corner.kefu.dao.SpOrderInfoMgMapper;
import com.corner.kefu.dao.sp.SpOrderDetailMgMapper;
import com.corner.kefu.service.SpOrderInfoService;
import com.corner.kefu.utils.MoneyTool;

@Service
public class SpOrderInfoServiceImpl extends BaseServiceImpl implements SpOrderInfoService {

	@Autowired
	SpOrderInfoMgMapper spOrderInfoMgMapper;
	@Autowired
	SpOrderDetailMgMapper spOrderDetailMgMapper;
	@Autowired
	SpOrderInfoMapper spOrderInfoMapper;
	@Autowired
	AcActionRecordMapper acActionRecordMapper;

	@Override
	public List<SpOrderInfo> getSpOrderInfoByOrderId(SpOrderMgCondition command) {
		return spOrderInfoMgMapper.getSpOrderInfoByOrderId(command);
	}

	@Override
	public List<SpOrderDetail> getSpOrderDetailByOrderId(SpOrderMgCondition command) {
		return spOrderInfoMgMapper.getSpOrderDetailByOrderId(command);
	}

	@Override
	public void getSpOrderAllForPage(String spOrderId, Model model) {
		SpOrderMgCondition command =new SpOrderMgCondition();
		command.setOrderId(spOrderId);
		command.setStatus((byte)5);
		command.setIsDelete(false);
		List<SpOrderInfo> listInfo=spOrderInfoMgMapper.getSpOrderInfoByOrderId(command);
		if(listInfo !=null && !listInfo.isEmpty()){
			command.setStatus(null);
			List<SpOrderDetail> listDetail=spOrderInfoMgMapper.getSpOrderDetailByOrderId(command);
			if(listDetail !=null && !listDetail.isEmpty()){
				model.addAttribute("spOrderInfo", listInfo.get(0));
				model.addAttribute("listDetail", listDetail);
				model.addAttribute("bigTotalMoney", MoneyTool.change(listInfo.get(0).getOrderPrice().doubleValue()));
			}
		}
	}

	@Override
	public ModelMsg updateAcStatus(CheckBillCondition command) {
		if(command ==null || StringUtils.isEmpty(command.getSpOrderIds())){
			return new ModelMsg(false, "参数错误");
		}else{
			command.setSpOrderIdArray(command.getSpOrderIds().split(","));
			int count = spOrderInfoMgMapper.updateSpOrderInfoBatch(command);
			if (count > 0) {
				return new ModelMsg(true, "修改成功");
			} else {
				return new ModelMsg(false, "修改失败");
			}
		}
		
	}

	@Override
	public List<SpOrderInfo> getSpOrderInfospc(SpOrderInfoRo spOrderInfoRo) {
		return spOrderInfoMgMapper.getSpOrderInfospc(spOrderInfoRo);
	}

	@Override
	public Integer getSpOrderInfospcCount(SpOrderInfoRo spOrderInfoRo) {
		return spOrderInfoMgMapper.selectSpOrderInfoSelectivepcCount(spOrderInfoRo);
	}

	@Override
	public SpOrderInfo getSpOrderInfo(SpOrderInfoRo spOrderInfoRo) {
		List<SpOrderInfo> list = spOrderInfoMgMapper.selectSpOrderInfoSelective(spOrderInfoRo);
		return list.size() == 1 ? list.get(0) : null;
	}

	@Override
	public List<SpOrderDetail> getOrderDetail(String orderid) {
		return spOrderDetailMgMapper.getOrderDetailByOrderId(orderid);
	}

	@Override
	public List<SpOrderInfoVo> selectSpOrderInfoFinace(SpOrderInfoRo spOrderInfoRo) {
		List<SpOrderInfoVo> list=spOrderInfoMgMapper.selectSpOrderInfoFinace(spOrderInfoRo);
		DecimalFormat df = new DecimalFormat("###0.000%");
		for (Iterator<SpOrderInfoVo> iterator = list.iterator(); iterator.hasNext();) {
			SpOrderInfoVo checkBillVo = iterator.next();
			if(checkBillVo !=null && checkBillVo.getSpZfee() !=null && checkBillVo.getSpOrderPrice() !=null ){
				if(checkBillVo.getSpOrderPrice().compareTo(new BigDecimal(0)) == 0 ){
					checkBillVo.setZfeeRate("0.000%");
				}else{
					BigDecimal result=checkBillVo.getSpZfee().divide(checkBillVo.getSpOrderPrice(),6,BigDecimal.ROUND_HALF_UP);
					checkBillVo.setZfeeRate(df.format(result));
				}
			}
		}
		return list;
	}

	@Override
	public Integer selectSpOrderCountOfFinace(SpOrderInfoRo spOrderInfoRo) {
		return spOrderInfoMgMapper.selectSpOrderCountOfFinace(spOrderInfoRo);
	}

	@Override
	public List<SpOrderInfoVo> selectFinaceSpOrderInfo(SpOrderInfoRo spOrderInfoRo) {
		List<SpOrderInfoVo> list = spOrderInfoMgMapper.selectFinaceSpOrderInfo(spOrderInfoRo);
		DecimalFormat df = new DecimalFormat("###0.000%");
		for (Iterator<SpOrderInfoVo> iterator = list.iterator(); iterator.hasNext();) {
			SpOrderInfoVo checkBillVo = iterator.next();
			if(checkBillVo !=null && checkBillVo.getSpZfee() !=null && checkBillVo.getSpOrderPrice() !=null ){
				if(checkBillVo.getSpOrderPrice().compareTo(new BigDecimal(0)) == 0 ){
					checkBillVo.setZfeeRate("0.000%");
				}else{
					BigDecimal result=checkBillVo.getSpZfee().divide(checkBillVo.getSpOrderPrice(),6,BigDecimal.ROUND_HALF_UP);
					checkBillVo.setZfeeRate(df.format(result));
				}
			}
		}
		return list;
	}

	@Override
	public Integer selectCountFinaceSpOrderInfo(SpOrderInfoRo spOrderInfoRo) {
		return spOrderInfoMgMapper.selectCountFinaceSpOrderInfo(spOrderInfoRo);
	}

	@Override
	public List<SpOrderInfoVo> selectSpOrderInfoJiesuan(SpOrderInfoRo spOrderInfoRo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpOrderInfoVo> selectSpOrderInfoJiesuanDe(SpOrderInfoRo spOrderInfoRo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateGetOrderAcStatus(SpOrderInfo spOrderInfo) {
		// TODO Auto-generated method stub
		return spOrderInfoMapper.updateByPrimaryKeySelective(spOrderInfo);
	}

	@Override
	public void addAcSystemAction(AcActionRecord acrecord) {
		// TODO Auto-generated method stub
		acActionRecordMapper.insertSelective(acrecord);
	}

	@Override
	public Integer getSpOrderInfoCountspc(SpOrderInfoRo spOrderInfoRo) {
		return this.spOrderInfoMgMapper.selectSpOrderInfoCountpc(spOrderInfoRo);
		
	}
	
	/**
	 * 
	* @Title: getOrdersFromSpOrderActiveMap 
	* @Description:从SpOrderActiveMap表中根据活动id和批发商id获取订单信息
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<SpOrderInfo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public Pager<SpOrderInfo> getOrdersFromSpOrderActiveMap(Map<String, Object> map) throws Exception {
		List<SpOrderInfo> list = spOrderInfoMgMapper.getOrdersFromSpOrderActiveMap(map);
		Integer count = spOrderInfoMgMapper.getCountOrdersFromSpOrderActiveMap(map);
		return new Pager<SpOrderInfo>(count, list);
	}
}
