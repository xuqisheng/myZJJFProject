package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.SpVoucherActive;
import com.corner.core.beans.SpVoucherGraded;
import com.corner.kefu.beans.vo.sp.SpVoucherGradedVo;

public interface SpVoucherGradedMgMapper {

	void deleteSpVoucherGradeByActiveId(SpVoucherActive spVoucherActive) throws Exception;

	void batchSave(List<SpVoucherGraded> list) throws Exception;

	/**
	 * 
	* @Title: getListByActiveId 
	* @Description:根据活动id获取该活动的优惠劵梯度
	* @param @param spVoucherActive
	* @param @return
	* @param @throws Exception
	* @return List<SpVoucherGradedVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<SpVoucherGradedVo> getListByActiveId(SpVoucherActive spVoucherActive) throws Exception;

}
