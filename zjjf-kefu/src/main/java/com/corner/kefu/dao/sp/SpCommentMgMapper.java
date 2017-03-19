package com.corner.kefu.dao.sp;

import java.util.List;

import com.corner.kefu.beans.ro.sp.SpCommentParamterRo;
import com.corner.kefu.beans.vo.sp.SpCommentVo;


/** 
* @ClassName: SpSupplierMgMapper 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月24日 上午10:08:27 
*  
*/

public interface SpCommentMgMapper {
	public List<SpCommentVo> getAllCommentByParameter(SpCommentParamterRo spCommentPatam);
	public int getAllCommentByParameterCount(SpCommentParamterRo spCommentPatam);
}
