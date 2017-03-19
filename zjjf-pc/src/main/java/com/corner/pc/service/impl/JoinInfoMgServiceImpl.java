package com.corner.pc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.pc.beans.JoinInfo;
import com.corner.pc.beans.ro.JoinCondition;
import com.corner.pc.beans.vo.ModelMsg;
import com.corner.pc.dao.JoinInfoMapper;
import com.corner.pc.service.JoinInfoMgService;

@Service
public class JoinInfoMgServiceImpl extends ABaseServiceImpl implements JoinInfoMgService {

	@Autowired
	JoinInfoMapper joinInfoMapper;

	@Override
	public ModelMsg submitJoinInfo(JoinCondition joinCondition) {
		if (joinCondition == null) {
			return new ModelMsg(false, "修改失败");
		} else {
			JoinInfo joinInfo = new JoinInfo();
			joinInfo.setSupplierName(joinCondition.getName());
			joinInfo.setMobile(joinCondition.getMobile());
			joinInfo.setCol1(joinCondition.getStoreName());// 店铺名
			joinInfo.setCol2(joinCondition.getStoreAdress());//店铺地址
			joinInfo.setCol3(joinCondition.getUserMessage());//留言
			joinInfo.setRegTime(new Date());
			joinInfo.setUpdateTime(new Date());
			if(joinCondition.getType()!=null){
				joinInfo.setType(joinCondition.getType());//用户类型
			}
			//joinInfo.setSupplierAddress(joinCondition.getStoreAdress());// 店铺地址
			//joinInfo.setSupplierMark(joinCondition.getUserMessage());// 用户留言
			int count = joinInfoMapper.insertSelective(joinInfo);
			if (count == 1) {
				return new ModelMsg(true, "修改成功");
			} else {
				return new ModelMsg(false, "修改异常");
			}
		}
	}

	@Override
	public List<JoinInfo> getJoinfoList() throws Exception {
		return joinInfoMapper.getJoinfoList();
	}

}
