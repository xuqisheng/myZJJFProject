package com.corner.kefu.dao.sp;

import java.util.List;

import com.corner.kefu.beans.vo.sp.RechargeGradeVo;

/**
 * 
* @ClassName: SpRechargeGradeMgMapper 
* @Description:充值dao
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年7月18日 下午7:29:43 
*
 */
public interface SpRechargeGradeMgMapper {

	/**
	 * 
	* @Title: getRechargeGradeList 
	* @Description:获取充值梯度列表
	* @param @return
	* @return List<RechargeGradeVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<RechargeGradeVo> getRechargeGradeList();

}
