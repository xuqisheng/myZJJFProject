package com.corner.kefu.service.sp;

import org.springframework.stereotype.Service;

import com.corner.core.beans.SpComment;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.sp.SpCommentParamterRo;
import com.corner.kefu.beans.vo.sp.SpCommentVo;

@Service
public interface SpCommentService {
	/**
	 * 查询所有评论
	 * @author longwu at  2015年11月27日下午4:53:45
	 * @email tiezhongtang@izjjf.cn
	 * @param List<SpCommentVo>
	 * @return
	 */
	public Pager<SpCommentVo> getAllCommentByParameter(SpCommentParamterRo spCommentPatam);
	
	/**
	 * 根据id获取评论
	 * @author longwu at  2015年11月27日下午4:53:45
	 * @email tiezhongtang@izjjf.cn
	 * @param List<SpCommentVo>
	 * @return
	 */
	public SpCommentVo getCommentById(String id);
	
	public ModelMsg updateCommentById(SpComment updateParameter);
}
