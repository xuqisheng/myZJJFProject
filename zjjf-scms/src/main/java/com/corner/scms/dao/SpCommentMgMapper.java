package com.corner.scms.dao;

import java.util.List;

import com.corner.scms.beans.ro.SpCommentParamterRo;
import com.corner.scms.beans.vo.SpCommentVo;

/** 
* @ClassName: SpSupplierMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月24日 上午10:08:27 
*  
*/

public interface SpCommentMgMapper {

	public List<SpCommentVo> getSpComment(SpCommentParamterRo paramter);
	
	public SpCommentVo getSumSpComment(SpCommentParamterRo paramter);
	
}
