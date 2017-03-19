package com.corner.pc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.pc.beans.Recruit;
import com.corner.pc.beans.ro.RecruitCondition;
import com.corner.pc.beans.vo.ModelMsg;
import com.corner.pc.beans.vo.Pager;
import com.corner.pc.beans.vo.RecruitVo;
import com.corner.pc.dao.RecruitMapper;
import com.corner.pc.dao.RecruitMgMapper;
import com.corner.pc.service.RecruitMgService;

@Service
public class RecruitMgServiceImpl extends ABaseServiceImpl implements RecruitMgService {

	@Autowired
	RecruitMgMapper recruitMgMapper;

	@Autowired
	RecruitMapper recruitMapper;

	@Override
	public ModelMsg updateByPrimaryKeySelective(Recruit recruit) {
		int count = recruitMapper.updateByPrimaryKeySelective(recruit);
		if (count == 1) {
			return new ModelMsg(true, "修改成功");
		} else {
			return new ModelMsg(false, "修改失败");
		}
	}

	@Override
	public Pager<RecruitVo> getRecruitPageList(RecruitCondition command) {
		List<RecruitVo> list = recruitMgMapper.getPageList(command);
		int size = recruitMgMapper.getPageListSize(command);
		return new Pager<RecruitVo>(size, list);
	}

	@Override
	public ModelMsg addObject(Recruit recruit) {
		int count = recruitMapper.insertSelective(recruit);
		if (count == 1) {
			return new ModelMsg(true, "新增成功");
		} else {
			return new ModelMsg(false, "新增异常");
		}
	}

}
