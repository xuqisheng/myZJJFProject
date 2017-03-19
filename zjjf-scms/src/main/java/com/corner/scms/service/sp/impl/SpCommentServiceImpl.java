package com.corner.scms.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.dao.SpCommentMapper;
import com.corner.scms.beans.ro.SpCommentParamterRo;
import com.corner.scms.beans.vo.SpCommentVo;
import com.corner.scms.beans.vo.SpCommentVo1;
import com.corner.scms.dao.SpCommentMgMapper;
import com.corner.scms.service.sp.SpCommentService;

@Service
public class SpCommentServiceImpl implements SpCommentService{
	@Autowired
	SpCommentMgMapper spCommentMgMapper;
	@Autowired
	SpCommentMapper spCommentMapper;
	
	@Override
	public SpCommentVo1 getSpComment(SpCommentParamterRo paramter){
		SpCommentVo1 spCommentVo1 = new SpCommentVo1();
		List<SpCommentVo> spCommentVoList = spCommentMgMapper.getSpComment(paramter);
		if(spCommentVoList != null && spCommentVoList.size()>0){
			int countNum = 0;//总人数
			double countScore = 0.0;//总分数
			for (SpCommentVo spCommentVo : spCommentVoList) {
				if(spCommentVo.getUnionFen()==1){
					spCommentVo1.setStar1(spCommentVo);
				}else if(spCommentVo.getUnionFen()==2){
					spCommentVo1.setStar2(spCommentVo);
				}else if(spCommentVo.getUnionFen()==3){
					spCommentVo1.setStar3(spCommentVo);
				}else if(spCommentVo.getUnionFen()==4){
					spCommentVo1.setStar4(spCommentVo);
				}else if(spCommentVo.getUnionFen()==5){
					spCommentVo1.setStar5(spCommentVo);
				}
//				switch (spCommentVo.getUnionFen()){
//				case 1:
//					spCommentVo1.setStar1(spCommentVo);
//					break;
//				case 2:
//					spCommentVo1.setStar2(spCommentVo);
//					break;
//				case 3:
//					spCommentVo1.setStar3(spCommentVo);
//					break;
//				case 4:
//					spCommentVo1.setStar4(spCommentVo);
//					break;
//				case 5:
//					spCommentVo1.setStar5(spCommentVo);
//					break;
//				}
				countNum += spCommentVo.getNum();
				countScore += spCommentVo.getUnionFen()*spCommentVo.getNum();
			}
			spCommentVo1.getStarCount().setCountNum(countNum);
			spCommentVo1.getStarCount().setAvgScore(countScore/countNum);
		}
		
		return spCommentVo1;
	}

	@Override
	public SpCommentVo getSumSpComment(SpCommentParamterRo paramter) {
		// TODO Auto-generated method stub
		return spCommentMgMapper.getSumSpComment(paramter);
	}
	
}
