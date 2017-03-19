package com.corner.account.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.core.dao.SpOrderInfoMapper;
import com.corner.core.utils.DateUtil;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.corner.account.beans.ro.SpOrderMgCondition;
import com.corner.account.config.SessionConfig;
import com.corner.account.dao.SpOrderInfoMgMapper;
import com.corner.account.service.SpOrderInfoService;
import com.corner.account.utils.MoneyTool;
import com.corner.core.beans.AcActionRecord;
import com.corner.core.beans.Accounter;
import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.AcActionRecordMapper;
import com.corner.core.utils.json.JacksonUtil;

@Service
public class SpOrderInfoServiceImpl extends BaseServiceImpl implements SpOrderInfoService {

	@Autowired
	SpOrderInfoMgMapper spOrderInfoMgMapper;
	@Autowired
	SpOrderInfoMapper spOrderInfoMapper;

	@Autowired
	AcActionRecordMapper acActionRecordMapper;

	@Override
	public Pager<SpOrderInfo> getSpOrderInfoList(SpOrderMgCondition command) {
		List<SpOrderInfo> list=spOrderInfoMgMapper.getSpOrderInfoByCondition(command);
		int size = spOrderInfoMgMapper.getSpOrderInfoByConditionCount(command);
		return new Pager<SpOrderInfo>(size, list);
	}

	@Override
	public Pager<SpOrderDetail> getSpOrderInfoDetailList(SpOrderMgCondition command) {
		List<SpOrderDetail> list=spOrderInfoMgMapper.getSpOrderInfoDetailByCondition(command);
		int size = spOrderInfoMgMapper.getSpOrderInfoDetailByConditionCount(command);
		return new Pager<SpOrderDetail>(size, list);
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
	public ModelMsg updateAcStatus(SpOrderMgCondition command) {
		if(command ==null || StringUtils.isEmpty(command.getSpOrderIds())){
			return new ModelMsg(false, "参数错误");
		}else{
			Accounter current = (Accounter)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(current==null){
				return new ModelMsg(false,"请登录后进行操作");
			}
			//修改状态
			command.setAcId(current.getId());
			command.setSpOrderIdArray(command.getSpOrderIds().split(","));
			command.setAcCheckTime(new Date());
			int count = spOrderInfoMgMapper.updateSpOrderInfoBatch(command);
			
			//保存操作记录
			AcActionRecord acActionRecord =new AcActionRecord();
			acActionRecord.setUserId(current.getId());
			acActionRecord.setObjectName("订单审核：");
			acActionRecord.setActionDate(JacksonUtil.objectToJSONString(command));
			acActionRecord.setActionTime(new Date());
			acActionRecord.setActionType(2);
			acActionRecordMapper.insertSelective(acActionRecord);
			
			if (count > 0) {
				return new ModelMsg(true, "修改成功");
			} else {
				return new ModelMsg(false, "修改失败");
			}
		}
	}
	@Override
	public ModelMsg updateSpOrderCol1(String remark , String id , String userId) {
		SpOrderInfo spOrderInfo = spOrderInfoMapper.selectByPrimaryKey(id);
		if(spOrderInfo != null){
			Map<String , String> map = new HashMap<>();
			map.put("remark" , remark);
			map.put("userId" , userId);
			map.put("updateTime" , DateUtil.getDateFormatter());
			spOrderInfo.setCol1(JSONUtil.objectToJSONString(map));
			if(spOrderInfoMapper.updateByPrimaryKeySelective(spOrderInfo) == 0)
				return new ModelMsg(false , "修改失败、请联系管理员");
			return new ModelMsg(true , "修改成功");
		}
		return new ModelMsg(false , "未找到订单信息");
	}

	@Override
	public Pager<SpOrderInfo> getScmsSpOrderInfoList(SpOrderMgCondition command) {
		List<SpOrderInfo> list = spOrderInfoMgMapper.getScmsSpOrderInfoByCondition(command);
		for (SpOrderInfo sp : list){
			if(!StringUtil.stringIsNullOrEmpty(sp.getCol1())){
				Map<String , String> map = JSONUtil.JSONToObject(sp.getCol1() , Map.class);
				sp.setAcRemark(map.get("remark"));
				sp.setCol2(map.get("updateTime"));
			}
		}
		Integer count = spOrderInfoMgMapper.getScmsSpOrderInfoByConditionCount(command);
		return new Pager<SpOrderInfo>( count ,list);
	}
}
