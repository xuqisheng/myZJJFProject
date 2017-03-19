package com.corner.kefu.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SpComment;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.SpCommentMapper;
import com.corner.kefu.beans.ro.sp.SpCommentParamterRo;
import com.corner.kefu.beans.vo.sp.SpCommentVo;
import com.corner.kefu.dao.sp.SpCommentMgMapper;
import com.corner.kefu.service.sp.SpCommentService;
import com.corner.kefu.utils.BeanUtil;

@Service
public class SpCommentServiceImpl implements SpCommentService{
	@Autowired
	SpCommentMgMapper spCommentMgMapper;
	@Autowired
	SpCommentMapper spCommentMapper;
	@Override
	public Pager<SpCommentVo> getAllCommentByParameter(SpCommentParamterRo spCommentPatam) {
		List<SpCommentVo> commentList = spCommentMgMapper.getAllCommentByParameter(spCommentPatam);
		int size = spCommentMgMapper.getAllCommentByParameterCount(spCommentPatam);
		return new Pager<SpCommentVo>(size,commentList);
	}
	@Override
	public SpCommentVo getCommentById(String id){
		SpComment spComment =spCommentMapper.selectByPrimaryKey(id);
		return BeanUtil.toObject(SpCommentVo.class, spComment);
	}
	@Override
	public ModelMsg updateCommentById(SpComment updateParameter) {
		
		int result = spCommentMapper.updateByPrimaryKeySelective(updateParameter);
		if(result == 0){
			return new ModelMsg(false , "修改失败");
		}
		return new ModelMsg(true , "修改成功");
	}
	
}
