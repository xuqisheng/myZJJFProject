package com.corner.scms.service.sp;

import org.springframework.stereotype.Service;

import com.corner.scms.beans.ro.SpCommentParamterRo;
import com.corner.scms.beans.vo.SpCommentVo;
import com.corner.scms.beans.vo.SpCommentVo1;

@Service
public interface SpCommentService {


	public SpCommentVo1 getSpComment(SpCommentParamterRo paramter);
	
	public SpCommentVo getSumSpComment(SpCommentParamterRo paramter);
}
